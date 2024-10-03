import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/HeroGame"; // Thay HeroGame bằng tên cơ sở dữ liệu của bạn
    private static final String USER = "root"; // Tên người dùng mặc định
    private static final String PASSWORD = "root"; // Mật khẩu mặc định

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Tải driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối với database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        // Gọi phương thức để thử kết nối
        getConnection();
    }
}
