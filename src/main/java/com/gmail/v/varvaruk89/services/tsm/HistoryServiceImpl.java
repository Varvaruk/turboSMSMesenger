package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import com.gmail.v.varvaruk89.entities.tsm.History;
import com.gmail.v.varvaruk89.entities.tsm.User;
import com.gmail.v.varvaruk89.repo.tsm.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void write(Sms sms, User user) {
        History history = new History();
        history.setSms(sms.getId().toString());
        history.setUser(user);
        history.setDate(sms.getAdded());
        historyRepository.save(history);

    }

    @Override
    public List<History> getAll() {
        return (List<History>) historyRepository.findAll();
    }
}
