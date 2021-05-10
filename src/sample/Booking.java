package sample;



public class Booking {
    private int bookingId;
    private int customerId;
    private int roomNumber;
    private int managerId;
    private String startDate;
    private String endDate;
    private String paymentMethod;
    private String bookingStatus;


    //constructor for managers booking view
    public Booking(int bookingId, int customerId, int roomNumber, String startDate, String endDate, String paymentMethod, String bookingStatus) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentMethod = paymentMethod;
        this.bookingStatus = bookingStatus;

    }

    //constructor for customer booking view
    public Booking(int bookingId, String startDate, String endDate, String paymentMethod, String bookingStatus) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentMethod = paymentMethod;
        this.bookingStatus = bookingStatus;
    }

    public Booking() {

    }


    public int getBookingId() {
        return bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void createBookingsTable() {
        String bookingsTable = "CREATE TABLE IF NOT EXISTS bookings (bookingId SERIAL PRIMARY KEY, customerId INTEGER REFERENCES customers (customerId), roomNumber INTEGER REFERENCES rooms (roomNumber)," +
                " startDate date, endDate date, paymentMethod VARCHAR(20), bookingStatus VARCHAR(20));";
        Connect.sqlUpdate(bookingsTable);
    }

}
