/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static admin.QuestionFXMLController.course;
import assistantdata.AssistantData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class QuestionEditPaneController implements Initializable {

    AssistantData db;

    ObservableList<String> list;

    @FXML
    private GridPane qlist;
    @FXML
    private JFXTextArea t1;
    @FXML
    private JFXTextField t3;
    @FXML
    private JFXTextField t2;
    @FXML
    private JFXTextField t4;
    @FXML
    private JFXTextField t5;
    @FXML
    private JFXComboBox<String> t6;
    @FXML
    private Label l1;
    @FXML
    private Pane questionPane;
    private Pane selectQuestionPane;
    static String course;
    static String id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        list.addAll("1", "2", "3", "4");
        t6.setItems(list);

        try {
            db = AssistantData.getDataBaseHandler();
            addQuestions();

        } catch (Exception ex) {
            Logger.getLogger(QuestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    @FXML
    private void isUpdating(ActionEvent event) {
        
        int q_number = Integer.parseInt(l1.getText());
        String question = t1.getText();
        String option1 = t2.getText();
        String option2 = t3.getText();
        String option3 = t4.getText();
        String option4 = t5.getText();
        String correctAns = t6.getValue();
        
       
        

        String q = "update " + course +"_question set q_number = " + q_number + ", question = "
                + "'" + question + "', option_1="
                + "'" + option1 + "',option_2 ="
                + "'" + option2 + "',option_3 = "
                + "'" + option3 + "',option_4 = "
                + "'" + option4 + "', correct_ans = "
                + "'" + correctAns + "' where q_number = " + id + "";
                
        System.out.println(q);
       
        
           if(db.isExeQuery(q)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Question is Updated!!!");
                alert.setTitle("Message");
                alert.showAndWait();
           }
           else
           {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Question is Not Updated!!!");
                alert.setTitle("Message");
                alert.showAndWait();
           }
        
    }
    
    
    public static void setCourse(String c, String q) {
        course = c;
        id = q;

    }
    
     private void addQuestions() throws SQLException {
           String q = "select * from " + course + "_question where q_number = " + id + "";
        ResultSet rs = db.getData(q);
        /* String q = "create table "+table+"_question("
                + "q_number int not null primary key auto_increment,"
                + "question varchar(500) not null,"
                + "option_1 varchar(500) not null,"
                + "option_2 varchar(500) not null,"
                + "option_3 varchar(500) not null,"
                + "option_4 varchar(500) not null,"
                + "correct_ans varchar(500) not null"
                + ")";*/

        if (rs != null) {
            if (rs.next()) {

                l1.setText(String.valueOf(rs.getInt("q_number")));
                t1.setText(rs.getString("question"));
                t2.setText(rs.getString("option_1"));
                t3.setText(rs.getString("option_2"));
                t4.setText(rs.getString("option_3"));
                t5.setText(rs.getString("option_4"));
                t6.setValue(rs.getString("correct_ans"));
               

            }
         
     }
    
    

     }

    @FXML
    private void setBackQuestionTable(ActionEvent event) {
    }
}
     

    


