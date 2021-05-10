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

public class customerNewBookingController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    ComboBox roomTypeCombo;
    @FXML
    ComboBox paymentMethodCombo;
    @FXML
    Label checkInDateLabel;
    @FXML
    Label checkOutDateLabel;
    @FXML
    Label roomTypeLabel;
    @FXML
    Label paymentMethodLabel;
    @FXML
    Label numberOfNightsLabel;
    @FXML
    DatePicker checkInDate;
    @FXML
    DatePicker checkOutDate;
    @FXML
    Button homeButton;
    @FXML
    Button proceedButton;
    @FXML
    Button backButton;
    @FXML
    Button forwardButton;


    //combo box lists
    String[] roomTypes = new String[]{"Single", "Double", "Executive suite", "Presidential suite"};
    String[] paymentMethods = new String[]{"Visa", "Mastercard", "Cash"};
//when page loaded do these
    public void initialize() {
        //reset roomtypes combo box
        roomTypeCombo.getItems().clear();
        roomTypeCombo.getItems().addAll(roomTypes);
//        reset payment methods combo box
        paymentMethodCombo.getItems().clear();
        paymentMethodCombo.getItems().addAll(paymentMethods);

        //room typecombo box listener
        roomTypeCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                roomTypeLabel.setText(roomTypeCombo.getSelectionModel().getSelectedItem().toString());
            }
        });
        //payment method combo box listener
        paymentMethodCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                paymentMethodLabel.setText(paymentMethodCombo.getSelectionModel().getSelectedItem().toString());
            }
        });


        //check in date listener
        checkInDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                checkInDateLabel.setText(checkInDate.getValue().toString());
            }
        });
        //check out date listener
        checkOutDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                checkOutDateLabel.setText(checkOutDate.getValue().toString());

                //calculate number of nights staying
                try {
                    Date dateIn = dateFormat.parse(checkInDate.getValue().toString());
                    Date dateOut = dateFormat.parse(checkOutDate.getValue().toString());
                    long dateDifference = dateOut.getTime() - dateIn.getTime();
                    int totalNights = (int) dateDifference / (1000 * 60 * 60 * 24);
                    numberOfNightsLabel.setText(Float.toString(totalNights));


                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });


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


    //list of strings to store the booking details that will be used in Connect prepupdate function
    private String bookingDetails[] = new String[6];

//when proceed add data to the bookings data table
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
//loop through fields
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
