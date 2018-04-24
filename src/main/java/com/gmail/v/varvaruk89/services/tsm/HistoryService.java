package com.gmail.v.varvaruk89.services.tsm;


import com.gmail.v.varvaruk89.entities.sms.Sms;
import com.gmail.v.varvaruk89.entities.tsm.History;
import com.gmail.v.varvaruk89.entities.tsm.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HistoryService {

    void write(Sms sms,User user);
    List<History> getAll();


}
