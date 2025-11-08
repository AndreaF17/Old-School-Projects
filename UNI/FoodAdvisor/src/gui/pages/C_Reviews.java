package src.gui.pages;

import java.awt.*;

import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import src.classes.Restaurant;
import src.classes.User;
import src.gui.components.FButton;
import src.gui.components.FLabel;
import src.gui.components.FPage;
import src.gui.components.FProgressBar;
import src.util.FileManager;

public class C_Reviews {
    private FPage page;
    private GridBagConstraints gbc;
    public  FLabel backIcon_lbl;
    private FLabel userIcon_lbl;
    private FLabel userName_lbl;
    private FLabel restName_lbl;
    private FLabel restImage_lbl;
    private FPage body;
    public FButton insert_btn;
    
    public FPage getPage() {
        return page;
    }    

    public C_Reviews(User user, Restaurant restaurant) {
      //Setup base della pagina
      page = new FPage(new BorderLayout());
        
      int topMargin = 20;

      FPage header = new FPage();
      gbc = new GridBagConstraints();
      //Label con immagine freccia sx
      try {
          backIcon_lbl = new FLabel("assets/BackIcon.png");
      } catch(IOException e) {
        System.out.println("Errore caricamento :50");
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
        System.out.println("Errore caricamento :72");
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

      FPage subHeader = new FPage();
      gbc = new GridBagConstraints();

      String imagePath;

      if(restaurant.GetType().toString().equals("Italiano")) 
        imagePath = "assets/SmallPizza.png";
      else if(restaurant.GetType().toString().equals("Etnico"))
        imagePath = "assets/SmallHamburger.png";
      else
        imagePath = "assets/SmallSushi.png";

      try {
        restImage_lbl = new FLabel(imagePath);
      } catch(IOException e) {
        System.out.println("Errore caricamento :106");
        e.printStackTrace();
      }

      gbc.anchor = GridBagConstraints.WEST;
      gbc.insets = new Insets(0, 100, 0, 0);
      gbc.gridheight = 2;
      setGridCoordinatesXY(gbc, 0, 0);
      subHeader.add(restImage_lbl, gbc);

      gbc = new GridBagConstraints();
      //Label per display nome ristorante
      restName_lbl = new FLabel(restaurant.GetName(), new Font("Manrope Medium", Font.PLAIN, 40));
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 0);
      gbc.insets = new Insets(0,20,0,0);
      subHeader.add(restName_lbl, gbc);

      String[] reviews = FileManager.GetRestaurantReviews(restaurant.GetId());
      int[] starsDistribution = new int[5];
      double averageScore = 0;

      for(String review: reviews) {
        String[] fields = review.split("\\|");
        starsDistribution[Integer.parseInt(fields[2])-1]++;
      }

      for(int i = 0; i < 5; i++)
        averageScore += starsDistribution[i] * (i+1);

      averageScore /= reviews.length;
      averageScore = Math.round(averageScore * 10) / 10.0;

      //Label "Recensioni"
      FLabel reviews_lbl = new FLabel("Recensioni (" + averageScore + ")", new Font("Manrope Regular", Font.PLAIN, 30));
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 1);
      gbc.insets = new Insets(0,20,0,0);
      gbc.gridwidth = 4;
      subHeader.add(reviews_lbl, gbc);

      gbc = new GridBagConstraints();
      setGridCoordinatesXY(gbc, 0, 1);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.insets = new Insets(50,0,0,0);
      gbc.gridwidth = 4;
      header.add(subHeader, gbc);

      page.add(header, BorderLayout.PAGE_START);

      body = new FPage(new GridLayout(1,2));
      body.setBackground(Color.LIGHT_GRAY);
      gbc = new GridBagConstraints();

      FPage leftSide = new FPage();

      FLabel fiveStars_lbl = new FLabel("Eccellente", new Font("Manrope Regular", Font.PLAIN, 20));
      gbc.insets = new Insets(10, 20, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 1);
      leftSide.add(fiveStars_lbl, gbc);

      FLabel fourStars_lbl = new FLabel("Molto buono", new Font("Manrope Regular", Font.PLAIN, 20));
      gbc.insets = new Insets(10, 20, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 2);
      leftSide.add(fourStars_lbl, gbc);

      FLabel threeStars_lbl = new FLabel("Nella media", new Font("Manrope Regular", Font.PLAIN, 20));
      gbc.insets = new Insets(10, 20, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 3);
      leftSide.add(threeStars_lbl, gbc);

      FLabel twoStars_lbl = new FLabel("Scarso", new Font("Manrope Regular", Font.PLAIN, 20));
      gbc.insets = new Insets(10, 20, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 4);
      leftSide.add(twoStars_lbl, gbc);

      FLabel oneStar_lbl = new FLabel("Pessimo", new Font("Manrope Regular", Font.PLAIN, 20));
      gbc.insets = new Insets(10, 20, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      setGridCoordinatesXY(gbc, 1, 5);
      leftSide.add(oneStar_lbl, gbc);

      gbc = new GridBagConstraints();
      FProgressBar fiveStars_pb = new FProgressBar(0,reviews.length, starsDistribution[4], String.valueOf(starsDistribution[4]));
      gbc.insets = new Insets(15, 0, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      setGridCoordinatesXY(gbc, 0, 1);
      leftSide.add(fiveStars_pb, gbc);

      FProgressBar fourStars_pb = new FProgressBar(0,reviews.length, starsDistribution[3], String.valueOf(starsDistribution[3]));
      gbc.insets = new Insets(15, 0, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      setGridCoordinatesXY(gbc, 0, 2);
      leftSide.add(fourStars_pb, gbc);

      FProgressBar threeStars_pb = new FProgressBar(0,reviews.length, starsDistribution[2], String.valueOf(starsDistribution[2]));
      gbc.insets = new Insets(15, 0, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      setGridCoordinatesXY(gbc, 0, 3);
      leftSide.add(threeStars_pb, gbc);

      FProgressBar twoStars_pb = new FProgressBar(0,reviews.length, starsDistribution[1], String.valueOf(starsDistribution[1]));
      gbc.insets = new Insets(15, 0, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      setGridCoordinatesXY(gbc, 0, 4);
      leftSide.add(twoStars_pb, gbc);

      FProgressBar oneStar_pb = new FProgressBar(0,reviews.length, starsDistribution[0], String.valueOf(starsDistribution[0]));
      gbc.insets = new Insets(15, 0, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      setGridCoordinatesXY(gbc, 0, 5);
      leftSide.add(oneStar_pb, gbc);

      gbc = new GridBagConstraints();
      insert_btn = new FButton("Inserisci");
      setGridCoordinatesXY(gbc, 0, 6);
      gbc.insets = new Insets(40, 0, 0, 0);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridwidth = 2;
      leftSide.add(insert_btn, gbc);
      
      body.add(leftSide);

      String[] reviewsRecords = FileManager.GetRestaurantReviews(restaurant.GetId());

      FPage reviewsList = new FPage();
      reviewsList.setLayout(new BoxLayout(reviewsList, BoxLayout.Y_AXIS));

      for(int i = 0; i < reviewsRecords.length; i++) {
        gbc = new GridBagConstraints();
        String[] fields = reviewsRecords[i].split("\\|");

        FPage reviewElement = new FPage();
        int color = i%2 == 0 ? 222 : 200;
        reviewElement.setBackground(new Color(color,color,color));
        reviewElement.setBorder(BorderFactory.createEmptyBorder());
        gbc = new GridBagConstraints();

        FLabel userName = new FLabel(fields[1]+":", new Font("Manrope Bold", Font.PLAIN, 18));
        gbc.insets = new Insets(20, 10, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 0, 0);
        reviewElement.add(userName, gbc);

        FLabel reviewTitle = new FLabel(fields[3], new Font("Manrope Medium", Font.PLAIN, 18));
        gbc.insets = new Insets(20, 10, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        setGridCoordinatesXY(gbc, 1, 0);
        reviewElement.add(reviewTitle, gbc);

        FLabel starsImage;

        try {
          starsImage = new FLabel("assets/"+fields[2]+"StarsSmall.png");
          gbc.insets = new Insets(20,10,0,0);
          gbc.anchor = GridBagConstraints.WEST;
          setGridCoordinatesXY(gbc, 2, 0);
          reviewElement.add(starsImage, gbc);
        } catch(IOException e) {
          System.out.println("Errore caricamento :275");
        }
        
        JTextArea reviewDescription = new JTextArea();
        reviewDescription.setText(fields[4]);
        reviewDescription.setFont(new Font("Manrope Regular", Font.PLAIN, 16));
        reviewDescription.setLineWrap(true);
        reviewDescription.setWrapStyleWord(true);
        reviewDescription.setEditable(false);
        reviewDescription.setFocusable(false);
        reviewDescription.setBackground(UIManager.getColor("reviewElement.background"));
        reviewDescription.setBorder(UIManager.getBorder("reviewElement.border"));

        gbc.insets = new Insets(5, 10, 20, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 3;
        setGridCoordinatesXY(gbc, 0, 1);
        reviewElement.add(reviewDescription, gbc);

        reviewElement.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewElement.setAlignmentX(SwingConstants.LEFT);
        reviewsList.add(reviewElement);
      }
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBorder(new EmptyBorder(new Insets(30,20,77,100)));
      scrollPane.setOpaque(true);
      scrollPane.setBackground(Color.WHITE);
      scrollPane.getViewport().setBackground(Color.WHITE);
      scrollPane.setViewportView(reviewsList);
      scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
      scrollPane.setAlignmentX(SwingConstants.LEFT);
      scrollPane.getViewport().setViewPosition(new Point(0,0));
      scrollPane.getVerticalScrollBar().setValue(0);

      body.add(scrollPane);

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