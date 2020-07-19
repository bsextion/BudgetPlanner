package com.cal.budget.service;

import com.cal.budget.dao.Bill;
import com.cal.budget.repo.BillRepo;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {
    @Mock
    private BillRepo billRepo;

    @InjectMocks
    private BillService billService = new BillService();

    @Test
    public void shouldSaveBill() {
        final Bill bill = new Bill("Netflix", new Date(), 12.99, "Entertainment");
        when(billRepo.save(any(Bill.class))).thenReturn(new Bill());
        Bill added = billService.addBill(bill);
        assertThat(added.getName()).isSameAs(bill.getName());
    }

    @Test
    public void shouldremoveBill() {
        final Bill bill = new Bill("Netflix", new Date(), 12.99, "Entertainment");
         assertNotNull(bill);
         billService.removeBill(bill.getId());
         verify(billRepo, times(1)).deleteById(bill.getId());
    }

    @Test
    public void shouldgetBills() {
        final Bill bill = new Bill("Netflix", new Date(), 12.99, "Entertainment");
        final Bill bill2 = new Bill("Hulu", new Date(), 5.99, "Entertainment");
        List<Bill> list = new ArrayList<>(); list.add(bill); list.add(bill2);

        given(billRepo.findAll()).willReturn(list);
        List<Bill> expected = billRepo.findAll();
        assertEquals(expected, list);
    }

    @Test
    public void shouldgetBillsOnDueDate() {
        Date date = new Date();
        date.setTime(1000);
        final Bill bill = new Bill("Netflix", date, 12.99, "Entertainment");
        final Bill bill2 = new Bill("Hulu", new Date(), 5.99, "Entertainment");
        List<Bill> list = new ArrayList<>(); list.add(bill2);

        given(billRepo.findAllByDueTime(date)).willReturn(list);
        List<Bill> expected = billRepo.findAllByDueTime(date);
        assertEquals(expected, list);

    }

    @Test
    public void getBillsSoon() {
        Date today = new Date();
        int noOfDays = 14; //i.e two weeks
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date date = calendar.getTime();

        final Bill bill = new Bill("Netflix", date, 12.99, "Entertainment");
        final Bill bill2 = new Bill("Hulu", new Date(), 5.99, "Entertainment");
        final Bill bill3 = new Bill("Disney Plus", date, 5.99, "Entertainment");
        List<Bill> list = new ArrayList<>(); list.add(bill2);
        given(billRepo.findAllByDueTimeBetween(today, date)).willReturn(list);
        List<Bill> expected = billRepo.findAllByDueTimeBetween(today,date);
        assertEquals(expected, list);

    }
}