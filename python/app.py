from fastapi import FastAPI
from pydantic import BaseModel
from typing import List, Optional, Dict, Any
import pandas as pd
from pathlib import Path
import os
from fastapi import HTTPException

# 컨테이너 내부 경로 기준
BASE_DIR = Path(__file__).resolve().parent
FILE_PATH = BASE_DIR / "rules.xlsx"
if not FILE_PATH.exists():
    raise HTTPException(status_code=500, detail=f"Rules file not found: {FILE_PATH}")


app = FastAPI(title="Rule-based Spend Analyzer", version="1.0")

# --- 규칙 로더 ---
def load_rules(fp: Path):
    data = pd.read_excel(fp, engine='openpyxl', sheet_name="rule")
    cat = pd.read_excel(fp, engine='openpyxl', sheet_name="category")
    rule_dic = {i: c for i, c in enumerate(cat['category'])}
    return data, rule_dic

data_df, rule_dic = load_rules(FILE_PATH)

def apply_rules(text: str) -> str:
    for k, keywords in data_df["keyword"].items():
        if keywords in text:  # 문자열 하나만 비교
            return str(data_df.loc[k, "category"])
    return "기타"

# --- 모델 ---
class Txn(BaseModel):
    id: Optional[str] = None
    description: str

class BatchRequest(BaseModel):
    items: List[Txn]

class BatchResponseItem(BaseModel):
    id: Optional[str] = None
    category: str

class BatchResponse(BaseModel):
    items: List[BatchResponseItem]

# --- 엔드포인트 ---
@app.get("/health")
def health() -> Dict[str, Any]:
    return {"status": "OK"}

@app.post("/analyze", response_model=BatchResponse)
def analyze(req: BatchRequest):
    out = []
    for item in req.items:
        category = apply_rules(item.description)
        out.append(BatchResponseItem(id=item.id, category=category))
    return BatchResponse(items=out)

@app.post("/_admin/reload")
def reload_rules():
    global data_df, rule_dic
    data_df, rule_dic = load_rules(FILE_PATH)
    return {"status": "reloaded", "rules": len(rule_dic)}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)

