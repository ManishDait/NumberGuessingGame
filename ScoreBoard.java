import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ScoreBoard {
  private Connection connection = null;
  private Statement statement;
  private ResultSet result;

  /** Method to connect msqlDB where scores are saved.
   * 
   */
  public void connect() {
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Game?"
           + "user=username&password=pass");
      statement = connection.createStatement();
      System.out.println("Welcome to the Number Guessing Game.....\n\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Method to display the score available in msqlDb.
   * @throws SQLException
   * 
   */
  public void displayScore() throws SQLException {
    String sql = "select * from NumberGame";
    result = statement.executeQuery(sql);
    System.out.println("\nScore Board:");
    System.out.println("-------------------------------");
    System.out.println("Name\t\t |Score\t\t");
    System.out.println("-------------------------------");
    while (result.next()) {
      System.out.println(result.getString(1) + "\t\t  " + result.getString(2) + "\n");
    }
  }

  public void addScore(String name, int score) throws SQLException {
    String sql = "insert into NumberGame values('" + name + "'," + score + ")";
    statement.execute(sql);
  }
}
