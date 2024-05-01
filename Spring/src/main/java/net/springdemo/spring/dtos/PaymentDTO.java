package net.springdemo.spring.dtos;

import jakarta.persistence.*;
import lombok.*;
import net.springdemo.spring.entities.PaymentStatus;
import net.springdemo.spring.entities.PaymentType;
import net.springdemo.spring.entities.Student;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}
