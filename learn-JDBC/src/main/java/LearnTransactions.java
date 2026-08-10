import java.sql.*;

public class LearnTransactions {
  private static final String URL = "jdbc:postgresql://localhost:5432/demo_db";
  private static final String USER = "postgres";
  private static final String PASSWORD = "postgres";

  public static void main(String[] args) {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
      System.out.println("Connected Successfully...!");

//      TURNED OFF AUTO COMMIT == NO AUTO SAVE
      conn.setAutoCommit(false);

      try {
        // orders, order_items
        //INSERT INTO ORDER
        int orderId = insertOrder(conn, 101, "Alice01", 2000.0);

        //INSERT INTO ORDER ITEM
        insertOrderItems(conn, orderId, "Laptop01", 1, 2000.00);

        // MANUAL COMMIT
        conn.commit();
        System.out.println("Transaction commited successfully");
      } catch (Exception e) {
        e.printStackTrace();
        conn.rollback();
        System.out.println("Operation wasn't successful. SO, ROLLBACK..!");
      } finally {
        conn.setAutoCommit(true);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void insertOrderItems(Connection conn, int orderId, String productName, int quantity, double price) {
    String sql = "INSERT INTO order_items (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?);";
    try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, orderId);
      pstmt.setString(2, productName);
      pstmt.setInt(3, quantity);
      pstmt.setDouble(4, price);

//      int x = 10/0; // manually creating exception for transaction testing
      int rows = pstmt.executeUpdate();
      System.out.println("Inserted into orders_items: " + rows);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static int insertOrder(Connection conn, int customerId, String customerName, double price) {
    String sql = "INSERT INTO orders (user_id, customer_name, total_amount) VALUES (?, ?, ?);";
    try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, customerId);
      pstmt.setString(2, customerName);
      pstmt.setDouble(3, price);

      int rows = pstmt.executeUpdate();
      System.out.println("Inserted into orders: " + rows);

      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        if (rs.next()) {
          int orderId = rs.getInt(1);
          System.out.println("ORDER ID: " + orderId);
          return orderId;
        } else {
          throw new SQLException("Order id not generated");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customerId;
  }
}
