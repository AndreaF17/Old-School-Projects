package ristoratori;
import util.FileManager;
import java.net.*;
import java.util.*;
import classes.Address.*;
import classes.*;
import classes.Restaurant.*;

public class Ristoratori {

    public static void main(String[] args) {
        // Variabile scelta utilizzata nei menù
        int choice;

        // Variabile per l'uscita dai menù,
        // verrà messa a false se sarà necessario ristampare il menù di accesso
        // e richiedere di eseguire una delle azioni, false se si può proseguire
        // alla prossima parte del programma
        boolean exit = true;

        // Oggetto scanner utilizzato per l'input
        Scanner in = new Scanner(System.in);
        // Ciclo di stampa del menù e esecuzione delle varie funzioni
        do {
            // Stampa del menù di accesso
            PrintMenu();

            // Lettura della scelta dell'azione da eseguire dall'utente
            choice = in.nextInt();

            // Esecuzione delle funzioni in base alla scelta dell'utente
            switch (choice) {
                // Registrazione del ristorante
                case 1:
                    // Re-istanziazione dell'oggetto scanner
                    in = new Scanner(System.in);

                    //Inizio inizializzazione di Address
                    System.out.println("Inserisci l'indirizzo del ristorante:");
                        // Richiesta e lettura del tipo di indirizzo + controllo
                        TypeAddress typeAddress = TypeAddress.INSTANCE;
                        int type_choice;
                        do {
                            System.out.print("Inserisci il tipo (0 = Via, 1 = Piazza): ");
                            type_choice = in.nextInt();
                            if (type_choice == 0) {
                                typeAddress = TypeAddress.Via;
                            }
                            if (type_choice == 1) {
                                typeAddress = TypeAddress.Piazza;
                            }
                        } while (typeAddress.equals(TypeAddress.INSTANCE));
                        in.nextLine();

                        // Richiesta e lettura del nome della via + controllo
                        String name;
                        do {
                            System.out.print("Inserisci il nome della "+ typeAddress.name() + ": ");
                            name = in.nextLine();
                        } while (name == null || name.isEmpty());

                        // Richiesta e lettura del numero civico + controllo
                        int street_number;
                        do {
                            System.out.print("Inserisci il numero civico: ");
                            street_number = in.nextInt();
                            in.nextLine();
                        } while (street_number == 0);

                        // Richiesta e lettura del nome della citta' + controllo
                        String city;
                        do {
                            System.out.print("Inserisci la citta': ");
                            city = in.nextLine();
                        } while (city == null || city.equals(null));

                        // Richiesta e lettura della provincia + controllo
                        String district;
                        do {
                            System.out.print("Inserisci la sigla della provincia: ");
                            district = in.nextLine();
                        } while (district == null || district.equals(null));

                        // Richiesta e lettura del CAP + controllo
                        int zip_code;
                        do {
                            System.out.print("Inserisci il CAP: ");
                            zip_code = in.nextInt();
                            in.nextLine();
                        } while (zip_code == 0);
                    
                    // Creazione dell'oggetto indirizzo
                    Address tAddress = new Address(typeAddress, name, street_number, city, district, zip_code);
                    
                    // Inizializzazione di Ristorante
                    System.out.println("Inserisci le informazioni del ristorante:\n");
                        // Set name null
                        name = null;

                        // Richiesta e lettura del nome del ristorante + controllo
                        do {

                            System.out.print("Inserisci il nome del ristorante: ");
                            name = in.nextLine();
                        } while (name == null || name.equals(null));

                        // Richiesta e lettura del numero di telefono + controllo
                        long phone_number;
                        do {
                            System.out.print("Inserisci il numero di telefono: ");
                            phone_number = in.nextLong();
                        } while (phone_number == 0);
                        in.nextLine();

                        // Richiesta e lettura del sito web + controllo
                        String input = null;
                        java.net.URL website=null;
                        do {
                            System.out.print("Inserisci il sito web: ");
                            try {
                                website = new URL(input=in.nextLine());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                    }while(input == null || input.equals(null));
                   
                    // Richiesta e lettura della tipologia di ristorante + controllo
                    TypeRestaurant typeRestaurant=TypeRestaurant.INSTANCE;
                    do{      
                        System.out.print("Inserisci la tipologia (0 = Etnico, 1 = Fusion, 2= Italiano): ");
                        type_choice = in.nextInt();
                        if(type_choice==0){typeRestaurant = TypeRestaurant.Etnico;}
                        if(type_choice==1){typeRestaurant= TypeRestaurant.Fusion;}
                        if(type_choice==2){typeRestaurant= TypeRestaurant.Italiano;}
                        }while(typeRestaurant.equals(TypeRestaurant.INSTANCE)); 
                        in.nextLine();
                    
                    // Acquisizione del codice univoco
                    // 1. Se nel file EatAdvisor.dati non ci sono records assegno il primo id
                    // 2. Se esistono record incremento l'id finche' non ne trovo uno libero
                    int id;
                    if (FileManager.FileIsEmpty("EatAdvisor.dati")==true) {
                        id=0;
                    }else{
                        boolean alreadyTaken;
                        id=1;
                        do {
                            alreadyTaken = FileManager.GetRecordFromID("EatAdvisor.dati", Integer.toString(id)) != null;
                            if (alreadyTaken==true) { id++;}
                        } while(alreadyTaken);
                    }
                    
                // Instanzio l'oggetto Ristorante
                Restaurant tRestaurant = new Restaurant(id, name, tAddress, phone_number, website, typeRestaurant);
                
                //Effettuo il salvataggio nel file di testo
                if (FileManager.SaveRestaurant(tRestaurant)) {
                    System.out.println("Registrazione effettuata con successo");
                }else{
                    System.out.println("errore");
                }  
            break;
            //LISTA DEI RISTORANTI REGISTRATI
            case 2:

                    
            break;
            
            
            default:
				System.err.println("\nInserisci una scelta valida!");
				exit = false;
			break;
            }
        }while(!exit);
        in.close();
    }
 
 
    /** Nome: StampaMenu
	 *  Obiettivo: Stampare il menu iniziale di registrazione
	 *  Ritorno: vuoto
	 *  Argomenti: vuoto */
    private static void PrintMenu(){
        System.out.println("\n+----------- MENU -----------+\n");
		System.out.println("  1) Registra ristorante");
        System.out.println("  2) Lista ristoranti registrati");
        System.out.println("\n+----------------------------+");
        System.out.print("\n\nInserisci scelta: ");
    }
}
