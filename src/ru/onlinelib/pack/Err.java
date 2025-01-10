package ru.onlinelib.pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Err {

    Err(Button addButton)
    {
        SimpleButton newAddButton = new SimpleButton(addButton);

        Stage errStage = new Stage();
        errStage.setTitle("Ошибка!");

        HBox errNode = new HBox(20);

        errNode.setAlignment(Pos.CENTER);

        Scene errScene = new Scene(errNode, 300, 150);

        errStage.setScene(errScene);

        Label text = new Label();

        text.setText("Вы не можете добавлять книги если не авторизованы ");
        text.setWrapText(true);

        text.setPadding(new Insets(10));

        text.setTextAlignment(TextAlignment.CENTER);

        text.setStyle("-fx-font-size: 12px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #4CAF50; ");


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!Main.getIsLogin())
                {
                    if(!errStage.isShowing())
                    {
                        errStage.show();
                    }
                }
                else
                {
                    AddBooks addBooks = new AddBooks(addButton);
                }

            }
        });


        errNode.getChildren().add(text);
    }
}
