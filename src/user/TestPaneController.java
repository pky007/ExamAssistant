package user;

import admin.AdminHomeController;
import assistantdata.AssistantData;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static user.UserHomeController.nameUser;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class TestPaneController implements Initializable {
    AssistantData db;

   ObservableList<String> list;
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
    private JFXComboBox<String> courseList;
    @FXML
    private Label l5;
    @FXML
    private AnchorPane rootTest;
    @FXML
    private Pane selectCoursePane;
    @FXML
    private Label label;
    @FXML
    private JFXButton btn_strt;
    @FXML
    private Label labelTest;
    boolean flag = true;

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
            Logger.getLogger(TestPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void setListCourse() throws SQLException {
        
      
       String query = "select courseName from totalCourse";
      ResultSet rs =  db.getData(query);
      if(rs!=null)
      {
          while(rs.next())
          {
              //System.out.println(rs.getString("courseName"));
              
             list.add(rs.getString("courseName"));
            
              
              
          }
      }
      
       courseList.setItems(list);
    }
    @FXML
    private void setCourseList(ActionEvent event) throws SQLException {
        
        String name = courseList.getValue();
          String query = "select * from totalCourse where courseName = '"+name+"'";
      ResultSet rs =  db.getData(query);
      if(rs!=null)
      {
          if(rs.next())
          {
              
            l1.setText("Subject Name : "+rs.getString("courseName"));
             l2.setText("Total Question : "+rs.getString("totalQuestion"));
              l3.setText("Max Marks : "+rs.getString("maxMarks"));
               l4.setText("Min Marks : "+rs.getString("minMarks"));
               l5.setText("Total Time : "+rs.getString("duration"));
            
             coursePane.setVisible(true); 
             selectCoursePane.setVisible(false);
             label.setText("Start Test");
             
            
              
          }
          
      }
      
      
     
     if(ValidTest(name))
     {
         flag=true;
         
         
     }
     else
         flag = false;
        
        
        
        
    }

    @FXML
    private void setTest(ActionEvent event) throws IOException {
        if(flag){
       new TestPaneStartController().setCourse(courseList.getValue());
       TestPaneStartController.username =  UserHomeController.nameUser;
        
       loadWindow("TestPaneStart.fxml", "ExamAssistant");
       btn_strt.getScene().getWindow().hide();;
        }
        else
        {
            return;
        }
       
       
        
    }
    
    private void loadWindow(String str, String title) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(str));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();

    }

    private boolean ValidTest(String name) throws SQLException {
        
      String que = "select courseName from  "+UserHomeController.nameUser+"_userTable";
      
      System.out.println(que);
      
      ResultSet ss = db.getData(que);
      
      if(ss!=null)
      {
          while(ss.next())
          {
              if(ss.getString("courseName").equalsIgnoreCase(name)){
                  
                  labelTest.setText("This exam you finished ");
                  return false;
              }
              else
              {
                  labelTest.setText("");
              }
              
          }
          
      }
      
      return true;
        
    }

    
}
