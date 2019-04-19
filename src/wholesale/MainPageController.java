package wholesale;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.Timer;
public class MainPageController implements Initializable {

    Connection con;
    PreparedStatement pst;
    DBconnect db;
    ResultSet rs;
    String Query;
    static String changes="";
    
    @FXML
    private JFXHamburger NaviHam;
    @FXML
    private JFXDrawer drawer;
    Timer t;
    @FXML
    private HBox hb;
    boolean check;
    static int time=0;
       
    Parent root;
    
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Label title_lbl;
    @FXML
    private JFXDrawer reportsDrawer;
    @FXML
    private Label datelbl;
    @FXML
    private TextField stockFld;
    @FXML
    private TextField BuyerFld;
    @FXML
    private TextField CustFld;
    @FXML
    private TextField UsrFld;
    long startTime,endTime,session;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            db=new DBconnect();
            con=db.DBconnects();
            startTime = System.currentTimeMillis();
            TODO();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            datelbl.setText(""+ dtf.format(now));

            VBox vb=FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
            drawer.setSidePane(vb);
            for (Node node : vb.getChildren()) {
            if(node.getAccessibleText() != null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {
                switch(node.getAccessibleText()){
                    case "buyer":{try {SwitchScene("AddBuyer.fxml");} catch (Exception ex) {}}break;
                    case "user":{try {SwitchScene("AddUserPage.fxml");}catch (Exception ex) {}}break;
                    case "viewstock":{try {SwitchScene("ViewStocks.fxml");} catch (Exception ex) {}}break;
                    case "stock":{try {SwitchScene("AddstockPage.fxml");} catch (Exception ex) {}}break;
                    case "deluser":{try {SwitchScene("DeleteUser.fxml");} catch (Exception ex) {}}break;
                    case "userdetails":{try {SwitchScene("viewUser.fxml");} catch (Exception ex) {}}break;
                    case "customer":{try {SwitchScene("AddCustomer.fxml");} catch (Exception ex) {}}break;
                    case "logout":{try {
                        onClose();
                        Stage stage=(Stage) node.getScene().getWindow();
                        root=FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                            Scene scene =new Scene(root);
                            stage.setScene(scene);
                            stage.setFullScreen(false);
                            stage.show();}
                    catch (Exception ex) {System.out.println(ex);}}
                        break;
                    case "exit":{try {onClose();Platform.exit();} catch (Exception ex) {}}
                        break;
                }});}}
        for (Node node : hb.getChildren()) {
            if(node.getAccessibleText() != null){
                check=true;
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {
                    switch(node.getAccessibleText()){
                        case "one":try {SwitchScene("AddUserPage.fxml");} catch (Exception ex) {}break;
                        case "two":try {SwitchScene("AddBuyer.fxml");} catch (Exception ex) {}break;
                        case "three":try {SwitchScene("AddCustomer.fxml");} catch (Exception ex) {}break;
                        case "four":try {SwitchScene("AddstockPage.fxml");}catch (Exception ex) {}break;
                        case "five":try {SwitchScene("ViewStocks.fxml");} catch (Exception ex) {}break;
                        case "seven":try {SwitchScene("viewUser.fxml");} catch (Exception ex) {}break;
                        case "eight":try {SwitchScene("DeleteUser.fxml");} catch (Exception ex){};break;}});}}}
        catch (Exception x){}
    HamburgerBasicCloseTransition transition1 = new HamburgerBasicCloseTransition(NaviHam);
    transition1.setRate(-1);
    NaviHam.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
        transition1.setRate(transition1.getRate()*-1);
        transition1.play();
        if(drawer.isShown())
            drawer.close();
        else
            drawer.open();});
    }
    void SwitchScene(String name) throws Exception{
        AnchorPane pane=FXMLLoader.load(getClass().getResource(name));
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);
    }
    
    
    @FXML
    private void callRep(ActionEvent event) throws Exception{
        HBox hb= FXMLLoader.load(getClass().getResource("ReportsBox.fxml"));
        reportsDrawer.setSidePane(hb);
        if(reportsDrawer.isShown())
        reportsDrawer.close();
        else
             reportsDrawer.open();
        SwitchScene("Reports.fxml");

        for (Node node : hb.getChildren()) {
        if(node.getAccessibleText() != null){
            node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {
            switch(node.getAccessibleText()){
                case "sales":{ try {SwitchScene("Reports.fxml");} catch (Exception ex) {  } }
                    break;
                case "buyers":{ try {SwitchScene("ReportsBuyer.fxml");} catch (Exception ex) {  } }
                    break;
                case "Timely":{ try {SwitchScene("TimelyReports.fxml");} catch (Exception ex) {  } }
                    break;
                case "users":{ try {SwitchScene("UserReports.fxml");} catch (Exception ex) {  } }
                    break;
                case "stocks":{ try {SwitchScene("ViewStocks.fxml");} catch (Exception ex) {  } }
                    break;
                case "logs":{ try {SwitchScene("ViewLogs.fxml");} catch (Exception ex) {  } }
                    break;
            }});}}} 

    String session(){
        endTime   = System.currentTimeMillis();
        session = endTime - startTime;
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(session),
        TimeUnit.MILLISECONDS.toMinutes(session) % TimeUnit.HOURS.toMinutes(1),
        TimeUnit.MILLISECONDS.toSeconds(session) % TimeUnit.MINUTES.toSeconds(1));
        return hms;
    }
        
    private void TODO(){
        try {
            con=db.DBconnects();
        Query="Select count(*) as total from stock";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            stockFld.setText(""+rs.getInt("total"));
        }
        pst.close();
        Query="Select count(*) as total from Buyer";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            BuyerFld.setText(""+rs.getInt("total"));
        }
        pst.close();
        Query="Select count(*) as total from Customer";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            CustFld.setText(""+rs.getInt("total"));
        }
        pst.close();
        Query="Select count(*) as total from Users";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            UsrFld.setText(""+rs.getInt("total"));
        }
        pst.close();
         } catch (Exception ex) {
             System.out.println(ex);
        }
        
        
    }

    void onClose()throws Exception{
        Query="UPDATE logs a INNER JOIN("
            + "SELECT LogID FROM logs ORDER BY LogID DESC LIMIT 1) b "
            + "ON a.LogID = b.LogID SET a.Session= ?,a.Changes=?";
        pst=con.prepareStatement(Query);
        pst.setString(1, session());
        pst.setString(2, changes);
        pst.execute();
        pst.close();
    }
    
    }
                        
