package ru.onlinelib.pack;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Main extends Application {

    Label response;
    private int k = 0;

    public static void main(String[] args) {
        System.out.println("Запуск JavaFX-приложения");

        launch(args);
    }

    public void init(){ }


    public void start(Stage onlineLibrary)//onlineLibrary - главный подмосток
    {

        onlineLibrary.setTitle("Онлайн библиотека by Fabrio1337"); // установка названия приложения при открытии

        FlowPane rootNode = new FlowPane(30, 30); //создание компоновки //граф сцены

        Scene libraryScene = new Scene(rootNode, 1000, 900); //создание сцены

        //установливание сцены на подмостки
        onlineLibrary.setScene(libraryScene);


        Stage profileStage = new Stage(); // подмосток для окна профиля

        profileStage.setTitle("Профиль"); // установка названия окна профиля

        FlowPane profileNode = new FlowPane(30, 30); // граф сцены профиля

        Scene profileScene = new Scene(profileNode, 400, 500); // создание сцены профиля

        profileStage.setScene(profileScene); // установка сцены профиля на подмосток


        Button profileBtn = new Button("Профиль");

        //изменение стиля кнопки
        profileBtn.setMinSize(75,75);
        profileBtn.setMaxSize(75, 75);
        profileBtn.setStyle(
                "-fx-background-radius: 50%; " + // Скругление углов до круга
                        "-fx-border-radius: 50%; " +     // Скругление границ до круга
                        "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                        "-fx-text-fill: white;"          // Цвет текста
        );

        FlowPane.setMargin(profileBtn, new Insets(30, 0, 0, 30)); // установка отступа сверху на 30, справа 0, снизу 0, слевва 30


        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            boolean isShow = profileStage.isShowing(); // переменная для проверки открыто ли данное окно или нет
            @Override
            public void handle(ActionEvent actionEvent) {
                if(k == 0) { profileStage.show(); } // показываем профиль при нажатии

                k++;

                if(!isShow) { k = 0; } // условие для предотвращения создания "клонов" профиля
            }
        });

        rootNode.getChildren().addAll(profileBtn);

        //показ подмосток и сцены
        onlineLibrary.show();
    }

    public void stop() { }
}