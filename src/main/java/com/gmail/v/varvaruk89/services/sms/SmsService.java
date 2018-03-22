package com.gmail.v.varvaruk89.services.sms;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import org.springframework.stereotype.Service;

@Service
public interface SmsService {
    void save(Sms sms);
}
