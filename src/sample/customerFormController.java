package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class customerFormController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    Button continueButton;
    @FXML
    Button homeButton;
    @FXML
    Button backButton;
    @FXML
    Button forwardButton;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField emailAddressField;
    @FXML
    TextField countryField;
    @FXML
    TextField addressLine1Field;
    @FXML
    TextField addressLine2Field;
    @FXML
    TextField postcodeField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField customerIdField;


    //list of strings used to format adding to database
    private String customerDetails[] = new String[8];


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

    //continue button used to get attributes to store in new customer
    public void onContinueButtonClick(ActionEvent actionEvent) {
        if (firstNameField.getText().isEmpty() & lastNameField.getText().isEmpty()) ;
        customerDetails[0] = this.firstNameField.getText();
        customerDetails[1] = this.lastNameField.getText();
        customerDetails[2] = this.emailAddressField.getText();
        customerDetails[3] = this.countryField.getText();
        customerDetails[4] = this.addressLine1Field.getText();
        customerDetails[5] = this.addressLine2Field.getText();
        customerDetails[6] = this.postcodeField.getText();
        customerDetails[7] = this.phoneNumberField.getText();

        //used to check if all  of fields are completed
        Boolean allFieldsChecked = false;
        Integer numberOfFieldsFull = 0;

        //loop round all the fields to check theyre not empty
        for (int i = 0; i < 8; i++) {
            if (!customerDetails[i].isEmpty()) {
                numberOfFieldsFull = numberOfFieldsFull + 1;


            }
            if (numberOfFieldsFull == 8) {
                allFieldsChecked = true;
            }
        }

//if all fields are filled
        if (allFieldsChecked) {
            //sql insert format
            String customerDetailsSql = "insert into customers values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connect.prepCustomerUpdate(customerDetailsSql, customerDetails);
//open the next page associated with creating a booking
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerNewBooking.fxml"));
                Parent secondWin = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Customer | Provide booking det");
                stage.setScene(new Scene(secondWin));
                stage.show();

                Stage stageclose = (Stage) continueButton.getScene().getWindow();
                stageclose.close();

            } catch (Exception e) {
                System.out.println(e);
            }


        }


    }
}
