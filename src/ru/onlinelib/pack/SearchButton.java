package ru.onlinelib.pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class SearchButton {

    Label response;

    private int q = 0;

    SearchButton(Button searchButton, TextField search)
    {
        response = new Label("пизда");

        FlowPane.setMargin(searchButton,  new Insets(30, 0, 0, 0)); // установка отступа сверху на 30, справа 0, снизу 0, слевва 0 для кнопки поиска

        searchButton.setStyle(
                "-fx-background-radius: 5%; " + // Скругление углов
                        "-fx-border-radius: 5%; " +     // Скругление границ
                        "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                        "-fx-text-fill: white;"          // Цвет текста
        );
        // Используем `setOnMousePressed` и `setOnMouseReleased` для изменения цвета при нажатии
        searchButton.setOnMousePressed(event -> {
            searchButton.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #388E3C; " + // Цвет фона кнопки
                            "-fx-text-fill: white;"          // Цвет текста
            );
        });

        searchButton.setOnMouseReleased(event -> {
            searchButton.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                            "-fx-text-fill: white;"          // Цвет текста
            );
        });

        //реализация поисковой кнопки
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            boolean isTextFieldEmpty = search.getText().isBlank();
            @Override
            public void handle(ActionEvent actionEvent) {
                if (search.getText().isBlank()) {
                    return; // Прерываем выполнение обработчика, если поле пустое
                }
                if(q == 0)
                {
                    response.setText(search.getText());
                    q++;
                }
                if(isTextFieldEmpty)
                {
                    search.setText("");
                    q = 0;
                }

            }
        });

    }

    public Label getResponse()
    {
        return response;
    }
}
