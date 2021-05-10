package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class managerServicesController {

    @FXML
    private TableView<Service> servicesTableView;
    @FXML
    private TableColumn<Service, Integer> serviceId;
    @FXML
    private TableColumn<Service, String> type;
    @FXML
    private TableColumn<Service, String> status;
    @FXML
    private TableColumn<Service, Integer> price;
    @FXML
    private TextField serviceTypeField;
    @FXML
    private TextField serviceStatusField;
    @FXML
    private TextField servicePriceField;
    @FXML
    private Label serviceIdLabel;
    @FXML
    private TextField bookingStatusField;
    @FXML
    private Button updateServiceButton;
    @FXML
    private Button homeButton;


    public ObservableList<Service> getServiceList() throws SQLException {
        ObservableList<Service> serviceObservableList = FXCollections.observableArrayList();
        String myQuery = "select * from services;";
        ResultSet rs = Connect.sqlExecute(myQuery);
        Service service;
        while (rs.next()) {
            service = new Service(rs.getInt("serviceId"), rs.getString("type"), rs.getString("status"),
                    rs.getInt("price"));
            serviceObservableList.add(service);
        }
        return serviceObservableList;
    }

    public void listServices() throws SQLException {
        ObservableList<Service> list = getServiceList();
        serviceId.setCellValueFactory(new PropertyValueFactory<Service, Integer>("serviceId"));
        type.setCellValueFactory(new PropertyValueFactory<Service, String>("type"));
        status.setCellValueFactory(new PropertyValueFactory<Service, String>("status"));
        price.setCellValueFactory(new PropertyValueFactory<Service, Integer>("price"));
        servicesTableView.setItems(list);

    }

    public void showItem(MouseEvent mouseEvent) {
        Service service = servicesTableView.getSelectionModel().getSelectedItem();

        serviceIdLabel.setText(Integer.toString(service.getServiceId()));
        serviceTypeField.setText(service.getType());
        serviceStatusField.setText(service.getStatus());
        servicePriceField.setText(Integer.toString(service.getPrice()));


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

    public void onSaveButtonClick() {


    }


    public void onApproveButtonClick(ActionEvent actionEvent) {
    }


    public void onDeclineButtonClick(ActionEvent actionEvent) {
    }

//    public void onUpdatePriceButtonClick(ActionEvent actionEvent) {
//
//       // String updatePricePerNight = "UPDATE rooms SET pricePerNight = " + Integer.parseInt(setPriceField.toString()) + "WHERE roomType = " + roomTypeLabel.getText() + ";";
//        String updatePricePerNight = "UPDATE rooms SET pricePerNight=" + setPriceField.getText() +" WHERE roomType='" + roomTypeLabel.getText() + "'";
//        Connect.sqlUpdate(updatePricePerNight);
//    }
//
//    public void onUpdateRoomButtonClick(ActionEvent actionEvent) {
//        System.out.println(roomStatusField.getText());
//        System.out.println(roomNumberField.getText());
//        String updateAvailability = "UPDATE rooms SET status='" + roomStatusField.getText() + "' WHERE roomNumber=" +roomNumberField.getText();
//        Connect.sqlUpdate(updateAvailability);
//    }

    public void onUpdateServiceButtonClick(ActionEvent actionEvent) {
        String updateServiceStatus = "UPDATE services SET type = '" + serviceTypeField + "' WHERE serviceId=" + serviceIdLabel.getText() + ";";


        Connect.sqlUpdate(updateServiceStatus);
    }

    public void onFileCustomerServiceButton(ActionEvent actionEvent) {
        String updateServiceStatusFiled = "UPDATE services SET status = 'Filed' WHERE serviceId=" + serviceIdLabel.getText() + ";";
        Connect.sqlUpdate(updateServiceStatusFiled);

    }
}
