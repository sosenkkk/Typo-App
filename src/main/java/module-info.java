module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.typo to javafx.fxml;
    exports com.example.typo;
}