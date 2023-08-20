import uvicorn
import argparse
import compiler

from fastapi import FastAPI

app = FastAPI()
app.include_router(compiler.router)

if __name__ == "__main__":
    argparser = argparse.ArgumentParser()
    
    argparser.add_argument(
        "--r",
        action = "store_true",
        dest = "reload"
    )
    argparser.add_argument(
        "--p",
        action = "store",
        dest = "port",
        type = int,
        default = 5000
    )
    argparser.add_argument(
        "--h",
        "-host",
        action = "store",
        dest = "host",
        type = str,
        default = "127.0.0.1"
    )
    argparser.add_argument(
        "--w",
        "-workers",
        action = "store",
        dest = "workers",
        type = int,
        default = 1
    )
    
    arguments = argparser.parse_args()
        
    uvicorn.run(
        app = app,
        host = arguments.host,
        port = arguments.port,
        reload = arguments.reload,
        workers = arguments.workers
    )