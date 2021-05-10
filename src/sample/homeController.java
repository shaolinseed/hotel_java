package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    @FXML
    Button customerButton;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label loginStatusLabel;
    @FXML
    Button loginButton;


    public void onCustomerButtonClick() throws IOException {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer1.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer | Home page");
            stage.setScene(new Scene(secondWin));
            stage.show();

            Stage stageclose = (Stage) customerButton.getScene().getWindow();
            stageclose.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
//authenticate login
    public boolean checkLoginDetails() {
        if (Connect.validateLogin(usernameField.getText(), passwordField.getText())) {
            System.out.println("Manager successfully logged in");
            return true;
        } else {
            System.out.println("Incorrect user details...");
            loginStatusLabel.setText("Invalid Login...");
            return false;
        }


    }
//if correct log in details launch the manager home page
    public void onLoginButtonClick() throws IOException {
        if (checkLoginDetails()) {


            try {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerHome.fxml"));
                Parent secondWin = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Manager | Home");
                stage.setScene(new Scene(secondWin));
                stage.show();

                Stage stageclose = (Stage) customerButton.getScene().getWindow();
                stageclose.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


}


//https://www.notion.so/Streamline-Icons-Free-Pack-License-bec2619f40394856b6eeb6cdbe7d6b7d