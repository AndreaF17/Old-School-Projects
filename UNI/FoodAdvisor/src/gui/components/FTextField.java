package src.gui.components;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class FTextField extends JTextField {
    /**
     * Creazione di un JTextField con parametri custom
    */
    private static final long serialVersionUID = 1L;

    public FTextField(String text, Font font) {
        super(text);
        //Parte grafica
        this.setFont(font);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(new Dimension(100, 40));
        this.setForeground(new Color(150,150,150));
    }
    public FTextField(int value, Font font) {
        super(value);
        //Parte grafica
        this.setFont(font);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(new Dimension(100, 40));
        this.setForeground(new Color(150,150,150));
    }
}