package ru.onlinelib.pack;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Запуск JavaFX-приложения");

        launch(args);
    }

    public void init(){
        System.out.println("В теле метода init().");
    }

    public void start(Stage onlineLibrary)//onlineLibrary - главный подмосток
    {
        System.out.println("В теле метода start");

        onlineLibrary.setTitle("Онлайн библиотека by Fabrio1337"); // установка названия приложения при открытии

        FlowPane rootNode = new FlowPane(); //создание компоновки

        Scene libraryScene = new Scene(rootNode, 1000, 900); //создание сцены

        //установливание сцены на подмостки
        onlineLibrary.setScene(libraryScene);

        //показ подмосток и сцены
        onlineLibrary.show();
    }

    public void stop()
    {
        System.out.println("В теле метода stop().");
    }
}