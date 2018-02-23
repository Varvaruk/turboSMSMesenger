package com.gmail.v.varvaruk89.services.sms;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import com.gmail.v.varvaruk89.repo.sms.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsRepository smsRepository;

    @Override
    public void addSms (Sms sms){
        smsRepository.save(sms);
    }
}
