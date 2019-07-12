FROM python:3

Maintainer Abdugofir Sharif

ADD ./ /app

WORKDIR /app

RUN pip install ndg-httpsclient==0.5.1
RUN pip install python-socketio==3.0.1
RUN pip install websocket-client==0.54.0

EXPOSE 8081

CMD [ "python", "/app/sample.py" ]
