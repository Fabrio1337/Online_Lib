package ru.onlinelib.pack;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Main extends Application {

    Label response;

    TextField search;

    private int q = 0;

    private String text;

    public static void main(String[] args) {
        System.out.println("Запуск JavaFX-приложения");

        launch(args);
    }

    public void init(){ }


    public void start(Stage onlineLibrary)//onlineLibrary - главный подмосток
    {

        onlineLibrary.setTitle("Онлайн библиотека by Fabrio1337"); // установка названия приложения при открытии

        FlowPane rootNode = new FlowPane(30, 30); //создание компоновки //граф сцены

        Scene libraryScene = new Scene(rootNode, 1000, 600); //создание сцены

        //установливание сцены на подмостки
        onlineLibrary.setScene(libraryScene);


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


        search = new TextField(); // создание текстового поля

        search.setPromptText("Введи название книги"); // установка подсказки для текстового поля

        search.setPrefColumnCount(65); // установка длины поисковой строки

        FlowPane.setMargin(search,  new Insets(30, 0, 0, 0)); // установка отступа сверху на 10, справа 0, снизу 0, слевва 0 для поисковой строки


        Button searchButton = new Button("Поиск");

        FlowPane.setMargin(searchButton,  new Insets(30, 0, 0, 0)); // установка отступа сверху на 10, справа 0, снизу 0, слевва 0 для кнопки поиска

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


        response = new Label("");

        //получение текста с текстового поля
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                text = search.getText();

            }
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

        Profile profile = new Profile(profileBtn); // получение экземпляра класса окна Профиля

        rootNode.getChildren().addAll(profileBtn, search, searchButton, response);

        //показ подмосток и сцены
        onlineLibrary.show();
    }

    public void stop() { }
}