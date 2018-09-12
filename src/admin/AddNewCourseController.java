package admin;

import assistantdata.AssistantData;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import javafx.scene.layout.StackPane;

/**
 *
 * @author PK YADAV
 */
public class AddNewCourseController implements Initializable {

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
    ObservableList list1;
    ObservableList list2;

    AssistantData db;
    @FXML
    private JFXComboBox<String> cb1;
    @FXML
    private JFXComboBox<String> cb2;
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            db = AssistantData.getDataBaseHandler();

            list1 = FXCollections.observableArrayList();
            list2 = FXCollections.observableArrayList();

            for (int i = 0; i < 60; i++) {
                if (i >= 0 && i <= 24) {
                    list1.add(String.valueOf(i));
                }
                list2.add(String.valueOf(i));
            }

            cb1.setItems(list1);
            cb2.setItems(list2);

        } catch (Exception ex) {
            Logger.getLogger(AddNewCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addNewCourse(ActionEvent event) throws SQLException {

        String course = t1.getText().trim();
        String totalQuestion = t2.getText().trim();
        String maxMarks = t3.getText().trim();
        String minMarks = t4.getText().trim();
        String questionPerMarks = t5.getText().trim();
        String h = cb1.getValue();
        String m = cb2.getValue();

        String duration = h + ":" + m;

        String query = "insert into TotalCourse values ("
                + "'" + course + "',"
                + "'" + totalQuestion + "',"
                + "'" + maxMarks + "',"
                + "'" + minMarks + "',"
                + "'" + questionPerMarks + "',"
                + "'" + duration + "'"
                + ")";

        System.out.println(duration);

        System.out.println(query);
        if (course.equals("") || totalQuestion.equals("") || maxMarks.equals("") || minMarks.equals("") || h == null || m == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please Fill All the Fields");
            alert.showAndWait();

            return;
        } else {
            if (db.isExeQuery(query)) {
                db.isCreateSubjectTable(course);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Course Created!!!");
                alert.showAndWait();

                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");

                cb1.setValue(null);
                cb2.setValue(null);

            } // System.out.println("DATA Inserted");
            else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Course Not Created!!!");
                alert.setTitle("Message");
                alert.showAndWait();

            }

        }

    }

    @FXML
    private void exit(ActionEvent event) {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");

        cb1.setValue(null);
        cb2.setValue(null);

    }

}
