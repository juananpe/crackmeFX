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

    private void setStyle(String style) {
        serial.getStyleClass().removeIf(s -> s.equals("error") || s.equals("ok"));
        serial.getStyleClass().add(style);
    }

    @FXML
    void onClick(ActionEvent event) {
        if (check(serial.getText())) {
            setStyle("ok");
        } else {
            setStyle("error");
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
        String slice = serial.split("-")[1];

        return slice.length() == 7 && slice.indexOf('0') > 0 &&
                slice.chars().map(c -> c - '0').sum() == 54 &&
                slice.chars().filter(c -> c == 'F').count() == 2 &&
                slice.chars().filter(c -> c == '3').count() == 2 ;
    }

    private boolean rule4(String serial) {
        char[] first = new char[]{'2', 'E', '1', 'A', '0', 'A', '0', 'U', '3', 'R', '0'};
        char[] second = new char[]{'z', 'v', 'i', 'u', 't', 'r', 's', 'd', '~', 'f', '|'};
        for (int i = 0; i < first.length; i++) {
            if ( (first[i] ^ serial.charAt(i)) != second[i])
                return false;
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
        return (c >= '0' && c <= '9') ||
                (c >= 'A' && c <= 'Z') || c == '-' || c == '!';
    }

    private boolean rule2(String serial) {
        return serial.charAt(11) == '-' && serial.charAt(18) == '!';
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
