package ru.onlinelib.pack;

import javafx.application.*;

import javafx.event.ActionEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.layout.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    Label response;

    TextField search;

    private static BooleanProperty isLoggedIn = new SimpleBooleanProperty(false);

    public static void setIsLogin(boolean isLog)
    {
        System.out.println(isLoggedIn.getValue());
        isLoggedIn.set(isLog);
    }



    public static void main(String[] args) {
        System.out.println("Запуск JavaFX-приложения");


        launch(args);
    }

    public void init(){ }


    public void start(Stage onlineLibrary)//onlineLibrary - главный подмосток
    {

        onlineLibrary.setTitle("Онлайн библиотека by Fabrio1337"); // установка названия приложения при открытии


        Button profileBtn = new Button("Профиль"); // создание кнопки профиля

        search = new TextField(); // создание текстового поля

        Button searchButton1 = new Button("Поиск"); // создание поисковой кнопки

        response = new Label(""); // метка тестовая

        Button addBook = new Button("Добавить книгу");


        VBox books = new VBox(20); //создание сцены для списка книг
        books.setPadding(new Insets(20));


        ScrollPane scroll = new ScrollPane(books); //создание скроллбара
        scroll.setFitToWidth(true);
        scroll.setPannable(true);
        VBox.setVgrow(scroll, Priority.ALWAYS); // ScrollPane растягивается по высоте
        scroll.setFitToHeight(true); // Подгоняет высоту содержимого

        HBox booksWithScroll = new HBox(20); //создание сцены для книг и скроллбара
        booksWithScroll.getChildren().add(scroll);

        FlowPane rootNode = new FlowPane(30, 30); //создание компоновки с отступом по 30 пикселей по горизонтали и вертикали между элементами
        rootNode.getChildren().addAll(profileBtn, search, searchButton1, response);

        VBox allApp = new VBox(30);
        allApp.getChildren().addAll(rootNode, addBook);
        allApp.setPadding(new Insets(20)); // Отступы от краёв
        allApp.setAlignment(Pos.TOP_CENTER); // Центрирование содержимого


        Scene libraryScene = new Scene(allApp, 1000, 600); //создание сцены
        //установливание сцены на подмостки
        onlineLibrary.setScene(libraryScene);

        // Привязываем текст кнопки addBook к значению isLogin
        addBook.textProperty().bind(
                isLoggedIn.asString().map(value ->
                        Boolean.parseBoolean(value) ? "Да" : "Нет"
                )
        );




        addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {



                // Привязываем текст кнопки addBook к значению isLogin
                addBook.textProperty().bind(
                        isLoggedIn.asString().map(value ->
                                Boolean.parseBoolean(value) ? "Да" : "Нет"
                        )
                );
            }
        });


        AddBooks addBooks = new AddBooks(addBook);

        SearchField searchField = new SearchField(search, "название книги", 65, 30); // получение экземпляра класса поискового поля

        SearchButton searchButton = new SearchButton(searchButton1, search); // получение экземпляра класса поисковой кнопки

        response = searchButton.getResponse(); //получение текста из текстового поля

        RegistrationWindow registrationWindow = new RegistrationWindow();
        registrationWindow.getRegWindow(profileBtn);


        //показ подмосток и сцены
        onlineLibrary.show();


    }

    public void stop() { }

    public void sampleBook()
    {

    }



}