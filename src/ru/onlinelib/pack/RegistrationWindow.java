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

import java.sql.Connection;


public class RegistrationWindow {

    private Button authReg = getButton("Регистрация");

    RadioButton auth = new RadioButton("Авторизация"); //переключатель для авторизации
    RadioButton reg = new RadioButton("Регистрация"); // переключатель для регистрации

    TextField firstName =  new TextField(); // текстовое поле для логина

    TextField pass = new PasswordField(); // текстовое поле для пароля

    private Label response = new Label();

    private int k = 0;

    private String first_name;
    private String password;

    DatabaseConnection db = new DatabaseConnection();

    Connection conn =  db.connect();

    private static boolean isLog = false;

    public static boolean getIsLog()
    {
        return isLog;
    }

    private void dbQuery(boolean whatIsQuery)
    {
       Boolean query = db.databaseQuery(whatIsQuery,conn,"users", first_name, password);

       isLog = query;
       System.out.println(query);
    }

    // Метод для установки обработчика в зависимости от выбранного переключателя
    private void updateAuthRegAction() {
        if (reg.isSelected()) {
            authReg.setText("Регистрация");
        } else if (auth.isSelected()) {
            authReg.setText("Авторизация");
        }

        authReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                first_name = firstName.getText();
                password = pass.getText();

                if(reg.isSelected())
                {
                    dbQuery(false);
                    if(isLog)
                    {
                        response.setText("Регистрация прошла успешно");
                    }
                    else {
                        System.out.println("Пользователь с таким именем уже существует");
                    }

                }
                else
                {
                    dbQuery(true);
                    response.setText("Вы успешно вошли в аккаунт");

                }

                firstName.clear();
                pass.clear();
            }
        });
    }



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

        ToggleGroup tg = new ToggleGroup(); // создание группы переключателей

        auth.setToggleGroup(tg); //добавление кнопки авторизации в переключатель
        reg.setToggleGroup(tg); //добавление кнопки регистрации в переключатель

        reg.setSelected(true); //установка автоматического выбора кнопки переключателя

        SearchField firstNameField = new SearchField(firstName, "логин", 20, 10); //получение экземпляра класса для поля логина

        SearchField passField = new SearchField(pass, "пароль", 20, 10); //получение экземпляра класса для поля пароля


        ProfileButton profileButton = new ProfileButton(profileBtn);






        VBox.setMargin(authReg, new Insets(20, 0, 0, 0)); // установка отступа сверху на 20, справа 0, снизу 0, слевва 0 для кнопки поиска

        // Установка начального обработчика
        updateAuthRegAction();

        // действие переключателя
        tg.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updateAuthRegAction();
        });


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

        profileNode.getChildren().addAll(firstName, pass,radioButtons, authReg, response);
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
