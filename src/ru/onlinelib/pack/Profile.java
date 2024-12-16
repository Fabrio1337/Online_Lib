package ru.onlinelib.pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Profile {

    private int k = 0;
    Profile(Button profileBtn)
    {
        Stage profileStage = new Stage(); // подмосток для окна профиля

        profileStage.setTitle("Профиль"); // установка названия окна профиля

        FlowPane profileNode = new FlowPane(30, 30); // граф сцены профиля

        Scene profileScene = new Scene(profileNode, 400, 500); // создание сцены профиля

        profileStage.setScene(profileScene); // установка сцены профиля на подмосток

        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            boolean isShow = profileStage.isShowing(); // переменная для проверки открыто ли данное окно или нет
            @Override
            public void handle(ActionEvent actionEvent) {
                if(k == 0) { profileStage.show(); } // показываем профиль при нажатии

                k++;

                if(!isShow) { k = 0; } // условие для предотвращения создания "клонов" профиля
            }
        });
    }
}
