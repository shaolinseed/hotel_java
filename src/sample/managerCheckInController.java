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

public class managerCheckInController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @FXML
    Button homeButton;
    @FXML
    TextField bookingIdField;
    @FXML
    Label checkInConfirmation;


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
        }catch (Exception e){
            System.out.println(e);        }

    }




//allow used to check in if their booking has been approved
    public void onCheckInButtonClick(ActionEvent actionEvent) {

        String currentBookingStatus = Connect.resultStringReturner(Connect.sqlExecute("select bookingStatus from bookings WHERE bookingId =" + bookingIdField.getText() + ";"));
        System.out.println(currentBookingStatus);
        if(currentBookingStatus.equals("Approved")) {
            String updateBookingStatus = "UPDATE bookings SET bookingStatus = 'Checked in' WHERE bookingId=" + bookingIdField.getText() + ";";
            Connect.sqlUpdate(updateBookingStatus);
            checkInConfirmation.setText("Customer has successfully checked in");



        } else{
            checkInConfirmation.setText("Customer could not check in at this time");
        }
    }
}
