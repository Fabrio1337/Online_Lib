package ru.onlinelib.pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class SimpleButton {

    SimpleButton(Button simpleButton)
    {


        FlowPane.setMargin(simpleButton,  new Insets(30, 0, 0, 0)); // установка отступа сверху на 30, справа 0, снизу 0, слевва 0 для кнопки поиска

        simpleButton.setStyle(
                "-fx-background-radius: 5%; " + // Скругление углов
                        "-fx-border-radius: 5%; " +     // Скругление границ
                        "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                        "-fx-text-fill: white;" +         // Цвет текста
                        "-fx-font-size: 20px; " +
                        "-fx-padding: 20px 40px;"
        );
        // Используем `setOnMousePressed` и `setOnMouseReleased` для изменения цвета при нажатии
        simpleButton.setOnMousePressed(event -> {
            simpleButton.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #388E3C; " + // Цвет фона кнопки
                            "-fx-text-fill: white;" +         // Цвет текста
                            "-fx-font-size: 20px; " +
                            "-fx-padding: 20px 40px;"
            );
        });

        simpleButton.setOnMouseReleased(event -> {
            simpleButton.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                            "-fx-text-fill: white;"  +        // Цвет текста
                            "-fx-font-size: 20px; " +
                            "-fx-padding: 20px 40px;"
            );
        });



    }
}
