package src.gui.components;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class FPage extends JPanel {
    /**
     * Creazione di un JPanel con parametri custom
    */
    private static final long serialVersionUID = 1L;

    public FPage() {
        super(new GridBagLayout());
        //Parte grafica
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setBackground(Color.WHITE);
    }

    public FPage(BorderLayout layout) {
        super(layout);
        //Parte grafica
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setBackground(Color.WHITE);
    }

    public FPage(GridLayout layout) {
        super(layout);
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setBackground(Color.WHITE);
    }

    public FPage(BoxLayout layout) {
        super(layout);
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setBackground(Color.WHITE);
    }
}