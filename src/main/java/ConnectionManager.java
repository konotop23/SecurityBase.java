import java.sql.*;

public final class ConnectionManager {
    private static final String PASSWORD_KEY = "db.password2";
    private static final String USERNAME_KEY = "db.username2";
    private static final String URL_KEY = "db.url2";

    public ConnectionManager() {
    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
