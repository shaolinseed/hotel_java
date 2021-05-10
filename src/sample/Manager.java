package sample;


public class Manager {
    private int managerId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    private String managerDetails[] = new String[8];

    public void createManagersTable(){
        String managersTable = "CREATE TABLE IF NOT EXISTS managers (managerId INTEGER PRIMARY KEY, userName VARCHAR(20), password VARCHAR(50), firstName VARCHAR(50), lastName VARCHAR(50));";
        Connect.sqlUpdate(managersTable);
    }


    public void addNewManagers(){



//set managers details
        if(Connect.checkTableEmpty("managers")) {
            managerDetails[0] = "1";
            managerDetails[1] = "timothy";
            managerDetails[2] = "admin";
            managerDetails[3] = "Timothy";
            managerDetails[4] = "Rodger";
            String insertSql = "insert into managers values (?, ?, ?, ?, ?);";
//add manager using connect class functio
            Connect.fillManagersTable(insertSql, managerDetails);

            managerDetails[0] = "2";
            managerDetails[1] = "spencer";
            managerDetails[2] = "admin";
            managerDetails[3] = "Spencer";
            managerDetails[4] = "Williams";
            Connect.fillManagersTable(insertSql, managerDetails);
//notify manager status on start up
            System.out.println("Managers have been setup...");
        } else{
            System.out.println("Managers have been recovered...");
        }
    }








}
