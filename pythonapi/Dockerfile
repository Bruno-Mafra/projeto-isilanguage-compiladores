FROM python:bookworm

ENV PROJECT_ROOT /app

USER root

RUN mkdir ${PROJECT_ROOT}

WORKDIR ${PROJECT_ROOT}

ADD . .

RUN apt-get update
RUN apt-get install -y default-jdk default-jre

RUN pip install --upgrade pip wheel uvicorn \
    && pip install -r requirements.txt

EXPOSE 5000

CMD ["uvicorn", "run_api:app", "--host", "0.0.0.0", "--port", "5000"]

