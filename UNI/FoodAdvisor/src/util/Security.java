package src.util;

import java.security.MessageDigest;

/** Classe responsabile per la sicurezza dei dati memorizzati */
public class Security {

    /**
     * Cifra la stringa inserita utilizzando l'algoritmo SHA1
     * @param plaintext Stringa da cifrare
     * @return Stringa cifrata con sha1 */
    public static String GetHash(String plaintext) {
        // Per poter utilizzare gli algoritmi di hashing è
        // necessario lavorare con i bytes, quindi preleviamo
        // i bytes della stringa usando il metodo getBytes()
        byte[] inputBytes = plaintext.getBytes();
        // Inizializzazione del valore di ritorno
        String hashValue = "";
        // Utilizzo del StringBuilder per lavorare in modo più
        // efficiente sulle stringhe
        StringBuilder sb;

        try {
            // Chiamo il costruttore di StringBuilder
            sb = new StringBuilder();
            // Seleziono l'algoritmo di cifratura desiderato
            MessageDigest msgDigest = MessageDigest.getInstance("sha1");
            // Applica l'algoritmo al byte buffer
            msgDigest.update(inputBytes);

            // Completa l'algoritmo
            byte[] digestedBytes = msgDigest.digest();
            // Convertiamo i bytes in hex string
            for(byte b: digestedBytes)
                hashValue = sb.append(String.format("%x", b)).toString();

        } catch(Exception e) {
            System.out.println("Errore Security :38");
            System.exit(0);
        }

        // Ritorno la stringa in SHA1
        return hashValue;
    }
}
    
