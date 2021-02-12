package com.korbiak.api.messageSenders;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsSender {
    @Value("${twilioValue.sid}")
    public String accountSid;

    @Value("${twilioValue.token}")
    public String authToken;

    @Value("${twilioValue.number}")
    public String number;

    public void sendSms(String phone, String messageStr) {
        Twilio.init(accountSid, authToken);

        Message.creator(new PhoneNumber(phone), // to
                        new PhoneNumber(number), // from
                        messageStr)
                .create();
    }
}
