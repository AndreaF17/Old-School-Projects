
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Server.Server;
import interfaces.PageInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public class App {
    public static void main(String[] args) throws Exception {
        boolean isActive = false;
        //1. Create the frame.
        JFrame frame = new JFrame("Client");

        //2. frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
        //...create emptyLabel...
        JTextField textField = new JTextField();
        JButton buttonSend = new JButton("Scarica");
        JButton startServer = new JButton("Start server");
        frame.getContentPane().add(new Label("Inserisci URL da scaricare: "), BorderLayout.NORTH);
        frame.getContentPane().add(textField,BorderLayout.CENTER);
        frame.getContentPane().add(buttonSend, BorderLayout.LINE_END);
        frame.getContentPane().add(startServer, BorderLayout.LINE_START);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Registry registry = LocateRegistry.getRegistry(1099);
                    PageInterface stub = (PageInterface) registry.lookup("");
                    // Nick
                } catch(Exception error) {

                }
            }
        });

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread serverThread = new Thread() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        super.run();
                      Server.startMain();
                      // Andre
                    }
                };
                serverThread.start();
            }
        });


        

        //4. Size the frame.
        frame.setSize(300, 300);

        //5. Show it.
        frame.setVisible(true);

        
    }
}
