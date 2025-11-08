package src.gui.components;

import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FWindow extends JFrame {
    /**
     * Creazione di un JFrame con parametri custom
    */
    private static final long serialVersionUID = 1L;
    
    public FWindow(String title) {
        super(title);
        //Parte grafica
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
        this.setBounds(0,0,1280,760);
        InputStream is = getClass().getResourceAsStream("/assets/icon.png");

        try {
            this.setIconImage(ImageIO.read(is));
        } catch(Exception e) {
            System.out.println("Errore FWindow :25");
        }
    }
} 