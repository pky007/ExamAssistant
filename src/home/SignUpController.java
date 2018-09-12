package home;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class SignUpController implements Initializable {

    @FXML
    private JFXTextField t1;
    @FXML
    private JFXTextField t2;
    @FXML
    private JFXPasswordField t3;
    @FXML
    private JFXDatePicker t4;
    @FXML
    private JFXTextField t5;
    @FXML
    private JFXTextField t6;
    @FXML
    private JFXTextArea t7;
    AssistantData db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = AssistantData.getDataBaseHandler();
        } catch (Exception ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setSignUp(ActionEvent event) throws SQLException {

        String name = t1.getText().trim();
        String username = t2.getText().trim();
        String password = t3.getText().trim();
        String dob = t4.getValue().toString().trim();
        String email = t5.getText().trim();
        String contact = t6.getText().trim();
        String address = t7.getText().trim();

        String query = "insert into UserSignUp values ('" + username + "',"
                + "'" + name + "',"
                + "'" + password + "',"
                + "'" + dob + "',"
                + "'" + email + "',"
                + "'" + contact + "',"
                + "'" + address + "'"
                + ")";

        if (db.isExeQuery(query)) {
            db.isCreateStudentTable(username);
            System.out.println("DATA INserted");
        }

    }

    @FXML
    private void addNewCourse(ActionEvent event) throws SQLException {

    }

}
