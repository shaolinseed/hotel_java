package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class customer1Controller {
    @FXML
    Button homeButton;
    @FXML
    Button createBookingButton;
    @FXML
    Button manageBookingButton;
    @FXML
    Button orderServiceButton;


    public void onHomeButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) homeButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer home");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //customer manage booking page launch
    public void onManageBookingClick(ActionEvent actionEvent) {
        try {
            Stage stageclose = (Stage) manageBookingButton.getScene().getWindow();
            stageclose.close();


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerBookings.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer | Manage booking");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //create a booking page launch
    public void onCreateButtonClick(ActionEvent actionEvent) {
        try {
            Stage stageclose = (Stage) createBookingButton.getScene().getWindow();
            stageclose.close();


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerForm.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer | Provide details");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //order a service page launch
    public void onOrderServiceButtonClick(ActionEvent actionEvent) {
        try {
            Stage stageclose = (Stage) orderServiceButton.getScene().getWindow();
            stageclose.close();


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerOrderService.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer | Order a service");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
