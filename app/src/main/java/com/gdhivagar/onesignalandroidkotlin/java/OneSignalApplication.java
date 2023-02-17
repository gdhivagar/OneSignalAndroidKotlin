package com.gdhivagar.onesignalandroidkotlin.java;

import android.app.Application;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class OneSignalApplication extends Application {
    private static final String ONESIGNAL_APP_ID = "########-####-####-####-############";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.setNotificationWillShowInForegroundHandler(new OneSignal.OSNotificationWillShowInForegroundHandler() {
            @Override
            public void notificationWillShowInForeground(OSNotificationReceivedEvent osNotificationReceivedEvent) {
                OSNotification notification = osNotificationReceivedEvent.getNotification();
                // Get custom additional data you sent with the notification
                JSONObject data = notification.getAdditionalData();
                if (data != null) {
                    // Complete with a notification means it will show
                    osNotificationReceivedEvent.complete(notification);
                } else {
                    // Complete with null means don't show a notification
                    osNotificationReceivedEvent.complete(null);
                }
            }
        });
        // Enable this line for Custom Notification Opened Event
        /* OneSignal.setNotificationOpenedHandler(new OneSignal.OSNotificationOpenedHandler() {
            @Override
            public void notificationOpened(OSNotificationOpenedResult result) {
                String actionId = result.getAction().getActionId();
                String type = result.getAction().getType(); // "ActionTaken" | "Opened"

                String title = result.getNotification().getTitle();
            }
        });*/
        OneSignal.unsubscribeWhenNotificationsAreDisabled(true);
        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        //OneSignal.promptForPushNotifications();


    }
}
