package ru.onlinelib.pack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBooks {
    TextField book = new TextField();

    TextField url = new TextField();

    Button completeBook = new Button("Добавить");

    AddBooks(Button addButton)
    {
        SimpleButton MainAddButt = new SimpleButton(addButton);
        addButton.setAlignment(Pos.BOTTOM_LEFT);

        Stage addBookStage = new Stage(); //создание подмостка для добавления книги
        addBookStage.setTitle("Добавление книги");

        VBox booksNode = new VBox(20); //создание контейнера для добавления книги

        booksNode.setPadding(new Insets(20)); // Устанавливаем внутренние отступы 20px со всех сторон

        booksNode.setAlignment(Pos.TOP_CENTER);

        Scene booksScene = new Scene(booksNode,350, 200); //создание сцены для добавления книги

        addBookStage.setScene(booksScene);

        SearchField bookName = new SearchField(book, "название книги", 20, 20);

        SearchField bookUrl = new SearchField(url, "ссылку из браузера на книгу", 20, 20);

        SimpleButton newAddButton = new SimpleButton(completeBook);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Main.getIsLogin())
                {
                    if(!addBookStage.isShowing())
                    {
                        addBookStage.show();
                    }
                }
                else
                {
                    Err err = new Err(addButton);
                }

            }
        });



        booksNode.getChildren().addAll(book, url, completeBook);

    }
}
