package ru.onlinelib.pack;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationWindow {

    RegistrationWindow()
    {
        Stage RegStage = new Stage(); // подмосток для окна регистрации

        RegStage.setTitle("Регистрация"); //установка заголовка для окна

        VBox profileNode = new VBox(); // граф сцены профиля

        profileNode.setAlignment(Pos.TOP_CENTER); // выравнивание всех элементов по центру сверху

        Scene RegScene = new Scene(profileNode, 400, 500); // создание сцены профиля

        RegStage.setScene(RegScene); // установка сцены регистрации на подмосток



        profileNode.getChildren().addAll();
    }

}
