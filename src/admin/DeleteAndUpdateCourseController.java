package admin;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class DeleteAndUpdateCourseController implements Initializable {

    AssistantData db;

    @FXML
    private ComboBox<String> listCourse;
    ObservableList<String> list;
    @FXML
    private AnchorPane deleteUpdatePane;
    @FXML
    private Pane selectPane;
    @FXML
    private JFXButton showCourse;
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
    private Label l6;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            db = AssistantData.getDataBaseHandler();
            list = FXCollections.observableArrayList();
            setListCourse();

        } catch (Exception ex) {
            Logger.getLogger(DeleteAndUpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setListCourse() throws SQLException {

        String query = "select courseName from totalCourse";
        ResultSet rs = db.getData(query);
        if (rs != null) {
            while (rs.next()) {

                list.add(rs.getString("courseName"));

            }
        }

        listCourse.setItems(list);
    }

    @FXML
    private void setUpdatePane(ActionEvent event) {

        UpdateCourseController obj = new UpdateCourseController();
        obj.setCourse(listCourse.getValue());
        try {
            deleteUpdatePane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("UpdateCourse.fxml"));

            deleteUpdatePane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void DeleteCourse(ActionEvent event) {
        String course = listCourse.getValue();
        String query = "delete from totalCourse where courseName='" + course + "'";
        String q = "drop table " + course + "_question";
        System.out.println(q);
        db.isExeQuery(query);
        db.isExeQuery(q);

    }

    @FXML
    private void showListCourse(ActionEvent event) throws SQLException {

        String name = listCourse.getValue();
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
                coursePane.setVisible(true);
                selectPane.setVisible(false);
                back.setVisible(true);

            }
        }
    }
    
    @FXML
    private void back(ActionEvent event) {
        coursePane.setVisible(false);
        selectPane.setVisible(true);
        back.setVisible(false);
    }
}
