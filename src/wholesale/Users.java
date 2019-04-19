
package wholesale;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {
    private final StringProperty Uname;
    private final StringProperty Name;
    private final StringProperty Type;
    private final StringProperty Date;


    Users(String Uname, String Name,String Type,String Date) {
        this.Uname = new SimpleStringProperty(Uname);
        this.Name = new SimpleStringProperty(Name);
        this.Type = new SimpleStringProperty(Type);
        this.Date=new SimpleStringProperty(Date);
    }
    String getUname() {
        return Uname.get();
    }

    String getName() {
        return Name.get();
    }

    String getType() {
        return Type.get();
    }

    String getDate(){
        return Date.get();
    }
    void setUName(String val) {
        Uname.set(val);
    }

    void setName(String val) {
        Name.set(val);
    }

    void setType(String val) {
        Type.set(val);
    }
    void setDate(String val){
        Date.set(val);
    }
    
    public StringProperty UnameProperty(){
        return Uname;
    }
    public StringProperty NameProperty(){
        return Name;
    }
    
    public StringProperty TypeProperty(){
        return Type;
    }
    public StringProperty DateProperty(){
        return Date;
    }
    

}
