package net.springdemo.spring.services;

import jakarta.transaction.Transactional;
import net.springdemo.spring.entities.Payment;
import net.springdemo.spring.entities.PaymentStatus;
import net.springdemo.spring.entities.PaymentType;
import net.springdemo.spring.entities.Student;
import net.springdemo.spring.repository.PaymentRepository;
import net.springdemo.spring.repository.StudentRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
@Transactional
public Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
    Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");
    if (!Files.exists(folderPath)) {
        Files.createDirectories(folderPath);
    }
    String fileName = UUID.randomUUID().toString();
    Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments", fileName + ".pdf");
    Files.copy(file.getInputStream(),filePath);
    Student student = studentRepository.findBycode(studentCode);
    Payment payment = Payment.builder()
            .date(date).type(type).student(student)
            .amount(amount)
            .file(filePath.toUri().toString())
            .status(PaymentStatus.CREATED)
            .build();
    return  paymentRepository.save(payment);
}
public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));}






    public Payment updatePaymentStatus( PaymentStatus status, Long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }



















}
