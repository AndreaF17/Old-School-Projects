package classes;

public class Review {
    // Foreign Key Ristorante, 0
    private int fk_restaurant;
    // Foreign Key User, 1
    private String fk_user;
    // Stars, 2
    private byte stars;
    // Recensione scritta, 3
    private String review;

    //GETTER AND SETTERS
    public int      GetFkRestaurant()   {return this.fk_restaurant; }
    public String   GetFkUser()         {return this.fk_user;       }
    public byte     GetStars()          {return this.stars;         }
    public String   GetReview()         {return this.review;        }

    private void    SetFkRestaurant (int fk_restaurant) {this.fk_restaurant=fk_restaurant; }
    private void    SetFkUser       (String fk_user)    {this.fk_user=fk_user;             }
    private void    SetStars        (byte stars)        {this.stars=stars;                 }
    private void    SetReview       (String review)     {this.review=review;               }
    
    /**
     * Costruttore della classe Address
     * @param fk_restaurant ID del ristorante per cui si sta registrando la recensione
     * @param fk_user Nickname dell'utente che sta registrando la recensione
     * @param stars Numero di stelle assegnate dall'utente al ristorante (in byte)
     * @param review Descrizione della recensione */
    public Review(int fk_restaurant, String fk_user, byte stars, String review) {
        SetFkRestaurant(fk_restaurant);
        SetFkUser(fk_user);
        SetStars(stars);
        SetReview(review);
    }


    /** Fornisce informazioni sull'istanza dell'oggetto
     * @return Stringa contenente i dati dell'oggetto */
    public String toString() {
        return String.format("%d|%s|%d|%s\n",GetFkRestaurant(),GetFkUser(),GetStars(),GetReview());
    }
}