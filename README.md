# smsgw-android
Android SMS Gateway

## About
SMS Gateway for Android, integrated with FCM (Firebase Cloud Messaging)

## Features
- Blazing fast message sender
- Easy to use

## Requirements
- Android Studio
- FCM
- Python >= 3.7
- Python modules: Flask, Requests

## Setup webserver
- run: python api.py
- Server will listening on port: 9000

## Sender device
- Compile and install app from Android Studio to Android device
- Make sure the app integrated with Google services
- If no mistakes, Android will listen to FCM sockets and send the messages

## How to use API
- Request method: POST
- Content type: JSON
- Example Data:
```json
{
  "phone": "TARGET_PHONE_NUMBER_HERE",
  "content": "THIS_IS_CONTENT"
}
```
- Example success response:
```json
{
  "status": "OK",
  "msg": "SMS sent to: TARGET_PHONE_NUMBER_HERE"
}
```
