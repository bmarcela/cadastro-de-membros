package com.projeto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage iniciar) throws IOException {
        FXMLLoader arquivo = new FXMLLoader(getClass().getResource("layout.fxml"));
        Parent root = arquivo.load();
        
        Scene scene = new Scene(root);
        iniciar.setTitle("Cadastro de homens.");
        iniciar.setScene(scene);
        iniciar.show();
    }
}