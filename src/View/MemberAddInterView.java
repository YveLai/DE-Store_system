package View;

import Controller.StringUtil;
import Controller.database_conn;
import Dao.ClientDao;
import Model.Client;

import javax.swing.*;
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

public class MemberAddInterView {
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
                    MemberAddInterView window = new MemberAddInterView();
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
    public MemberAddInterView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 530, 418);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("Name");
        label.setBounds(30, 67, 61, 16);
        frame.getContentPane().add(label);

        member_nameTxt = new JTextField();
        member_nameTxt.setColumns(10);
        member_nameTxt.setBounds(125, 62, 130, 26);
        frame.getContentPane().add(member_nameTxt);

        JLabel label_1 = new JLabel("Birthday");
        label_1.setBounds(30, 112, 61, 16);
        frame.getContentPane().add(label_1);

        member_DOBTxt = new JTextField();
        member_DOBTxt.setColumns(10);
        member_DOBTxt.setBounds(125, 107, 130, 26);
        frame.getContentPane().add(member_DOBTxt);

        JLabel label_2 = new JLabel("Gender");
        label_2.setBounds(30, 157, 61, 16);
        frame.getContentPane().add(label_2);

        member_genderTxt = new JTextField();
        member_genderTxt.setColumns(10);
        member_genderTxt.setBounds(125, 152, 130, 26);
        frame.getContentPane().add(member_genderTxt);

        JLabel label_3 = new JLabel("Phone Number");
        label_3.setBounds(30, 202, 103, 16);
        frame.getContentPane().add(label_3);

        member_phoneTxt = new JTextField();
        member_phoneTxt.setColumns(10);
        member_phoneTxt.setBounds(125, 197, 130, 26);
        frame.getContentPane().add(member_phoneTxt);

        JLabel label_4 = new JLabel("Line1");
        label_4.setBounds(274, 67, 61, 16);
        frame.getContentPane().add(label_4);

        line1Txt = new JTextField();
        line1Txt.setColumns(10);
        line1Txt.setBounds(330, 62, 130, 26);
        frame.getContentPane().add(line1Txt);

        JLabel label_5 = new JLabel("Line2");
        label_5.setBounds(274, 112, 61, 16);
        frame.getContentPane().add(label_5);

        line2Txt = new JTextField();
        line2Txt.setColumns(10);
        line2Txt.setBounds(330, 107, 130, 26);
        frame.getContentPane().add(line2Txt);

        JLabel label_6 = new JLabel("Town");
        label_6.setBounds(274, 157, 61, 16);
        frame.getContentPane().add(label_6);

        townTxt = new JTextField();
        townTxt.setColumns(10);
        townTxt.setBounds(330, 152, 130, 26);
        frame.getContentPane().add(townTxt);

        JLabel label_7 = new JLabel("Postcode");
        label_7.setBounds(274, 202, 61, 16);
        frame.getContentPane().add(label_7);

        PostcodeTxt = new JTextField();
        PostcodeTxt.setColumns(10);
        PostcodeTxt.setBounds(330, 197, 130, 26);
        frame.getContentPane().add(PostcodeTxt);

        JLabel label_8 = new JLabel("Loyalty");
        label_8.setBounds(274, 247, 61, 16);
        frame.getContentPane().add(label_8);

        loyaltyTxt = new JTextField();
        loyaltyTxt.setColumns(10);
        loyaltyTxt.setBounds(330, 242, 130, 26);
        frame.getContentPane().add(loyaltyTxt);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null,"Personal",TitledBorder.LEADING,TitledBorder.TOP,null,null));
        panel_1.setBounds(19, 37, 243, 272);
        frame.getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null,"Address",TitledBorder.LEADING,TitledBorder.TOP,null,null));
        panel_2.setBounds(266, 37, 224, 272);
        frame.getContentPane().add(panel_2);

        JButton btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberAddActionPerformed(e);
            }
        });
        btnAdd.setBounds(106, 315, 117, 29);
        frame.getContentPane().add(btnAdd);

        JButton btnClear = new JButton("CLEAR");
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberResetvalueActionPerformed(e);
            }
        });
        btnClear.setBounds(306, 315, 117, 29);
        frame.getContentPane().add(btnClear);

        JButton btnManageMember = new JButton("Manage member");
        btnManageMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberManageInterView.main();
            }
        });
        btnManageMember.setBounds(384, 6, 140, 29);
        frame.getContentPane().add(btnManageMember);
    }

    private void MemberAddActionPerformed(ActionEvent e){
        String member_name = this.member_nameTxt.getText();
        String member_DOB = this.member_DOBTxt.getText();
        String member_gender = this.member_genderTxt.getText();
        String member_phone = this.member_phoneTxt.getText();
        String member_line1 = this.line1Txt.getText();
        String member_line2 = this.line2Txt.getText();
        String member_town = this.townTxt.getText();
        String member_postcode = this.PostcodeTxt.getText();
        String member_loyalty = this.loyaltyTxt.getText();
        if(StringUtil.isEmpty(member_name)){
            JOptionPane.showMessageDialog(null, "The member name is empty!");
            return;
        }
        if(StringUtil.isEmpty(member_phone)){
            JOptionPane.showMessageDialog(null, "The member phone is empty!");
            return;
        }
        if(StringUtil.isEmpty(member_postcode)){
            JOptionPane.showMessageDialog(null, "The member postcode is empty!");
            return;
        }
        Client client= new Client(member_name, member_DOB, member_gender, member_phone, member_line1, member_line2, member_town, member_postcode, member_loyalty);
        Connection conn = null;
        try{
            conn = database_conn.getCon();
            int result = ClientDao.AddClient(conn, client);
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

    private void MemberResetvalueActionPerformed(ActionEvent e){
        this.resetValue();
    }

    private void resetValue(){
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



}
