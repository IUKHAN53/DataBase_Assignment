
package wholesale;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogsController implements Initializable {

    DBconnect db;
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    String Query;
     @FXML
    private TableView<Logs> LogsTable;

    @FXML
    private TableColumn<Logs, String> UnameCol;

    @FXML
    private TableColumn<Logs, String> dateCol;

    @FXML
    private TableColumn<Logs, String> SessiontimeCol;

    @FXML
    private TableColumn<Logs, String> ChngsCol;
    private ObservableList<Logs>data;
    @FXML
    private TextField totLogs;
    @FXML
    private TextField totsession;
    @FXML
    private TextField othercount;
    @FXML
    private TextField mostlog;
    @FXML
    private TextField totusr;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        db=new DBconnect();
        con=db.DBconnects();
        populate();
        fillFields();
        }
        catch(Exception x){
            System.out.println(x);
        }
        }

    private void populate() {
        try{
             data=FXCollections.observableArrayList();
        Query="SELECT UserName,DateAndTime,Session,Changes from logs";
        rs=con.createStatement().executeQuery(Query);
        while(rs.next())
            data.add(new Logs(rs.getString("UserName"),rs.getString("DateAndTime"),rs.getString("Session"),rs.getString("Changes")));
        UnameCol.setCellValueFactory(new PropertyValueFactory<>("Uname"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        SessiontimeCol.setCellValueFactory(new PropertyValueFactory<>("session"));
        ChngsCol.setCellValueFactory(new PropertyValueFactory<>("changes"));
        LogsTable.setItems(null);
        LogsTable.setItems(data);
    }    catch (Exception ex) { System.out.println(ex); }  
    }

    private void fillFields() {
    try {
        Query="Select count(*) as a,sum(Session) as b from logs";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            totLogs.setText(""+rs.getInt("a"));
            totsession.setText(""+rs.getInt("b"));
        }
        pst.close();
        Query="SELECT Username FROM logs group by UserName order by count(*) desc limit 1";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            mostlog.setText(rs.getString("Username"));           
        }
        pst.close();
        Query="SELECT count(Username) as a FROM logs group by UserName";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        int x=0;
        while(rs.next()){
            x++;
        }
        totusr.setText(""+x);
        pst.close();
        
         } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
}
