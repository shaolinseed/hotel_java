package sample;

public class Payment {
    int paymentId;
    int amount;
    String paymentMethod;
    int cardNumber;
    String cardHolderName;
    String expiryDate;
    int securityCode;
    String status;
//create the payments table in postgres
    public void createPaymentsTable(){
        String paymentsTable = "CREATE TABLE IF NOT EXISTS rooms (paymentId SERIAL PRIMARY KEY, amount INT, paymentMethod VARCHAR(20), cardNumber INT," +
                "cardHolderName VARCHAR(150), expiryDate DATE, securityCode INT, pricePerNight INT);";
        Connect.sqlUpdate(paymentsTable);
    }



}
