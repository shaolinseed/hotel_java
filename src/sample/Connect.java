package sample;

import java.sql.*;

public class Connect {

    //database details please add hotelRMNY to postgres databases
    private static String userName = "postgres";
    private static String password = "admin";
    private static Connection db;
    private static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";

    //function to connect to the database
    public static boolean dbConnect() {

        try {
            db = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("Database connected...");
            return true;
        } catch (Exception e) {
            System.out.println("Database connection failed...");
            return false;
        }
    }

    //peform sql query with no return
    public static void sqlUpdate(String statement) {
        try {
            Statement sqlStat = db.createStatement();
            sqlStat.executeUpdate(statement);
            System.out.println("SQL Statement Updated Successfully...");
        } catch (Exception e) {
            System.out.println("SQL Error...");
        }
    }

    //peform sql query that returns result set
    public static ResultSet sqlExecute(String statement) {
        try {
            Statement sqlStat = db.createStatement();
            ResultSet rs = sqlStat.executeQuery(statement);
            System.out.println("SQL Statement Updated Successfully...");
            return rs;

        } catch (Exception e) {
            System.out.println("SQL Error...");
        }
        return null;

    }


    //prepare update to add new booking
    public static void prepNewBookingUpdate(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setInt(1, Integer.parseInt(values[0]));
            preparedStatement.setInt(2, Integer.parseInt(values[1]));
            preparedStatement.setDate(3, Date.valueOf(values[2]));
            preparedStatement.setDate(4, Date.valueOf(values[3]));
            preparedStatement.setString(5, values[4]);
            preparedStatement.setString(6, values[5]);


            preparedStatement.executeUpdate();
            System.out.println("Entry Updated... ");

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }

    //prepare booking to be approved
    public static void prepBookingApproved(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setString(8, values[0]);


            preparedStatement.executeUpdate();
            System.out.println("Entry Updated... ");

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }

//prepare the customer update
    public static void prepCustomerUpdate(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setString(1, values[0]);
            preparedStatement.setString(2, values[1]);
            preparedStatement.setString(3, values[2]);
            preparedStatement.setString(4, values[3]);
            preparedStatement.setString(5, values[4]);
            preparedStatement.setString(6, values[5]);
            preparedStatement.setString(7, values[6]);
            preparedStatement.setString(8, values[7]);

            preparedStatement.executeUpdate();
            System.out.println("Entry Updated... ");

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }
//prepare sql to update servce
    public static void prepNewServiceUpdate(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setString(1, values[0]);
            preparedStatement.setString(2, values[1]);
            preparedStatement.setInt(3, Integer.parseInt(values[2]));


            preparedStatement.executeUpdate();
            System.out.println("Entry Updated... ");

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }

    public static void prepNewServiceBookingsUpdate(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setInt(1, Integer.parseInt(values[0]));
            preparedStatement.setInt(2, Integer.parseInt(values[1]));

            preparedStatement.executeUpdate();
            System.out.println("Entry Updated... ");

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }

    public static void fillRoomsTable(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setString(1, values[0]);
            preparedStatement.setString(2, values[1]);
            preparedStatement.setInt(3, Integer.parseInt(values[2]));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }

    public static void fillManagersTable(String pStatement, String[] values) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement(pStatement);
            preparedStatement.setInt(1, Integer.parseInt(values[0]));
            preparedStatement.setString(2, values[1]);
            preparedStatement.setString(3, values[2]);
            preparedStatement.setString(4, values[3]);
            preparedStatement.setString(5, values[4]);
            preparedStatement.executeUpdate();
            System.out.println("Entry Updated... ");

        } catch (Exception e) {
            System.out.println("SQL Error...");
            System.out.println(e);
        }
    }


    public static ResultSet prepExecute(String pStatment, String value) {
        try {
            PreparedStatement prepStat = db.prepareStatement(pStatment);
            prepStat.setString(1, value);
            ResultSet rs = prepStat.executeQuery();
            System.out.println("Query Executed...");
            return rs;
        } catch (Exception e) {
            System.out.println("SQL Error...");
        }
        return null;
    }

    public static void resultPrinter(ResultSet result) {
        try {
            while (result.next()) {
                String row = "";
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    row += result.getString(i) + ", ";
                }
                System.out.println(row);
            }
        } catch (Exception e) {
            System.out.println("Error In Printing");
        }
    }


    public static String resultStringReturner(ResultSet result) {
        String row = "";
        try {
            while (result.next()) {
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    row += result.getString(i);
                }

            }
        } catch (Exception e) {
            System.out.println("Error In Printing");
        }
        return row;

    }


    public static String resultStringReturner2(ResultSet result) {
        String row = "";
        try {
            while (result.next()) {
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    row += result.getString(i);
                    row += ",";
                }

            }
        } catch (Exception e) {
            System.out.println("Error In Printing");
        }
        return row;

    }

    public static Integer resultIntReturner(ResultSet result) {
        int row = 0;
        try {
            while (result.next()) {
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    row = result.getInt(i);
                }

            }
        } catch (Exception e) {
            System.out.println("Error In Printing");
        }
        System.out.println(row);
        return row;

    }


    public static boolean checkTableEmpty(String tableName) {
        String query = "SELECT COUNT(*) FROM   (SELECT 1  FROM  " + tableName + " LIMIT  1) s;";

        if (resultStringReturner(sqlExecute(query)).equals("0")) {
            return true;

        } else {
            return false;
        }


    }

//used to validate log in
    public static boolean validateLogin(String username, String password) {
        String checkLogin = "SELECT COUNT(*) From managers where username='" + username + "' AND password='" + password + "'";
        String checkLogin2 = "SELECT COUNT(*) From managers where username='Rahim' AND password='Maxo'";

        //if correct log in details found return true
        if (resultStringReturner(sqlExecute(checkLogin)).equals("1")) {
            return true;

        }
        //if correct login details not found return false
        else {
            return false;
        }


    }




}

