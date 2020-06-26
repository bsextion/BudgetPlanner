package com.cal.budget.controller;

import com.cal.budget.dao.Bill;
import com.cal.budget.dao.User;
import com.cal.budget.service.BillService;
import com.cal.budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/bill/")
public class BillController {
    private final BillService billService;
    public BillController(@Autowired BillService billService) {
        this.billService = billService;
    }

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public List<Bill> billsAll() {
        return billService.getBills() ;
    }

    @RequestMapping(value = "/{billId}", produces = "application/json", method = RequestMethod.GET)
    public Optional<Bill> billById(@PathVariable("billId") String billId) {
        return billService.getBillById(billId);
    }

    @RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
    public Bill createBill(@RequestBody Bill bill) {
        billService.addBill(bill);
        return bill;
    }
    @RequestMapping(value = "/add/list", produces = "application/json", method = RequestMethod.POST)
    public List<Bill> createBills(@RequestBody List<Bill> bills) {
        billService.addBills(bills);
        return bills;
    }
    @RequestMapping(value = "/remove/{billId}", produces = "application/json", method = RequestMethod.DELETE)
    public void removeBill(@PathVariable("billId") String billId){
        billService.removeBill(billId);
    }

    @RequestMapping(value = "/amountgreat/{amount}", produces = "application/json", method = RequestMethod.GET)
    public List<Bill>billsGreaterThan(@PathVariable("amount") int amount) {
        return billService.findBillByMoreThan(amount);
    }

    @RequestMapping(value = "/amountless/{amount}", produces = "application/json", method = RequestMethod.GET)
    public List<Bill> billsLessThan(@PathVariable("amount") int amount) {
        return billService.findBillByLessThan(amount);
    }

    @RequestMapping(value = "/category/{type}", produces = "application/json", method = RequestMethod.GET)
    public List<Bill> billsByCategory(@PathVariable("type") String type) {
        return billService.findBillsByCategory(type);
    }
}
