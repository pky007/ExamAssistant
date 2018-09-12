
package user;

import admin.AdminHomeController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class UserHomeController implements Initializable {
    
    public static String nameUser = "user";
    @FXML
    private StackPane root;
    @FXML
    private Label usernameLabel ;
    @FXML
    static public VBox sidePane;

    public String getUsernameLabel() {
        return usernameLabel.getText();
    }
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           usernameLabel.setText(nameUser);
                   setName();
                  
        
        try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("UserPane.fxml"));
         
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }    

    @FXML
    private void addTestPane(ActionEvent event) {
          
        try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ExamPane1.fxml"));
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void setTestPane(ActionEvent event) {
        
        
         try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageTestPane.fxml"));
           //  TestPaneStartController.setSideWindow(sidePane);
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }



    @FXML
    private void setHome(ActionEvent event) {
           
        try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("UserPane.fxml"));
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    

    private void setName() {
         usernameLabel.setText(nameUser);
        
        
    }
    
    public void sidePane(boolean str) {
        sidePane.setVisible(str);
        
        
    }

    @FXML
    private void setResultPane(ActionEvent event) {
        
         try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ResultPaneFirst.fxml"));
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void setSettingsPane(ActionEvent event) {
        
        
          try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("SettingsPane.fxml"));
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void setNoticePane(ActionEvent event) {
          
        
          try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void setAboutPane(ActionEvent event) {
          
        
          try {
             root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("About.fxml"));
            
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
