package View;


import Controller.StringUtil;
import Controller.database_conn;
import Dao.OrderDao;
import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderInterView {
    private JFrame frame;
    private JTable table;
    private JTextField textField;
    private JTextField order_nameTxt;
    private JTextField order_numTxt;
    private JTextField order_dateTxt;
    private JTextField order_addressTxt;
    private JTextField order_phoneTxt;
    private JScrollPane scrollpane;
    private LayoutManager groupLayout;
    private OrderDao orderDao = new OrderDao();

    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderInterView window = new OrderInterView();
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
    public OrderInterView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.getContentPane().setLayout(null);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Number", "Date", "Address", "Tex"}
        )
        {
            boolean[] columnEditables = new boolean[]{false, false, false, false, false};
            public boolean isCellEditable(int row, int column){ return columnEditables[column]; }
        });
        table.getColumnModel().getColumn(0);
        table.getColumnModel().getColumn(1);
        frame.getContentPane().setLayout(groupLayout);
        this.fillOrdertTable(new Order());
        table.setBounds(6, 6, 251, 266);
        frame.getContentPane().add(table);

        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(269, 6, 61, 16);
        frame.getContentPane().add(lblProduct);

        order_nameTxt = new JTextField();
        order_nameTxt.setBounds(320, 0, 130, 26);
        frame.getContentPane().add(order_nameTxt);
        order_nameTxt.setColumns(10);

        JLabel lblNumber = new JLabel("Number:");
        lblNumber.setBounds(269, 45, 61, 16);
        frame.getContentPane().add(lblNumber);

        order_numTxt = new JTextField();
        order_numTxt.setBounds(320, 40, 130, 26);
        frame.getContentPane().add(order_numTxt);
        order_numTxt.setColumns(10);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(269, 84, 61, 16);
        frame.getContentPane().add(lblDate);

        order_dateTxt = new JTextField();
        order_dateTxt.setBounds(320, 79, 130, 26);
        frame.getContentPane().add(order_dateTxt);
        order_dateTxt.setColumns(10);

        JLabel lblStoreId = new JLabel("Store ID:");
        lblStoreId.setBounds(269, 123, 61, 16);
        frame.getContentPane().add(lblStoreId);

        order_addressTxt = new JTextField();
        order_addressTxt.setBounds(320, 118, 130, 26);
        frame.getContentPane().add(order_addressTxt);
        order_addressTxt.setColumns(10);

        JLabel lblTex = new JLabel("TEX:");
        lblTex.setBounds(269, 162, 61, 16);
        frame.getContentPane().add(lblTex);

        order_phoneTxt = new JTextField();
        order_phoneTxt.setBounds(320, 157, 130, 26);
        frame.getContentPane().add(order_phoneTxt);
        order_phoneTxt.setColumns(10);

        JButton btnOrder = new JButton("Order");
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrderAction(e);
            }
        });
        btnOrder.setBounds(365, 194, 79, 29);
        frame.getContentPane().add(btnOrder);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResetAction(e);
            }
        });
        btnClear.setBounds(365, 235, 79, 29);
        frame.getContentPane().add(btnClear);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchbookTypeActionPerformed(e);
            }
        });
        btnRefresh.setBounds(269, 235, 79, 29);
        frame.getContentPane().add(btnRefresh);

        textField = new JTextField();
        textField.setBounds(41, 221, 130, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
    }

    private void addOrderAction(ActionEvent e){
        String order_name = this.order_nameTxt.getText();
        String order_num = this.order_numTxt.getText();
        String order_time = this.order_dateTxt.getText();
        String order_address = this.order_addressTxt.getText();
        String order_TXE = this.order_phoneTxt.getText();
        if(StringUtil.isEmpty(order_name)){
            JOptionPane.showMessageDialog(null, "The product name is empty!");
            return;
        }
        if(StringUtil.isEmpty(order_num)){
            JOptionPane.showMessageDialog(null, "The order number is empty!");
            return;
        }
        if(StringUtil.isEmpty(order_time)){
            JOptionPane.showMessageDialog(null, "The order date is empty!");
            return;
        }
        Order order = new Order(order_name, order_num, order_time, order_address, order_TXE);
        Connection conn = null;
        try{
            conn = database_conn.getCon();
            int result = orderDao.addorder(conn, order);
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

    private void ResetAction(ActionEvent e){
        this.resetValue();
    }

    private void resetValue(){
        this.order_nameTxt.setText("");
        this.order_numTxt.setText("");
        this.order_dateTxt.setText("");
        this.order_addressTxt.setText("");
        this.order_phoneTxt.setText("");
    }



    private void fillOrdertTable(Order order){
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = database_conn.getCon();
            rs = orderDao.listproduct(conn, order);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("order_id"));
                v.add(rs.getString("order_name"));
                v.add(rs.getString("order_num"));
                v.add(rs.getString("order_time"));
                v.add(rs.getString("address"));
                v.add(rs.getString("phone"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            database_conn.close(conn, rs);
        }
    }

    private void searchbookTypeActionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String order_name = textField.getText();
        Order order = new Order(order_name);
        this.fillOrdertTable(order);
    }

}

