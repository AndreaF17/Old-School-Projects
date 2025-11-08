package src.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import src.classes.*;
import src.classes.Address.TypeAddress;
import src.classes.Restaurant.TypeRestaurant;

/** Classe responsabile per l'accesso ai file dati */
public class FileManager {

    /**
     * Ottiene il path del progetto
     * @return Path del progetto */
    public static String GetProjectPath() {
        String projectDir = "";
        try {
            projectDir = (new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile()).getPath();
        } catch(Exception e) {
            System.out.println("Errore caricamento :27");
        }
        return projectDir;
    }

    /**
     * Memorizza un nuovo utente nel file Utenti.dati
     * @param user Istanza della classe User da salvare sul file
     * @return Un valore booleano che indica l'esito del salvataggio */
    public static boolean SaveUser(User user) {
        try {
            String filePath = "/data" + File.separator + "Utenti.dati";
            String fullPath = GetProjectPath()+filePath;
            BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath, true));
            bw.write(user.toString());
            bw.close();

            return true;

        } catch(IOException e) {
            System.out.println("Errore file manager :47");
            return false;
        }
    }

    /**
     * Memorizza un nuovo ristorante nel file EatAdvisor.dati
     * @param restaurant Istanza della classe Restaurant da salvare sul file
     * @return Un valore booleano che indica l'esito del salvataggio */
    public static boolean SaveRestaurant(Restaurant restaurant) {
        try {
            String filePath = "/data" + File.separator + "EatAdvisor.dati";
            String fullPath = GetProjectPath()+filePath;
            BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath, true));
            bw.write(restaurant.toString());
            bw.close();

            return true;

        } catch(IOException e) {
            System.out.println("Errore file manager :67");
            return false;
        }
    }

    /**
     * Memorizza una nuova recensione di un utente ad un ristorante del file EatAdvisor.dati
     * @param review Istanza della classe Review, oggetto recensione da memorizzare
     * @return Un valore booleano che indica l'esito del salvataggio */
    public static boolean SaveRestaurantReview(Review review) {
        try {
            String filePath = "/data" + File.separator + "EatAdvisor.dati";
            String fullPath = GetProjectPath()+filePath;
            BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath, true));
            bw.write(review.toString());
            bw.close();

            return true;

        } catch(IOException e) {
            System.out.println("Errore file manager :87");
            return false;
        }
    }

    /**
     * Ottiene una lista di istanze della classe Restaurant, contenute nel file EatAdvisor.dati
     * @return Lista di Restaurant */
    public static List<Restaurant> GetRestaurants() {
        String[] records = GetFileRecords("EatAdvisor.dati");
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
    
        /* FORMATO RECORD RISTORANTE */
        /* 2|Mesopotamia|Via|Isonzo|10|Azzate|VA|21022|3883085877|https://www.google.it/|Fusion */
        for(String record: records) {
            String[] fields = record.split("\\|");

            if(fields.length > 5) {
                TypeAddress typeAddress = Integer.parseInt(fields[4]) == 0 ? TypeAddress.Via : TypeAddress.Piazza;
                Address address = new Address(typeAddress, fields[3], Integer.parseInt(fields[4]), fields[5], fields[6], Integer.parseInt(fields[7]));

                try {
                    URL website = new URL(fields[9]);

                    TypeRestaurant typeRestaurant = TypeRestaurant.INSTANCE;
                    if      (fields[10].equals("Etnico"))   typeRestaurant = TypeRestaurant.Etnico;
                    else if (fields[10].equals("Italiano")) typeRestaurant = TypeRestaurant.Italiano;
                    else if (fields[10].equals("Fusion"))   typeRestaurant = TypeRestaurant.Fusion;

                    Restaurant restaurant = new Restaurant(Integer.parseInt(fields[0]), fields[1], address, Long.parseLong(fields[8]), website, typeRestaurant);
                    restaurants.add(restaurant);

                } catch(MalformedURLException e) {
                    System.out.println("Errore file manager :120");
                    System.err.println(e);
                }
            }
        }

        return restaurants;
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
            String filePath = "/data" + File.separator + fileName;

            String fullPath = GetProjectPath()+filePath;

            // Oggetto per la lettura ottimizzata da file di testo
            BufferedReader br = new BufferedReader(new FileReader(new File(fullPath)));

            // Finché ci sono righe da leggere, leggile e mettile nella stringa "data"
            while(br.ready())
                data += br.readLine() + "\n";
            
            // Una volta finito di leggere, chiudo il reader
            br.close();
            // Ritorno la stringa di dati
            return data;

        } catch(FileNotFoundException e) {
            System.out.println("Errore file manager :178");
        } catch(IOException e) {
            System.out.println("Errore file manager :180");
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

            if(records[0].split("\\|").length <= 1) return new String[]{};

            // Ritorno dei record
            return records;

        } catch(Exception e) {
            System.out.println("Errore file manager :203");
            return new String[] {};
        }
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