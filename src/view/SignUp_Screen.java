package view;

import control.SignUp_InVO;
import model.SignUp_InImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp_Screen extends JFrame {
    //회원가입
    private JTextField nickname;
    private JTextField account;
    private JPasswordField password;

    public  SignUp_Screen(){
        setTitle("회원가입");
        setBounds(new Rectangle(0, 0, 300, 400));
        getContentPane().setLayout(null);

        JLabel lblNewLabelNick = new JLabel("닉네임");
        lblNewLabelNick.setBounds(50, 115, 57, 15);
        getContentPane().add(lblNewLabelNick);

        JLabel lblNewLabel = new JLabel("아이디");
        lblNewLabel.setBounds(50, 150, 57, 15);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("비밀번호");
        lblNewLabel_1.setBounds(50, 185, 57, 15);
        getContentPane().add(lblNewLabel_1);

        nickname = new JTextField();
        nickname.setBounds(100,112, 120, 21);
        getContentPane().add(nickname);
        nickname.setColumns(10);

        account = new JTextField();
        account.setBounds(100, 147, 120, 21);
        getContentPane().add(account);
        account.setColumns(10);

        password = new JPasswordField();
        password.setBounds(100, 182, 120, 21);
        getContentPane().add(password);
        password.setColumns(10);

        JLabel error = new JLabel("사용할 수 없는 아이디 또는 비밀번호 입니다.");
        error.setBounds(50, 205, 200, 30);
        error.setForeground(Color.RED);
        error.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(error);

        Font fontNick = new Font(lblNewLabelNick.getFont().getName(), Font.PLAIN, 10);
        lblNewLabelNick.setFont(fontNick);
        Font fontError = new Font(error.getFont().getName(), Font.PLAIN, 9);
        error.setFont(fontError);
        Font fontAccount = new Font(lblNewLabel.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel.setFont(fontAccount);
        Font fontPassword = new Font(lblNewLabel_1.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel_1.setFont(fontPassword);


        JButton joinButton = new JButton("회원가입");
        joinButton.setBounds(50, 240, 85, 23);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredNick = nickname.getText();
                String enteredAccount = account.getText();
                char[] enteredPassword = password.getPassword();
                String enteredPasswordString = new String(enteredPassword);
                SignUp_InImpl signUpIn = new SignUp_InImpl();

                SignUp_InVO vo = new SignUp_InVO();
                vo.setNickname(enteredNick);
                vo.setAccount(enteredAccount);
                vo.setPassword(enteredPasswordString);

                boolean isSuccess = signUpIn.joinService(vo);

                if (isSuccess) {
                    System.out.println("성공");
                } else {
                    System.out.println("실패");
                }
            }
        });
        getContentPane().add(joinButton);

        JButton backButton = new JButton("뒤로가기");
        backButton.setBounds(150, 240, 85, 23);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main_SignIn_Screen();
                setVisible(false);
            }
        });
        getContentPane().add(backButton);
        setVisible(true);
    }
}
