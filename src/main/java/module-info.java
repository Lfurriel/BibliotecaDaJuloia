module lfurriel.bancojuloiagui {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui to javafx.fxml;
    exports gui;
    exports gui.controllers;
    opens gui.controllers to javafx.fxml;
}