FROM python:3.11-slim

WORKDIR /app

# requirements.txt 복사
COPY requirements.txt .

# 패키지 설치
RUN pip install --no-cache-dir -r requirements.txt

# 소스 코드 복사
COPY . .

# FastAPI 실행
CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
