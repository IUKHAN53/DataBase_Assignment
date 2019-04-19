package wholesale;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockDetails{
    private final StringProperty name;
    private final IntegerProperty Quantity;
    private final StringProperty Comments;
    private final IntegerProperty price;


    StockDetails(String name, int Quantity, String Comments, int price) {
        this.name = new SimpleStringProperty(name);
        this.Quantity = new SimpleIntegerProperty(Quantity);
        this.Comments = new SimpleStringProperty(Comments);
        this.price=new SimpleIntegerProperty(price);
    }
    String getName() {
        return name.get();
    }

    int getQuantity() {
        return Quantity.get();
    }

    String getComments() {
        return Comments.get();
    }

    int getprice(){
        return price.get();
    }
    void setName(String val) {
        name.set(val);
    }

    void setQuantity(int val) {
        Quantity.set(val);
    }

    void setComments(String val) {
        Comments.set(val);
    }
    void setprice(int val){
        price.set(val);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    public IntegerProperty QuantityProperty(){
        return Quantity;
    }
    public StringProperty CommentProperty(){
        return Comments;
    }
    public IntegerProperty priceProperty(){
        return price;
    }
    

}
