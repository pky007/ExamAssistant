package home;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import user.UserHomeController;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class LoginAndSignUpController implements Initializable {

    private AnchorPane loginRoot;
    @FXML
    private JFXTextField t1;
    @FXML
    private JFXPasswordField t2;
    AssistantData db;
    @FXML
    private JFXTextField t11;
    @FXML
    private JFXTextField t21;
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
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton signUpbtn;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private JFXProgressBar p1;
    @FXML
    private JFXProgressBar p2;
    @FXML
    private JFXProgressBar p3;
    @FXML
    private JFXProgressBar p4;
    @FXML
    private StackPane rootS;
    @FXML
    private JFXCheckBox adminChk;
    @FXML
    private JFXButton btn_log;
    @FXML
    private AnchorPane loginPane1;
    @FXML
    private JFXButton btn_log1;
    @FXML
    private AnchorPane signUpPane1;
    @FXML
    private JFXTextField t111;
    @FXML
    private JFXTextField t211;
    @FXML
    private JFXPasswordField t31;
    @FXML
    private JFXDatePicker t41;
    @FXML
    private JFXTextField t51;
    @FXML
    private JFXTextField t61;
    @FXML
    private JFXTextArea t71;
    @FXML
    private Label lab_password;
    private JFXTextField t22;
    @FXML
    private Label label_forgot;
    @FXML
    private JFXTextField tx1;
    @FXML
    private JFXTextField tx2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = AssistantData.getDataBaseHandler();
        } catch (Exception ex) {
            Logger.getLogger(LoginAndSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setSignUpPane(ActionEvent event) {

        signUpPane.setVisible(true);
        adminChk.setVisible(false);
        loginPane1.setVisible(false);
        label_forgot.setVisible(false);

    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException, InterruptedException {

        if (adminChk.isSelected()) {
            adminValid();

        } else {

            userValid();

        }

    }

    public void userValid() throws SQLException, IOException {

        String username = t1.getText().trim();
        String password = t2.getText().trim();

        if (username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Enter Data First!!!");
            alert.setTitle("Message");
            alert.showAndWait();
            return;

        }
        String query = "select * from UserSignUp where username = '" + username + "' ";

        ResultSet rs = db.getData(query);

        if (rs != null) {
            if (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {

                    UserHomeController.nameUser = username;
                    loadWindow("/user/UserHome.fxml", "ExamAssistant");
                    btn_log.getScene().getWindow().hide();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("UserName & Password is Not Correct!!!");
                    alert.setTitle("Message");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("UserName & Password is Not Correct!!!");
                alert.setTitle("Message");
                alert.showAndWait();

            }
        }
    }

    public void adminValid() throws SQLException, IOException {

        String name = t1.getText().trim();
        String password = t2.getText().trim();

        if (name.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Enter Data First!!!");
            alert.setTitle("Message");
            alert.showAndWait();
            return;

        }

        String qq = "select * from ExamAdmin";

        ResultSet rss = db.getData(qq);

        if (rss != null) {
            if (rss.next()) {
                if (rss.getString("name").equals(name) && rss.getString("password").equals(password)) {
                    loadWindow("/admin/AdminHome.fxml", "ExamAssistant");
                    btn_log.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Admin Name & Password is Not Correct!!!");
                    alert.setTitle("Message");
                    alert.showAndWait();

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Admin Name & Password is Not Correct!!!");
                alert.setTitle("Message");
                alert.showAndWait();

            }
        }

    }

    @FXML
    private void Cancel(ActionEvent event) {
        System.exit(0);
    }

    private void loadWindow(String str, String title) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(str));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    private void setSignUp(ActionEvent event) throws SQLException {

        String name = t11.getText().trim();
        String username = t21.getText().trim();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Account is Created!!!");
            alert.showAndWait();
        }

    }

    @FXML
    private void addNewCourse(ActionEvent event) {
    }

    @FXML
    private void setLoginPane(ActionEvent event) {
        loginPane.setVisible(true);
        signUpPane.setVisible(false);
        adminChk.setVisible(true);
        label_forgot.setVisible(true);
        loginPane1.setVisible(false);
    }

    @FXML
    private void getPassword(ActionEvent event) throws SQLException {

        String username = tx1.getText().trim();
        String email = tx2.getText().trim();

        if (username.equals("") || email.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Enter Fill All Data First!!!");
            alert.setTitle("Message");
            alert.showAndWait();
            return;

        }
        String query = "select * from UserSignUp where username = '" + username + "' ";

        ResultSet rs = db.getData(query);

        if (rs != null) {
            if (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("email").equals(email)) {
                    lab_password.setText("-> : " + rs.getString("password"));

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("userName & email  is Not Correct!!!");
                    alert.setTitle("Message");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("userName & email is Not Correct!!!");
                alert.setTitle("Message");
                alert.showAndWait();

            }
        }
    }

    @FXML
    private void setForgotPane(MouseEvent event) {
        loginPane.setVisible(false);
        signUpPane.setVisible(false);
        adminChk.setVisible(false);
        label_forgot.setVisible(false);
        loginPane1.setVisible(true);
    }

}
