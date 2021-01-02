package com.korbiak.api.repo;

import com.korbiak.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findUserByFirstNameAndSecondName(String firstName, String secondName);
}
