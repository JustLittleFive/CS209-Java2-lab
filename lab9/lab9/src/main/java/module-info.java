module practice {
    requires javafx.controls;
    requires javafx.fxml;

    opens practice to javafx.fxml;
    exports practice;
}
