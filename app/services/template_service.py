import os
import shutil
import zipfile
from datetime import datetime

TEMPLATE_BASE = "templates/spring_boot_base"
OUTPUT_DIR = "generated"

def generate_project_zip(request):

    project_folder = f"{OUTPUT_DIR}/{request.project_name}_{datetime.now().timestamp()}"
    shutil.copytree(TEMPLATE_BASE, project_folder)

    zip_path = f"{project_folder}.zip"

    with zipfile.ZipFile(zip_path, 'w', zipfile.ZIP_DEFLATED) as zipf:
        for root, dirs, files in os.walk(project_folder):
            for file in files:
                full_path = os.path.join(root, file)
                zipf.write(full_path)

    return zip_path
