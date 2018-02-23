package com.gmail.v.varvaruk89.repo.tsm;

import com.gmail.v.varvaruk89.entities.tsm.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
