package src.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class FProgressBar extends JProgressBar {
    /***/
    private static final long serialVersionUID = 1L;

    public FProgressBar(int min, int max, int value, String strvalue) {
        super(min,max);
        this.setValue(value);
        this.setString(value > 0 ? strvalue : "");
        addStyle();
    }

    public void addStyle() {
        this.setPreferredSize(new Dimension(300,30));
        this.setStringPainted(true);
        this.setForeground(new Color(237, 71, 53));
        this.setBackground(new Color(237, 206, 213));
        UIManager.put("ProgressBar.selectionBackground", Color.GRAY);
        UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
        this.setBorderPainted(false);
        this.setFont(new Font("Manrope Medium", Font.PLAIN, 15));
    }
}