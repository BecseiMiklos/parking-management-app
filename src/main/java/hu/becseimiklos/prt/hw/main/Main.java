package hu.becseimiklos.prt.hw.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("hu.becseimiklos.prt.hw")
@EnableJpaRepositories("hu.becseimiklos.prt.hw.repository")
@EntityScan("hu.becseimiklos.prt.hw.entity")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
