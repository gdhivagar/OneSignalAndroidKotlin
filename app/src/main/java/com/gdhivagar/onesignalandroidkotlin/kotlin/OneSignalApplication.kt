package com.gdhivagar.onesignalandroidkotlin.kotlin

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "########-####-####-####-############"

class OneSignalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.setNotificationWillShowInForegroundHandler {
            //val data: JSONObject = it.notification.getAdditionalData()
            run {
                // Get custom additional data you sent with the notification
                //val data: JSONObject = it.notification.getAdditionalData()
                if (it.notification != null) {
                    //Complete with a notification means it will show
                    it.complete(it.notification)
                } else {
                    //Complete with null means don't show a notification.
                    it.complete(null)
                }
            }
        }
        // Enable this line for Custom Notification Opened Event
        //OneSignal.setNotificationOpenedHandler()
        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        //OneSignal.promptForPushNotifications()

    }

}