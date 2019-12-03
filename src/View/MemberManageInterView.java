package View;

import Controller.StringUtil;
import Controller.database_conn;
import Dao.ClientDao;
import Model.Client;

import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MemberManageInterView {

    private JFrame frame;
    private JTextField member_idTxt;
    private JTextField member_nameTxt;
    private JTextField member_DOBTxt;
    private JTextField member_genderTxt;
    private JTextField member_phoneTxt;
    private JTextField line1Txt;
    private JTextField line2Txt;
    private JTextField townTxt;
    private JTextField PostcodeTxt;
    private JTextField loyaltyTxt;
    private JTable table;
    JScrollPane scrollpane = new JScrollPane();
    private static database_conn database = new database_conn();
    private static ClientDao clientDao = new ClientDao();
    private LayoutManager groupLayout;

    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemberManageInterView window = new MemberManageInterView();
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
    public MemberManageInterView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 530, 418);
        frame.getContentPane().setLayout(null);
        /**
         * "Manage member" Tab
         */

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickMousememberTable(e);
            }
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Birthday", "gender", "phone", "line1",
                        "line2", "town", "postcode", "loyalty"}
        )
        {
            boolean[] columnEditables = new boolean[]{false, false, false, false, false, false};
            public boolean isCellEditable(int row, int column){ return columnEditables[column]; }
        });
        table.getColumnModel().getColumn(0);
        table.getColumnModel().getColumn(1);
        frame.getContentPane().setLayout(groupLayout);
        this.fillMemberTable(new Client());
        table.setBounds(6, 6, 497, 130);
        frame.getContentPane().add(table);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(31, 148, 61, 16);
        frame.getContentPane().add(lblId);

        member_idTxt = new JTextField();
        member_idTxt.setEditable(false);
        member_idTxt.setBounds(88, 143, 108, 26);
        frame.getContentPane().add(member_idTxt);
        member_idTxt.setColumns(10);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(31, 191, 61, 16);
        frame.getContentPane().add(lblName);

        member_nameTxt = new JTextField();
        member_nameTxt.setEditable(false);
        member_nameTxt.setBounds(88, 186, 108, 26);
        frame.getContentPane().add(member_nameTxt);
        member_nameTxt.setColumns(10);

        JLabel lblBirthday = new JLabel("Birthday:");
        lblBirthday.setBounds(31, 234, 61, 16);
        frame.getContentPane().add(lblBirthday);

        member_DOBTxt = new JTextField();
        member_DOBTxt.setEditable(false);
        member_DOBTxt.setColumns(10);
        member_DOBTxt.setBounds(88, 229, 108, 26);
        frame.getContentPane().add(member_DOBTxt);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(31, 277, 61, 16);
        frame.getContentPane().add(lblGender);

        member_genderTxt = new JTextField();
        member_idTxt.setEditable(false);
        member_genderTxt.setColumns(10);
        member_genderTxt.setBounds(88, 272, 108, 26);
        frame.getContentPane().add(member_genderTxt);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(31, 320, 61, 16);
        frame.getContentPane().add(lblPhone);

        member_phoneTxt = new JTextField();
        member_phoneTxt.setColumns(10);
        member_phoneTxt.setBounds(88, 315, 108, 26);
        frame.getContentPane().add(member_phoneTxt);

        JLabel lblLine = new JLabel("Line1:");
        lblLine.setBounds(235, 148, 61, 16);
        frame.getContentPane().add(lblLine);

        line1Txt = new JTextField();
        line1Txt.setColumns(10);
        line1Txt.setBounds(293, 143, 108, 26);
        frame.getContentPane().add(line1Txt);

        JLabel lblLine_1 = new JLabel("Line2:");
        lblLine_1.setBounds(235, 191, 61, 16);
        frame.getContentPane().add(lblLine_1);

        line2Txt = new JTextField();
        line2Txt.setColumns(10);
        line2Txt.setBounds(293, 186, 108, 26);
        frame.getContentPane().add(line2Txt);

        JLabel lblTown = new JLabel("Town:");
        lblTown.setBounds(235, 234, 61, 16);
        frame.getContentPane().add(lblTown);

        townTxt = new JTextField();
        townTxt.setColumns(10);
        townTxt.setBounds(293, 229, 108, 26);
        frame.getContentPane().add(townTxt);

        JLabel lblPostcode = new JLabel("Postcode:");
        lblPostcode.setBounds(235, 277, 61, 16);
        frame.getContentPane().add(lblPostcode);

        PostcodeTxt = new JTextField();
        PostcodeTxt.setColumns(10);
        PostcodeTxt.setBounds(293, 272, 108, 26);
        frame.getContentPane().add(PostcodeTxt);

        JLabel lblLoyalty = new JLabel("Loyalty:");
        lblLoyalty.setBounds(235, 320, 61, 16);
        frame.getContentPane().add(lblLoyalty);

        loyaltyTxt = new JTextField();
        loyaltyTxt.setColumns(10);
        loyaltyTxt.setBounds(293, 315, 108, 26);
        frame.getContentPane().add(loyaltyTxt);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(6, 143, 197, 201);
        frame.getContentPane().add(panel_4, BorderLayout.CENTER);

        JPanel panel_5 = new JPanel();
        panel_5.setBounds(208, 143, 197, 201);
        frame.getContentPane().add(panel_5);

        JButton btnNewButton = new JButton("MODIFY");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMemberActionPerformed(e);
            }
        });
        btnNewButton.setBounds(404, 143, 105, 29);
        frame.getContentPane().add(btnNewButton, BorderLayout.EAST);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMemberActionPerformed(e);
            }
        });
        btnDelete.setBounds(404, 234, 105, 29);
        frame.getContentPane().add(btnDelete);

        JButton btnClear_1 = new JButton("CLEAR");
        btnClear_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberResetActionPerformed(e);
            }
        });
        btnClear_1.setBounds(404, 315, 105, 29);
        frame.getContentPane().add(btnClear_1);
    }

    private void deleteMemberActionPerformed(ActionEvent arg0) {
        String client_id = member_idTxt.getText();
        if (StringUtil.isEmpty(client_id)) {
            JOptionPane.showMessageDialog(null, "Select record to delete");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "You sure you want to delete it?");
        if(n==0) {
            Connection conn = null;
            try {
                conn = database_conn.getCon();
                Client client = new Client(Integer.parseInt(client_id));
                int result = clientDao.DeleteMember(conn, client);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "delete success!");
                    this.fillMemberTable(new Client());
                    this.ResetValue();
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

    private void updateMemberActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        String member_id = member_idTxt.getText();
        String member_name = member_nameTxt.getText();
        String member_DOB = member_DOBTxt.getText();
        String member_gender = member_genderTxt.getText();
        String member_phone = member_phoneTxt.getText();
        String member_line1 = line1Txt.getText();
        String member_line2 = line2Txt.getText();
        String member_town = townTxt.getText();
        String member_postcode = PostcodeTxt.getText();
        String member_loyalty= loyaltyTxt.getText();
        if(StringUtil.isEmpty(member_id)){
            JOptionPane.showMessageDialog(null, "Select record to modify!");
            return;
        }
        if(StringUtil.isEmpty(member_name)){
            JOptionPane.showMessageDialog(null, "Please input member name!");
            return;
        }
        Client client = new Client(Integer.parseInt(member_id), member_name,member_DOB,member_gender, member_phone,
                member_line1,member_line2,member_town,member_postcode,member_loyalty);
        Connection conn = null;
        try {
            conn = database_conn.getCon();
            int result = clientDao.UpdataMember(conn, client);
            if(result==1){
                JOptionPane.showMessageDialog(null, "Modify success!");
                this.fillMemberTable(new Client());
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

    private void MemberResetActionPerformed(ActionEvent e){
        this.ResetValue();
    }
    private void ResetValue(){
        this.member_idTxt.setText("");
        this.member_nameTxt.setText("");
        this.member_DOBTxt.setText("");
        this.member_genderTxt.setText("");
        this.member_phoneTxt.setText("");
        this.line1Txt.setText("");
        this.line2Txt.setText("");
        this.townTxt.setText("");
        this.PostcodeTxt.setText("");
        this.loyaltyTxt.setText("");
    }

    private void clickMousememberTable(MouseEvent arg0) {
        // TODO Auto-generated method stub
        int row = table.getSelectedRow();
        member_idTxt.setText((String)table.getValueAt(row, 0));
        member_nameTxt.setText((String)table.getValueAt(row, 1));
        member_DOBTxt.setText((String)table.getValueAt(row, 2));
        member_genderTxt.setText((String)table.getValueAt(row,3));
        member_phoneTxt.setText((String)table.getValueAt(row,4));
        line1Txt.setText((String)table.getValueAt(row, 5));
        line2Txt.setText((String)table.getValueAt(row, 6));
        townTxt.setText((String)table.getValueAt(row, 7));
        PostcodeTxt.setText((String)table.getValueAt(row, 8));
        loyaltyTxt.setText((String)table.getValueAt(row, 9));
    }

    private void fillMemberTable(Client client){
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = database_conn.getCon();
            rs = clientDao.listproduct(conn, client);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("client_id"));
                v.add(rs.getString("client_name"));
                v.add(rs.getString("DOB"));
                v.add(rs.getString("gender"));
                v.add(rs.getString("phone_num"));
                v.add(rs.getString("line1"));
                v.add(rs.getString("line2"));
                v.add(rs.getString("town"));
                v.add(rs.getString("postcode"));
                v.add(rs.getString("loyalty_card"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            database_conn.close(conn, rs);
        }
    }
}
