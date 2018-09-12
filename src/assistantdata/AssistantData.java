package assistantdata;
import com.sun.nio.sctp.Notification;
import java.sql.*;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 *
 * @author PK YADAV
 */
public class AssistantData {
    
    private static AssistantData db = null;
    private static Connection con = null;
    private static Statement st = null;

    private AssistantData() throws Exception {
        getConnection();
        createAdminTable();
        setNewCourse();
        setNewUser();
     
    }

    private void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Exam__assistant", "root", "");
            st = con.createStatement();
            System.out.println("Driver & Connection OK");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Problem " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Problem " + e.getMessage());

        }

    }

    
    
    
    
    public static AssistantData getDataBaseHandler() throws Exception {

        if (db == null) {
            db = new AssistantData();
        }

        return db;
    }    
    

   

    private void  setNewCourse() throws SQLException {

        DatabaseMetaData dbmd = con.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "TotalCourse", null);

        if (rs.next()) {
            System.out.println("Table TotalCourse Already Exist");
        } else {
            String addNewCourse = "CREATE Table TotalCourse("
                    + "CourseName varchar(200) NOT NULL PRIMARY KEY,"
                    + "totalQuestion varchar(10) NOT NULL,"
                    + "maxMarks varchar(10) NOT NULL,"
                    + "minMarks varchar(10) NOT NULL,"
                    + "questionPerMarks varchar(10) NOT NULL,"
                    + "duration varchar(50) NOT NULL"
                    + ")";
            st.execute(addNewCourse);
            System.out.println("Table TotalCours Created");
        }

    }
    
    
      
        
        
    
    
    public boolean isCreateSubjectTable(String table) throws SQLException {
        try {
            
            
        DatabaseMetaData dbmd = con.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, table+"_question", null);

        if (rs.next()) {
            System.out.println("Table "+table+"_question Already Exist");
        }
        
            
        else{            
        String q = "create table "+table+"_question("
                + "q_number int not null primary key auto_increment,"
                + "question varchar(500) not null,"
                + "option_1 varchar(500) not null,"
                + "option_2 varchar(500) not null,"
                + "option_3 varchar(500) not null,"
                + "option_4 varchar(500) not null,"
                + "correct_ans varchar(500) not null"
                + ")";
        
            
            
            
            
            st.execute(q);
            System.out.println("Query executed");           
        }
        
            return true;

        } catch (SQLException e) {
            System.out.println("Problem in SQL " + e.getMessage());
           
            return false;
        }
    }

    public boolean isExeQuery(String query) {
        try {
            
            st.execute(query);
            System.out.println("Query executed");           

            return true;

        } catch (SQLException e) {
            System.out.println("Problem in SQL " + e.getMessage());
           
            return false;
        }
    }

    public ResultSet getData(String query) {
        
        ResultSet rs = null;
        try {
           // st = con.createStatement();
            rs = st.executeQuery(query);          

        } catch (SQLException ex) {
             
            System.out.println("Load Data Problem " + ex.getMessage());
        }       
        return rs;

    }
    

     public void isCreateStudentTable(String str) throws SQLException {
         
         String q = "create table "+str+"_userTable("
                 + "courseName varchar(200) NOT NULL Primary Key,"
                 + "correct varchar(200) NOT NULL,"
                 + "wrong varchar(200) NOT NULL,"
                 + "skip varchar(200) NOT NULL,"
                 + "status varchar(50) NOT NULL"
                 + ")";
         st.execute(q);
         
     } 
      private void  setNewUser() throws SQLException {
          
          
           DatabaseMetaData dbmd = con.getMetaData();
        ResultSet rs = dbmd.getTables(null, null,"UserSignUp", null);

        if (rs.next()) {
            System.out.println("Table UserSignUp Already Exist");
        }
        else {
          
          
          
          String query = "create table UserSignUp("
                   + "username varchar(200) NOT NULL PRIMARY KEY,"
                  + "name varchar(200) NOT NULL,"                
                  + "password varchar(200) NOT NULL,"
                  + "dob varchar(50) NOT NULL,"
                  + "email varchar(200) NOT NULL,"
                  + "contact varchar(15),"
                  + "address varchar(500)"
                  + ")";
          
          st.execute(query);
        }
          
      }
      
      
      
      
     public void showNotiFication(String title,String text)
     {
         Notifications nf = Notifications.create()
                 .title(title)
                 .text(text)
                 .graphic(null)
                 .hideAfter(Duration.millis(10000))
                 .position(Pos.CENTER);
         
        
         nf.showInformation();
         
     }

    private void createAdminTable() {
         try {
            
            
        DatabaseMetaData dbmd = con.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "ExamAdmin", null);

        if (rs.next()) {
            System.out.println("Table ExamAdmin Already Exist");
        }
        
            
        else{            
        String q = "create table ExamAdmin ("
                + "name varchar(20) not null,"
                + "password varchar(200) not null,"
                 + "email varchar(200) not null"             
                + ")";        
            
            
            
            
            st.execute(q);            
            String qq = "insert into ExamAdmin values ('Admin', 'admin','xyz123@gmail.com')";
            st.execute(qq);                       
        }
        
           

        } catch (SQLException e) {
            System.out.println("Problem in SQL " + e.getMessage());
           
           
        }
        
    }

    
}
