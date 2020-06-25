package com.cal.budget.repo;

import com.cal.budget.dao.Bill;
import com.cal.budget.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

    public interface BillRepo extends MongoRepository<Bill, String> {

    }