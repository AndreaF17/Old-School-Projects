package src.gui.pages;

import java.awt.*;

import src.classes.Address;
import src.classes.Restaurant;
import src.classes.User;
import src.gui.components.FLabel;
import src.gui.components.FPage;
import src.util.FileManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class C_RestaurantInfo {
    
    private FPage page;
    private GridBagConstraints gbc;
    public FLabel backIcon_lbl;
    private FLabel userIcon_lbl;
    private FLabel userName_lbl;
    private FLabel restName_lbl;
    private FLabel restImage_lbl;
    private FLabel ratings_lbl;
    private FLabel location_lbl;
    private FLabel website_lbl;
    private FLabel telephone_lbl;
    private FLabel restTypology_lbl;
    private FPage body;
    public FLabel ratingsText_lbl;
    public String action;
    private Restaurant restaurant;

    public FPage getPage() {
        return page;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public C_RestaurantInfo(User user, Restaurant restaurant) {
        //Setup base della pagina
        page = new FPage(new BorderLayout());
        this.restaurant = restaurant;
        
        int topMargin = 20;

        FPage header = new FPage();
        gbc = new GridBagConstraints();
        //Label con immagine freccia sx
        try {
            backIcon_lbl = new FLabel("assets/BackIcon.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :56");
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.06;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(topMargin,0,0,0);
        setGridCoordinatesXY(gbc, 0, 0);
        header.add(backIcon_lbl, gbc);

        //Label di gap
        FLabel gap_lbl = new FLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(topMargin,0,0,0);
        gbc.weightx = 0.8;
        setGridCoordinatesXY(gbc, 1, 0); 
        header.add(gap_lbl, gbc);

        //Label con l'immagine guest o user
        String iconPath = user != null ? "assets/UserIcon.png" : "assets/GuestIcon.png";
        try {
            userIcon_lbl = new FLabel(iconPath);
        } catch(IOException e) {
            System.out.println("Errore caricamento :78");
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.06;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(topMargin,0,0,0);
        setGridCoordinatesXY(gbc, 2, 0);
        header.add(userIcon_lbl, gbc);

        //Label per display username
        String username = user != null ? user.GetNickname() : "Guest";
        userName_lbl = new FLabel(username, new Font("Manrope Regular", Font.PLAIN, 25));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.weightx = 0.03;
        setGridCoordinatesXY(gbc, 3, 0);
        gbc.insets = new Insets(topMargin,0,0,0);
        header.add(userName_lbl, gbc);
        
        page.add(header, BorderLayout.PAGE_START);

        body = new FPage();
        gbc = new GridBagConstraints();

        //Label con l'immagine del ristorante
        String imagePath = null;

        if(restaurant.GetType().toString().equals("Italiano")) 
            imagePath = "assets/Pizza.png";
        else if(restaurant.GetType().toString().equals("Etnico")) 
            imagePath = "assets/Hamburger.png";
        else if(restaurant.GetType().toString().equals("Fusion")) 
            imagePath = "assets/Sushi.png";

        try {
            restImage_lbl = new FLabel(imagePath);
        } catch(IOException e) {
            System.out.println("Errore caricamento :115");
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,100,0);
        setGridCoordinatesXY(gbc, 0, 0);
        body.add(restImage_lbl, gbc);

        FPage rightSide = new FPage();

        //Label per display nome ristorante
        restName_lbl = new FLabel(restaurant.GetName(), new Font("Manrope Medium", Font.PLAIN, 80));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setGridCoordinatesXY(gbc, 0, 0);
        gbc.insets = new Insets(0,50,0,0);
        gbc.gridwidth = 2;
        rightSide.add(restName_lbl, gbc);

        gbc = new GridBagConstraints();

        String[] reviews = FileManager.GetRestaurantReviews(restaurant.GetId());
        int numberOfReviews = reviews.length;
        int numberOfStars = 0;
        
        if(numberOfReviews > 0) {
            int sum = 0;
            for(String review: reviews) {
                String[] fields = review.split("\\|");
                sum += Integer.parseInt(fields[2]);
            }
            numberOfStars = (int)Math.floor(sum/numberOfReviews);
        }

        //Label con immagine stelle di rating
        try {
            ratings_lbl = new FLabel("assets/" + numberOfStars + "Stars.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :152");
        }
        gbc.insets = new Insets(0, 50, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 0, 1);
        rightSide.add(ratings_lbl, gbc);
        
        action = FileManager.GetRestaurantReviews(restaurant.GetId()).length == 0 ? "inserisci" : "visualizza";

        ratingsText_lbl = new FLabel("<html>" + numberOfReviews + " recensioni, <font color='blue'>"+action+"</font></html>", new Font("Manrope Regular", Font.PLAIN, 20));
        gbc.insets = new Insets(0, 15, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 1, 1);
        rightSide.add(ratingsText_lbl, gbc);
        //Azioni della label
        ratingsText_lbl.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent e) {
                //"Ospite" scritto in rosso se ci si avvicina con il mouse 
                ratingsText_lbl.setText("<html>" + numberOfReviews + " recensioni, <font color='red'>"+action+"</font></html>");
            }
            public void mouseExited(final MouseEvent e) {
                //"Ospite" scritto in blu se ci si allontana con il mouse
                ratingsText_lbl.setText("<html>" + numberOfReviews + " recensioni, <font color='blue'>"+action+"</font></html>");
            }
        });

        FPage infoBox = new FPage();
        gbc = new GridBagConstraints();

        //Label con immagine stelle di rating
        try {
            location_lbl = new FLabel("assets/LocationIcon.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :185");
        }
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 0, 0);
        infoBox.add(location_lbl, gbc);

        Address address = restaurant.GetAddress();
        String addressType = address.GetType().toString();
        String addressName = address.GetStreetName();
        String addressNumber = String.valueOf(address.GetStreetNumber());
        String zipcode = String.valueOf(address.GetZipCode());
        String town = address.GetTownName();
        String district = address.GetDistrict();

        String restAddress = String.format("%s %s %s, %s %s %s", addressType, addressName, addressNumber, zipcode, town, district);
        FLabel locationTxt_lbl = new FLabel(restAddress, new Font("Manrope Regular", Font.PLAIN, 20));
        gbc.insets = new Insets(0, 15, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 1, 0);
        infoBox.add(locationTxt_lbl, gbc);

        try {
            website_lbl = new FLabel("assets/WebsiteIcon.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :210");
        }
        gbc.insets = new Insets(15, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 0, 1);
        infoBox.add(website_lbl, gbc);

        String website = restaurant.GetURL().toString();
        FLabel websiteTxt_lbl = new FLabel(website, new Font("Manrope Regular", Font.PLAIN, 20));
        gbc.insets = new Insets(15, 15, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 1, 1);
        infoBox.add(websiteTxt_lbl, gbc);

        try {
            telephone_lbl = new FLabel("assets/TelephoneIcon.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :227");
        }
        gbc.insets = new Insets(15, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 0, 2);
        infoBox.add(telephone_lbl, gbc);

        String telnumber = restaurant.GetPhoneNumber().toString();
        FLabel telephoneTxt_lbl = new FLabel(telnumber, new Font("Manrope Regular", Font.PLAIN, 20));
        gbc.insets = new Insets(15, 15, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 1, 2);
        infoBox.add(telephoneTxt_lbl, gbc);
        
        try {
            restTypology_lbl = new FLabel("assets/RestTypologyIcon.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :244");
        }
        gbc.insets = new Insets(15, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 0, 3);
        infoBox.add(restTypology_lbl, gbc);

        String typology = restaurant.GetType().toString();
        FLabel restTypologyTxt_lbl = new FLabel(typology, new Font("Manrope Regular", Font.PLAIN, 20));
        gbc.insets = new Insets(15, 15, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 1, 3);
        infoBox.add(restTypologyTxt_lbl, gbc);
        
        gbc = new GridBagConstraints();
        setGridCoordinatesXY(gbc, 0, 2);
        gbc.insets = new Insets(25, 50, 100, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        rightSide.add(infoBox, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,50,70,0);
        setGridCoordinatesXY(gbc, 1, 0);
        body.add(rightSide, gbc);

        page.add(body, BorderLayout.CENTER);
    }

    /**
     * Metodo per settare le coordinate più efficacemente
     * @param gbc Istanza di GridBagConstraints
     * @param x Colonna
     * @param y Riga */
    public static void setGridCoordinatesXY(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
    }
}