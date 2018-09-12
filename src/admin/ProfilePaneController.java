/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class ProfilePaneController implements Initializable {

    @FXML
    private AnchorPane s_root;
    @FXML
    private Label label;
    @FXML
    private Pane pane1;   
    @FXML
    private Pane pane2;
    @FXML
    private JFXPasswordField t22;
    
    AssistantData db;
    @FXML
    private JFXPasswordField t1;
    @FXML
    private JFXTextField t11;
    @FXML
    private JFXTextField t33;

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
    }    

    @FXML
    private void chkPassword(ActionEvent event) throws SQLException {
        
        String password = t1.getText().trim();
        String q = "select * from ExamAdmin";
        ResultSet rs = db.getData(q);
        if(password.equals(""))
        {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Please Enter Password!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                return;
            
            
        }
        else
        {
            
            
        if(rs!=null)
        {
            if(rs.next())
            {
                if(password.equals(rs.getString("password")))
                {
                    t11.setText(rs.getString("name"));
                     t22.setText(rs.getString("password"));
                      t33.setText(rs.getString("email"));
                    
                    pane1.setVisible(false);
                    pane2.setVisible(true);
                    
                }
                else
                {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Soo Sorry Password is not Correct!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                return;
                    
                }
            }
        }
              
        }
        
        
        
        
    }

    @FXML
    private void updateProfile(ActionEvent event) {
        
        String name = t11.getText().trim();
         String password = t22.getText().trim();
          String email = t33.getText().trim();
          
          if(name.equals("") || password.equals("") || password.equals(""))
          {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Please Fill All the Fields!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                return;  
          }
         else
          { 
              String q = "update ExamAdmin set name = '"+name+"', password ='"+password+"', email ='"+email+"'";
              if(db.isExeQuery(q))
              {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Data Updated!!!");
                alert.setTitle("Message");
                alert.showAndWait();
               
              }
              else
              {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Data NOT Updated!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                  
              }
                      
           }
        
        
        
        
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
