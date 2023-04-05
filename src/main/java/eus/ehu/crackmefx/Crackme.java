package eus.ehu.crackmefx;

import eus.ehu.crackmefx.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Crackme extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Crackme");

        // https://stackoverflow.com/a/30883817/243532
        scene.getStylesheets().add(
                getClass().getClassLoader().getResource("error.css").toString());

        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}
