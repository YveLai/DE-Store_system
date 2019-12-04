package View;

import Controller.StringUtil;
import Controller.database_conn;
import Dao.UserDao;
import Model.Product;
import Model.User;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

public class Register {

    private JFrame frame;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    private UserDao userDao = new UserDao();

    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register window = new Register();
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
    public Register() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(93, 85, 76, 16);
        frame.getContentPane().add(lblUsername);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(174, 80, 130, 26);
        frame.getContentPane().add(usernameTxt);
        usernameTxt.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(93, 142, 61, 16);
        frame.getContentPane().add(lblPassword);

        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(174, 137, 130, 26);
        frame.getContentPane().add(passwordTxt);
        passwordTxt.setColumns(10);

        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sign_upAction(e);
            }
        });
        btnSignUp.setBounds(93, 190, 91, 29);
        frame.getContentPane().add(btnSignUp);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetAction(e);
            }
        });
        btnClear.setBounds(213, 190, 91, 29);
        frame.getContentPane().add(btnClear);
    }

    public void Sign_upAction(ActionEvent e){
        String Username = this.usernameTxt.getText();
        String Password = new String(this.passwordTxt.getPassword());
        if(StringUtil.isEmpty(Username)){
            JOptionPane.showMessageDialog(null, "The Username is empty!");
            return;
        }
        if(StringUtil.isEmpty(Password)){
            JOptionPane.showMessageDialog(null, "The Password is empty!");
            return;
        }
        User user = new User(Username, Password);
        Connection conn = null;
        try{
            conn = database_conn.getCon();
            int result = userDao.Sign_up(conn, user);
            if(result==1){
                JOptionPane.showMessageDialog(null, "Registration success!");
                this.resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "Registration failed!");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed!");
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
        this.usernameTxt.setText("");
        this.passwordTxt.setText("");
    }

}
