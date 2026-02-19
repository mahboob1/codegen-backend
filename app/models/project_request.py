from pydantic import BaseModel
from typing import List

class ProjectRequest(BaseModel):
    project_name: str
    build_tool: str
    dependencies: List[str]
