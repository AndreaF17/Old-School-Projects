package clienti;

import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import util.FileManager;
import util.Security;
import classes.*;
import classes.Address.TypeAddress;
import classes.Restaurant.TypeRestaurant;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Clienti {
    public static void main(String[] args) {
        // Oggetto utente che verrà istanziato se il login
        // verrà effettuato correttamente
        User user = null;
        // Variabile scelta utilizzata nei menù
        int choice;

        // Variabile per l'uscita dai menù,
        // verrà messa a false se sarà necessario ristampare
        // il menù di accesso iniziale e richiedere di eseguire
        // una delle azioni, false se si potrà proseguire alla
        // prossima parte del programma
        boolean exit = true;

        // Oggetto scanner utilizzato per l'input
        Scanner in = new Scanner(System.in);

        // Variabili utilizzate in più case
        String nickname;
        String password;

        // Ciclo di stampa del menù ed esecuzione delle varie funzioni
        do {
            // Stampa del menù di accesso
            PrintAccessMenu();
            // Lettura della scelta dell'azione da eseguire
            choice = in.nextInt();

            // Esecuzione delle funzioni in base alla scelta dell'utente
            switch(choice) {
                // LOGIN
                case 1:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                    // Richiesta e lettura del nickname
                    System.out.print("Inserisci nickname: ");
                    nickname = in.nextLine();

                    // Richiesta e lettura password
                    System.out.print("Inserisci password: ");
                    password = in.nextLine();

                    // 1. Assegno il valore di ritorno di Authenticate a exit,
                    //    se l'accesso è riuscito, è possibile uscire e proseguire
                    //    con il programma, altrimenti viene ristampato il menu 
                    //    dell'accesso iniziale.
                    // 2. Controllo l'esito del metodo di accesso e stampo un
                    //    messaggio appropriato
                    if((exit = AuthenticateUser(nickname, password))) {
                        // Ottengo i dati dell'utente per istanziare l'oggetto "user"
                        String userRecord = FileManager.GetRecordFromID("Utenti.dati", nickname);
                        String[] fields = userRecord.split("\\|");
                        user = new User(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6]);

                        System.out.println("\nAccesso effettuato correttamente come: " + fields[0]);
                    }
                    else System.out.println("\nLe credenziali inserite sono errate.");

                    break;


                // SIGNUP
                case 2:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                    // In ambo gli esiti della registrazione, bisognerà ristampare il menù
					// se la registrazione andrà a buon fine, l'utente dovrà accedere,
					// se la registrazione non andrà a buon fine, verrà comunque ristampato
					// il menù di accesso iniziale.
                    exit = false;
                    
                    /*------------------------------------------------------*/
					/* Input di tutti i dati richiesti per la registrazione */
					/*------------------------------------------------------*/

                    // 0. NICKNAME
                    // Variabile per il controllo della presenza nel file utenti
                    // di un utente avente lo stesso nickname inserito
                    boolean alreadyTaken;
                    do {
                        // Richiesta e lettura del nickname
                        System.out.print("\nInserisci nickname: ");
                        nickname = in.nextLine();

                        // Inizializzazione della variabile usata nel controllo
                        alreadyTaken = FileManager.GetRecordFromID("Utenti.dati", nickname) != null;

                        // Controllo del valore
                        if(alreadyTaken) System.err.println("Il nickname inserito è già stato utilizzato");
                    } while(alreadyTaken);

                    // 1. NOME
                    // Richiesta e lettura del nome
                    System.out.print("Inserisci nome: ");
                    String name = in.nextLine();

                    // 2. COGNOME
                    // Richiesta e lettura del cognome
                    System.out.print("Inserisci cognome: ");
                    String surname = in.nextLine();

                    // 3. COMUNE
                    // Richiesta e lettura del comune di residenza
                    System.out.print("Inserisci comune: ");
                    String town = in.nextLine();

                    // 4. PROVINCIA
                    // Richiesta e lettura della provincia
                    System.out.print("Inserisci provincia: ");
                    String district = in.nextLine();

                    // 5. EMAIL
                    // Richiesta e lettura dell'indirizzo email
                    System.out.print("Inserisci email: ");
                    String email = in.nextLine();

                    // 6. PASSWORD (con hash)
                    // Richiesta, lettura e cifratura della password
                    System.out.print("Inserisci password: ");
                    password = Security.GetHash( in.nextLine() );

                    // Istanziazione dell'oggetto utente
                    User newUser = new User(nickname,name,surname,town,district,email,password);

                    // Chiamata del metodo per la registrazione
                    if(RegisterNewUser(newUser))
                        System.out.println("Registrazione completata! Ora puoi accedere.");
                    else
                        System.err.println("Registrazione fallita, ritenta.");

                    break;

                // GUEST
                case 3:
                    System.out.println("\nProcedendo come ospite, alcune funzioni saranno disabilitate.");
                    exit = true;
                    break;
                
                default:
                    System.err.println("\nInserisci una scelta valida!");
                    exit = false;
                    break;
            }
        } while(!exit);

        // Lista originale degli oggetti ristoranti
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        // Lista risultante dalle operazioni di filtraggio
        List<Restaurant> filteredList = new ArrayList<Restaurant>();
        // Record ristoranti sotto forma di stringhe
        String[] records = FileManager.GetRestaurants();

        /* FORMATO RECORD RISTORANTE */
        /* 2|Mesopotamia|Via|Isonzo|10|Azzate|VA|21022|3883085877|https://www.google.it/|Fusion */
        for(String record: records) {
            String[] fields = record.split("\\|");
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
                System.err.println(e);
            }
        }

        // SECONDO MENU
        do {
            // Variabili utilizzate in più case
            String town, typology;
            

            // Stampa del menù di ricerca
            PrintSearchMenu();
            // Lettura della scelta dell'azione da eseguire
            choice = in.nextInt();

            // Esecuzione delle funzioni in base alla scelta dell'utente
            switch(choice) {
                // Ricerca ristoranti per comune
                case 1:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                    // Richiesta e lettura del comune
                    System.out.print("Inserisci comune: ");
                    town = in.nextLine();

                    filteredList = FilterListBy(restaurants, "Town", new String[] {town});

                    System.out.println("\nRistoranti nel comune di " + town + ":\n");
                    for(Restaurant restaurant: filteredList) {
                        System.out.println("  > " + filteredList.indexOf(restaurant) + " - " + restaurant.GetName());
                    }

                    break;

                // Ricerca ristoranti per tipologia
                case 2:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                    // Richiesta e lettura della tipologia
                    System.out.print("Inserisci tipologia (Etnico, Italiano, Fusion): ");
                    typology = in.nextLine();

                    if(!typology.equals("Etnico") && !typology.equals("Italiano") && !typology.equals("Fusion"))
                        System.out.println("Tipologia non valida!");
                    else {
                        filteredList = FilterListBy(restaurants, "Typology", new String[] {typology});

                        System.out.println("\nRistoranti di tipologia " + typology + ":\n");
                        for(Restaurant restaurant: filteredList) {
                            System.out.println("  > " + filteredList.indexOf(restaurant) + " - " + restaurant.GetName());
                        }
                    }
                    break;

                // Ricerca ristoranti per nome
                case 3:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                     // Richiesta e lettura del nome
                     System.out.print("Inserisci nome: ");
                     String name = in.nextLine();
 
                     filteredList = FilterListBy(restaurants, "Name", new String[] {name});
 
                     System.out.println("\nRistoranti trovati:\n");
                     for(Restaurant restaurant: filteredList) {
                         System.out.println("  > " + filteredList.indexOf(restaurant) + " - " + restaurant.GetName());
                     }

                    break;

                // Ricerca ristoranti per comune e tipologia
                case 4:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                    // Richiesta e lettura del comune
                    System.out.print("Inserisci comune: ");
                    town = in.nextLine();

                    // Richiesta e lettura della tipologia
                    System.out.print("Inserisci tipologia (Etnico, Italiano, Fusion): ");
                    typology = in.nextLine();

                    if(!typology.equals("Etnico") && !typology.equals("Italiano") && !typology.equals("Fusion"))
                        System.out.println("Tipologia non valida!");
                    else {
                        filteredList = FilterListBy(restaurants, "Town&Typology", new String[] {town, typology});

                        System.out.println("\nRistoranti nel comune " + town + " di tipologia " + typology + ":\n");
                        for(Restaurant restaurant: filteredList) {
                            System.out.println("  > " + filteredList.indexOf(restaurant) + " - " + restaurant.GetName());
                        }
                    }
                    break;
            }

            System.out.print("\nSeleziona uno dei ristoranti: ");
            choice = in.nextInt();

            if(choice < 0 || choice > filteredList.size()-1) System.err.println("Scelta non valida");

            else {
                Restaurant selectedRestaurant = filteredList.get(choice);
                PrintRestaurantInfo(selectedRestaurant);

                System.out.println("\n 1) Inserisci giudizio");
                System.out.println(" 2) Torna indietro\n");
                System.out.print("Inserisci scelta: ");

                exit = false;
                choice = in.nextInt();
                in.nextLine();

                if(choice == 1) {
                    if(user == null) { 
                        exit = true;
                        System.err.println("Per inserire giudizi devi prima accedere."); 
                    } else {
                        // Inserimento giudizio
                        System.out.print("Inserisci il valore della recensione (1-5): ");
                        int rating = in.nextInt();
                        in.nextLine();
                        System.out.println("Inserisci il commento: \n");
                        String description = in.nextLine();

                        Review review = new Review(selectedRestaurant.GetId(), user.GetNickname(), (byte)rating, description);

                        if(FileManager.SaveRestaurantReview(review))
                            System.out.println("\nRecensione registrata con successo!");
                        else 
                            System.err.println("\nRegistrazione della recensione non riuscita");
                    }
                }
            }

        } while(!exit);

        System.out.println("\n");
        in.close();
    }

    /**
     * Stampa del primo menù di accesso */
    public static void PrintAccessMenu() {
        System.out.println("\n+------------- MENU -------------+");
        System.out.println("  1) Accedi");
        System.out.println("  2) Registrati");
        System.out.println("  3) Procedi come ospite");
        System.out.println("+--------------------------------+");
        System.out.print("\nInserisci scelta: ");
    }

    /**
     * Stampa del secondo menù delle funzioni */
    public static void PrintSearchMenu() {
        System.out.println("\n+------------- MENU DI RICERCA -------------+");
        System.out.println("  1) Per comune");
        System.out.println("  2) Per tipologia");
        System.out.println("  3) Per nome");
        System.out.println("  4) Per comune e tipologia");
        System.out.println("+-------------------------------------------+");
        System.out.print("\nInserisci scelta: ");
    }

    /**
     * Stampa le informazioni relative al ristorante selezionato
     * @param restaurant Ristorante da visualizzare */
    public static void PrintRestaurantInfo(Restaurant restaurant) {
        /* 2|Mesopotamia|Via|Isonzo|10|Azzate|VA|21022|3883085877|https://www.google.it/|Fusion */
        String[] addrFields = restaurant.GetAddress().toString().split("\\|");
        String address = String.format("%s %s, %s, %s, %s - %s", addrFields[0],addrFields[1],addrFields[2],addrFields[3],addrFields[4],addrFields[5]);

        String[] restReviews = FileManager.GetRestaurantReviews(restaurant.GetId());
        int[] ratingValues = new int[5];
        float[] ratingPercentages = new float[5];

        for(String review: restReviews) {
            String[] revFields = review.split("\\|");
            ratingValues[Integer.parseInt(revFields[2])-1]++;
        }

        for(int i = 0; i < 5; i++)
            ratingPercentages[i] = (float)ratingValues[i] / (float)restReviews.length;

        System.out.println("\n+-------------- INFO RISTORANTE --------------+");
        System.out.println("  Nome:      " + restaurant.GetName());
        System.out.println("  Tipologia: " + restaurant.GetType().toString());
        System.out.println("  Indirizzo: " + address);
        System.out.println("  Numero:    " + restaurant.GetPhoneNumber().toString());
        System.out.println("  Sito web:  " + restaurant.GetURL().toString());
        
        System.out.println("\n  RECENSIONI:");

        for(int i = 0; i < 5; i++) {
            System.out.print("\n " + (i+1) + " stella/e: ");
            for(int s = 0; s < Math.floor(ratingPercentages[i]*30); s++)
                System.out.print("*");
        }

        System.out.println("\n");

        for(String review: restReviews) {
            String[] revFields = review.split("\\|");
            Review rev = new Review(Integer.parseInt(revFields[0]), revFields[1], (byte)Integer.parseInt(revFields[2]), revFields[3]);
            PrintRestaurantReview(rev);
        }

        System.out.println("\n+---------------------------------------------+");
    }

    /**
     * Stampa le informazioni relative ad una recensione
     * @param review Istanza della classe Review da visualizzare */
    public static void PrintRestaurantReview(Review review) {
        System.out.println("\n+---------- RECENSIONE ------------+");
        System.out.println(" " + review.GetFkUser() + " - " + review.GetStars() + "stella/e:");
        System.out.println(" " + review.GetReview());
        System.out.println("+----------------------------------+");
    }

    /**
     * Autentica l'account di un cliente
     * @param nickname Nickname da autenticare
     * @param password Password da autenticare
     * @return Esito dell'autenticazione (boolean) */
    public static boolean AuthenticateUser(String nickname, String password) {
        // Array di stringhe contenente l'intero contenuto del file Utenti.dati 
        String[] records = FileManager.GetFileRecords("Utenti.dati");

        // Se la stringa in posizione 0 ritornata dal metodo per leggere
        // dal file è "Error" ritorno (false), l'autenticazione non è
        // andata a buon fine, altrimenti continuo
        if(records[0].equals("Error")) return false;

        // Per ognuno dei record (utenti) controllo se il nickname e
        // la password inseriti dall'utente che desidera accedere corrispondono
        for(String record: records) {
            // Splitto il record corrente per i suoi campi
            String[] fields = record.split("\\|");
            // Field 0 => Nickname, Field 6 => Hashed password
            String inputPasswordHash = Security.GetHash(password);
            if(fields[0].equals(nickname) && fields[6].equals(inputPasswordHash)) {
                // Accesso riuscito correttamente
                return true;
            }
        }

        return false;
    }
    
    /**
     * Registra un nuovo utente inserendolo nel file "Utenti.dati"
     * @param user Oggetto User da registrare
     * @return Esito della registrazione (boolean) */
    public static boolean RegisterNewUser(User user) {
        return FileManager.SaveUser(user);
    }

    /**
     * Filtra una lista data in input in base ad un parametro
     * @param list Lista di oggetti Restaurant originale da filtrare
     * @param filterType Stringa rappresentante l'attributo da filtrare: (Town, Typology, Name, Town&Typology)
     * @param values Array di stringhe contenenti i valori per cui filtrare
     * @return Lista di ristoranti filtrata */
    public static List<Restaurant> FilterListBy(List<Restaurant> list, String filterType, String[] values) {
        /* FORMATO RECORD RISTORANTE */
        /* 2|Mesopotamia|Via|Isonzo|10|Azzate|VA|21022|3883085877|https://www.google.it/|Fusion */
        
        if(list.isEmpty()) return new ArrayList<Restaurant>();
        
        Predicate<Restaurant> filter;

        switch(filterType) {
            case "Town": 
                filter = restaurant -> restaurant.GetAddress().GetTownName().equals(values[0]);
                break;

            case "Typology":
                filter = restaurant -> restaurant.GetType().toString().equals(values[0]);
                break;

            case "Name":
                filter = restaurant -> restaurant.GetName().contains(values[0]);
                break;

            case "Town&Typology":
                list = FilterListBy(list, "Town", new String[] {values[0]});
                if(list.isEmpty()) return new ArrayList<Restaurant>();
                filter = restaurant -> restaurant.GetType().toString().equals(values[1]);
                break;

            default:
                filter = restaurant -> restaurant.toString() != null;
                break;
        }

        List<Restaurant> filteredList = list.stream()
            .filter(filter)
            .collect(Collectors.toList());

        return filteredList;
    }
}