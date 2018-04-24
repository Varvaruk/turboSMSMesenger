package com.gmail.v.varvaruk89.services.sms;

import com.gmail.v.varvaruk89.entities.sms.Sms;

import java.util.List;


public interface SmsService {
    void save(Sms sms);
    Sms getOne (String id);
    List<Sms> getAllSms();
}
