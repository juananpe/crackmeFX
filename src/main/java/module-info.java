module eus.ehu.crackmefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires log4j.api;

    opens eus.ehu.crackmefx to javafx.fxml;
    opens eus.ehu.crackmefx.controllers to javafx.fxml;

    exports eus.ehu.crackmefx;
    exports eus.ehu.crackmefx.controllers;
}
