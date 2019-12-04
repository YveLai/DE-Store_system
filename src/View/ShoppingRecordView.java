package View;

import Dao.ShoppingDao;
import Model.Shoppingrecords;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ShoppingRecordView {

    private JFrame frame;
    private JTable table;
    private String head[] = null;
    private Object[][]data = null;
    private ShoppingDao shoppingDao = new ShoppingDao();
    private LayoutManager groupLayout;
    private JScrollPane scrollPane;

    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShoppingRecordView window = new ShoppingRecordView();
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
    public ShoppingRecordView() {
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
        head=new String[] {
                "id", "payer", "DOB", "gender", "product", "price", "Enabling",
        };

        DefaultTableModel tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(tableModel);

        //scrollPane.setViewportView(table);
        table.setBounds(6, 6, 344, 266);
        frame.getContentPane().add(table);

        JButton btnImport = new JButton("Import");
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnImport.setBounds(351, 6, 93, 29);
        frame.getContentPane().add(btnImport);
    }

    public Object[][] queryData(){
        List<Shoppingrecords> list = shoppingDao.queryAllUser();
        data=new Object[list.size()][head.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getId();
                data[i][1]=list.get(i).getPayer();
                data[i][2]=list.get(i).getDOB();
                data[i][3]=list.get(i).getGender();
                data[i][4]=list.get(i).getProduct();
                data[i][5]=list.get(i).getPrice();
                data[i][6]=list.get(i).getEnabling();
            }
        }
        return data;
    }

}
