module furriel.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens furriel.biblioteca to javafx.fxml;
    exports furriel.biblioteca;
    exports furriel.biblioteca.gui.controllers;
    opens furriel.biblioteca.gui.controllers to javafx.fxml;
}