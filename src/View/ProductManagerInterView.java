package View;

import Controller.StringUtil;
import Controller.database_conn;
import Dao.ProductDao;
import Model.Product;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ProductManagerInterView extends JInternalFrame {
    private JFrame frame;
    private JTextField s_product_name;
    private JTable table;
    private JTextField product_ID;
    private JTextField product_name;
    private JTextField product_price;
    private JTextField product_stock;
    private JTextField notes;
    private JTextField s_product_stock;
    JScrollPane scrollpane = new JScrollPane();
    private static database_conn database = new database_conn();
    private static ProductDao productDao = new ProductDao();
    private LayoutManager groupLayout;

    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductManagerInterView window = new ProductManagerInterView();
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
    public ProductManagerInterView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 450);
        frame.getContentPane().setLayout(null);

        JLabel lblProductName = new JLabel("product name");
        lblProductName.setBounds(6, 21, 101, 16);
        frame.getContentPane().add(lblProductName);

        s_product_name = new JTextField();
        s_product_name.setBounds(119, 16, 130, 26);
        frame.getContentPane().add(s_product_name);
        s_product_name.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchbookTypeActionPerformed(e);
            }
        });
        btnSearch.setBounds(283, 16, 79, 29);
        frame.getContentPane().add(btnSearch);

        JLabel lblProductStock_1 = new JLabel("Product Stock");
        lblProductStock_1.setBounds(6, 46, 101, 16);
        frame.getContentPane().add(lblProductStock_1);

        s_product_stock = new JTextField();
        s_product_stock.setBounds(119, 41, 130, 26);
        frame.getContentPane().add(s_product_stock);
        s_product_stock.setColumns(10);

        JButton btnLowStock = new JButton("Low Stock");
        btnLowStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStockActionPerformed(e);
            }
        });
        btnLowStock.setBounds(283, 41, 101, 29);
        frame.getContentPane().add(btnLowStock);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickMouseproductTable(e);
            }
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "ID", "Name", "Price", "Stock", "Notes"
                }
        ){
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column){
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0);
        table.getColumnModel().getColumn(1);
        scrollpane.setViewportView(table);
        getContentPane().setLayout(groupLayout);
        this.fillProductTable(new Product());
        table.setBounds(6, 82, 438, 103);
        frame.getContentPane().add(table);

        JLabel lblProductId = new JLabel("Product ID");
        lblProductId.setBounds(21, 219, 86, 16);
        frame.getContentPane().add(lblProductId);

        product_ID = new JTextField();
        product_ID.setBounds(104, 214, 79, 26);
        frame.getContentPane().add(product_ID);
        product_ID.setColumns(10);

        JLabel lblProductName_1 = new JLabel("Product Name");
        lblProductName_1.setBounds(190, 219, 101, 16);
        frame.getContentPane().add(lblProductName_1);

        product_name = new JTextField();
        product_name.setBounds(303, 214, 130, 26);
        frame.getContentPane().add(product_name);
        product_name.setColumns(10);

        JLabel lblProductPrice = new JLabel("Product Price");
        lblProductPrice.setBounds(21, 266, 86, 16);
        frame.getContentPane().add(lblProductPrice);

        product_price = new JTextField();
        product_price.setBounds(104, 261, 79, 26);
        frame.getContentPane().add(product_price);
        product_price.setColumns(10);

        JLabel lblProductStock = new JLabel("Product Stock");
        lblProductStock.setBounds(190, 266, 101, 16);
        frame.getContentPane().add(lblProductStock);

        product_stock = new JTextField();
        product_stock.setBounds(303, 261, 130, 26);
        frame.getContentPane().add(product_stock);
        product_stock.setColumns(10);

        JLabel lblNotes = new JLabel("Notes");
        lblNotes.setBounds(21, 311, 61, 16);
        frame.getContentPane().add(lblNotes);

        notes = new JTextField();
        notes.setBounds(108, 306, 325, 26);
        frame.getContentPane().add(notes);
        notes.setColumns(10);

        JButton btnModify = new JButton("Modify");
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProductActionPerformed(e);
            }
        });
        btnModify.setBounds(21, 365, 117, 29);
        frame.getContentPane().add(btnModify);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProdectActionPerformed(e);
            }
        });
        btnDelete.setBounds(174, 365, 117, 29);
        frame.getContentPane().add(btnDelete);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productResetActionPerformed(e);
            }
        });
        btnClear.setBounds(316, 365, 117, 29);
        frame.getContentPane().add(btnClear);

        JPanel panel = new JPanel();
        panel.setBounds(6, 197, 438, 225);
        frame.getContentPane().add(panel);
    }

    private void deleteProdectActionPerformed(ActionEvent arg0) {
        String product_id = product_ID.getText();
        if (StringUtil.isEmpty(product_id)) {
            JOptionPane.showMessageDialog(null, "Select record to delete");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "You sure you want to delete it?");
        if(n==0) {
            Connection conn = null;
            try {
                conn = database_conn.getCon();
                Product product = new Product(Integer.parseInt(product_id));
                int result = productDao.deleteproduct(conn, product);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "delete success!");
                    this.fillProductTable(new Product());
                    this.resetValue();
                } else {
                    JOptionPane.showMessageDialog(null, "delete fail!");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "delete fail!");
            } finally {
                try {
                    database_conn.close(conn);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateProductActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        String product_Id = product_ID.getText();
        String product_Name = product_name.getText();
        String product_Price = product_price.getText();
        String product_Num = product_stock.getText();
        String product_notes = notes.getText();
        if(StringUtil.isEmpty(product_Id)){
            JOptionPane.showMessageDialog(null, "Select record to modify!");
            return;
        }
        if(StringUtil.isEmpty(product_Name)){
            JOptionPane.showMessageDialog(null, "Please input product name!");
            return;
        }
        Product product = new Product(Integer.parseInt(product_Id), product_Name,product_Price,product_Num, product_notes);
        Connection conn = null;
        try {
            conn = database_conn.getCon();
            int result = productDao.updateproduct(conn, product);
            if(result==1){
                JOptionPane.showMessageDialog(null, "Modify success!");
                this.fillProductTable(new Product());
            }else{
                JOptionPane.showMessageDialog(null, "Modify fail!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Modify fail!");
        }finally{
            try {
                database_conn.close(conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private void searchbookTypeActionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String product_name = s_product_name.getText();
        Product product = new Product(product_name);
        this.fillProductTable(product);
    }

    private void searchStockActionPerformed(ActionEvent e){
        String product_num = s_product_stock.getText();
        Product product = new Product(product_num);
        this.fillProductStockTable(product);
    }

    private void productResetActionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        this.resetValue();
    }

    private void resetValue(){
        this.product_ID.setText("");
        this.product_name.setText("");
        this.product_price.setText("");
        this.product_stock.setText("");
        this.notes.setText("");
    }
    private void clickMouseproductTable(MouseEvent arg0) {
        // TODO Auto-generated method stub
        int row = table.getSelectedRow();
        product_ID.setText((String)table.getValueAt(row, 0));
        product_name.setText((String)table.getValueAt(row, 1));
        product_price.setText((String)table.getValueAt(row, 2));
        product_stock.setText((String)table.getValueAt(row,3));
        notes.setText((String)table.getValueAt(row,4));
    }
    private void fillProductTable(Product product){
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = database_conn.getCon();
            rs = productDao.listproduct(conn, product);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("product_num"));
                v.add(rs.getString("product_price"));
                v.add(rs.getString("notes"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            database_conn.close(conn, rs);
        }
    }
    private void fillProductStockTable(Product product){
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = database_conn.getCon();
            rs = productDao.QueryStock(conn, product);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("product_num"));
                v.add(rs.getString("product_price"));
                v.add(rs.getString("notes"));
                dtm.addRow(v);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            {
                database_conn.close(conn, rs);
            }
        }
    }
}
