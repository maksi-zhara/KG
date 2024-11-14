module com.example.task3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.vsu.cs.task3.Application to javafx.fxml;
    exports ru.vsu.cs.task3.Application;
}