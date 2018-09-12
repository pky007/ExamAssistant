/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ManageQuestionPaneController implements Initializable {

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
    private void setAddQuestionPane(ActionEvent event) {
        try {
             root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewQuestion.fxml"));
            
            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAddViewQuestionPane(ActionEvent event) {
        try {
             root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("UpdateDeleteQuestionPane.fxml"));
            
            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void ManageQuestions(ActionEvent event) {
            try {
             root1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("DeleteAndUpdateQuestion.fxml"));
            
            root1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
