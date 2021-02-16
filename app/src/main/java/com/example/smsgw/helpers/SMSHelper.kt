package com.example.smsgw.helpers

import android.telephony.SmsManager

class SMSHelper {
    fun sendSms(phone: String?, content: String?) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phone, null, content, null, null)
    }
}