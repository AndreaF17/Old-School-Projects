package src.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FButton extends JButton {
    /** Creazione di un JButton con parametri custom */
	private static final long serialVersionUID = 1L;

    public FButton(String text) {
        super(text);
        AddStyle();
    }
    
    private void AddStyle() {
        //Parte grafica
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(237, 71, 53));
        this.setFont(new Font("Manrope", Font.PLAIN, 22));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(true);
        
        this.setPreferredSize(new Dimension(100, 45));

        //Parte funzionale
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent e) {
                setBackground(new Color(196, 48, 31));
            }
            public void mouseExited(final MouseEvent e) {
                setBackground(new Color(237, 71, 53));
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent arg0) {
                setBackground(new Color(237, 71, 53));
            }
        });
    }
}