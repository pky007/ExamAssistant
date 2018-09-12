package admin;

import assistantdata.AssistantData;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class AdminPaneController implements Initializable {

    AssistantData db;

    @FXML
    private AnchorPane rootAdminPane;
    @FXML
    private Label tStudent;
    @FXML
    private Label tCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = AssistantData.getDataBaseHandler();
            setCourseLabel();
            setStudentLabel();
        } catch (SQLException ex) {
            Logger.getLogger(AdminPaneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setStudentPane(MouseEvent event) {
         try {
            rootAdminPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("StudentPane.fxml"));

            rootAdminPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setCoursePane(MouseEvent event) {

        try {
            rootAdminPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageCoursePane.fxml"));

            rootAdminPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setResultPane(MouseEvent event) {
    }

    @FXML
    private void setSettingsPane(MouseEvent event) {
        
        try {
            rootAdminPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("SettingsPane.fxml"));

            rootAdminPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void setFeedBackPane(MouseEvent event) {
        try {
            rootAdminPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/user/Feedback.fxml"));

            rootAdminPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setInstructionPane(MouseEvent event) {

        try {
            rootAdminPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/user/About.fxml"));

            rootAdminPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setCourseLabel() throws SQLException {

        String query = "select count(*) from totalCourse";
        ResultSet rs = db.getData(query);
        if (rs != null) {
            if (rs.next()) {
                tCourse.setText(rs.getString(1));
            }

        }
    }

    private void setStudentLabel() throws SQLException {

        String query = "select count(*) from UserSignUp";
        ResultSet rs = db.getData(query);
        if (rs != null) {
            if (rs.next()) {
                tStudent.setText(rs.getString(1));
            }

        }

    }

    @FXML
    private void setManageQuestion(MouseEvent event) {

        try {
            rootAdminPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageQuestionPane.fxml"));

            rootAdminPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
