package wholesale;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TimelyReportsController implements Initializable {

    String Query,Desc="";
    DBconnect db;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    
    @FXML
    private ComboBox<String> DayCombo;
    @FXML
    private ComboBox<String> MonthCombo;
    @FXML
    private ComboBox<String> yearCombo;
    @FXML
    private TextField soldFld;
    @FXML
    private TextField QuantityFld;
    @FXML
    private TextField BoughtFld;
    @FXML
    private TextField BuyerFld;
    @FXML
    private TextArea Description;
    @FXML
    private TextField StockFld;
    @FXML
    private TextField bestDayFld;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        
        db=new DBconnect();
        con=db.DBconnects();
        
        Query="SELECT left(DateAndTime,10) a From reciept where "
                + "Paid=(select max(paid) from reciept)";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next())
            bestDayFld.setText(rs.getString("a"));
        pst.close();
        Query="SELECT distinct(left(DateAndTime,10)) a FROM reciept order by a desc";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            DayCombo.getItems().add(rs.getString("a"));
        }
        pst.close();
        Query="SELECT distinct(left(DateAndTime,7)) a FROM reciept order by a desc";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            MonthCombo.getItems().add(rs.getString("a"));
        }
        pst.close();
        Query="SELECT distinct(left(DateAndTime,4)) a FROM reciept order by a desc";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            yearCombo.getItems().add(rs.getString("a"));
        }
        pst.close();
        
        DayCombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Change(DayCombo.getValue());
                }});
        
            MonthCombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Change(MonthCombo.getValue());
            }});
            yearCombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Change(yearCombo.getValue());
            }});
                
    } catch (Exception ex) {
        System.out.println(ex);
    }
    }    
    void Change(String date){
        try{
            Desc="";
            Query="Select Buyer_name a,Quantity b,Stock_bought c,right(DateAndTime,8) d from reciept where DateAndTime like '"+date+"%'";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next()){
                Desc+=rs.getString("a")+" bought "+rs.getString("b")+" "+rs.getString("c")+" at "+rs.getString("d")+"\n";
            }
            Description.setText(Desc);
            pst.close();
            Query="Select sum(Paid) a,sum(Quantity) b from reciept "
                    + "where DateAndTime like '%"+date+"%'";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next()){
              soldFld.setText(rs.getString("a"));
              QuantityFld.setText(rs.getString("b"));
            }
            pst.close();
            Query="SELECT Buyer_name a FROM reciept where Paid="
                    + "(select max(paid) from reciept) AND DateAndTime like '%"+date+"%'";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next())
                BuyerFld.setText(rs.getString("a"));
            pst.close();
            Query="SELECT Buyer_name a FROM reciept where Paid="
                    + "(select max(paid) from reciept) AND DateAndTime like '%"+date+"%'";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next())
                BuyerFld.setText(rs.getString("a"));
            pst.close();
            Query="SELECT sum(Price) a FROM Stock where Date_added like '%"+date+"%'";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next())
                BoughtFld.setText(rs.getString("a"));
            pst.close();
            Query="SELECT Stock_bought a FROM reciept where Paid="
                + "(select max(paid) from reciept) And DateAndTime like '%"+date+"%'";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next())
                StockFld.setText(rs.getString("a"));
            pst.close();
            } 
            catch (Exception ex) {System.out.println(ex);}
    }
    
}
