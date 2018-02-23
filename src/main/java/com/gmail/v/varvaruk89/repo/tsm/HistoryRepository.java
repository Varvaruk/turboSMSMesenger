package com.gmail.v.varvaruk89.repo.tsm;

import com.gmail.v.varvaruk89.entities.tsm.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

}
