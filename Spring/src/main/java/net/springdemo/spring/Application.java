package net.springdemo.spring;

import net.springdemo.spring.entities.Payment;
import net.springdemo.spring.entities.PaymentStatus;
import net.springdemo.spring.entities.PaymentType;
import net.springdemo.spring.entities.Student;
import net.springdemo.spring.repository.PaymentRepository;
import net.springdemo.spring.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository){
		return  args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("hamid")
					.lastName("hamid lastName")
					.code("9999")
					.programId("programID")
					.build());

		studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
				.firstName("fn2")
				.lastName("ln2")
				.code("code2")
				.programId("pId2")
				.build());

		studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
				.firstName("fn3")
				.lastName("ln3")
				.code("code3")
				.programId("pId3")
				.build());

		studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
				.firstName("fn4")
				.lastName("ln4")
				.code("code4")
				.programId("pPayTypes.value(;Random random = new Random(;Id4")
				.build());

		PaymentType[] paymentTypes = PaymentType.values();
		Random random = new Random();
		studentRepository.findAll().forEach(st->{
			for (int i = 0; i <10; i++) {
				int index = random.nextInt(paymentTypes.length);
				Payment payment = Payment.builder()
				.amount(1000+(int) (Math.random()*20000))
				.type (paymentTypes [index])
				.status(PaymentStatus.CREATED)
				.date (LocalDate.now())
				.student(st)
				.build();
				paymentRepository.save(payment);
				}
			});
		};


	}



}
