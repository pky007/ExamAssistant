package user;

import assistantdata.AssistantData;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PK YADAV
 */
public class AllCoursePaneController implements Initializable {

    AssistantData db;
    @FXML
    private VBox root1;
    @FXML
    private ScrollPane root;
    @FXML
    CorsePaneController obj;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obj = new  CorsePaneController();
     
       
        try {
            setAllPane();
        } catch (SQLException ex) {
            Logger.getLogger(AllCoursePaneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AllCoursePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void  setAllPane() throws SQLException, IOException {

       // Node pane[] = new Node[1];
       
         AnchorPane pane = FXMLLoader.load(getClass().getResource("CorsePane.fxml"));
     
       

//       String query = "select * from totalCourse";
  //      ResultSet rs = db.getData(query);
    //    if (rs != null) {
      //      if(rs.next()) {
                /*  String addNewCourse = "CREATE Table TotalCourse("
                    + "CourseName varchar(200) NOT NULL PRIMARY KEY,"
                    + "totalQuestion varchar(10) NOT NULL,"
                    + "maxMarks varchar(10) NOT NULL,"
                    + "minMarks varchar(10) NOT NULL,"
                    + "duration varchar(10) NOT NULL"
                    + ")";*/    
            //    String name = rs.getString("courseName");
              //  String totalQuestion = rs.getString("totalQuestion");
                //String max = rs.getString("maxMarks");
                //String min = rs.getString("minMarks");
                //String duration = rs.getString("duration");
                //System.out.println(name+totalQuestion+max+min+duration);
                
               
                  CorsePaneController.s="NEW";
               
              
                  // pane = FXMLLoader.load(getClass().getResource("CorsePane.fxml"));
                  obj.setTT("YYYY");
                 
                   root1.getChildren().add(pane);
                     //obj.SetLabel(new Label("OKS"));
                     
                       
              
            }
    //        }
  //  }
          
        }

    


   

