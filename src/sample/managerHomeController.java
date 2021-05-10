package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class managerHomeController {
    @FXML
    Button homeButton;
    @FXML
    Button manageBookingsButton;
    @FXML
    Button manageRoomsButton;
    @FXML
    Button checkInButton;
    @FXML
    Button checkOutButton;
    @FXML
    Button reportButton;



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
        }catch (Exception e){
            System.out.println(e);        }

    }


//launch manage bookings page
    public void onManageBookingsButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) manageBookingsButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerBookings.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager | Manage bookings");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
//launch managen rooms page
    public void onManageRoomsButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) manageBookingsButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerRooms.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager | Manage rooms");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//launch check out page
    public void onCheckOutButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) checkInButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerCheckOut.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager | Check customer out");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//launch check in page
    public void onCheckInButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) checkInButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerCheckIn.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager | Check customer in");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



//launch reports page
    public void oneReportsButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) manageBookingsButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerRooms.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager | Reports");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//manage services used to file a customer service order
    public void onManageServicesButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) manageBookingsButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerServices.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager | Manage services");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}