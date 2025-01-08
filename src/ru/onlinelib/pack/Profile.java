package ru.onlinelib.pack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    private BooleanProperty isProfile = new SimpleBooleanProperty(true);

    RegistrationWindow registrationWindow;

    Profile(RegistrationWindow registrationWindow)
    {
        this.registrationWindow = registrationWindow;
    }


    public boolean getIsProfile()
    {
        return  isProfile.get();
    }


    public void getProfile(Button profileBtn, Stage RegStage)
    {
        Stage profileStage = new Stage(); // подмосток для окна профиля

        profileStage.setTitle("Профиль"); // установка названия окна профиля

        VBox profileNode = new VBox(10); // граф сцены профиля

        profileNode.setAlignment(Pos.TOP_CENTER); // выравнивание всех элементов по центру сверху

        Scene profileScene = new Scene(profileNode, 400, 500); // создание сцены профиля

        profileStage.setScene(profileScene); // установка сцены профиля на подмосток


        response = new Label();

        response.setText(registrationWindow.getFirst_name()); // имя которое выводится которое указано в профиле

        response.setFont(new Font(24)); //установка размера текста размером 24



        VBox.setMargin(response, new Insets(20, 0, 0, 0)); // установка отступа сверху на 20, справа 0, снизу 0, слевва 0 для кнопки поиска

        Button statisticsButton = getButton("Статистика");

        Button exitButton = getButton("Выйти");

        ProfileButton profileButton = new ProfileButton(profileBtn);


        statisticsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        //реализация кнопки "Профиль"
        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(getIsProfile())
                {
                    if(!profileStage.isShowing())
                    {
                        profileStage.show();
                    }
                }
                else
                {
                    RegistrationWindow.isLog = false;
                    RegStage.show();
                    registrationWindow.response.setText("");
                }
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isProfile.set(false);
                profileStage.close();
            }
        });

        profileNode.getChildren().addAll(response, statisticsButton, exitButton);
    }

    //метод для инициализации кнопки
    private static Button getButton(String text) {
        Button statisticsButton = new Button(text);

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

        return statisticsButton;
    }

}
