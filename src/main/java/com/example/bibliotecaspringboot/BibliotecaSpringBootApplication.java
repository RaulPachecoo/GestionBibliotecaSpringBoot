package com.example.bibliotecaspringboot;
import com.example.bibliotecaspringboot.models.helper.LogFile;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class BibliotecaSpringBootApplication {

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaSpringBootApplication.class, args);
    }

    // Este método se ejecutará después de que Spring Boot haya iniciado la aplicación
    @PostConstruct
    public void logConnectionInfo() {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            String message = "Usuario Conectado: " + databaseUsername + " (" + formattedDateTime + ")";
            LogFile.saveLOG(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


