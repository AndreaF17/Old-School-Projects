package src.gui.pages;

//Import
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import src.gui.components.*;

public class C_Register3 { 
    //Oggetti

    private FPage page;

    private GridBagConstraints gbc;
    private FLabel title_lbl;
    private String text;
    private FLabel info_lbl;
    public FTextField email_tf;
    public FPasswordField password1_pf;
    public FPasswordField password2_pf;
    private FPage bts_pane;
    public FButton back_btn;
    public FButton continue_btn;
    private FLabel procedure_lb;
    private FLabel questionMark1_image;
    private FLabel questionMark2_image;

    //Var
    boolean emailtxtAlreadyClicked;
    boolean pass1txtAlreadyClicked;
    boolean pass2txtAlreadyClicked;

    public FPage getPage() {
        return page;
    }

    public C_Register3() {
        //Setup base della pagina
        page = new FPage();
        gbc = new GridBagConstraints();

        //Label del titolo
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

        //TextField dell'email
        emailtxtAlreadyClicked = false;
        email_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        email_tf.setText("Email");
        //Azioni del mouse
        email_tf.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
            //Click del mouse (pressed)
                if(!emailtxtAlreadyClicked || email_tf.getText().equals("Email")) {
                    email_tf.setText("");
                    emailtxtAlreadyClicked = true;
                    email_tf.setForeground(Color.BLACK);
                }
            }
        });
        //Azioni di focus
        email_tf.addFocusListener(new FocusAdapter() {
                public void focusLost(final FocusEvent e) {
                //Perdita di focus
                    if(email_tf.getText().equals("")){
                        email_tf.setForeground(Color.gray);
                        email_tf.setText("Email");
                    }
                }
        });
        
        email_tf.setBorder(new LineBorder(Color.BLACK, 1));
        setGridCoordinatesXY(gbc, 0, 2);
        gbc.insets = new Insets(30, 0, 0, 0);
        email_tf.setBackground(Color.WHITE);
        page.add(email_tf, gbc);

        //PasswordField principale
        password1_pf = new FPasswordField(38, new Font("Manrope", Font.PLAIN, 22));
        password1_pf.setForeground(Color.GRAY);
        password1_pf.setText("Password");
        //Azioni del focus
        password1_pf.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(String.valueOf(password1_pf.getPassword()).equals("")){
                    password1_pf.setForeground(Color.GRAY);
                    password1_pf.setText("Password");
                }
            }
            public void focusGained(final FocusEvent e) {
                //Acquisizione di focus
                if(!pass1txtAlreadyClicked || String.valueOf(password1_pf.getPassword()).equals("Password")) {
                    password1_pf.setText("");
                    pass1txtAlreadyClicked = true;
                    password1_pf.setForeground(Color.BLACK);
                }
            }
        });
        password1_pf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 3);
        page.add(password1_pf, gbc);

        //Label immagine del punto di domanda
        try {
            questionMark1_image = new FLabel("assets/QM_Red_32.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :127");
        }
        //Azione del mouse
        questionMark1_image.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                JOptionPane.showMessageDialog(null, "Inserisci in questo campo la password", "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 5, 0, 0);
        setGridCoordinatesXY(gbc, 1, 3);
        page.add(questionMark1_image, gbc);

        //PasswordField di conferma
        password2_pf = new FPasswordField(38, new Font("Manrope", Font.PLAIN, 22));
        password2_pf.setForeground(Color.GRAY);
        password2_pf.setText("Password");
        //Azione di focus
        password2_pf.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(String.valueOf(password2_pf.getPassword()).equals("")){
                    password2_pf.setForeground(Color.GRAY);
                    password2_pf.setText("Password");
                }
            }
            public void focusGained(final FocusEvent e) {
                //Acquisizione di focus
                if(!pass2txtAlreadyClicked || String.valueOf(password2_pf.getPassword()).equals("Password")) {
                    password2_pf.setText("");
                    pass2txtAlreadyClicked = true;
                    password2_pf.setForeground(Color.BLACK);
                }
            }
        });
        password2_pf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.insets = new Insets(20, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 4);
        page.add(password2_pf, gbc);

        //Label immagine del punto di domanda
        try {
            questionMark2_image = new FLabel("assets/QM_Red_32.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :171");
        }
        //Azioni del mouse
        questionMark2_image.addMouseListener(new MouseAdapter() {
            //Se premuta dialog
            public void mousePressed(final MouseEvent arg0) {
                JOptionPane.showMessageDialog(null, "Ripeti la tua password", "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 5, 0, 0);
        setGridCoordinatesXY(gbc, 1, 4);
        page.add(questionMark2_image, gbc);

        //Pannello
        bts_pane = new FPage();
        setGridCoordinatesXY(gbc, 0, 5);
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        page.add(bts_pane, gbc);

        //Pulsante indietro
        back_btn = new FButton("Indietro");
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 0);
        bts_pane.add(back_btn, gbc);

        //Label di gap
        FLabel gap_lbl = new FLabel();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0.1;
        setGridCoordinatesXY(gbc, 1, 0);
        bts_pane.add(gap_lbl, gbc);

        //Pulsante continua 
        continue_btn = new FButton("Finito");
        //Azione del mouse
        continue_btn.addMouseListener(new MouseAdapter() {
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
            procedure_lb = new FLabel("assets/Step3.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :225");
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