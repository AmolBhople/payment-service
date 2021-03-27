package ab.techstack.spring.paymentservice.service;

import ab.techstack.spring.paymentservice.entity.Payment;
import ab.techstack.spring.paymentservice.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    Logger logger = LoggerFactory.getLogger(PaymentService.class);
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        logger.info("PaymentService doPayment request{} ", new ObjectMapper().writeValueAsString(payment));
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }


    public String paymentProcessing(){
        return new Random().nextBoolean()?"Success":"Failed";
    }

    public Payment getByOrderId(int orderId) throws JsonProcessingException {
        Payment byOrderId = paymentRepository.findByOrderId(orderId);
        logger.info("PaymentService getByOrderId paymentObj{} ",  new ObjectMapper().writeValueAsString(byOrderId));
        return byOrderId;
    }
}
