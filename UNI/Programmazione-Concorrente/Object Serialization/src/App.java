import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import Object.Person;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "Prova.txt";
        Person person = new Person("Andrea", "Ferrario");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(person);
            out.close();
            System.out.println("Serialization completed!");
        } catch (IOException e) { 
            e.printStackTrace();
        }

        
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            person = (Person) in.readObject();
            in.close();
            System.out.println("De-serialization completed!");
            System.out.println("Name: " + person.getFirstName() + " Last name: " + person.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
