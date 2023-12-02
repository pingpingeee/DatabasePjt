package view;

import control.Join_LoginVO;
import model.Join_LoginDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField nickname;
    private JTextField account;
    private JPasswordField password;

    public LoginScreen() {
        setTitle("로그인");
        setBounds(new Rectangle(0, 0, 300, 400));
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("아이디");
        lblNewLabel.setBounds(50, 130, 57, 15);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("비밀번호");
        lblNewLabel_1.setBounds(50, 165, 57, 15);
        getContentPane().add(lblNewLabel_1);

        account = new JTextField();
        account.setBounds(100, 127, 120, 21);
        getContentPane().add(account);
        account.setColumns(10);

        password = new JPasswordField();
        password.setBounds(100, 162, 120, 21);
        getContentPane().add(password);
        password.setColumns(10);

/*        JLabel error = new JLabel("아이디 또는 비밀번호가 올바르지않습니다.");
        error.setBounds(50, 185, 200, 30);
        error.setForeground(Color.RED);
        error.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(error);

        Font fontError = new Font(error.getFont().getName(), Font.PLAIN, 9);
        error.setFont(fontError);*/
        Font fontAccount = new Font(lblNewLabel.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel.setFont(fontAccount);
        Font fontPassword = new Font(lblNewLabel_1.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel_1.setFont(fontPassword);


        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(50, 230, 85, 23);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredAccount = account.getText();
                char[] enteredPassword = password.getPassword();
                String enteredPasswordString = new String(enteredPassword);
                Join_LoginDAOImpl signUpIn = new Join_LoginDAOImpl(LoginScreen.this);

                if (enteredAccount.equals("") || enteredPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "모든 칸을 입력해주세요.",
                            "경고", JOptionPane.ERROR_MESSAGE);
                }else{
                    Join_LoginVO vo = new Join_LoginVO();
                    vo.setId(enteredAccount);
                    vo.setPw(enteredPasswordString);
                    signUpIn.loginService(vo);
                }
            }
        });
        getContentPane().add(loginButton);

        JButton registerButton = new JButton("회원가입");
        registerButton.setBounds(150, 230, 85, 23);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new JoinScreen();
                setVisible(false);
            }
        });
        getContentPane().add(registerButton);


        JButton doctorButton = new JButton("의사회원가입");
        doctorButton.setBounds(100, 260, 110, 23);
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new JoinScreenDoctor();
                setVisible(false);
            }
        });
        getContentPane().add(doctorButton);


        setVisible(true);
    }
}
