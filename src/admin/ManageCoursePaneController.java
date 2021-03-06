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

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class ManageCoursePaneController implements Initializable {

  
    @FXML
    private AnchorPane root1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void setAddCoursePane(ActionEvent event) {
       try {
             root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewCourseFXML.fxml"));
            
            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void setEditPane(ActionEvent event) {        
        try {
            root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("DeleteAndUpdateCourse.fxml"));

            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void setDeletePane(ActionEvent event) {
         
        try {
            root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("DeleteAndUpdateCourse.fxml"));

            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @FXML
    private void setAddViewCoursePane(ActionEvent event) {
          
        try {
            root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewCourse.fxml"));

            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
