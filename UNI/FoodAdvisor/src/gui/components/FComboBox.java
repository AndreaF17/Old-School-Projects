package src.gui.components;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

public class FComboBox extends JComboBox<String> {
    /**
     *Creazione di una JComboBox con parametri custom 
    */
    private static final long serialVersionUID = 1L;
    
    public FComboBox(String[] values, Font font) {
        super(values);
        //Parte grafica
        this.setOpaque(false);
        this.setFont(font);
        this.setBackground(Color.WHITE);
        ((JLabel)this.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
}