package com.gmail.v.varvaruk89.repo.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

}
