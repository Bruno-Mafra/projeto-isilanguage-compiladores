import requests
import json

if __name__ == "__main__":
    with open("input.isi", "r") as f:
        input_code = f.read()
        
    response = requests.post(
        "http://127.0.0.1:5000/compile/{}".format(
            "Java"
        ),
        json = {
            "code": input_code
        }
    )
    
    assert response.status_code == 200, "Error"
    
    output_code = json.loads(response.content)["output_code"]
    
    print(output_code)