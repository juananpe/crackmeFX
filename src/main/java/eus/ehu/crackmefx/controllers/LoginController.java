package eus.ehu.crackmefx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;


public class LoginController {

    private Logger logger;

    @FXML
    private TextField serial;


    @FXML
    void onClick(ActionEvent event) {
        if (check(serial.getText())) {
            serial.getStyleClass().removeIf(style -> style.equals("error"));
            serial.getStyleClass().add("ok");
        } else {
            serial.getStyleClass().removeIf(style -> style.equals("error"));
            serial.getStyleClass().add("error");
        }
    }

    private boolean check(String serial) {
        return
                rule1(serial) &&
                        rule2(serial) &&
                        rule3(serial) &&
                        rule4(serial) &&
                        rule5(serial);
    }

    private boolean rule5(String serial) {
        for (String slice : serial.split("-"))
            if (slice.chars().distinct().count() != slice.length())
                return false;
        return true;
    }

    private boolean rule4(String serial) {
        for (int i = 0; i < serial.length(); i++) {
            if (serial.charAt(i) != serial.charAt(serial.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private boolean rule3(String serial) {
        for (int i = 0; i < serial.length(); i++) {
            if (!valid(serial.charAt(i)))
                return false;
        }
        return true;
    }

    private boolean valid(char c) {
        return (c > '0' && c <= '9') ||
                (c >= 'A' && c <= 'Z') || c == '-';
    }

    private boolean rule2(String serial) {
        AtomicBoolean res = new AtomicBoolean(true);
        List.of(4, 9, 14).forEach(i -> {
            if (serial.charAt(i) != '-')
                res.set(false);
        });
        return res.get();
    }

    private boolean rule1(String serial) {
        return serial.length() == 19;
    }

    @FXML
    void initialize() {

        logger = LogManager.getLogger(LoginController.class);
        Configurator.setLevel(logger.getName(), Level.ALL);

        logger.debug("Starting the crackme...");
        logger.info("Info log message");
        logger.error("Error log message");
        logger.warn("Warn log message");
    }

}
