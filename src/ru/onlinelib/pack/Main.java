package ru.onlinelib.pack;

import javafx.application.*;

import javafx.event.ActionEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    private BooleanProperty isLoggedIn = new SimpleBooleanProperty(false);



    public static void main(String[] args) {
        System.out.println("Запуск JavaFX-приложения");


        launch(args);
    }

    public void init(){ }


    public void start(Stage onlineLibrary)//onlineLibrary - главный подмосток
    {
    	
        onlineLibrary.setTitle("Онлайн библиотека by Fabrio1337"); // установка названия приложения при открытии

        FlowPane rootNode = new FlowPane(30, 30); //создание компоновки с отступом по 30 пикселей по горизонтали и вертикали между элементами

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

        loadInitialVindow(profileBtn);

        isLoggedIn.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                openProfile(profileBtn);
            }
            else {
                openReg(profileBtn);
                response.setText(isLoggedIn.getName());
            }
        });

        rootNode.getChildren().addAll(profileBtn, search, searchButton1, response);
        //показ подмосток и сцены
        onlineLibrary.show();

       
    }

    public void stop() { }

    private void loadInitialVindow(Button profilebtn)
    {
        if(isLogin)
        {
            openProfile(profilebtn);
        }
        else
        {
            openReg(profilebtn);
        }

    }

    //код для открытия окна профиля
    private void openProfile(Button profileBtn)
    {
        Profile profile = new Profile(profileBtn);
    }

    private void openReg(Button profileBtn)
    {
        RegistrationWindow regWindow = new RegistrationWindow(profileBtn);

        regWindow.isLogProperty().addListener((observable,oldValue, newValue) -> {
            if( newValue)
            {
                isLoggedIn.set(true);
            }
        });
    }

}