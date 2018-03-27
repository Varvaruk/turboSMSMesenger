package com.gmail.v.varvaruk89.services.sms;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SmsService {
    void save(Sms sms);
    Sms getOne (String id);
    List<Sms> getAllSms();
}
