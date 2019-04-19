
package wholesale;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static wholesale.AddBuyerController.con;

public class UserReportsController implements Initializable {

    DBconnect db;
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    String Query;
    
    private ObservableList<Users> data;
    @FXML
    private TextField EmailFld;
    @FXML
    private TextField DesFld;
    @FXML
    private TableView<Users> UsrTable;
    @FXML
    private TableColumn<Users, String> UsrCol;
    @FXML
    private TableColumn<Users, String> nameCol;
    @FXML
    private TableColumn<Users, String> TypeCol;
    @FXML
    private TableColumn<Users, String> DandTCol;
    @FXML
    private ComboBox<String> UnameCombo;
    @FXML
    private TextField adrsFld;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        db=new DBconnect();
        con=db.DBconnects();
        DO();
        populate();
    }
    catch(Exception x){
        System.out.println(x);
    }
    }    
    void DO(){
    try{
        Query="Select UserName a from users";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next())
            UnameCombo.getItems().add(rs.getString("a"));   
        UnameCombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try{
        Query="SELECT Email a,Designation b,Adress c FROM users where UserName=?";
        pst=con.prepareStatement(Query);
        pst.setString(1, UnameCombo.getValue());
        rs=pst.executeQuery();
        while(rs.next()){
            EmailFld.setText(rs.getString("a"));
            DesFld.setText(rs.getString("b"));
            adrsFld.setText(rs.getString("c"));
        } }catch(SQLException s){
            System.out.println(s);
        }}});
        
        
        
    }catch(SQLException x){
        System.out.println(x);
    }
    }

    private void populate() {
    data=FXCollections.observableArrayList();
    try{
        Query="SELECT UserName a,Name b,UserType c, left(Date_added,10) d FROM users";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next())
            data.add(new Users(rs.getString("a"),rs.getString("b"),rs.getString("c"),rs.getString("d")));
        UsrCol.setCellValueFactory(new PropertyValueFactory<>("Uname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        DandTCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        UsrTable.setItems(null);
        UsrTable.setItems(data);
        
    }catch(SQLException x){
        System.out.println(x);
    }
    }
    
}
