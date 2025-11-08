package src.gui.components;

import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class FList<E> extends JList<E> {
    /** Creazione di una JList con parametri custom */
    private static final long serialVersionUID = 1L;

    public FList() {
        super();
        addStyle();
    }

    public FList(E[] items) {
        super(items);
        addStyle();
    }

    public FList(DefaultListModel<E> listModel) {
        super(listModel);
        addStyle();
    }

    //Parte grafica
    public void addStyle() {
        this.setFixedCellHeight(50);
        this.setFont(new Font("Manrope", Font.PLAIN, 22));
        this.setOpaque(false);
        this.setBackground(new Color(222,222,222));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
    }
}