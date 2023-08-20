import requests
import json

if __name__ == "__main__":
    with open("input.isi", "r") as f:
        input_code = f.read()
    
    output_extension = {
        "Java": "java",
        "Python": "py",
        "C": "c"
    }
    
    for target_language in ["Java", "Python", "C"]:
        response = requests.post(
            "http://127.0.0.1:5000/compile/{}".format(
                target_language
            ),
            json = {
                "code": input_code
            }
        )
        
        assert response.status_code == 200, "Error"
        
        output_code = json.loads(response.content)["output_code"]
        
        with open("target_code.{}".format(output_extension[target_language]), "w") as f:
            f.write(output_code)