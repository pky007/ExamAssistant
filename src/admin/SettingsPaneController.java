/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class SettingsPaneController implements Initializable {

    @FXML
    private AnchorPane s_root;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setProfilePane(MouseEvent event) throws IOException {
        
          s_root.getChildren().clear();
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePane.fxml"));
        
        s_root.getChildren().addAll(pane);       
        
        
    }
    
}
