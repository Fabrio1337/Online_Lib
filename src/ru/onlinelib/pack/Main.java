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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    Label response;

    TextField search;

    private int q = 0;

    private String text;

    private boolean isLogin = false;

    public static String connect(String text)
    {
        String result = "";

        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                String query = text; // Замените на ваш SQL-запрос
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    System.out.println("Строка из БД: " + rs.getString("first_name"));
                    result = rs.getString("first_name");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return result;
    }

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


        Button profileBtn = new Button("Профиль"); // создание кнопки профиля


        search = new TextField(); // создание текстового поля


        Button searchButton1 = new Button("Поиск"); // создание поисковой кнопки


        response = new Label(""); // метка тестовая

        
        




        SearchField searchField = new SearchField(search, "название книги", 65, 30); // получение экземпляра класса поискового поля

        SearchButton searchButton = new SearchButton(searchButton1, search); // получение экземпляра класса поисковой кнопки



        response = searchButton.getResponse(); //получение текста из текстового поля




        if(isLogin)
        {
            Profile profile = new Profile(profileBtn); // получение экземпляра класса окна Профиля
            rootNode.getChildren().addAll(profileBtn, search, searchButton1, response);
        }
        else
        {
            RegistrationWindow regWindow = new RegistrationWindow(profileBtn);
            rootNode.getChildren().addAll(profileBtn, search, searchButton1, response);
        }

        //показ подмосток и сцены
        onlineLibrary.show();

       
    }

    public void stop() { }
}