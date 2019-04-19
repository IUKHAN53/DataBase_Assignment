
package wholesale;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Logs {
    private final StringProperty Uname;
    private final StringProperty date;
    private final StringProperty session;
    private final StringProperty changes;


    Logs(String Uname, String date,String Comments,String price) {
        this.Uname = new SimpleStringProperty(Uname);
        this.date = new SimpleStringProperty(date);
        this.session = new SimpleStringProperty(Comments);
        this.changes=new SimpleStringProperty(price);
    }
    String getUname() {
        return Uname.get();
    }

    String getdate() {
        return date.get();
    }

    String getsession() {
        return session.get();
    }

    String getchanges(){
        return changes.get();
    }
    void setName(String val) {
        Uname.set(val);
    }

    void setdate(String val) {
        date.set(val);
    }

    void setsession(String val) {
        session.set(val);
    }
    void setchanges(String val){
        changes.set(val);
    }
    
    public StringProperty UnameProperty(){
        return Uname;
    }
    public StringProperty dateProperty(){
        return date;
    }
    
    public StringProperty sessionProperty(){
        return session;
    }
    public StringProperty changesProperty(){
        return changes;
    }
    

}
