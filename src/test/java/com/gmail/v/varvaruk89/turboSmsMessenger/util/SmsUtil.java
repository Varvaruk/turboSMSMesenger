package com.gmail.v.varvaruk89.turboSmsMessenger.util;

import com.gmail.v.varvaruk89.entities.sms.Sms;

public class SmsUtil {

    public  static Sms createSms(){
        Sms sms = new Sms("380936094346","VADUM","HELLO!" );
       return sms;
    }
}
