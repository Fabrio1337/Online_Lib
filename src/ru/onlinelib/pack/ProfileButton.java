package ru.onlinelib.pack;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ProfileButton {
    ProfileButton(Button profileBtn)
    {
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

        FlowPane.setMargin(profileBtn, new Insets(20, 0, 0, 10)); // установка отступа сверху на 10, справа 0, снизу 0, слевва 10

    }
}
