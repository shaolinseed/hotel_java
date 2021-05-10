package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class customerReceiptController {



    @FXML
    ComboBox roomTypeCombo;
    @FXML
    ComboBox paymentMethodCombo;

    @FXML
    DatePicker checkInDate;
    @FXML
    DatePicker checkOutDate;
    @FXML
    Button homeButton;
    @FXML
    Button proceedButton;

//return home
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

//store details of booking in format for sql
    private String bookingDetails[] = new String[6];


    public void onProceedButtonClick(ActionEvent actionEvent) {
        //find the most recent customer sign up to associate booking with this
        String findCurrentCustomer = "select max(customerId) from customers;";

        Room room = new Room();
        String suitableRoom = room.findRoomNumber(this.roomTypeCombo.getValue().toString());

        //set booking details equal to fields
        bookingDetails[0] = Connect.resultStringReturner(Connect.sqlExecute(findCurrentCustomer));
        bookingDetails[1] = suitableRoom;
        bookingDetails[2] = this.checkInDate.getValue().toString();
        bookingDetails[3] = this.checkOutDate.getValue().toString();
        bookingDetails[4] = this.paymentMethodCombo.getValue().toString();
        bookingDetails[5] = "Pending";


        //used to check all fields are checked
        Boolean allFieldsChecked = false;
        Integer numberOfFieldsFull = 0;

        for (int i = 0; i < 5; i++) {
            if (!bookingDetails[i].isEmpty()) {
                numberOfFieldsFull = numberOfFieldsFull + 1;


            }
            if (numberOfFieldsFull == 5) {
                allFieldsChecked = true;
            }
        }
        if (allFieldsChecked) {

            try {
                //close current window
                Stage stageclose = (Stage) proceedButton.getScene().getWindow();
                stageclose.close();
                //open home window
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookingConfirmation.fxml"));
                Parent secondWin = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Customer | Booking confirmation");
                stage.setScene(new Scene(secondWin));
                stage.show();
            } catch (Exception e) {
                System.out.println(e);
            }

            //insert into bookings
            String insertSql = "insert into bookings values (DEFAULT, ?, ?, ?, ?, ?, ?);";
            Connect.prepNewBookingUpdate(insertSql, bookingDetails);


        }


    }


}
