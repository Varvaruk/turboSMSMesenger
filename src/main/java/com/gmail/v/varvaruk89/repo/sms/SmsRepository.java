package com.gmail.v.varvaruk89.repo.sms;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends CrudRepository<Sms,Long> {

}
