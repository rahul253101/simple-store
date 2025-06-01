import java.util.*;

public class Seller extends User{

    // public String name;
    // public String sellerId;
        // String password;

    public Seller(String yourName, String yourSellerId, String yourPassword){
        super(yourName, yourSellerId, yourPassword);
        inventory = new Inventory();
    }
    @Override
    public String toString(){
        return super.toString();
    }

}
