package com.gmail.v.varvaruk89.repo.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
