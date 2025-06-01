import java.util.*;

public class Buyer extends User{
    // String name;
    // String buyerId;
    // String password;


    public Buyer(String yourName, String yourId, String yourPassword){
        super(yourName, yourId, yourPassword);
        // name = yourName;
        // buyerId = yourId;
        // password = yourPassword;
        cart = new HashMap<>();
    }

    public void displayCart(){

        if(cart.size() > 0){

            for (Map.Entry<Inventory.Tuple,Integer> entry : cart.entrySet()) {
                Inventory.Tuple key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }else{
            System.out.println("cart is empty!!");
        }
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
