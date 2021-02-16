package com.example.smsgw.services

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import com.example.smsgw.helpers.SMSHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {
    private val TAG = javaClass.name

    override fun onNewToken(newToken: String) {
        Log.i(TAG, "New token received: $newToken")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        if (remoteMessage.data.isNotEmpty()) {
            val phone = remoteMessage.data["phone"]
            val content = remoteMessage.data["content"]
            Log.i(TAG, "Data received: $phone | $content")

            // send sms
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val currentTime = Calendar.getInstance().time
                Log.i(TAG, "Perform send SMS at: $currentTime")
            }
            val smsHelper = SMSHelper()
            smsHelper.sendSms(phone, content)
        }
    }
}