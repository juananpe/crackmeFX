module eus.ehu.crackmefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;

    opens eus.ehu.crackmefx to javafx.fxml;
    opens eus.ehu.crackmefx.controllers to javafx.fxml;

    exports eus.ehu.crackmefx;
    exports eus.ehu.crackmefx.controllers;
}
