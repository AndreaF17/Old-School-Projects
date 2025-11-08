package src.gui.pages;

//Import
import src.gui.components.*;
import java.awt.*;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class C_Login {
    //Oggetti
    private FPage page;
    private FLabel title_lbl;
    public FTextField email_tf;
    public FPasswordField password_pf;
    private FLabel questionMark_image;
    
    public FButton register_btn;
    private FPage bts_pane;
    public FButton login_btn;

    final FLabel info_lbl;
    public FLabel guest_lb; 

    //Var
    final String text;
    boolean emailtxtAlreadyClicked;
    boolean passtxtAlreadyClicked;

    private GridBagConstraints gbc;

    public FPage getPage() {
        return page; 
    }
    
    public C_Login(){
        //Oggetto per la pagina
        page = new FPage();
        gbc = new GridBagConstraints();

        //Parte grafica
        title_lbl = new FLabel("Benvenuto!", new Font("Manrope Regular", Font.PLAIN, 99));
        title_lbl.setForeground(Color.BLACK);
        gbc.fill = GridBagConstraints.CENTER;
        setGridCoordinatesXY(gbc, 0, 0);
        gbc.insets = new Insets(-30, 0, 0, 0);
        page.add(title_lbl, gbc);

        //Info label
        text = "Accedi ed iniza a cercare nuovi ristoranti";
        info_lbl = new FLabel(text, new Font("Manrope Light", Font.PLAIN, 27));
        setGridCoordinatesXY(gbc, 0, 1);
        gbc.insets = new Insets(10, 0, 15, 0);
        page.add(info_lbl, gbc);

        //Email TextField
        emailtxtAlreadyClicked = false;
        email_tf = new FTextField(38, new Font("Manrope", Font.PLAIN, 22));
        email_tf.setText("Email");
        //Azioni del textfield
        email_tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent arg0) {
                //Click del mouse (pressed)
                if(!emailtxtAlreadyClicked || email_tf.getText().equals("Email")) {
                    email_tf.setText("");
                    emailtxtAlreadyClicked = true;
                    email_tf.setForeground(Color.BLACK);
                }
            }
        });

        email_tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
                //Perdita di focus
                if(email_tf.getText().equals("")){
                    email_tf.setForeground(Color.GRAY);
                    email_tf.setText("Email");
                }
            }
        });
        //Parte grafica
        email_tf.setBorder(new LineBorder(Color.BLACK, 1));
        setGridCoordinatesXY(gbc, 0, 2);
        gbc.insets = new Insets(30, 0, 0, 0);
        email_tf.setBackground(Color.WHITE);
        page.add(email_tf, gbc);

        //Password PasswordField
        password_pf = new FPasswordField(38, new Font("Manrope", Font.PLAIN, 22));
        password_pf.setForeground(Color.GRAY);
        password_pf.setText("Password");
        //Azioni del PasswordField
        password_pf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent arg0) {
                //Perdita di focus
                if(String.valueOf(password_pf.getPassword()).equals("")){
                    password_pf.setText("Password");
                    password_pf.setForeground(Color.GRAY);
                }
            }
            @Override
            public void focusGained(final FocusEvent e) {
                //Acquisizione di focus
                if(!passtxtAlreadyClicked || String.valueOf(password_pf.getPassword()).equals("Password")) {
                    password_pf.setText("");
                    passtxtAlreadyClicked = true;
                    password_pf.setForeground(Color.BLACK);
                }
            }
        });

        //Parte grafica
        password_pf.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 3);
        page.add(password_pf, gbc);

        //Label Icona
        try {
            questionMark_image = new FLabel("assets/QM_Red_32.png");
        } catch(IOException e) {
            System.out.println("Errore caricamento :132");
        }
        //Azioni dell'icona
        questionMark_image.addMouseListener(new MouseAdapter() {
            //Se premuta dialog
            @Override
            public void mousePressed(final MouseEvent arg0) {
                JOptionPane.showMessageDialog(null, "Inserisci in questo campo la tua password", "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });

        //Parte grafica
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 5, 0, 0);
        setGridCoordinatesXY(gbc, 1, 3);
        page.add(questionMark_image, gbc);

        //Pannello secondario
        bts_pane = new FPage();
        setGridCoordinatesXY(gbc, 0, 4);
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        page.add(bts_pane, gbc);

        //Pulsante per il login
        login_btn = new FButton("Accedi");
        login_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent arg0) {
                
            }
        });
        
        //Parte grafica
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        setGridCoordinatesXY(gbc, 0, 0);
        bts_pane.add(login_btn, gbc);

        //Label usata come gap
        FLabel gap_lbl = new FLabel();
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.weightx = 0.1;
        setGridCoordinatesXY(gbc, 1, 0);
        bts_pane.add(gap_lbl, gbc);

        //Pulsante per registrazione
        register_btn = new FButton("Registrati");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.weightx = 0.5;
        setGridCoordinatesXY(gbc, 2, 0);
        bts_pane.add(register_btn, gbc);

        //Label accesso come ospite
        //Abbiamo usato il tag html per modificare più efficacemente la label
        guest_lb = new FLabel("<html>Oppure procedi come <font color='blue'>ospite</font></html>", new Font("Manrope", Font.PLAIN, 15));
        //Azioni della label
        guest_lb.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent e) {
                //"Ospite" scritto in rosso se ci si avvicina con il mouse 
                guest_lb.setText("<html>Oppure procedi come <font color='red'>ospite</font></html>");
            }
            public void mouseExited(final MouseEvent e) {
                //"Ospite" scritto in blu se ci si allontana con il mouse
                guest_lb.setText("<html>Oppure procedi come <font color='blue'>ospite</font></html>");
            }
        });

        setGridCoordinatesXY(gbc, 0, 1);
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.ipadx = -110;
        bts_pane.add(guest_lb, gbc);
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