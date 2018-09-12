
package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class AdminHomeController implements Initializable {

    @FXML
    public StackPane root;
    @FXML
    private StackPane parentRoot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminPane.fxml"));

            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void addManageCourse(ActionEvent event) {

        try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageCoursePane.fxml"));
            
         
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    


    @FXML
    private void addQuestionPane(ActionEvent event) {

        try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageQuestionPane.fxml"));

            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    public void  setDialog(String s)
    {
      /*  
          BoxBlur blur =  new BoxBlur(3, 3, 3);
             JFXDialogLayout layout = new JFXDialogLayout() ;   
             JFXButton button = new JFXButton("OK!!!");
             JFXDialog dialog = new JFXDialog(root,layout,JFXDialog.DialogTransition.TOP);
              button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
               //    root.setEffect(null);
                  dialog.close();
                  
              });
              
              dialog.setOnDialogClosed((JFXDialogEvent event1) ->{                  
               //   root.setEffect(null);                 
                  
              });
              
              
              layout.setHeading(new Text(s));
              layout.setActions(button);
              dialog.setDialogContainer(root);
              dialog.show();
              
             // root.setEffect(blur);
             
            */
        
    }

    @FXML
    private void setAdminPanel(ActionEvent event) {  
        
        try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminPane.fxml"));

            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
    }
    
    
    public void setPane(String pan)
    {
        
        try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(pan));

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
    private void setStudentPane(ActionEvent event) {
        
         try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("StudentPane.fxml"));

            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void setNoticeBoardPane(ActionEvent event) {       
         
         try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/user/Feedback.fxml"));

            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        
    }

    @FXML
    private void setAboutPane(ActionEvent event) {
         
         try {
            root.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/user/About.fxml"));

            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
