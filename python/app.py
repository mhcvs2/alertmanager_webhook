import json
from flask import Flask, request

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route("/send", methods=['POST'])
def send():
    data = json.loads(request.data)
    print "receive data------------------------------------------------------------"
    print json.dumps(data, indent=4)
    return 'ok'


if __name__ == '__main__':
    app.run()
