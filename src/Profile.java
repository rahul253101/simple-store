import java.util.*;


public class Profile{

    public static class Tuple{
        String id;
        Character role;

        public Tuple(String yourId, Character yourRole){
            id = yourId;
            role = yourRole;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return id.equals(tuple.id) && role.equals(tuple.role);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, role);
        }

        @Override
        public String toString() {
            return "Tuple{id='" + id + "', role=" + role + "}";
        }
    }

    public static ArrayList<Seller> sellers = new ArrayList<>();
    public static ArrayList<Buyer> buyers = new ArrayList<>();
    public static HashMap<Tuple,User> users = new HashMap<>();


    public static void addProfile(Seller data){
        if (data != null && !sellers.contains(data)) {
            sellers.add(data);
            users.put(new Tuple(data.id,'S'), data);
            System.out.println("in");
            System.out.println(sellers.size());
            getAllSellers();
        }else{
            System.out.println("This profile already exist!!");
        }
    }

    public static void addProfile(Buyer data){
        if (data != null && !buyers.contains(data)) {
            buyers.add(data);
            users.put(new Tuple(data.id,'B'), data);
            System.out.println("in");
            getAllBuyers();
        }else{
            System.out.println("This profile already exist!!");
        }
    }


    public static void getAllSellers(){

        if (sellers.size() > 0){
            for(Seller res: sellers){
                System.out.println(res);
            }
        }else{
            System.out.println("no sellers");
        }

    }

    public static void getAllBuyers(){

        if (buyers.size() > 0){
            for(Buyer res: buyers){
            System.out.println(res);
            }
        }else{
            System.out.println("no buyers");
        }
    }


}
