module com.abraham.ts {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.abraham.ts.controllers to javafx.fxml;
    opens com.abraham.ts to javafx.fxml;
    exports com.abraham.ts;
}