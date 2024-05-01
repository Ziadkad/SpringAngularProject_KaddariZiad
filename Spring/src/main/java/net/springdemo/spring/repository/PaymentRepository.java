package net.springdemo.spring.repository;

import net.springdemo.spring.entities.Payment;
import net.springdemo.spring.entities.PaymentStatus;
import net.springdemo.spring.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentCode(String Code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);


}
