import java.util.*;
public class User{
    public String name;
    public String id;
    public String password;
    HashMap<Inventory.Tuple,Integer> cart;
    int total = 0;
    public Inventory inventory;


    public User(String yourName, String yourId, String yourPassword){
        name = yourName;
        id = yourId;
        password = yourPassword;
    }

    public String toString(){
        return "Name: " + name + ", ID: " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id.equals(user.id);
        }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, password);
    }

}
