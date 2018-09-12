/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class UpdateCourseController implements Initializable {

    AssistantData db;
     ObservableList list1;
     ObservableList list2;

    @FXML
    private AnchorPane updatePane;
    static String course;
    @FXML
    private JFXTextField t1;
    @FXML
    private JFXTextField t2;
    @FXML
    private JFXTextField t3;
    @FXML
    private JFXTextField t4;
    @FXML
    private JFXTextField t5;
    @FXML
    private JFXComboBox<String> cb1;
    @FXML
    private JFXComboBox<String> cb2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            db = AssistantData.getDataBaseHandler();
               list1 = FXCollections.observableArrayList();
           list2 = FXCollections.observableArrayList();
           
           for(int i = 0; i<=60;  i++)
           {
               if(i>= 0 && i<=24)
                   list1.add(String.valueOf(i));
               list2.add(String.valueOf(i));
           }
           
           cb1.setItems(list1);
           cb2.setItems(list2);
            setAllFields();
        } catch (Exception ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCourse(String s) {

        this.course = s;
    }

    private void setAllFields() throws SQLException {
        /*  String addNewCourse = "CREATE Table TotalCourse("
                    + "CourseName varchar(200) NOT NULL PRIMARY KEY,"
                    + "totalQuestion varchar(10) NOT NULL,"
                    + "maxMarks varchar(10) NOT NULL,"
                    + "minMarks varchar(10) NOT NULL,"
                    + "duration varchar(10) NOT NULL"
                    + ")";*/

        String s = "select * from totalCourse where courseName = '" + course + "'";

        ResultSet rs = db.getData(s);
        if (rs != null) {
            if (rs.next()) {
                t1.setText(rs.getString("CourseName"));
                t2.setText(rs.getString("totalQuestion"));
                t3.setText(rs.getString("maxMarks"));
                 t4.setText(rs.getString("minMarks"));
                 t5.setText(rs.getString("questionPerMarks"));
                 String time[]  = rs.getString("duration").split(":");
                cb1.setPromptText(time[0]);
              
              
              cb2.setPromptText(time[1]);

            }

        }

    }

    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void updateCourse(ActionEvent event) {
        
         /*  String addNewCourse = "CREATE Table TotalCourse("
                    + "CourseName varchar(200) NOT NULL PRIMARY KEY,"
                    + "totalQuestion varchar(10) NOT NULL,"
                    + "maxMarks varchar(10) NOT NULL,"
                    + "minMarks varchar(10) NOT NULL,"
                    + "duration varchar(10) NOT NULL"
                    + ")";*/
        
        String courseU = t1.getText().trim();
         String totalQuestion = t2.getText().trim();
         String maxMarks = t3.getText().trim();
         String minMarks = t4.getText().trim();
          String eachQuestionMqrks = t5.getText().trim();
         String h = cb1.getValue();
         String m = cb2.getValue();
         String duration = h+":"+m;
         
        String query = "update  totalCourse set courseName="+"'"+courseU+"',"
                + "totalQuestion="+"'"+totalQuestion+"',"
                + "maxMarks = "+ "'"+maxMarks+"',"
                + "minMarks = " + "'"+minMarks+"',"
                 + "questionPerMarks = " + "'"+eachQuestionMqrks+"',"
                + "duration = "  + "'"+duration+"'"
                   +"where courseName = '"+course+"'";
                
        
         System.out.println(query);
         if (course.equals("") || totalQuestion.equals("") || maxMarks.equals("") || minMarks.equals("")) {
                            System.out.println("NOOO");               
               
                return;
         }
         else{
         if (db.isExeQuery(query)){
             db.isExeQuery("Alter table "+course+"_question Rename  "+courseU+"_question");
             System.out.println("Yess");
             
         } 
             
           // System.out.println("DATA Inserted");
         else{
         
         // JOptionPane.showMessageDialog(null, "Error Check Data, No Book Add","Information",HEIGHT);   
          System.out.println("DATA Not Inserted");
         }
           
         }   
         
         t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        
        
        
        
        
        
        
    }
}
