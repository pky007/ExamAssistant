package admin;

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
public class DeleteAndUpdateQuestionController implements Initializable {

    AssistantData db;

   
    ObservableList<String> list;
    @FXML
    private Pane selectRoot;
    @FXML
    private JFXComboBox<String> course_list;
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
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = AssistantData.getDataBaseHandler();
            list = FXCollections.observableArrayList();
            setListCourse();
            // TODO
        } catch (Exception ex) {
            Logger.getLogger(DeleteAndUpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setListCourse() throws SQLException {

        String query = "select courseName from totalCourse";
        ResultSet rs = db.getData(query);
        if (rs != null) {
            while (rs.next()) {
                //System.out.println(rs.getString("courseName"));

                list.add(rs.getString("courseName"));

            }
        }

        course_list.setItems(list);
    }

    @FXML
    private void cancelCourse(ActionEvent event) {
    }

    @FXML
    private void setAddQuestionPane(ActionEvent event) {

        try {
            rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("QuestionEditPane.fxml"));

            rootPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setBack(ActionEvent event) {
    }

    @FXML
    private void setCoursepane(ActionEvent event) throws SQLException {

     
     String course = course_list.getValue().toString();
       new UpdateDeleteQuestionPaneController().setCourse(course);
     
        try {
            rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("UpdateDeleteQuestionPane.fxml"));

            rootPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
}
