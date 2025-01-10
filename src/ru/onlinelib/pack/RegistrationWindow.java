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

import java.sql.Connection;
import java.sql.Statement;


public class RegistrationWindow {

    private Button authReg = getButton("Регистрация");

    RadioButton auth = new RadioButton("Авторизация"); //переключатель для авторизации
    RadioButton reg = new RadioButton("Регистрация"); // переключатель для регистрации

    TextField firstName =  new TextField(); // текстовое поле для логина

    TextField pass = new PasswordField(); // текстовое поле для пароля

    public Label response = new Label();

    private Label title = new Label();

    private static String first_name;
    private String password;

    DatabaseConnection db = new DatabaseConnection();

    Connection conn =  db.connect();

    public static boolean isLog = false;

    public String getFirst_name()
    {
        return  first_name;
    }

    private void dbQuery(boolean whatIsQuery)
    {
        Boolean query = db.databaseQuery(whatIsQuery,conn,"users", first_name, password);

        isLog = query;
        //isLogin.set(isLog);
        //System.out.println(query);
    }

    // Метод для установки обработчика в зависимости от выбранного переключателя
    private void updateAuthRegAction(Button profileBtn, Stage RegStage) {
        if (reg.isSelected()) {
            title.setText("Регистрация");
            authReg.setText("Регистрация");
        } else if (auth.isSelected()) {
            title.setText("Авторизация");
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
                        response.setText("Пользователь с таким именем уже существует");
                    }

                }
                else
                {
                    dbQuery(true);
                    if(isLog) {  // Проверяем успешность авторизации
                        response.setText("Вы успешно вошли в аккаунт");
                        Profile profile = new Profile(RegistrationWindow.this);  // Сразу создаем профиль
                        profile.getProfile(profileBtn, RegStage);   // И открываем его
                        Main.setIsLogin(isLog);
                        if(!profile.getIsProfile())
                        {
                            isLog = false;
                            if (!RegStage.isShowing()) {
                                RegStage.show();
                            }
                            Main.setIsLogin(isLog);

                        }
                    } else {
                        response.setText("Неверный логин или пароль");
                    }

                }

                if(!RegStage.isShowing())
                {
                    response.setText("");
                }

                firstName.clear();
                pass.clear();
            }
        });
    }

    public void getRegWindow(Button profileBtn)
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

        // Применяем стили через setStyle
        title.setStyle("-fx-font-size: 24px; " +  // Размер текста
                "-fx-font-weight: bold; " +  // Жирный текст
                "-fx-text-fill: #4CAF50; " );  // Зеленый цвет текста

        auth.setStyle("-fx-font-size: 12px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #4CAF50; ");

        reg.setStyle("-fx-font-size: 12px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #4CAF50; ");

        // Установка начального обработчика
        updateAuthRegAction(profileBtn, RegStage);

        // действие переключателя
        tg.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updateAuthRegAction(profileBtn, RegStage);
        });


        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!isLog) {
                    Main.setIsLogin(isLog);
                    // Показываем окно регистрации, если пользователь не вошел
                    if (!RegStage.isShowing()) {
                        RegStage.show();
                    }
                } else {
                    Main.setIsLogin(isLog);
                    // Если пользователь авторизован, открываем профиль
                    Profile profile = new Profile(RegistrationWindow.this);
                    profile.getProfile(profileBtn, RegStage);
                    if(!isLog)
                    {
                        RegStage.show();
                    }

                }
                if(!RegStage.isShowing())
                {
                    response.setText("");
                }
            }
        });


        radioButtons.getChildren().addAll(auth, reg);

        profileNode.getChildren().addAll(title, firstName, pass,radioButtons, authReg, response);
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