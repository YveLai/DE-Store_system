package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main() {
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
        /**
         * add product
         */
        JButton btnAddProducts = new JButton("Add Products");
        btnAddProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductAddInterView.main();
            }
        });
        btnAddProducts.setBounds(6, 50, 145, 29);
        frame.getContentPane().add(btnAddProducts);
        /**
         * button manage product
         */
        JButton btnManageProducts = new JButton("Manage Products");
        btnManageProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductManagerInterView.main();
            }
        });
        btnManageProducts.setBounds(6, 100, 145, 29);
        frame.getContentPane().add(btnManageProducts);
        /**
         * button customer list
         */
        JButton btnCustomerList = new JButton("Manager members");
        btnCustomerList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberAddInterView.main();
            }
        });
        btnCustomerList.setBounds(6, 150, 145, 29);
        frame.getContentPane().add(btnCustomerList);
        /**
         * button shopping records
         */
        JButton btnShoppingRecord = new JButton("Shopping Record");
        btnShoppingRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingRecordView.main();
            }
        });
        btnShoppingRecord.setBounds(6, 200, 145, 29);
        frame.getContentPane().add(btnShoppingRecord);
    }
}


