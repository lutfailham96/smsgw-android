from flask import Flask, request
import requests
import json

app = Flask(__name__)
server_key = 'FCM_SERVER_KEY_HERE'
device_token = 'DEVICE_TOKEN_HERE'


def send_fcm(phone, content):
    global server_key
    global device_token
    url = 'https://fcm.googleapis.com/fcm/send'
    headers = {
        'Content-Type':
        'application/json',
        'Authorization':
        'key={}'.format(server_key)
    }
    body = {
        'to': device_token,
        'data': {
            'phone': phone,
            'content': content,
        }
    }
    res = requests.post(url, headers=headers, data=json.dumps(body))
    return res.status_code == 200


@app.route('/', methods=['POST'])
def main():
    errors = []
    json = request.get_json()
    try:
        phone = json['phone']
    except:
        error = {'phone': 'Missing phone parameter'}
        errors.append(error)
    try:
        content = json['content']
    except:
        error = {'content': 'Missing content parameter'}
        errors.append(error)
    if errors:
        return {
            'status': 'ERROR',
            'errors': errors,
        }, 422
    if send_fcm(phone, content):
        return {
            'status': 'OK',
            'msg': 'SMS sent to: {}'.format(phone),
        }
    return {
        'status': 'ERROR',
        'msg': 'Internal server error',
    }, 500


if __name__ == '__main__':
    app.run(port=9000)
