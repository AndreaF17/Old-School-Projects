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

public class C_Register { 
    //Oggetti
    private FPage page;

    private GridBagConstraints gbc;
    private FLabel title_lbl;
    private String text;
    private FLabel info_lbl;
    public FTextField nick_tf;
    public FTextField name_tf;
    public FTextField surname_tf;
    private FPage bts_pane;
    public FButton back_btn;
    public FButton continue_btn;
    private FLabel procedure_lb;

    //Var
    boolean nicktxtAlreadyClicked;
    boolean nametxtAlreadyClicked;
    boolean surnametxtAlreadyClicked;

    public FPage getPage() {
        return page;
    }

    public C_Register() {
        //Setup della pagina
        page = new FPage();
        gbc = new GridBagConstraints();

        //Label di registrazione
        title_lbl = new FLabel("Registrazione", new Font("Manrope Regular", Font.PLAIN, 99));
        title_lbl.setForeground(Color.BLACK);
        gbc.fill = GridBagConstraints.CENTER;
        setGridCoordinatesXY(gbc, 0, 0);
        gbc.insets = new Insets(-30, 0, 0, 0);
        page.add(title_lbl, gbc);

        //Label di info
        text = "Registrati per avere accesso a tutte le funzioni";
        info_lbl = new FLabel(text, new Font("Manrope Light", Font.PLAIN, 27));
        setGridCoordinatesXY(gbc, 0, 1);
        gbc.insets = new Insets(10, 0, 15, 0);
        page.add(info_lbl, gbc);

        //TextField per il nome
        nicktxtAlreadyClicked = false;
        nick_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        nick_tf.setText("Nickname");
        //Azioni TextField del mouse
        nick_tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent arg0) {
            //Click del mouse (pressed)
                if(!nicktxtAlreadyClicked || nick_tf.getText().equals("Nickname")) {
                    nick_tf.setText("");
                    nicktxtAlreadyClicked = true;
                    nick_tf.setForeground(Color.BLACK);
                }
            }
        });
        //Azioni TextField del focus
        nick_tf.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(final FocusEvent e) {
                    //Perdita di focus
                    if(nick_tf.getText().equals("")){
                        nick_tf.setForeground(Color.GRAY);
                        nick_tf.setText("Nickname");
                    }
                }
        });
        
        nick_tf.setBorder(new LineBorder(Color.BLACK, 1));
        setGridCoordinatesXY(gbc, 0, 2);
        gbc.insets = new Insets(30, 0, 0, 0);
        nick_tf.setBackground(Color.WHITE);
        page.add(nick_tf, gbc);

        //TextField per il nome
        name_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        name_tf.setText("Nome");
        //Azioni TextField del focus
        name_tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(name_tf.getText().equals("")){
                    name_tf.setForeground(Color.GRAY);
                    name_tf.setText("Nome");
                }
            }
            @Override
            public void focusGained(final FocusEvent e) {
                //Acquisizione focus
                if(!nametxtAlreadyClicked || name_tf.getText().equals("Nome")) {
                    name_tf.setText("");
                    nametxtAlreadyClicked = true;
                    name_tf.setForeground(Color.BLACK);
                }
            }
            });
            name_tf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 3);
        page.add(name_tf, gbc);

        //TextField cognome
        surname_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        surname_tf.setText("Cognome");
        //Azioni TextField del focus
        surname_tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(surname_tf.getText().equals("")){
                    surname_tf.setForeground(Color.GRAY);
                    surname_tf.setText("Cognome");
                    
                }
            }
            @Override
            public void focusGained(final FocusEvent e) {
                //Acquisizione di focus
                if(!surnametxtAlreadyClicked || surname_tf.getText().equals("Cognome")) {
                    surname_tf.setText("");
                    surnametxtAlreadyClicked = true;
                    surname_tf.setForeground(Color.BLACK);
                }
            }
        });
        surname_tf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 4);
        page.add(surname_tf, gbc);

        //Pannello
        bts_pane = new FPage();
        setGridCoordinatesXY(gbc, 0, 5);
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        page.add(bts_pane, gbc);

        //Pulsante per andare indietro
        back_btn = new FButton("Indietro");
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 0);
        bts_pane.add(back_btn, gbc);

        //Label usata come gap
        FLabel gap_lbl = new FLabel();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0.1;
        setGridCoordinatesXY(gbc, 1, 0);
        bts_pane.add(gap_lbl, gbc);

        //Pulsante continua
        continue_btn = new FButton("Continua");
        continue_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent arg0) {
                //mouse clicked
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0.5;
        setGridCoordinatesXY(gbc, 2, 0);
        bts_pane.add(continue_btn, gbc);

        //Immagine che dimostra l'andamento della registrazione
        try {
            procedure_lb = new FLabel("assets/Step1.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :192");
        }
        setGridCoordinatesXY(gbc, 0, 1);
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.ipadx = -600;
        bts_pane.add(procedure_lb, gbc);
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