package eus.ehu.crackmefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;

public class Crackme extends Application {

    private static final Logger logger = LogManager.getLogger(Crackme.class);

    @Override
    public void start(Stage stage) throws IOException {
        Configurator.setLevel(logger.getName(), Level.ERROR);

        logger.debug("Starting the application...");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Crackme");

        // https://stackoverflow.com/a/30883817/243532
        scene.getStylesheets().add(
                getClass().getClassLoader().getResource("error.css").toString());

        stage.setScene(scene);
        stage.show();

        logger.info("Application started successfully.");
    }


    public static void main(String[] args) {
        launch();
    }
}
