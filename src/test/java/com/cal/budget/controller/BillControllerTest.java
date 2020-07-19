package com.cal.budget.controller;

import com.cal.budget.dao.Bill;
import com.cal.budget.service.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(BillController.class)
public class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BillService billService = new BillService();

    @InjectMocks
    private BillController billController;

    @Autowired
    private ObjectMapper objectMapper  = new ObjectMapper();

    private List<Bill> billList =  new ArrayList<>();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(billController).build();
    }

    @Test
    public void shouldFetchAllBills() throws Exception {
        Date today = new Date();
        billList.add(new Bill("Netflix", new Date(), 12.99,"Entertainment"));
        billList.add(new Bill("Hulu", new Date(), 9.99,"Entertainment"));
        given(billService.getBills()).willReturn(billList);

        this.mockMvc.perform(get("/bill/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(billList.size())));
    }

    @Test
    public void shouldFetchBillsToday() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formatter.parse(formatter.format(new Date()));
        billList.add(new Bill("Netflix", today, 12.99,"Entertainment"));

        given(billService.getBillsOnDueDate(today)).willReturn(billList);

        this.mockMvc.perform(get("/bill/today"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(billList.size())));
    }

    @Test
    public void shouldFetchBillsSoon() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formatter.parse(formatter.format(new Date()));

        int noOfDays = 14; //i.e two weeks
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date week = calendar.getTime();

        billList.add(new Bill("Hulu", week, 5.99,"Entertainment"));
        given(billService.getBillsSoon(today,week)).willReturn(billList);
        this.mockMvc.perform(get("/bill/upcoming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(billList.size())));
    }

    @Test
    public void shouldremoveBill() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = formatter.parse(formatter.format(new Date()));
        Bill bill = new Bill("Netflix", today, 12.99,"Entertainment");
        bill.setId("1234");
        Bill bill2 = new Bill("Hulu", new Date(), 9.99,"Entertainment");
        bill2.setId("4567");

        this.mockMvc.perform(delete("/bill/remove/{billId}", bill.getId()))
                .andExpect(status().isOk());


    }

    @Test
    public void shouldUpdateBill() throws Exception {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String id = "45621";
//        Bill bill = new Bill("Netflix", new Date(), 12.99,"Entertainment");
//        bill.setId(id);
//
//        given(billService.addBill(bill)).t;
//
//        this.mockMvc.perform(put("/bill/update", bill.getId())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(objectMapper.writeValueAsString(bill)))
//                .andExpect(status().isOk());

    }

    @Test
    public void shouldcreateBill() throws Exception {
        given(billService.addBill(any(Bill.class))).willAnswer((invocation -> invocation.getArgument(0)));
        Bill bill = new Bill("Netflix", new Date(), 12.99,"Entertainment");

        this.mockMvc.perform(post("/bill/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bill)))
                .andExpect(status().isOk());

    }
}