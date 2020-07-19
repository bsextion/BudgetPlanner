package com.cal.budget.controller;

import com.cal.budget.dao.Bill;
import com.cal.budget.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/bill")
public class BillController {
    private final BillService billService;
    public BillController(@Autowired BillService billService) {
        this.billService = billService;
    }

    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public List<Bill> billsAll() {
        return billService.getBills() ;
    }

    @RequestMapping(value = "/today", produces = "application/json", method = RequestMethod.GET)
    public List<Bill> billdueToday() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formatter.parse(formatter.format(new Date()));
        return billService.getBillsOnDueDate(today);
    }

    @RequestMapping(value = "/upcoming", produces = "application/json", method = RequestMethod.GET)
    public List<Bill> billdueComing() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formatter.parse(formatter.format(new Date()));

        int noOfDays = 14; //i.e two weeks
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date date = calendar.getTime();
        return billService.getBillsSoon(today,date);
    }

//    @RequestMapping(value = "/{billId}", produces = "application/json", method = RequestMethod.GET)
//    public Optional<Bill> billById(@PathVariable("billId") String billId) {
//        return billService.getBillById(billId);
//    }

    @RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
    public Bill createBill(@RequestBody Bill bill) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = formatter.parse(formatter.format(bill.getDueTime()));
        bill.setDueTime(dueDate);
        billService.addBill(bill);
        return bill;
    }

    @RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.PUT)
    public Bill updateBill(@RequestBody Bill bill) {
        billService.addBill(bill);
        return bill;
    }
//    @RequestMapping(value = "/add/list", produces = "application/json", method = RequestMethod.POST)
//    public List<Bill> createBills(@RequestBody List<Bill> bills) {
//        billService.addBills(bills);
//        return bills;
//    }
    @RequestMapping(value = "/remove/{billId}", produces = "application/json", method = RequestMethod.DELETE)
    public void removeBill(@PathVariable("billId") String billId){
        billService.removeBill(billId);
    }

//    @RequestMapping(value = "/amountgreat/{amount}", produces = "application/json", method = RequestMethod.GET)
//    public List<Bill>billsGreaterThan(@PathVariable("amount") int amount) {
//        return billService.findBillByMoreThan(amount);
//    }

//    @RequestMapping(value = "/category/{type}", produces = "application/json", method = RequestMethod.GET)
//    public List<Bill> billsByCategory(@PathVariable("type") String type) {
//        return billService.findBillsByCategory(type);
//    }
}
