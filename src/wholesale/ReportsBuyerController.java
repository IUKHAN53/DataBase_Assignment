
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

public class ReportsBuyerController implements Initializable {

    String Query;
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    DBconnect db;
    
    @FXML
    private TextField totBuyerFld;
    @FXML
    private TextField totStockFld;
    @FXML
    private TextField TotPaidFld;
    @FXML
    private TextField MethodFld;
    @FXML
    private TableView<Buyer> BuyerTable;
    @FXML
    private TableColumn<Buyer, String> NameCol;
    @FXML
    private TableColumn<Buyer, String> StockCol;
    @FXML
    private TableColumn<Buyer, Integer> QuantityCol;
    @FXML
    private TableColumn<Buyer, Integer> Pricecol;
    @FXML
    private TableColumn<Buyer, Integer> PaidCol;
    @FXML
    private TableColumn<Buyer, String> MethodCol;

    private ObservableList<Buyer>data;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    db=new DBconnect();
    try {
        con=db.DBconnects();
        populateTable();
        FillFields();
    } catch (Exception ex) {
        System.out.println(ex); 
    }}

    
    private void populateTable() {
        try {
            data=FXCollections.observableArrayList();
            Query="SELECT Name a,Quantity b,Payment c,Stock d,Paid e,Paid/Quantity f FROM buyer";
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next()){
                data.add(new Buyer(rs.getString("a"),rs.getString("d"),
                        rs.getInt("f"), rs.getString("c"), rs.getInt("b"),rs.getInt("e")));
            }
            NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            StockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            QuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            Pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
            PaidCol.setCellValueFactory(new PropertyValueFactory<>("paid"));
            MethodCol.setCellValueFactory(new PropertyValueFactory<>("method"));
            BuyerTable.setItems(null);
            BuyerTable.setItems(data);
 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void FillFields() {
    try {     
        Query="Select count(*) as a,sum(Quantity) as b,sum(Paid) as c,max(Quantity) as d from Buyer";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            totBuyerFld.setText(rs.getString("a"));
            totStockFld.setText(rs.getString("b"));
            TotPaidFld.setText(rs.getString("c"));
            MethodFld.setText(rs.getString("d"));

        }
        pst.close();
        Query="Select count(*) as total from Buyer";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            totBuyerFld.setText(""+rs.getInt("total"));
        }
        pst.close();
         } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
   
    
}

