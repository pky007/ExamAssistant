/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import assistantdata.AssistantData;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import user.ExamPane1Controller;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class ViewCourseController implements Initializable {
     AssistantData db;

     ObservableList list;
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course, String> c1;
    @FXML
    private TableColumn<Course, String> c2;
    @FXML
    private TableColumn<Course, String> c3;
    @FXML
    private TableColumn<Course, String> c4;
    @FXML
    private TableColumn<Course, String> c5;
    @FXML
    private TableColumn<Course, String> c6;
    @FXML
    private TableColumn<Course, String> c7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     initCol();
        
         try {
             db = AssistantData.getDataBaseHandler();
             list = FXCollections.observableArrayList();
             setData() ;
         } catch (Exception ex) {
             Logger.getLogger(ViewCourseController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    
    
    
    private void setData() throws SQLException {
        /* String addNewCourse = "CREATE Table TotalCourse("
                    + "CourseName varchar(200) NOT NULL PRIMARY KEY,"
                    + "totalQuestion varchar(10) NOT NULL,"
                    + "maxMarks varchar(10) NOT NULL,"
                    + "minMarks varchar(10) NOT NULL,"
                    + "duration varchar(10) NOT NULL"
                    + ")";*/

        String q = "select * from TotalCourse";
        ResultSet res = db.getData(q);
        int n =0;
        if (res != null) {
            while (res.next()) {

               
                list.add(new ViewCourseController.Course(++n, res.getString("coursename"), res.getString("totalQuestion"),
                        res.getString("maxMarks"), res.getString("minMarks"),res.getString("questionPerMarks"), res.getString("duration")));

                 System.out.println("DataFound");
                
            }
            table.getItems().setAll(list);

        }

    }
    
    
    
    
    
     private void initCol() {
       
        c1.setCellValueFactory(new PropertyValueFactory<>("sn"));
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("totalq"));
        c4.setCellValueFactory(new PropertyValueFactory<>("max"));
        c5.setCellValueFactory(new PropertyValueFactory<>("min"));
        c6.setCellValueFactory(new PropertyValueFactory<>("emarks"));
        c7.setCellValueFactory(new PropertyValueFactory<>("duration"));
         
    }
    
    
    
    
 public static class Course {

        private final SimpleIntegerProperty sn;
        private final SimpleStringProperty name;
        private final SimpleStringProperty totalq;
        private final SimpleStringProperty max;
        private final SimpleStringProperty min;
          private final SimpleStringProperty emarks;
        private final SimpleStringProperty duration;
     

        public Course(Integer sn, String name, String total, String maxMarks, String minMarks,String emarks, String duration) {
            this.sn = new SimpleIntegerProperty(sn);
            this.name = new SimpleStringProperty(name);
            this.totalq = new SimpleStringProperty(total);
            this.max = new SimpleStringProperty(maxMarks);
            this.min = new SimpleStringProperty(minMarks);
               this.emarks = new SimpleStringProperty(emarks);
            this.duration = new SimpleStringProperty(duration);
           

        }

        public String getEmarks() {
            return emarks.get();
        }

        public int getSn() {
            return sn.get();
        }

        public String getName() {
            return name.get();
        }

        public String getTotalq() {
            return totalq.get();
        }

        public String getMax() {
            return max.get();
        }

        public String getMin() {
            return min.get();
        }

        public String getDuration() {
            return duration.get();
        }

        }
        
    
}
