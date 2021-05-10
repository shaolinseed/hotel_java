package sample;

public class Room {
    private int roomNumber;
    private String roomType;
    private String status;
    private int pricePerNight;

    public Room(int roomNumber, String roomType, String status, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.pricePerNight = pricePerNight;
    }

    public Room() {

    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getStatus() {
        return status;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    private String[] details = new String[3];
//sql to create rooms table
    public void createRoomsTable() {
        String roomsTable = "CREATE TABLE IF NOT EXISTS rooms (roomNumber SERIAL PRIMARY KEY, roomType VARCHAR(70), status VARCHAR(20), pricePerNight INT);";
        Connect.sqlUpdate(roomsTable);
    }


    //function to populate rooms table if first time starting on db
    public void fillRooms() {
        if (Connect.checkTableEmpty("rooms")) {
            for (int i = 0; i < 20; i++) {
                details[0] = "Single";
                details[1] = "Available";
                details[2] = "50";
                String insertSql = "insert into rooms values (DEFAULT, ?, ?, ?);";

                Connect.fillRoomsTable(insertSql, details);

            }
            for (int i = 0; i < 30; i++) {
                details[0] = "Double";
                details[1] = "Available";
                details[2] = "80";
                String insertSql = "insert into rooms values (DEFAULT, ?, ?, ?);";

                Connect.fillRoomsTable(insertSql, details);

            }
            for (int i = 0; i < 10; i++) {
                details[0] = "Executive suite";
                details[1] = "Available";
                details[2] = "200";
                String insertSql = "insert into rooms values (DEFAULT, ?, ?, ?);";

                Connect.fillRoomsTable(insertSql, details);

            }
            for (int i = 0; i < 5; i++) {
                details[0] = "Presidential suite";
                details[1] = "Available";
                details[2] = "500";
                String insertSql = "insert into rooms values (DEFAULT, ?, ?, ?);";

                Connect.fillRoomsTable(insertSql, details);

            }


            System.out.println("Rooms have been setup...");
        } else {
            //when the rooms have already been set up
            System.out.println("Rooms have been recovered...");

        }


    }


    public String findRoomNumber(String roomType) {

        String roomNumberQuery = "SELECT MIN(rooms.roomNumber) FROM rooms WHERE roomType = '" + roomType + "' AND status = 'Available'";
        String foundRoom = Connect.resultStringReturner(Connect.sqlExecute(roomNumberQuery));
        String updateRoomStatus = "UPDATE rooms SET status = 'Occupied' WHERE roomNumber=" + foundRoom + ";";
        Connect.sqlUpdate(updateRoomStatus);

        return foundRoom;


    }
}


