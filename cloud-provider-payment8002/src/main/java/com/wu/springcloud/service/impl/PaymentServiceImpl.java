package com.wu.springcloud.service.impl;

import com.wu.springcloud.dao.PaymentDao;
import com.wu.springcloud.entities.Payment;
import com.wu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return this.paymentDao.create(payment);
    }

    public Payment getPaymentById(Integer id) {
        return this.paymentDao.getPaymentById(id);
    }
}
