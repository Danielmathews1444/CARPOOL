import java.sql.*;
public class User {

    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String gender;

    public User(String username, String password, String email, String mobileNumber, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }
    public User(String username, String email, String mobileNumber, String gender) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }
    public String getUsername() {
        return username;
    }

    public void save(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Users (username, password, email, mobile_number, gender) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        statement.setString(1, this.username);
        statement.setString(2, this.password);
        statement.setString(3, this.email);
        statement.setString(4, this.mobileNumber);
        statement.setString(5, this.gender);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.close();
        statement.close();
    }
    public static boolean checkUsernameExists(Connection connection, String username) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            return count > 0;
        }
    }
    public static boolean checkPassword(Connection connection, String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        return count > 0;
    }
    public static User profile(Connection connection, String username) throws SQLException {
        User user = null;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String mobileNumber = resultSet.getString("mobile_number");
            String gender = resultSet.getString("gender");
            user = new User(username, password, email, mobileNumber, gender);
        }
        resultSet.close();
        statement.close();
        return user;
    }
    public void display() {
        System.out.println("Username: " + this.username);
        System.out.println("Email: " + this.email);
        System.out.println("Mobile Number: " + this.mobileNumber);
        System.out.println("Gender: " + this.gender);
    }
    
}
