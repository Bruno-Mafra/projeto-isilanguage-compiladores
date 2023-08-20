import os
from fastapi import APIRouter
from pydantic import BaseModel

router = APIRouter()

class InputLanguage(BaseModel):
    code: str

@router.post("/compile/{target_language}")
async def compile_code(
    body: InputLanguage,
    target_language: str, 
):
    code = body.code
    
    with open("input.isi", "w") as f:
        f.write(code)
        
    assert target_language in ["Python", "Java", "C"], "Target language unavailable"
    
    os.system("java -jar compiler.jar "+target_language)
    
    output_file = "MainClass.java" if target_language == "Java" else \
        "main.py" if target_language == "Python" else "main.c"
        
    with open(output_file, "r") as f:
        output_code = f.read()
        
    return {"output_code": output_code}