package View;

import Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainView window = new MainView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnAddProducts = new JButton("Add Products");
        btnAddProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductAddInterView.main();
            }
        });
        btnAddProducts.setBounds(40, 102, 145, 29);
        frame.getContentPane().add(btnAddProducts);

        JButton btnManageProducts = new JButton("Manage Products");
        btnManageProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductManagerInterView.main();
            }
        });
        btnManageProducts.setBounds(260, 102, 151, 29);
        frame.getContentPane().add(btnManageProducts);
    }
}


