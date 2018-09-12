
package user;

import admin.AdminHomeController;
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
public class UserPaneController implements Initializable {

    @FXML
    private AnchorPane rootUser;
    @FXML
    private Label labelTotalExam;
    AssistantData db;
    @FXML
    private Label labelTotalAttemptExam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            db = AssistantData.getDataBaseHandler();
            setCourseLabel();
            setAttemptExam();
            
            //db.showNotiFication("pk");
            System.out.println( UserHomeController.nameUser);
        } catch (Exception ex) {
            Logger.getLogger(UserPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    

    @FXML
    private void setResultPane(MouseEvent event) {
        try {
             rootUser.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ResultPaneFirst.fxml"));
            
            rootUser.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setInstruction(MouseEvent event) {
         try {
             rootUser.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("About.fxml"));
            
            rootUser.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void setStudenAttemptPane(MouseEvent event) {
        try {
             rootUser.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageTestPane.fxml"));
            
            rootUser.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setExamPane(MouseEvent event) {
        
        try {
             rootUser.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ExamPane1.fxml"));
            
            rootUser.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
    }

    @FXML
    private void setSettingPane(MouseEvent event) {
        
         try {
             rootUser.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("SettingsPane.fxml"));
            
            rootUser.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    private void setCourseLabel() throws SQLException {
     
          String query = "select count(*) from totalCourse";
      ResultSet rs =  db.getData(query);
      if(rs!=null)
      {
          if(rs.next())
          {
              labelTotalExam.setText(rs.getString(1));
          }
              
          }
          
    }

    private void setAttemptExam() throws SQLException {
           String query = "select count(*) from "+UserHomeController.nameUser+"_userTable";
      ResultSet rs =  db.getData(query);
      if(rs!=null)
      {
          if(rs.next())
          {
              labelTotalAttemptExam.setText(rs.getString(1));
              System.out.println("WORKKKKK");
          }
              
          }
        
        
    }

    @FXML
    private void setFeedBackPane(MouseEvent event) {
        
        
          try {
             rootUser.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
            
            rootUser.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
