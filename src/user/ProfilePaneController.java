package user;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class ProfilePaneController implements Initializable {

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
            Logger.getLogger(ProfilePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            fillAll();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setUpdate(ActionEvent event) {

        String name = t1.getText().trim();

        String password = t3.getText().trim();

        String dob = t4.getValue().toString().trim();
        String email = t5.getText().trim();
        String contact = t6.getText().trim();
        String address = t7.getText().trim();

        if (name.equals("") || password.equals("") || dob.equals("") || email.equals("") || contact.equals("") || address.equals("")) {
            return;
        } else {

            String q = " update UserSignUp set name = '" + name + "', password = '" + password + "',"
                    + " dob = '" + dob + "', email = '" + email + "',"
                    + " contact = '" + contact + "', address = '" + address + "' ";

            if (db.isExeQuery(q)) {
                System.out.println("Data Updated");
            } else {
                System.out.println("Data Not  Updated");
            }

        }

    }

    @FXML
    private void isEmpty(ActionEvent event) {
    }

    private void fillAll() throws SQLException {

        String q = "select * from UserSignUp where username = '" + UserHomeController.nameUser + "'";

        ResultSet rs = db.getData(q);

        if (rs != null) {

            while (rs.next()) {

                t2.setText(rs.getString("username"));
                t1.setText(rs.getString("name"));
                t3.setText(rs.getString("password"));
                t4.setValue(LocalDate.parse(rs.getString("dob")));
                t5.setText(rs.getString("email"));
                t6.setText(rs.getString("contact"));
                t7.setText(rs.getString("address"));

            }
        }

    }

}
