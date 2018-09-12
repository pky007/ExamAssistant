/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static admin.UpdateDeleteQuestionPaneController.course;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class StudentPaneController implements Initializable {

    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> c1;
    @FXML
    private TableColumn<Student, String> c2;
    @FXML
    private TableColumn<Student, String> c3;
    @FXML
    private TableColumn<Student, String> c4;
    @FXML
    private TableColumn<Student, String> c5;
    @FXML
    private TableColumn<Student, String> c6;
    
    AssistantData db;
    ObservableList list;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        
        try {
            db = AssistantData.getDataBaseHandler();
            list = FXCollections.observableArrayList();
            loadData();
        } catch (Exception ex) {
            Logger.getLogger(StudentPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    
    private void initCol()
    {
        
         c1.setCellValueFactory(new PropertyValueFactory<>("sn"));
        c2.setCellValueFactory(new PropertyValueFactory<>("username"));
        c3.setCellValueFactory(new PropertyValueFactory<>("name"));
        c4.setCellValueFactory(new PropertyValueFactory<>("dob"));
        c5.setCellValueFactory(new PropertyValueFactory<>("email"));
        c6.setCellValueFactory(new PropertyValueFactory<>("contact"));
       
         
    }

    private void loadData() throws SQLException {
        
     
                 
       String q = "select * from UserSignUp ";
       
       ResultSet rs = db.getData(q);
       int sn=0;
       
       if(rs!=null)
       {
           while(rs.next())
           {
               
               list.add(new Student(++sn, rs.getString("username"), rs.getString("name"), rs.getString("dob"), rs.getString("email"),rs.getString("contact")));
               
           }
           
       }
       table.getItems().setAll(list);
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        
        
         StudentPaneController.Student student =  table.getSelectionModel().getSelectedItem();
        
        if(student == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Please Select data First!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                return;
            
        }
        
        else
        {
            String name = student.getUsername();
            
            String q ="delete from UserSignUp  where username ='"+name+"' ";
            String qq = "drop table  "+name+"_userTable";
           
            
            
            if(db.isExeQuery(q)){
                db.isExeQuery(qq);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Student is deleted!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
                // setData();
            }
            else
            {
                if(db.isExeQuery(q)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Student Not deleted!!!");
                alert.setTitle("Message");
                alert.showAndWait();
            }
            }
            
          
        }
        
        
        
        
        
        
    }
    
    
    
    
    
 public static   class Student{
        
                private final SimpleIntegerProperty sn;
        private final SimpleStringProperty username;
        private final SimpleStringProperty name;
        private final SimpleStringProperty dob;
        private final SimpleStringProperty email;
          private final SimpleStringProperty contact;
          
          Student(Integer sn, String uname, String name, String dob, String email, String contact)
          {
              this.sn = new SimpleIntegerProperty(sn);
              this.username = new SimpleStringProperty(uname);
               this.name = new SimpleStringProperty(name);
                this.dob = new SimpleStringProperty(dob);
                 this.email = new SimpleStringProperty(email);
                  this.contact = new SimpleStringProperty(contact);
              
              
          }

        public int getSn() {
            return sn.get();
        }

        public String getUsername() {
            return username.get();
        }

        public String getName() {
            return name.get();
        }

        public String getDob() {
            return dob.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getContact() {
            return contact.get();
        }
      
     
        
    }
    
    
}
