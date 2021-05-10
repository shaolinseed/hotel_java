
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class customerBookingsController {
    @FXML
    private TableView<Booking> bookingsTableView;
    @FXML
    private TableColumn<Booking, Integer> bookingId;
    @FXML
    private TableColumn<Booking, Integer> customerId;
    @FXML
    private TableColumn<Booking, Integer> roomNumber;
    @FXML
    private TableColumn<Booking, String> startDate;
    @FXML
    private TableColumn<Booking, String> endDate;
    @FXML
    private TableColumn<Booking, String> roomType;
    @FXML
    private TableColumn<Booking, String> paymentMethod;
    @FXML
    private TableColumn<Booking, String> bookingStatus;
    @FXML
    private TextField bookingIdField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;
    @FXML
    private TextField roomTypeField;
    @FXML
    private TextField paymentMethodField;
    @FXML
    private TextField bookingStatusField;
    @FXML
    private Button searchButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField thisBookingField;
    @FXML
    private Button updateBookingButton;







    private String bookingUpdate[] = new String[8];
    private String approvalUpdate[] = new String[1];



    public ObservableList<Booking> getBookinglist() throws SQLException {
        ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList();
        String myQuery = "select * from bookings where bookingId =" + thisBookingField.getText();
        ResultSet rs= Connect.sqlExecute(myQuery);
        Booking booking;
        while (rs.next()){
            booking = new Booking(rs.getInt("bookingId"), rs.getString("startDate"), rs.getString("endDate"),
                    rs.getString("paymentMethod"), rs.getString("bookingStatus"));
            bookingObservableList.add(booking);         }
        return bookingObservableList;
    }

    public void listBookings() throws SQLException {

        if (!thisBookingField.getText().isEmpty()) {
            ObservableList<Booking> list = getBookinglist();
            bookingId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingId"));
            startDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("endDate"));
            paymentMethod.setCellValueFactory(new PropertyValueFactory<Booking, String>("paymentMethod"));
            bookingStatus.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingStatus"));
            bookingsTableView.setItems(list);
        }
    }









    public void onHomeButtonClick(ActionEvent actionEvent) {
        try {
            //close current window
            Stage stageclose = (Stage) homeButton.getScene().getWindow();
            stageclose.close();
            //open home window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerHome.fxml"));
            Parent secondWin = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manage Bookings");
            stage.setScene(new Scene(secondWin));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void onSaveButtonClick(){

        bookingUpdate[0]=this.bookingIdField.getText();
        bookingUpdate[3]=this.startDateField.getText();
        bookingUpdate[4]=this.endDateField.getText();
        bookingUpdate[6]=this.paymentMethodField.getText();
        bookingUpdate[7]=this.bookingStatusField.getText();



        approvalUpdate[0] = this.bookingStatusField.getText();
        System.out.println(approvalUpdate);

//        saveLabel.setText("DataBase Has Been Updated.");

        String insertSql = "insert into bookings values (?, ?, ?, ?, ?, ?, ?, ?);";
        String updatesql = "UPDATE bookings SET bookingStatus = 'Approved' WHERE bookingId=1;";
        //String insertSql = "";
        Connect.prepBookingApproved(updatesql, approvalUpdate);
    }

    public void onManageBookingsButtonClick(ActionEvent actionEvent) {
    }

    public void onManageRoomsButtonClick(ActionEvent actionEvent) {
    }

    public void showItem(MouseEvent mouseEvent) {
        Booking booking = bookingsTableView.getSelectionModel().getSelectedItem();

        thisBookingField.setText(Integer.toString(booking.getBookingId()));
        startDateField.setText(booking.getStartDate());
        endDateField.setText(booking.getEndDate());
        paymentMethodField.setText(booking.getPaymentMethod());




    }








    public void onCancelButtonClick(ActionEvent actionEvent) {
        String updatesql = "UPDATE bookings SET bookingStatus = 'Cancelled' WHERE bookingId=" + thisBookingField.getText() + ";";
        Connect.sqlUpdate(updatesql);

    }

    //update booking changes done by customer
    public void onUpdateButtonClick(ActionEvent actionEvent) {
        String updateStartDateSql = "UPDATE bookings SET startDate = '" +startDateField.getText()+ "' WHERE bookingId=" + thisBookingField.getText() + ";";
        Connect.sqlUpdate(updateStartDateSql);
        String updateEndDateSql = "UPDATE bookings SET endDate = '" +endDateField.getText()+ "' WHERE bookingId=" + thisBookingField.getText() + ";";
        Connect.sqlUpdate(updateEndDateSql);
        String updatePaymentMethod = "UPDATE bookings SET paymentMethod = '" +paymentMethodField.getText()+ "' WHERE bookingId=" + thisBookingField.getText() + ";";
        Connect.sqlUpdate(updatePaymentMethod);


    }
}


