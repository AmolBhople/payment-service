package ab.techstack.spring.paymentservice.controller;

import ab.techstack.spring.paymentservice.entity.Payment;
import ab.techstack.spring.paymentservice.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        logger.info("Payment Controller doPaymant orderID :"+payment.getOrderId());
        return paymentService.doPayment(payment);
    }


    @GetMapping("/orderId/{orderId}")
    public Payment getByOrderId(@PathVariable int orderId) throws JsonProcessingException {

        return paymentService.getByOrderId(orderId);
    }

}
