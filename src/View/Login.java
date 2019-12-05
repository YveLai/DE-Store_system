package View;

import Controller.StringUtil;
import Controller.database_conn;
import Dao.UserDao;
import Model.User;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

public class Login {

    private JFrame frame;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    private UserDao userDao = new UserDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
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
    public Login() {
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

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(93, 85, 83, 16);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(93, 142, 83, 16);
        frame.getContentPane().add(lblPassword);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(174, 80, 130, 26);
        frame.getContentPane().add(usernameTxt);
        usernameTxt.setColumns(10);

        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(174, 137, 130, 26);
        frame.getContentPane().add(passwordTxt);
        passwordTxt.setColumns(10);

        JButton btnSign = new JButton("Sign in");
        btnSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sign_inAction(e);
            }
        });
        btnSign.setBounds(89, 192, 87, 29);
        frame.getContentPane().add(btnSign);

        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register.main();
            }
        });
        btnSignUp.setBounds(174, 192, 87, 29);
        frame.getContentPane().add(btnSignUp);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetAction(e);
            }
        });
        btnClear.setBounds(262, 192, 87, 29);
        frame.getContentPane().add(btnClear);
    }

    private void Sign_inAction(ActionEvent arg0) {
        // TODO Auto-generated method stub
        String username = this.usernameTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if(StringUtil.isEmpty(username)){
            JOptionPane.showMessageDialog(null, "The username is empty!");
            return;
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null, "The password is empty!");
            return;
        }
        User user = new User(username, password);
        Connection conn = null;
        try {
            conn = database_conn.getCon();
            User currentUser = userDao.login(conn, user);
            if(currentUser!=null){
                JOptionPane.showMessageDialog(null, "Login successful!");
                MainView.main();
            }else{
                JOptionPane.showMessageDialog(null, "Login fail, the username or password is wrong!");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Login fail!");
        }finally{
            try {
                database_conn.close(conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void ResetAction(ActionEvent arg0) {
        // TODO Auto-generated method stub
        this.resetValue();
    }

    private void resetValue(){
        this.usernameTxt.setText("");
        this.passwordTxt.setText("");
    }
}
