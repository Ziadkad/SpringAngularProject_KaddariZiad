package net.springdemo.spring.web;

import net.springdemo.spring.entities.Payment;
import net.springdemo.spring.entities.PaymentStatus;
import net.springdemo.spring.entities.PaymentType;
import net.springdemo.spring.entities.Student;
import net.springdemo.spring.repository.PaymentRepository;
import net.springdemo.spring.repository.StudentRepository;
import net.springdemo.spring.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping("/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }
    @GetMapping("/payments/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code){return paymentRepository.findByStudentCode(code);}
    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){return paymentRepository.findById(id).get();}

    @GetMapping("/students")
    public List<Student> allstudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{code}")
    public Student getStudentBycode(@PathVariable String code){
        return studentRepository.findBycode(code);
    }
    @GetMapping("/studentsByProgramId/{programId}")
    public List<Student> getStudentsByProgramId(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }
    @PutMapping ("/payment/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        return paymentService.updatePaymentStatus(status,id);
    }

    @PostMapping(path="/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam  MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        return this.paymentService.savePayment(file,date,amount,type,studentCode);
    }
    @GetMapping(path="/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {

        return this.paymentService.getPaymentFile(paymentId);
    }

}
