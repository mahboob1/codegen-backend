from fastapi import APIRouter
from app.models.project_request import ProjectRequest
from app.services.template_service import generate_project_zip

router = APIRouter(prefix="/generate", tags=["generate"])

@router.post("/project")
def generate_project(req: ProjectRequest):
    zip_path = generate_project_zip(req)
    return {"zip_path": zip_path}
