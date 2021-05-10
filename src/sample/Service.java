package sample;

import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {
    private int serviceId;
    private String type;
    private String status;
    private int price;

    public Service(int serviceId, String type, String status, int price) {
        this.serviceId = serviceId;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public Service() {

    }

    public int getServiceId() {
        return serviceId;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }
//create the table in postgres
    public void createServicesTable() {
        String serviceTable = "CREATE TABLE IF NOT EXISTS services (serviceId SERIAL PRIMARY KEY, type VARCHAR(70), status VARCHAR(20), price INT);";
        Connect.sqlUpdate(serviceTable);

    }
//create the linking table
    public void createServiceBookingsTable() {
        String serviceBookingsTable = "CREATE TABLE IF NOT EXISTS serviceBookings(serviceId INTEGER NOT NULL,bookingId INTEGER NOT NULL,PRIMARY KEY(serviceId, bookingId));";
        Connect.sqlUpdate(serviceBookingsTable);
    }




}


