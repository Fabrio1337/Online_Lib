package ru.onlinelib.pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Profile {

    Label response;

    private int k = 0;
    Profile(Button profileBtn)
    {
        Stage profileStage = new Stage(); // подмосток для окна профиля

        profileStage.setTitle("Профиль"); // установка названия окна профиля

        VBox profileNode = new VBox(); // граф сцены профиля

        profileNode.setAlignment(Pos.TOP_CENTER); // выравнивание всех элементов по центру сверху

        Scene profileScene = new Scene(profileNode, 400, 500); // создание сцены профиля

        profileStage.setScene(profileScene); // установка сцены профиля на подмосток


        response = new Label();

        response.setText("your name"); // имя которое выводится которое указано в профиле

        response.setFont(new Font(24)); //установка размера текста размером 24



        VBox.setMargin(response, new Insets(20, 0, 0, 0)); // установка отступа сверху на 20, справа 0, снизу 0, слевва 0 для кнопки поиска

        Button statisticsButton = new Button("Статистика");

        statisticsButton.setStyle(
                "-fx-background-radius: 5%; " + // Скругление углов
                        "-fx-border-radius: 5%; " +     // Скругление границ
                        "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                        "-fx-text-fill: white;" + // Цвет текста
                        "-fx-font-size: 20px; " + // размер текста
                        "-fx-padding: 20px 40px;"// размер кнопки
        );
        // Используем `setOnMousePressed` и `setOnMouseReleased` для изменения цвета при нажатии
        statisticsButton.setOnMousePressed(event -> {
            statisticsButton.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #388E3C; " + // Цвет фона кнопки
                            "-fx-text-fill: white;" + // Цвет текста
                            "-fx-font-size: 20px; " + // размер текста
                            "-fx-padding: 20px 40px;"// размер кнопки
            );
        });

        statisticsButton.setOnMouseReleased(event -> {
            statisticsButton.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                            "-fx-text-fill: white;" + // Цвет текста
                            "-fx-font-size: 20px; " + // размер текста
                            "-fx-padding: 20px 40px;"// размер кнопки
            );
        });

        VBox.setMargin(statisticsButton, new Insets(20, 0, 0, 0)); // установка отступа сверху на 20, справа 0, снизу 0, слевва 0 для кнопки поиска

        //изменение стиля кнопки
        profileBtn.setMinSize(75,75);
        profileBtn.setMaxSize(75, 75);
        profileBtn.setStyle(
                "-fx-background-radius: 50%; " + // Скругление углов до круга
                        "-fx-border-radius: 50%; " +     // Скругление границ до круга
                        "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                        "-fx-text-fill: white;"          // Цвет текста
        );
        // Используем `setOnMousePressed` и `setOnMouseReleased` для изменения цвета при нажатии
        profileBtn.setOnMousePressed(event -> {
            profileBtn.setStyle(
                    "-fx-background-radius: 50%; " + // Скругление углов до круга
                            "-fx-border-radius: 50%; " +     // Скругление границ до круга
                            "-fx-background-color: #388E3C; " + // Цвет фона кнопки
                            "-fx-text-fill: white;"
            );
        });

        profileBtn.setOnMouseReleased(event -> {
            profileBtn.setStyle(
                    "-fx-background-radius: 50%; " + // Скругление углов до круга
                            "-fx-border-radius: 50%; " +     // Скругление границ до круга
                            "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                            "-fx-text-fill: white;"
            );
        });

        FlowPane.setMargin(profileBtn, new Insets(10, 0, 0, 10)); // установка отступа сверху на 10, справа 0, снизу 0, слевва 10

        //реализация кнопки "Профиль"
        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            boolean isShow = profileStage.isShowing(); // переменная для проверки открыто ли данное окно или нет
            @Override
            public void handle(ActionEvent actionEvent) {
                if(k == 0) { profileStage.show(); } // показываем профиль при нажатии

                k++;

                if(!isShow) { k = 0; } // условие для предотвращения создания "клонов" профиля
            }
        });

        statisticsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        profileNode.getChildren().addAll(response, statisticsButton);
    }
}
