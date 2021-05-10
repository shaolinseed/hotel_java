package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class managerCheckOutController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @FXML
    Button homeButton;
    @FXML
    TextField bookingIdField;
    @FXML
    Label checkOutConfirmation;
    @FXML
    Button checkOutButton;


    //for adding to database
    private String customerDetails[] = new String[8];


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


    public void onCheckOutButtonClick(ActionEvent actionEvent) {
//check if user is checked in
        String currentBookingStatus = Connect.resultStringReturner(Connect.sqlExecute("select bookingStatus from bookings WHERE bookingId =" + bookingIdField.getText() + ";"));
        if (currentBookingStatus.equals("Checked in")) {
            //if checked in change status to checked out
            String updateBookingStatus = "UPDATE bookings SET bookingStatus = 'Checked out' WHERE bookingId=" + bookingIdField.getText() + ";";
            Connect.sqlUpdate(updateBookingStatus);
            checkOutConfirmation.setText("Customer has successfully checked out");


        } else {
            checkOutConfirmation.setText("Customer could not check out at this time");
        }
    }
}

