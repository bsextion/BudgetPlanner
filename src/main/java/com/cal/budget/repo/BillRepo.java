package com.cal.budget.repo;

import com.cal.budget.dao.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
    @Repository
    public interface BillRepo extends MongoRepository<Bill, String> {

        List<Bill> findBillsByAmountGreaterThan(int amount);
        List<Bill> findBillsByAmountLessThan(int amount);
        List<Bill> findAllByCategoryEquals(String category);
        List<Bill> findAllByDueTime(Date dueDate);
        List<Bill> findAllByDueTimeBetween(Date today, Date week);

    }