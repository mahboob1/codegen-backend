from fastapi import FastAPI
from app.routes import generate

app = FastAPI(title="Spring Boot Generator")

app.include_router(generate.router)

@app.get("/")
def root():
    return {"status": "running"}
