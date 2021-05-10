package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //create first screen of UI
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Hotel RMNY");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


        //run dbConnect method to connect to database
        if (!Connect.dbConnect()) {
            System.exit(0);

        }


        //create customers table
        Customer customer = new Customer();
        customer.createCustomersTable();

        //create and fill rooms table
        Room room = new Room();
        room.createRoomsTable();
        room.fillRooms();

        //create bookings table
        Booking booking = new Booking();
        booking.createBookingsTable();

        //create services table table
        Service service = new Service();
        service.createServicesTable();
        //create service bookings table
        service.createServiceBookingsTable();

        //create and fill managers table
        Manager manager = new Manager();
        manager.createManagersTable();
        manager.addNewManagers();






    }


}
