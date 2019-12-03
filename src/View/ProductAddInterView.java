package View;

import javax.swing.*;

import Controller.database_conn;
import Controller.StringUtil;
import Dao.ProductDao;
import Model.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import static javax.swing.text.StyleConstants.Alignment;

public class ProductAddInterView extends JInternalFrame {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    private database_conn database = new database_conn();
    private ProductDao productDao= new ProductDao();

    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductAddInterView window = new ProductAddInterView();
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
    public ProductAddInterView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.getContentPane().setLayout(null);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(62, 243, 98, 29);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productAddActionPerformed(e);
            }
        });
        frame.getContentPane().add(btnAdd);

        JButton btnClear = new JButton("CLEAR");
        btnClear.setBounds(291, 243, 98, 29);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productResetvalueActionPerformed(e);
            }
        });
        frame.getContentPane().add(btnClear);

        JLabel lblProduct = new JLabel("product name");
        lblProduct.setBounds(62, 39, 108, 16);
        frame.getContentPane().add(lblProduct);

        textField = new JTextField();
        textField.setBounds(182, 34, 207, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("product price");
        lblNewLabel.setBounds(62, 90, 98, 16);
        frame.getContentPane().add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(182, 85, 207, 26);
        frame.getContentPane().add(textField_1);

        JLabel lblProducetStock = new JLabel("producet stock");
        lblProducetStock.setBounds(62, 140, 98, 16);
        frame.getContentPane().add(lblProducetStock);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(182, 135, 207, 26);
        frame.getContentPane().add(textField_2);

        JLabel lblNotes = new JLabel("notes");
        lblNotes.setBounds(62, 185, 61, 16);
        frame.getContentPane().add(lblNotes);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(352, 185, -169, 49);
        frame.getContentPane().add(textArea);

        textField_3 = new JTextField();
        textField_3.setBounds(182, 180, 207, 26);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);
    }

    private void productAddActionPerformed(ActionEvent e){
        String product_name = this.textField.getText();
        String product_price = this.textField_1.getText();
        String product_num = this.textField_2.getText();
        String product_notes = this.textField_3.getText();
        if(StringUtil.isEmpty(product_name)){
            JOptionPane.showMessageDialog(null, "The product name is empty!");
            return;
        }
        if(StringUtil.isEmpty(product_price)){
            JOptionPane.showMessageDialog(null, "The product price is empty!");
            return;
        }
        if(StringUtil.isEmpty(product_num)){
            JOptionPane.showMessageDialog(null, "The product stock is empty!");
            return;
        }
        Product product = new Product(product_name, product_price, product_num, product_notes);
        Connection conn = null;
        try{
            conn = database_conn.getCon();
            int result = productDao.addproduct(conn, product);
            if(result==1){
                JOptionPane.showMessageDialog(null, "Add success!");
                this.resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "Add fail!");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Add fail!");
        }finally{
            try{
                database_conn.close(conn);
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    private void productResetvalueActionPerformed(ActionEvent e){
        this.resetValue();
    }

    private void resetValue(){
        this.textField.setText("");
        this.textField_1.setText("");
        this.textField_2.setText("");
        this.textField_3.setText("");
    }
}
