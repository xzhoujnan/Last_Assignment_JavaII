import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/HeroGame"; // Thay HeroGame bằng tên cơ sở dữ liệu của bạn
    private static final String USER = "root"; // Tên người dùng
    private static final String PASSWORD = "root"; // Mật khẩu

    // Phương thức thêm người chơi
    public void insertPlayer(Player player) {
        String query = "INSERT INTO Player (NationalId, PlayerName, HighScore, Level) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, player.getNationalId());
            pstmt.setString(2, player.getPlayerName());
            pstmt.setInt(3, player.getHighScore());
            pstmt.setInt(4, player.getLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa người chơi
    public void deletePlayer(int playerId) {
        String query = "DELETE FROM Player WHERE PlayerId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, playerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức hiển thị tất cả người chơi
    public List<Player> displayAll() {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM Player";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("PlayerId"),
                        rs.getInt("NationalId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level")
                );
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    // Phương thức hiển thị người chơi theo tên
    public List<Player> displayAllByPlayerName(String playerName) {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM Player WHERE PlayerName LIKE ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + playerName + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Player player = new Player(
                            rs.getInt("PlayerId"),
                            rs.getInt("NationalId"),
                            rs.getString("PlayerName"),
                            rs.getInt("HighScore"),
                            rs.getInt("Level")
                    );
                    players.add(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    // Phương thức hiển thị top 10 người chơi
    public List<Player> displayTop10() {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM Player ORDER BY HighScore DESC LIMIT 10";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("PlayerId"),
                        rs.getInt("NationalId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level")
                );
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
