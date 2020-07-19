package com.cal.budget.dao;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

import java.util.Date;

public class BillTest {

    @Test
    public void testBill() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new Bill("Netflix", new Date(), 12.99, "Entertainment"));
    }
}