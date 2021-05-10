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

public class customerOrderServiceController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    ComboBox serviceTypeCombo;
    @FXML
    Label serviceTypeLabel;
    @FXML
    Label servicePriceLabel;
    @FXML
    Button homeButton;
    @FXML
    Button orderServiceButton;
    @FXML
    TextField bookingIdField;
    @FXML
    Label serviceConfirmationLabel;

//fill combo box with service types
    String[] serviceTypes = new String[]{"Food", "Beverage", "SPA", "Taxi", "Custom"};

    public void initialize() {
        //reset roomtypes combo box
        serviceTypeCombo.getItems().clear();
        serviceTypeCombo.getItems().addAll(serviceTypes);
//        reset payment methods combo box


        //Service type combo box listener
        serviceTypeCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                serviceTypeLabel.setText(serviceTypeCombo.getSelectionModel().getSelectedItem().toString());
                if (serviceTypeCombo.getValue().toString().equals("Food")) {
                    servicePriceLabel.setText("40");
                } else if (serviceTypeCombo.getValue().toString().equals("Beverage")) {
                    servicePriceLabel.setText("5");
                } else if (serviceTypeCombo.getValue().toString().equals("SPA")) {
                    servicePriceLabel.setText("60");
                } else if (serviceTypeCombo.getValue().toString().equals("Taxi")) {
                    servicePriceLabel.setText("30");
                } else {
                    servicePriceLabel.setText("100");
                }
            }
        });


    }
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
            stage.setTitle("Customer | Home");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

//string list used to format sql
    private String serviceDetails[] = new String[3];


    public void onOrderServiceButtonClick(ActionEvent actionEvent) {

//fill list of service details
        serviceDetails[0] = this.serviceTypeCombo.getValue().toString();
        serviceDetails[1] = "Pending";
        serviceDetails[2] = this.servicePriceLabel.getText();

        String currentBookingStatus = Connect.resultStringReturner(Connect.sqlExecute("select bookingStatus from bookings WHERE bookingId =" + bookingIdField.getText() + ";"));

//update sql if the customer has checked in else print no service at this time
        if (currentBookingStatus.equals("Checked in")) {
            String insertServiceSql = "insert into services values (DEFAULT, ?, ?, ?);";
            Connect.prepNewServiceUpdate(insertServiceSql, serviceDetails);

            //query to find current customer to associate booking with
            String findCurrentService = "select max(serviceId) from services;";

            String serviceBookingsDetails[] = new String[2];

//append to service bookings linking entity
            serviceBookingsDetails[0] = Connect.resultStringReturner(Connect.sqlExecute(findCurrentService));
            serviceBookingsDetails[1] = bookingIdField.getText();

            String insertServiceBookingsSql = "insert into serviceBookings values (?, ?);";
//update the sql
            Connect.prepNewServiceBookingsUpdate(insertServiceBookingsSql, serviceBookingsDetails);
            serviceConfirmationLabel.setText("Service order confirmed");

        } else {
            serviceConfirmationLabel.setText("Could not order service at this time");

        }


    }





}

