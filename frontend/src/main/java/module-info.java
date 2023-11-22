module dev.atslega.cpmf {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens dev.atslega.cpmf to javafx.fxml;
    exports dev.atslega.cpmf;
    exports dev.atslega.cpmf.workspace;
    opens dev.atslega.cpmf.workspace to javafx.fxml;
    exports dev.atslega.cpmf.model;
    opens dev.atslega.cpmf.model to javafx.fxml;
    exports dev.atslega.cpmf.panes;
    opens dev.atslega.cpmf.panes to javafx.fxml;

}