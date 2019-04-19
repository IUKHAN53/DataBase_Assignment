package wholesale;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.SpringLayout;

public class ViewStocksController implements Initializable {
    DBconnect db;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String Query;
    
    @FXML
    private TableView<StockDetails> stockTable;
    @FXML
    private TableColumn<StockDetails, String> nameCol;
    @FXML
    private TableColumn<StockDetails, Integer> QuantityCol;
    @FXML
    private TableColumn<StockDetails, String> CommentsCol;
    
    private ObservableList<StockDetails>data;
    
    @FXML
    private TableColumn<StockDetails, Integer> PriceCol;
    @FXML
    private TextField TotPrice;
    @FXML
    private TextField MaxPrice;
    @FXML
    private TextField totQuantity;
    @FXML
    private TextField MAxStock;
    @FXML
    private TextField totStock;
    @FXML
    private TextField LowStock;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        db=new DBconnect();
        con=db.DBconnects();
        Load();
        fillFields();
        
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    void Load() throws Exception{
        data=FXCollections.observableArrayList();
            Query="select name,quantity,comments,Price from stock";
        rs=con.createStatement().executeQuery(Query);
        while(rs.next()) {
            data.add(new StockDetails(rs.getString("name"),rs.getInt("quantity")
                    ,rs.getString("comments"),rs.getInt("Price")));
        }
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        CommentsCol.setCellValueFactory(new PropertyValueFactory<>("Comments"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        stockTable.setItems(null);
        stockTable.setItems(data);

            
    }

    private void fillFields() {
    try{
        Query="Select count(name) as a, sum(Price) as b,max(Price) as c,sum(quantity) as d from stock";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            totStock.setText(""+rs.getInt("a"));
            TotPrice.setText(""+rs.getInt("b"));
            MaxPrice.setText(""+rs.getInt("c"));
            totQuantity.setText(""+rs.getInt("d"));
        }
        pst.close();
        
        Query="SELECT name FROM stock where price>=all(select price from stock)";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            MAxStock.setText(rs.getString("name"));
        }
        pst.close();
        Query="SELECT name FROM stock where price<=all(select price from stock) limit 1";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            LowStock.setText(rs.getString("name"));
        }
        pst.close();
        
    }
    catch(Exception e){
        System.out.println(e);
    }
    }
}