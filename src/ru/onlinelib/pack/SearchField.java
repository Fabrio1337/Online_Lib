package ru.onlinelib.pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class SearchField {

    private String textFromSearch;
    SearchField(TextField search, String name, int PrefCol, int Marg)
    {
        search.setPromptText("Введите " + name); // установка подсказки для текстового поля

        search.setPrefColumnCount(PrefCol); // установка длины поисковой строки

        FlowPane.setMargin(search,  new Insets(Marg, 0, 0, 0)); // установка отступа сверху на 30, справа 0, снизу 0, слевва 0 для поисковой строки

        //получение текста с текстового поля
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                textFromSearch = search.getText();

            }
        });

    }

    public String getTextFromSearch() {
        return textFromSearch;
    }
}
