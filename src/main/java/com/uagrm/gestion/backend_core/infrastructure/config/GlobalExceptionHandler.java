package com.uagrm.gestion.backend_core.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Files.write(Paths.get("error.log"), sw.toString().getBytes());
        } catch (Exception ex) {
            // ignore
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " - " + e.getClass().getName());
    }
}
