package src.gui.pages;

//Import
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import src.gui.components.*;

public class R_Register {
    //Oggetti
    private FPage page;
    public String pageTitle;
    private GridBagConstraints gbc;
    private FLabel title_lbl;
    private String text;
    private FLabel info_lbl;
    public FTextField name_tf;
    public FTextField number_tf;
    public FTextField website_tf;
    public FComboBox type_cb;
    public FButton back_btn;
    public FButton continue_btn;
    private FLabel procedure_lb;

    //Var
    boolean nametxtAlreadyClicked;
    boolean numbertxtAlreadyClicked;
    boolean websitetxtAlreadyClicked;

    public FPage getPage() {
        return page;
    }

    public R_Register() {

        //Setup base della pagina
        page = new FPage();
        gbc = new GridBagConstraints();

        //Label di titolo
        title_lbl = new FLabel("Registrazione", new Font("Manrope Regular", Font.PLAIN, 99));
        title_lbl.setForeground(Color.BLACK);
        gbc.fill = GridBagConstraints.CENTER;
        setGridCoordinatesXY(gbc, 0, 0);
        gbc.insets = new Insets(-30, 0, 0, 0);
        page.add(title_lbl, gbc);

        //Label di info
        text = "Registra un nuovo ristorante!";
        info_lbl = new FLabel(text, new Font("Manrope Light", Font.PLAIN, 27));
        setGridCoordinatesXY(gbc, 0, 1);
        gbc.insets = new Insets(10, 0, 15, 0);
        page.add(info_lbl, gbc);

        //TextField per il nome
        name_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        name_tf.setText("Nome");
        //Azioni del focus
        name_tf.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(name_tf.getText().equals("")){
                    name_tf.setForeground(Color.GRAY);
                    name_tf.setText("Nome");
                }
            }
        });
        //Azioni del mouse
        name_tf.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                //Click del mouse
                if(!nametxtAlreadyClicked || name_tf.getText().equals("Nome")) {
                    name_tf.setText("");
                    nametxtAlreadyClicked = true;
                    name_tf.setForeground(Color.BLACK);
                }
            } 
        });
        name_tf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 2);
        page.add(name_tf, gbc);

        //TextField del numero di telefono
        number_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        number_tf.setText("Numero di Telefono");
        //Azioni di focus
        number_tf.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(number_tf.getText().equals("")){
                    number_tf.setForeground(Color.GRAY);
                    number_tf.setText("Numero di Telefono");
                    
                }
            }
            public void focusGained(final FocusEvent e) {
                //Acquisizione di focus
                if(!numbertxtAlreadyClicked || number_tf.getText().equals("Numero di Telefono")) {
                    number_tf.setText("");
                    numbertxtAlreadyClicked = true;
                    number_tf.setForeground(Color.BLACK);
                }
            }
        });
        number_tf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 3);
        page.add(number_tf, gbc);

        //TextField sito web
        website_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        website_tf.setText("Sito Web");
        //Azioni di focus
        website_tf.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(website_tf.getText().equals("")){
                    website_tf.setForeground(Color.GRAY);
                    website_tf.setText("Sito Web");
                    
                }
            }
            public void focusGained(final FocusEvent e) {
                //Acquisizione di focus
                if(!websitetxtAlreadyClicked || website_tf.getText().equals("Sito Web")) {
                    website_tf.setText("");
                    websitetxtAlreadyClicked = true;
                    website_tf.setForeground(Color.BLACK);
                }
            }
        });
        website_tf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 4);
        page.add(website_tf, gbc);

        //Combobox tipologia di ristorante
        String[] values = new String[] {"Tipologia","Italiano","Etnico","Fusion"};
        type_cb = new FComboBox(values, new Font("Manrope", Font.PLAIN, 22));
        type_cb.setBorder(new LineBorder(Color.BLACK, 1));
        //gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 5);
        page.add(type_cb, gbc);
       
        //Pulsante continua
        continue_btn = new FButton("Continua");
        //Azioni del mouse
        continue_btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent arg0) {
                //Click del mouse
            }
        });        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 6);
        page.add(continue_btn, gbc);

        //Immagine che dimostra l'andamento della registrazione
        try {
            procedure_lb = new FLabel("assets/Step1.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :171");
        }
        setGridCoordinatesXY(gbc, 0, 7);
        gbc.insets = new Insets(30, 0, 0, 0);
        page.add(procedure_lb, gbc);
    }

    /**
     * Metodo per settare le coordinate più efficacemente
     * @param gbc Istanza di GridBagConstraints
     * @param x Colonna
     * @param y Riga */
    public static void setGridCoordinatesXY(final GridBagConstraints gbc, final int x, final int y) {
        gbc.gridx = x;
        gbc.gridy = y;
    }
}