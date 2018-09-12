package admin;

import assistantdata.AssistantData;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class UpdateDeleteQuestionPaneController implements Initializable {

    AssistantData db;

    ObservableList list   = FXCollections.observableArrayList();
    static String course;
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course, String> tb2;
    @FXML
    private TableColumn<Course, String> tb3;
    @FXML
    private TableColumn<Course, String> tb4;
    @FXML
    private TableColumn<Course, String> tb5;
    @FXML
    private TableColumn<Course, String> tb6;
    @FXML
    private TableColumn<Course, String> tb7;

    @FXML
     private TableColumn<Course, String>  tb1;
    @FXML
    private AnchorPane rootView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            db = AssistantData.getDataBaseHandler();

           
        } catch (Exception ex) {
            Logger.getLogger(UpdateDeleteQuestionPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            setData();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteQuestionPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public void setCourse(String s) {
        this.course = s;
    }
    private void setData() throws SQLException {

        String q = "select * from "+course+"_question";
        ResultSet res = db.getData(q);

        
        if (res != null) {
            while (res.next()) {
              
                list.add(new Course(String.valueOf(res.getString("q_number")), res.getString("question"), res.getString("option_1"),
                        res.getString("option_2"), res.getString("option_3"), res.getString("option_4"), res.getString("correct_ans")));

                System.out.println(String.valueOf(res.getInt("q_number")));
            }
            table.getItems().setAll(list);

        }

    }
    
    
   
    private void initCol() {
       
        tb2.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb3.setCellValueFactory(new PropertyValueFactory<>("option1"));
        tb4.setCellValueFactory(new PropertyValueFactory<>("option2"));
        tb5.setCellValueFactory(new PropertyValueFactory<>("option3"));
        tb6.setCellValueFactory(new PropertyValueFactory<>("option4"));
        tb7.setCellValueFactory(new PropertyValueFactory<>("correct"));
         tb1.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

    


    @FXML
    private void deleteQuestion(ActionEvent event) throws SQLException {
          
        Course question =  table.getSelectionModel().getSelectedItem();
        
        if(question == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Please Select data First!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                return;
            
        }
        
        else
        {
            String num = question.getId();
            
            String q ="delete from "+course+"_question where q_number ="+num+" ";
           
            
            
            if(db.isExeQuery(q)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Question is deleted!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
                // setData();
            }
            else
            {
                if(db.isExeQuery(q)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Question Not deleted!!!");
                alert.setTitle("Message");
                alert.showAndWait();
            }
            }
            
          
        }
        
    }

    @FXML
    private void updateQuestion(ActionEvent event) throws IOException {
         
        Course question =  table.getSelectionModel().getSelectedItem();
        
        if(question == null)
        {
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Please Select data First!!!");
                alert.setTitle("Message");
                alert.showAndWait();
                return;
            
            
        }
        
        else
        {
            String num = question.getId();
            
            new QuestionEditPaneController().setCourse(course, num);
            rootView.getChildren().clear();        
             AnchorPane pane = FXMLLoader.load(getClass().getResource("QuestionEditPane.fxml"));
                rootView.getChildren().add(pane);
            
        }
        
    }

    public static class Course {

        private final SimpleStringProperty id;
        private final SimpleStringProperty question;
        private final SimpleStringProperty option1;
        private final SimpleStringProperty option2;
        private final SimpleStringProperty option3;
        private final SimpleStringProperty option4;
        private final SimpleStringProperty correct;

        public Course(String id, String q, String op1, String op2, String op3, String op4, String correct) {
            this.id = new SimpleStringProperty(id);
            this.question = new SimpleStringProperty(q);
            this.option1 = new SimpleStringProperty(op1);
            this.option2 = new SimpleStringProperty(op2);
            this.option3 = new SimpleStringProperty(op3);
            this.option4 = new SimpleStringProperty(op4);
            this.correct = new SimpleStringProperty(correct);
           // System.out.println(question);

        }

        public String getId() {
            return id.get();
        }

        public String getQuestion() {
            return question.get();
        }

        public String getOption1() {
            return option1.get();
        }

        public String getOption2() {
            return option2.get();
        }

        public String getOption3() {
            return option3.get();
        }

        public String getOption4() {
            return option4.get();
        }

        public String getCorrect() {
            return correct.get();
        }

    }

}
