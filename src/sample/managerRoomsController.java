package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class managerRoomsController {

    @FXML
    private TableView<Room> roomsTableView;
    @FXML
    private TableColumn<Room, Integer> roomNumber;
    @FXML
    private TableColumn<Room, String> roomType;
    @FXML
    private TableColumn<Room, String> status;
    @FXML
    private TableColumn<Room, Integer> pricePerNight;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private TextField roomTypeField;
    @FXML
    private TextField roomStatusField;
    @FXML
    private Label pricePerNightLabel;
    @FXML
    private TextField setPriceField;
    @FXML
    private Label roomTypeLabel;
    @FXML
    private Label roomTypeLabel0;


    @FXML
    private Button approveButton;
    @FXML
    private Button homeButton;
    @FXML
    private ComboBox roomTypeCombo;


//combo box info loaded
    String[] roomTypes = new String[]{"Single", "Double", "Executive suite", "Presidential suite"};

    public void initialize(){
        roomTypeCombo.getItems().clear();
        roomTypeCombo.getItems().addAll(roomTypes);

        //room typecombo box listener
        roomTypeCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                roomTypeLabel.setText(roomTypeCombo.getSelectionModel().getSelectedItem().toString());
            }
        });


    }


    public ObservableList<Room> getRoomlist() throws SQLException {
        ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
        String myQuery = "select * from rooms;";
        ResultSet rs= Connect.sqlExecute(myQuery);
        Room room;
        while (rs.next()){
            room = new Room(rs.getInt("roomNumber"), rs.getString("roomType"), rs.getString("status"),
                    rs.getInt("pricePerNight"));
            roomObservableList.add(room);         }
        return roomObservableList;
    }

    public void listRooms() throws SQLException {
        ObservableList<Room> list = getRoomlist();
        roomNumber.setCellValueFactory(new PropertyValueFactory<Room,Integer>("roomNumber"));
        roomType.setCellValueFactory(new PropertyValueFactory<Room,String>("roomType"));
        status.setCellValueFactory(new PropertyValueFactory<Room,String>("status"));
        pricePerNight.setCellValueFactory(new PropertyValueFactory<Room,Integer>("pricePerNight"));
        roomsTableView.setItems(list);

    }

    public void showItem(MouseEvent mouseEvent) {
        Room room = roomsTableView.getSelectionModel().getSelectedItem();

        roomNumberLabel.setText(Integer.toString(room.getRoomNumber()));
        roomTypeLabel0.setText(room.getRoomType());
        roomStatusField.setText(room.getStatus());
        pricePerNightLabel.setText(Integer.toString(room.getPricePerNight()));





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



    }



    public void onApproveButtonClick(ActionEvent actionEvent) {
    }



    public void onDeclineButtonClick(ActionEvent actionEvent) {
    }

    public void onUpdatePriceButtonClick(ActionEvent actionEvent) {

       // String updatePricePerNight = "UPDATE rooms SET pricePerNight = " + Integer.parseInt(setPriceField.toString()) + "WHERE roomType = " + roomTypeLabel.getText() + ";";
        String updatePricePerNight = "UPDATE rooms SET pricePerNight=" + setPriceField.getText() +" WHERE roomType='" + roomTypeLabel.getText() + "'";
        Connect.sqlUpdate(updatePricePerNight);
    }

    public void onUpdateRoomButtonClick(ActionEvent actionEvent) {
        System.out.println(roomStatusField.getText());
        System.out.println(roomNumberLabel.getText());
        String updateAvailability = "UPDATE rooms SET status='" + roomStatusField.getText() + "' WHERE roomNumber=" +roomNumberLabel.getText();
        Connect.sqlUpdate(updateAvailability);
    }
}
