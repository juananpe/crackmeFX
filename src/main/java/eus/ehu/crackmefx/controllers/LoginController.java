package eus.ehu.crackmefx.controllers;

import eus.ehu.crackmefx.Crackme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginController {

        @FXML
        private TextField serial;


        private Crackme main;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        void onClick(ActionEvent event) {
                if(check(serial.getText())){
                        serial.getStyleClass().removeIf(style -> style.equals("error"));
                        serial.getStyleClass().add("ok");
                }else{
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
                for(String slice : serial.split("-"))
                        if (slice.chars().distinct().count() != slice.length())
                                return false;
                return true;
        }

        private boolean rule4(String serial) {
                for(int i=0; i<serial.length(); i++) {
                        if (serial.charAt(i) != serial.charAt(serial.length()-1-i)){
                                return false;
                        }
                }
                return true;
        }

        private boolean rule3(String serial) {
                for(int i=0; i<serial.length(); i++){
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
                List.of(4,9,14).forEach( i -> {
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


        }

        public void setMain(Crackme crackme) {
                this.main = crackme;
        }
}
