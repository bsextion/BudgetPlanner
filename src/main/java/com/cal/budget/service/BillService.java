package com.cal.budget.service;

import com.cal.budget.dao.Bill;
import com.cal.budget.repo.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private BillRepo billRepo;

    @Autowired
    public void setUserRepo(BillRepo billRepo){
        this.billRepo = billRepo;
    }

    public void addBill(Bill bill){
        billRepo.save(bill);
    }
    public void addBills(List<Bill> bills) {billRepo.saveAll(bills);}
    public void removeBill(String id) { billRepo.deleteById(id);}
    public Optional<Bill> getBillById(String billId) {
        return billRepo.findById(billId);
    }
    public List<Bill> getBills() {
        return billRepo.findAll();
    }
    public List<Bill> getBillsByDueDate(String dueDate){
        return billRepo.findBillsByDueTime(dueDate);
    }
    public List<Bill> findBillByLessThan(int amount) {
        return billRepo.findBillsByAmountLessThan(amount);
    }
    public List<Bill> findBillByMoreThan(int amount) {
        return billRepo.findBillsByAmountGreaterThan(amount);
    }
    public List<Bill> findBillsByCategory(String category) {
        return billRepo.findAllByCategoryEquals(category);
    }
}
