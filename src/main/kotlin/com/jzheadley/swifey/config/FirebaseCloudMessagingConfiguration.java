package com.jzheadley.swifey.config;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseCloudMessagingConfiguration {

    @Bean
    public IFcmClientSettings buildFcmSettings() {
        return new IFcmClientSettings() {
            @Override
            public String getFcmUrl() {
                return "https://fcm.googleapis.com/fcm/send";
            }

            @Override
            public String getApiKey() {
                return "AAAApEYxFvc:APA91bGTnxOIlaXFvkQEmLMOP-YRZfOJtfKVu9AK9Bq7GlsCjv-GTBX9rtswXaOsQys0xUBUAbkkKALPXghTqkGgequ9leCQWOXIzXXpIEC3kY5aajS1cplafBl2cn34rH7osYKLC7bu";
            }
        };
    }

    @Bean
    public FcmClient buildFcmClient(IFcmClientSettings fcmClientSettings) {
        return new FcmClient(fcmClientSettings);
    }
}
