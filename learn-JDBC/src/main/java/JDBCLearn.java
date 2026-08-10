import java.sql.*;

public class JDBCLearn {
  private static final String URL = "jdbc:postgresql://localhost:5432/demo_db";
  private static final String USER = "postgres";
  private static final String PASSWORD = "postgres";

  public static void main(String[] args) {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
      System.out.println("Connected Successfully...!");

      insertStudent(conn, "Alice", "Alice@gmail.com");
//      insertStudent(conn, "java'); DROP TABLE students; --", "hack@Gmail.com"); sql injection attacks
      updateStudent(conn, 2, "Harry", "harry@codeWithHarry.com");
      retrieveStudent(conn);
//      deleteStudent(conn, 1);


    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void insertStudent(Connection conn, String name, String email) {
    String sql = "INSERT INTO students (name, email) VALUES ('" + name + "', '"+email+"')";
    try(Statement stmt = conn.createStatement()) {
      int rows = stmt.executeUpdate(sql);
      System.out.println("Inserted Rows: "+rows);
    } catch (SQLException e) {
      e.printStackTrace();  
    }
  }

  private static void retrieveStudent(Connection conn) {
    String sql = "SELECT * FROM students";
    try(Statement stmt = conn.createStatement()) {
      ResultSet resultSet = stmt.executeQuery(sql);
      System.out.println("Student List: ");
      while (resultSet.next()){
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        System.out.println(id+" : "+name+" : "+email); 
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void updateStudent(Connection conn, int id, String name, String email) {
//    String sql = "UPDATE students SET name = '" + name+ "', email = '"+ email + "' WHERE id = "+id;
    String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
    try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, name);
      pstmt.setString(2, email);
      pstmt.setInt(3, id);

      int rows = pstmt.executeUpdate();
      System.out.println("Updated Rows: "+rows);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void deleteStudent(Connection conn, int id) {
    String sql = "DELETE  FROM students WHERE id = "+id;
    try(Statement stmt = conn.createStatement()) {
      int rows = stmt.executeUpdate(sql);
      System.out.println("Deleted Rows: "+rows);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}



/*
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(URL, USER, PASSWORD);
      System.out.println("Connected Successfully...!");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
        System.out.println("Connection closed");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
*/
