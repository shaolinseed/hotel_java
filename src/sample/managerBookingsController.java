
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

public class managerBookingsController {
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
    private TextField paymentMethodField;
    @FXML
    private TextField bookingStatusField;
    @FXML
    private Button approveButton;
    @FXML
    private Button homeButton;






    private String bookingUpdate[] = new String[7];
    private String approvalUpdate[] = new String[1];



    public ObservableList<Booking> getBookingList() throws SQLException {
        ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList();
        String myQuery = "select * from bookings;";
        ResultSet rs= Connect.sqlExecute(myQuery);
        Booking booking;
        while (rs.next()){
            booking = new Booking(rs.getInt("bookingId"), rs.getInt("customerId"), rs.getInt("roomNumber"),
                    rs.getString("startDate"), rs.getString("endDate"),
                    rs.getString("paymentMethod"), rs.getString("bookingStatus"));
            bookingObservableList.add(booking);         }
        return bookingObservableList;
    }

    public void listBookings() throws SQLException {
        ObservableList<Booking> list = getBookingList();
        bookingId.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("bookingId"));
        customerId.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("customerId"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("roomNumber"));
        startDate.setCellValueFactory(new PropertyValueFactory<Booking,String>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Booking,String>("endDate"));
        paymentMethod.setCellValueFactory(new PropertyValueFactory<Booking,String>("paymentMethod"));
        bookingStatus.setCellValueFactory(new PropertyValueFactory<Booking,String>("bookingStatus"));
        bookingsTableView.setItems(list);
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



    public void onManageBookingsButtonClick(ActionEvent actionEvent) {
    }

    public void onManageRoomsButtonClick(ActionEvent actionEvent) {
    }

    public void showItem(MouseEvent mouseEvent) {
        Booking booking = bookingsTableView.getSelectionModel().getSelectedItem();

        bookingIdField.setText(Integer.toString(booking.getBookingId()));
        customerIdField.setText(Integer.toString(booking.getCustomerId()));
        roomNumberField.setText(Integer.toString(booking.getRoomNumber()));
        startDateField.setText(booking.getStartDate());
        endDateField.setText(booking.getEndDate());
        paymentMethodField.setText(booking.getPaymentMethod());
        bookingStatusField.setText(booking.getBookingStatus());



    }



    public void onApproveButtonClick(ActionEvent actionEvent) {
        Room room = new Room();
            String updateBookingStatus = "UPDATE bookings SET bookingStatus = 'Approved' WHERE bookingId=" + bookingIdField.getText() + ";";
            Connect.sqlUpdate(updateBookingStatus);



    }

    public void onDeclineButtonClick(ActionEvent actionEvent) {
        String updatesql = "UPDATE bookings SET bookingStatus = 'Declined' WHERE bookingId=" + bookingIdField.getText() + ";";
        Connect.sqlUpdate(updatesql);
    }

}


