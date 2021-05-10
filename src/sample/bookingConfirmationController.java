package sample;


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

public class bookingConfirmationController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @FXML
    Button homeButton;

    @FXML
    Label bookingIdLabel;


    String findCurrentCustomer = "select max(bookingId) from bookings;";

    public void initialize() {


    }

//return to home
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


    private String bookingDetails[] = new String[7];




    public void onDisplayBookingButtonCLick(ActionEvent actionEvent) {

        String currentBooking = Connect.resultStringReturner(Connect.sqlExecute(findCurrentCustomer));
        bookingIdLabel.setText(currentBooking);

    }
}
