

package wholesale;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Buyer {
    private final StringProperty name;
    private final StringProperty stock;
    private final IntegerProperty price;
    private final StringProperty method;
    private final IntegerProperty quantity;
    private final IntegerProperty paid;

    Buyer(String name, String stock,int price,String method,int quantity,int paid) {
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleStringProperty(stock);
        this.price = new SimpleIntegerProperty(price);
        this.method=new SimpleStringProperty(method);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.paid = new SimpleIntegerProperty(paid);
    }
    String getname() {
        return name.get();
    }

    String getstock() {
        return stock.get();
    }

    int getprice() {
        return price.get();
    }
     int getpaid() {
        return paid.get();
    }
     int getquantity() {
        return quantity.get();
    }

    String getmethod(){
        return method.get();
    }
    void setname(String val) {
        name.set(val);
    }

    void setstock(String val) {
        stock.set(val);
    }

    void setprice(int val) {
        price.set(val);
    }
    void setpaid(int val) {
        paid.set(val);
    }
    void setquantity(int val) {
        quantity.set(val);
    }
    void setmethod(String val){
        method.set(val);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    public StringProperty stockProperty(){
        return stock;
    }
    
    public IntegerProperty priceProperty(){
        return price;
    }
    public IntegerProperty quantityProperty(){
        return quantity;
    }
    public IntegerProperty paidProperty(){
        return paid;
    }
    public StringProperty methodProperty(){
        return method;
    }
    

}