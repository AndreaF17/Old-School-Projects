package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import classes.Restaurant;
import classes.Review;
import classes.User;

/** Classe responsabile per l'accesso ai file dati */
public class FileManager {
    /**
     * Memorizza un nuovo utente nel file Utenti.dati
     * @param user Istanza della classe User da salvare sul file
     * @return Un valore booleano che indica l'esito del salvataggio */
    public static boolean SaveUser(User user) {
        try {
            String filePath = "data" + File.separator + "Utenti.dati";
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(user.toString());
            bw.close();

            return true;

        } catch(IOException e) {
            /* Exception handling */
            return false;
        }
    }

        /**
     * Memorizza un nuovo ristorante nel file EatAdvisor.dati
     * @param restaurant Istanza della classe Restaurant da salvare sul file
     * @return Un valore booleano che indica l'esito del salvataggio */
    public static boolean SaveRestaurant(Restaurant restaurant) {
        try {
            String filePath = "data" + File.separator + "EatAdvisor.dati";
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(restaurant.toString());
            bw.close();

            return true;

        } catch(IOException e) {
            /* Exception handling */
            return false;
        }
    }

    /**
     * Memorizza una nuova recensione di un utente ad un ristorante del file EatAdvisor.dati
     * @param review Istanza della classe Review, oggetto recensione da memorizzare
     * @return Un valore booleano che indica l'esito del salvataggio */
    public static boolean SaveRestaurantReview(Review review) {
        try {
            String filePath = "data" + File.separator + "EatAdvisor.dati";
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(review.toString());
            bw.close();

            return true;

        } catch(IOException e) {
            /* Exception handling */
            return false;
        }
    }

    public static String[] GetRestaurants() {
        String[] allRecords = GetFileRecords("EatAdvisor.dati");
        List<String> restaurants = new ArrayList<String>();        

        for(String record: allRecords)
            if(record.split("\\|").length > 4) restaurants.add(record);

        String[] result = new String[restaurants.size()];

        for(int i = 0; i < restaurants.size(); i++)
            result[i] = restaurants.get(i);

        return result;
    }

    /**
     * Restituisce le recensioni di un ristorante
     * @param restId ID del ristorante di cui si vogliono ottenere le recensioni
     * @return Array di stringhe contenete le recensioni */
    public static String[] GetRestaurantReviews(int restId) {
        List<String> allRecords = Arrays.asList(GetFileRecords("EatAdvisor.dati"));
        
        Predicate<String> byID = record -> Integer.parseInt(record.split("\\|")[0]) == restId;
        List<String> reviews = allRecords.stream()
            .filter(byID)
            .collect(Collectors.toList());

        reviews.remove(0);

        String[] result = new String[reviews.size()];

        for(int i = 0; i < reviews.size(); i++)
            result[i] = reviews.get(i);

        return result;
    }

    /**
     * Legge e restituisce l'intero contenuto di un file di testo
     * @param fileName Nome del file da leggere
     * @return Una stringa contenente l'intero contenuto del file*/
    public static String ReadFile(String fileName) {
        try {
            // Stringa in cui verranno man mano inseriti i record
            String data = "";
            // Costruzione del path 
            String filePath = "data" + File.separator + fileName;
            // Istanza del file da leggere
            File file = new File(filePath);
            // Oggetto per la lettura ottimizzata da file di testo
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Finché ci sono righe da leggere, leggile e mettile nella stringa "data"
            while(br.ready())
                data += br.readLine() + "\n";
            
            // Una volta finito di leggere, chiudo il reader
            br.close();
            // Ritorno la stringa di dati
            return data;

        } catch(FileNotFoundException e) {
            /* Exception handling */
        } catch(IOException e) {
            /* Exception handling */
        }

        return "Error";
    }

    /**
     * Legge un file e restituisce i record in un array di stringhe
     * @param fileName Nome del file da leggere
     * @return Array di stringhe contenente i record */
    public static String[] GetFileRecords(String fileName) {
        try {
            // Lettura completa del file
            String data = ReadFile(fileName);
            // Divisione del contenuto in record
            String[] records = data.split("\n");

            // Ritorno dei record
            return records;

        } catch(Exception e) {
            /* Exception handling */
        }

        return new String[] {"Error"};
    }

    /**
     * Restituisce il record presente nel file specificato con l'id specificato
     * @param fileName Nome del file da leggere
     * @param id Identificatore univoco del record da ricercare nel file
     * @return Stringa contenente il record avente l'id indicato se presente, null altrimenti */
    public static String GetRecordFromID(String fileName, String id) {
        
        // Itero nei record del file specificato
        for(String record: GetFileRecords(fileName)) {
            // Splitto i campi del record corrente
            String[] fields = record.split("\\|");
            // Controllo se il campo 0 del record, il quale contiene
            // per convenzione l'id univoco del record, è uguale
            // all'id che è stato passato come argomento al metodo,
            // in quel caso ritorno il record corrente.
            if(fields[0].equals(id)) return record;
        }

        // Non è stato trovato alcun record nel file avente quell'id
        return null;
    }
    /**
     * Controlla se il file e' vuoto
     * @param fileName Nome del File da controllare
     * @return (boolean) */
    public static boolean FileIsEmpty(String fileName) {
        if(ReadFile(fileName).length()==0){return true;}else{return false;}
    }
}