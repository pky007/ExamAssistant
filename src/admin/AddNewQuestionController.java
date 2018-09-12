package admin;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class AddNewQuestionController implements Initializable {
      AssistantData db;
    @FXML
    private JFXComboBox<String> course_list;
    ObservableList<String> list;
    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private Pane selectRoot;
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
    private Label l6;
    @FXML
    private Label l5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              db =   AssistantData.getDataBaseHandler();
              list = FXCollections.observableArrayList();
           ; 
                addListOfCourse();
          } catch (Exception ex) {
              Logger.getLogger(AddNewQuestionController.class.getName()).log(Level.SEVERE, null, ex);
          }
      
    }    

    private void addListOfCourse() throws SQLException {
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
      
       course_list.setItems(list);
    }

    @FXML
    private void addQuestion(ActionEvent event) throws SQLException {
        
        
        
        
        String name = course_list.getValue();
        String query = "select * from totalCourse where courseName = '" + name + "'";
        ResultSet rs = db.getData(query);
        if (rs != null) {
            if (rs.next()) {
                l1.setText("Subject Name - " + rs.getString("courseName"));
                l2.setText("Total Question - " + rs.getString("totalQuestion"));
                l3.setText("Max Marks - " + rs.getString("maxMarks"));
                l4.setText("Min Marks - " + rs.getString("minMarks"));
                l5.setText("Each question - " + rs.getString("questionPerMarks") + " marks");
                l6.setText("Total Time (H:M) - " + rs.getString("duration"));
                selectRoot.setVisible(false);
                coursePane.setVisible(true);
                course_list.setVisible(false);
               // back.setVisible(true);

            }
        } 
        
    } 

    @FXML
    private void setAddQuestionPane(ActionEvent event) {
           QuestionFXMLController q = new QuestionFXMLController();
        q.setCourse(course_list.getValue());
       
        try {
             anchorpane1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("QuestionFXML.fxml"));
            
            anchorpane1.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelCourse(ActionEvent event) {
        course_list.setValue(null);
    }

    @FXML
    private void setBack(ActionEvent event) {
       
                coursePane.setVisible(false);
                 selectRoot.setVisible(true);
                  course_list.setVisible(true);
    }

   
    
}
