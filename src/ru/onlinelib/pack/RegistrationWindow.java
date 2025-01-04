package ru.onlinelib.pack;

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


public class RegistrationWindow {

    private Button authReg = getButton("");

    private int k = 0;

    RegistrationWindow(Button profileBtn)
    {
        Stage RegStage = new Stage(); // подмосток для окна регистрации

        RegStage.setTitle("Регистрация"); //установка заголовка для окна

        VBox profileNode = new VBox(20); // граф сцены профиля
        profileNode.setPadding(new Insets(20)); // Устанавливаем внутренние отступы 20px со всех сторон

        profileNode.setAlignment(Pos.TOP_CENTER); // выравнивание всех элементов по центру сверху

        Scene RegScene = new Scene(profileNode, 400, 500); // создание сцены профиля

        RegStage.setScene(RegScene); // установка сцены регистрации на подмосток

        HBox radioButtons = new HBox(20); // создание сцены для кнопок "Авторизация" и "Регистрация"
        radioButtons.setAlignment(Pos.CENTER);

        RadioButton auth = new RadioButton("Авторизация"); //переключатель для авторизации
        RadioButton reg = new RadioButton("Регистрация"); // переключатель для регистрации

        ToggleGroup tg = new ToggleGroup(); // создание группы переключателей

        auth.setToggleGroup(tg); //добавление кнопки авторизации в переключатель
        reg.setToggleGroup(tg); //добавление кнопки регистрации в переключатель

        reg.setSelected(true); //установка автоматического выбора кнопки переключателя

        TextField firstName =  new TextField(); // текстовое поле для логина

        SearchField firstNameField = new SearchField(firstName, "логин", 20, 10); //получение экземпляра класса для поля логина


        TextField pass = new PasswordField(); // текстовое поле для пароля

        SearchField passField = new SearchField(pass, "пароль", 20, 10); //получение экземпляра класса для поля пароля


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

        FlowPane.setMargin(profileBtn, new Insets(20, 0, 0, 10)); // установка отступа сверху на 10, справа 0, снизу 0, слевва 10




        // Изменяем текст кнопки в зависимости от выбранного переключателя
        tg.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (auth.isSelected()) {
                authReg.setText("Авторизация");
            } else if (reg.isSelected()) {
                authReg.setText("Регистрация");
            }
        });



        VBox.setMargin(authReg, new Insets(20, 0, 0, 0)); // установка отступа сверху на 20, справа 0, снизу 0, слевва 0 для кнопки поиска




        //реализация кнопки "Профиль"
        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            boolean isShow = RegStage.isShowing(); // переменная для проверки открыто ли данное окно или нет
            @Override
            public void handle(ActionEvent actionEvent) {
                if(k == 0) { RegStage.show(); } // показываем профиль при нажатии

                k++;

                if(!isShow) { k = 0; } // условие для предотвращения создания "клонов" профиля
            }
        });


        radioButtons.getChildren().addAll(auth, reg);

        profileNode.getChildren().addAll(firstName, pass,radioButtons, authReg);
    }

    //метод для инициализации кнопки
    private static Button getButton(String text) {
        Button authReg = new Button(text);

        authReg.setStyle(
                "-fx-background-radius: 5%; " + // Скругление углов
                        "-fx-border-radius: 5%; " +     // Скругление границ
                        "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                        "-fx-text-fill: white;" + // Цвет текста
                        "-fx-font-size: 20px; " + // размер текста
                        "-fx-padding: 20px 40px;"// размер кнопки
        );
        // Используем `setOnMousePressed` и `setOnMouseReleased` для изменения цвета при нажатии
        authReg.setOnMousePressed(event -> {
            authReg.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #388E3C; " + // Цвет фона кнопки
                            "-fx-text-fill: white;" + // Цвет текста
                            "-fx-font-size: 20px; " + // размер текста
                            "-fx-padding: 20px 40px;"// размер кнопки
            );
        });

        authReg.setOnMouseReleased(event -> {
            authReg.setStyle(
                    "-fx-background-radius: 5%; " + // Скругление углов
                            "-fx-border-radius: 5%; " +     // Скругление границ
                            "-fx-background-color: #4CAF50; " + // Цвет фона кнопки
                            "-fx-text-fill: white;" + // Цвет текста
                            "-fx-font-size: 20px; " + // размер текста
                            "-fx-padding: 20px 40px;"// размер кнопки
            );
        });

        VBox.setMargin(authReg, new Insets(20, 0, 0, 0)); // установка отступа сверху на 20, справа 0, снизу 0, слевва 0 для кнопки поиска

        return authReg;
    }

}
