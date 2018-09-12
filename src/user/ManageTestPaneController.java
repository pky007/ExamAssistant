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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class ManageTestPaneController implements Initializable {

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
    private void setAddTestPane(ActionEvent event) {

        try {
            root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("TestPane.fxml"));
            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void setAddViewTestPane(ActionEvent event) {
        try {
            root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ExamPane1.fxml"));

            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
