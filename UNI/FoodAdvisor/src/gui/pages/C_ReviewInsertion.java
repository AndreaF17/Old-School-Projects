package src.gui.pages;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import src.classes.Restaurant;
import src.classes.User;
import src.gui.components.FLabel;
import src.gui.components.FPage;
import src.gui.components.FButton;
import src.gui.components.FComboBox;
import src.gui.components.FTextField;

public class C_ReviewInsertion {
    
    private FPage page;
    private GridBagConstraints gbc;
    public  FLabel backIcon_lbl;
    private FLabel userIcon_lbl;
    private FLabel userName_lbl;
    private FLabel restName_lbl;
    private FLabel restImage_lbl;
    private FPage body;
    public FButton submit_btn;
    public JTextArea textField;
    public JScrollPane scrollPane;
    public FComboBox stars;
    public FTextField reviewTitle_tf;
    public FPage getPage() {
        return page;
    }

    public C_ReviewInsertion(User user, Restaurant restaurant) {
        //Setup base della pagina
        page = new FPage(new BorderLayout());
        
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
        gbc.anchor = GridBagConstraints.CENTER;
        setGridCoordinatesXY(gbc, 0, 0);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridwidth = 2;
        rightSide.add(restName_lbl, gbc);

        gbc = new GridBagConstraints();

        //TextField searchbar
        reviewTitle_tf = new FTextField(30, new Font("Manrope", Font.PLAIN, 20));
        reviewTitle_tf.setText("Titolo recensione");
        //Azioni del mouse
        reviewTitle_tf.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                //Click del mouse (pressed)
                if(reviewTitle_tf.getText().equals("Titolo recensione")) {
                    reviewTitle_tf.setText("");
                    reviewTitle_tf.setForeground(Color.BLACK);
                }
            }
        });
        //Azioni di focus
        reviewTitle_tf.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                //Perdita di focus
                if(reviewTitle_tf.getText().equals("")){
                    reviewTitle_tf.setForeground(Color.GRAY);
                    reviewTitle_tf.setText("Titolo recensione");
                }
            }
        });
        reviewTitle_tf.setBorder(new LineBorder(Color.BLACK, 1));
        setGridCoordinatesXY(gbc, 0, 1);
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        reviewTitle_tf.setBackground(Color.WHITE);
        rightSide.add(reviewTitle_tf, gbc);
        
        stars = new FComboBox(new String[]{"*","1","2","3","4","5"}, new Font("Manrope", Font.PLAIN, 20));
        setGridCoordinatesXY(gbc, 1, 1);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20,20,0,0);
        rightSide.add(stars, gbc);
        
        //JTextArea
        gbc = new GridBagConstraints();
        textField = new JTextArea("Descrizione", 5, 34);
        textField.setFont(new Font("Manrope", Font.PLAIN, 20));
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        textField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        textField.getInputMap().put(KeyStroke.getKeyStroke("RETURN"), "none");
        textField.getInputMap().put(KeyStroke.getKeyStroke("Enter"), "none");
        textField.getInputMap().put(KeyStroke.getKeyStroke("Return"), "none");

        scrollPane = new JScrollPane(textField);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        textField.setLineWrap(true);
        textField.setWrapStyleWord(true); 
        setGridCoordinatesXY(gbc, 0, 2);
        gbc.insets = new Insets(20,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;

        //Azioni di focus
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(final FocusEvent e) {
                //Click del mouse (pressed)
                if(textField.getText().equals("Descrizione")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(final FocusEvent e) {
                //Perdita di focus
                if(textField.getText().equals("")){
                    textField.setForeground(Color.GRAY);
                    textField.setText("Descrizione");
                }
            }
        });

        rightSide.add(scrollPane, gbc);
        
        submit_btn = new FButton("Invia");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20,0,0,0);
        setGridCoordinatesXY(gbc, 0, 3);
        rightSide.add(submit_btn, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,50,150,0);
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