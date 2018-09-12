/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class CorsePaneController implements Initializable {

    @FXML
    private Label l0 ;
    @FXML
      private Label l1;
    @FXML
     private Label l2;
    @FXML
     private Label l3;
    @FXML
   private Label l4;
   static String s="old" ;

    
   
    
   

   
   
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  // l1.setText(s);
         setLabel();
            
     
    }  
    
    public void setTT(String s)
    {
        this.s = s;
    }

    @FXML
    private void setCourseList(ActionEvent event) {
        
         l1.setText(s);
       System.out.println(s);
      
       
    }

    public static void setAllLabelText(String s) {
        
      
         //  l1.setText(s);
        
    }

    private void setLabel() {
        //l1.setText(s);
    }
    
 
    
}
