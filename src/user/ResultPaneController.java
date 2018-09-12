package user;

import assistantdata.AssistantData;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import static user.ResultPaneController.course;
import static user.TestPaneStartController.course;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class ResultPaneController implements Initializable {

    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;

    static String course;
    AssistantData db;

    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private Label l7;
    @FXML
    private Label l8;
    @FXML
    private Label l9;
    @FXML
    private Label name;
    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        l1.setText(course);

        try {
            db = AssistantData.getDataBaseHandler();
            setAll();
            setResult();
            setName();
        } catch (Exception ex) {
            Logger.getLogger(ResultPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCourse(String s) {
        this.course = s;

    }

    public void setAll() throws SQLException {
        String query = "select * from " + UserHomeController.nameUser + "_userTable where courseName = '" + course + "'";
        ResultSet rs = db.getData(query);
        if (rs != null) {
            if (rs.next()) {

                l3.setText(rs.getString("correct"));
                l4.setText(rs.getString("wrong"));
                l5.setText(rs.getString("skip"));
                l9.setText(rs.getString("status"));

            }
        }

        String qqq = "select * from totalCourse where courseName = '" + course + "'";
        String eachMarks = null;

        ResultSet rss = db.getData(qqq);
        if (rss != null) {
            if (rss.next()) {
                eachMarks = rss.getString("questionPerMarks");

            }

        }

        int total = Integer.parseInt(eachMarks) * Integer.parseInt(l3.getText());

        l8.setText(String.valueOf(total));

    }

    private void setResult() throws SQLException {

        String query2 = "select * from TotalCourse where CourseName = '" + course + "'";
        ResultSet rs2 = db.getData(query2);

        if (rs2 != null) {

            if (rs2.next()) {
                l2.setText(rs2.getString("totalQuestion"));
                l6.setText(rs2.getString("maxMarks"));
                l7.setText(rs2.getString("minMarks"));

            }
        }

    }

    private void setName() throws SQLException {

        /*String query = "create table UserSignUp("
                   + "username varchar(200) NOT NULL PRIMARY KEY,"
                  + "name varchar(200) NOT NULL,"                
                  + "password varchar(200) NOT NULL,"
                  + "dob varchar(50) NOT NULL,"
                  + "email varchar(200) NOT NULL,"
                  + "contact varchar(15),"
                  + "address varchar(500)"
                  + ")";*/
        String query = "select * from UserSignUp where username = '" + UserHomeController.nameUser + "'";

        ResultSet rs = db.getData(query);
        if (rs != null) {

            if (rs.next()) {
                name.setText("name: " + rs.getString("name"));
                email.setText("email: " + rs.getString("email"));

            }
        }

    }

}
