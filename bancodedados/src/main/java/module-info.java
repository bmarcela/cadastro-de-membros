module com.projeto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens com.projeto to javafx.fxml;
    exports com.projeto;

    opens com.projeto.modelagemDeDados to javafx.base;
}
