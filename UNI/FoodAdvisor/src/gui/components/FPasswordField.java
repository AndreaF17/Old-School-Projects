package src.gui.components;

import javax.swing.JPasswordField;

import java.awt.Dimension;
import java.awt.Font;


public class FPasswordField extends JPasswordField {
    /**
     * Creazione di un JPasswordField con parametri custom
    */
    private static final long serialVersionUID = 1L;
    
    public FPasswordField(int i, Font font) {
        super(36);
        //Parte grafica
        //this.setFont(font);
        this.setHorizontalAlignment(JPasswordField.CENTER);
        this.setFont(new Font("Arial", Font.PLAIN, 21));
        this.setPreferredSize(new Dimension(100, 40));
    }
}