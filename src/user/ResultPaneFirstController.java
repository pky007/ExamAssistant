package user;

import admin.AdminHomeController;
import assistantdata.AssistantData;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class ResultPaneFirstController implements Initializable {

    @FXML
    private AnchorPane rootTest;
    @FXML
    private Pane selectCoursePane;
    @FXML
    private JFXComboBox<String> courseList;
    @FXML
    private Pane coursePane;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label label;
     AssistantData db;

   ObservableList<String> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            db = AssistantData.getDataBaseHandler();
        } catch (Exception ex) {
            Logger.getLogger(TestPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = FXCollections.observableArrayList();
           
        try {
            setListCourse();
        } catch (SQLException ex) {
            Logger.getLogger(ResultPaneFirstController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void setCourseList(ActionEvent event) {
    }

    @FXML
    private void setTest(ActionEvent event) {
        
    }

    private void setListCourse() throws SQLException {
        
       String query = "select courseName from "+UserHomeController.nameUser +"_userTable";
      ResultSet rs =  db.getData(query);
      if(rs!=null)
      {
          while(rs.next())
          {
              
             list.add(rs.getString("courseName"));       
              
          }
      }
      
       courseList.setItems(list);
    }

    @FXML
    private void setResultPane(ActionEvent event) {
        if(courseList.getValue()!=null){        
        ResultPaneController.course= courseList.getValue();
        
         try {
             rootTest.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ResultPane.fxml"));
            
            rootTest.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        }
    }
    
    
}
