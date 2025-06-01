import java.util.*;
import java.util.Objects;

public class Inventory{

    public static class Tuple{
        String product;
        String hash;

        public Tuple(String productName, String id){
            product = productName;
            hash = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return hash.equals(tuple.hash) && product.equals(tuple.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, hash);
        }

        @Override
        public String toString() {
            return "Tuple{product='" + product + "', id=" + hash + "}";
        }
    }

    public static HashMap<Tuple, Integer> catalog = new HashMap<>();
    public HashMap<Tuple,Integer> inventory;


    public Inventory(){
        inventory = new HashMap<>();
    }

    // addItem(), updateItem(), listInventory(), buyItem(), addToCart()

    public void addItem(String product, String id, int count){
        Inventory.Tuple obj = new Inventory.Tuple(product, id);
        if(!inventory.containsKey(obj)){
            inventory.put(obj,count);
            catalog.put(obj,count);
            System.out.println(inventory);
        }else{
            System.out.println("this product exists you can update !!");
        }
    }

    public static HashMap<Buyer,Inventory> addToCart(String product, Buyer obj,String sellerId, int count, Inventory sellerInventory){

        Inventory.Tuple pro = new Inventory.Tuple(product, sellerId);
        if(catalog.containsKey(pro)){
            int productCount = catalog.get(pro);
            if(count <= productCount){
                int res = productCount - count;


                obj.cart.put(pro,count);
                sellerInventory.updateItem(product,sellerId,res);
            }
            // sellerInventory.updateItem(product,sellerId,0);
        }
        HashMap<Buyer,Inventory> f = new HashMap<Buyer,Inventory>();
        f.put(obj,sellerInventory);
        return f;
    }

    public void removeItem(){
        System.out.println("yes ill remove");
    }

    public void updateItem(String product, String hash, int count){
        Tuple obj = new Tuple(product, hash);
        if(inventory.containsKey(obj)){
            inventory.remove(obj);
            inventory.put(obj,count);
            catalog.remove(obj);
            catalog.put(obj,count);

        }else{
            System.out.println("this product doesnt exists you can add!!");
        }
    }



    public void listInventory(){

        if(inventory.size() > 0){

            for (Map.Entry<Tuple, Integer> entry : inventory.entrySet()) {
                Tuple key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }else{
            System.out.println("inventory is empty!!");
        }
    }


}
