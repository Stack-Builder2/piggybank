from fastapi import FastAPI
from pydantic import BaseModel
from typing import List, Optional, Dict, Any
import pandas as pd
from pathlib import Path

FILE_PATH = Path(r'C:\\BAE\\python\rules.xlsx')

app = FastAPI(title="Rule-based Spend Analyzer", version="1.0")

# --- 규칙 로더 ---
def load_rules(fp: Path):
    data = pd.read_excel(fp, engine='openpyxl', sheet_name="rule")
    cat = pd.read_excel(fp, engine='openpyxl', sheet_name="category")

    rule_dic = {i: cat.split('|') for i, cat in enumerate(cat['category'])}
    return data, rule_dic

data_df, rule_dic = load_rules(FILE_PATH)

def apply_rules(text: str) -> str:
    for k, keywords in rule_dic.items():
        if all(word in text for word in keywords):
            return str(data_df.loc[k, "response"])
    return "기타"

# --- 모델 ---
class Txn(BaseModel):
    id: Optional[str] = None
    description: str

class BatchRequest(BaseModel):
    items: List[Txn]

class BatchResponseItem(BaseModel):
    id: Optional[str] = None
    description: str
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
        out.append(BatchResponseItem(id=item.id, description=item.description, category=category))
    return BatchResponse(items=out)

@app.post("/_admin/reload")
def reload_rules():
    global data_df, rule_dic
    data_df, rule_dic = load_rules(FILE_PATH)
    return {"status": "reloaded", "rules": len(rule_dic)}