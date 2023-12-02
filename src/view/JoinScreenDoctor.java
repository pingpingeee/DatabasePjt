package view;

import control.Join_LoginVO;
import model.Join_LoginDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinScreenDoctor extends JFrame {
    //회원가입
    private JTextField nickname;
    private JTextField account;
    private JPasswordField password;
    private JTextField tel;
    private JButton joinButton;
    public JoinScreenDoctor(){
        setTitle("의사회원가입");
        setBounds(new Rectangle(0, 0, 300, 400));
        getContentPane().setLayout(null);

        JLabel lblNewLabelNick = new JLabel("닉네임");
        lblNewLabelNick.setBounds(50, 80, 57, 15);
        getContentPane().add(lblNewLabelNick);

        JLabel lblNewLabel = new JLabel("아이디");
        lblNewLabel.setBounds(50, 115, 57, 15);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("비밀번호");
        lblNewLabel_1.setBounds(50, 150, 57, 15);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2= new JLabel("전화번호");
        lblNewLabel_2.setBounds(50, 185, 57, 15);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3= new JLabel("진료과");
        lblNewLabel_3.setBounds(50, 210, 57, 15);
        getContentPane().add(lblNewLabel_3);

        nickname = new JTextField();
        nickname.setBounds(100,77, 120, 21);
        getContentPane().add(nickname);
        nickname.setColumns(10);

        account = new JTextField();
        account.setBounds(100, 112, 120, 21);
        getContentPane().add(account);
        account.setColumns(10);

        password = new JPasswordField();
        password.setBounds(100, 147, 120, 21);
        getContentPane().add(password);
        password.setColumns(10);

        tel = new JTextField();
        tel.setBounds(100, 182, 120, 21);
        getContentPane().add(tel);
        tel.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"내과", "신경과", "정신과", "정형외과", "신경외과", "산부인과",
        "이비인후과", "피부과", "비뇨기과", "결핵과", "재활의확과", "가정의학과"}));
        comboBox.setBounds(100, 217, 120, 21);
        getContentPane().add(comboBox);

/*         JLabel error = new JLabel("사용할 수 없는 아이디 또는 닉네임 입니다.");
        error.setBounds(50, 205, 200, 30);
        error.setForeground(Color.RED);
        error.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(error);*/

/*        Font fontError = new Font(error.getFont().getName(), Font.PLAIN, 9);
        error.setFont(fontError);*/

        Font fontNick = new Font(lblNewLabelNick.getFont().getName(), Font.PLAIN, 10);
        lblNewLabelNick.setFont(fontNick);
        Font fontAccount = new Font(lblNewLabel.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel.setFont(fontAccount);
        Font fontPassword = new Font(lblNewLabel_1.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel_1.setFont(fontPassword);
        Font fontTel = new Font(lblNewLabel_2.getFont().getName(), Font.PLAIN, 10);
        lblNewLabel_1.setFont(fontTel);

        JButton check = new JButton("아이디중복확인");
        check.setBounds(80, 250, 120, 23);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String accout = account.getText();
                Join_LoginDAOImpl check = new Join_LoginDAOImpl(JoinScreenDoctor.this);

                Join_LoginVO vo = new Join_LoginVO();
                vo.setId(accout);
                vo.setType(1);
                check.duplicateCheck(vo);
            }
        });
        getContentPane().add(check);

        joinButton = new JButton("회원가입");
        joinButton.setBounds(50, 280, 85, 23);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredNick = nickname.getText();
                String enteredAccount = account.getText();
                char[] enteredPassword = password.getPassword();
                String enteredPasswordString = new String(enteredPassword);
                String enteredTel = tel.getText();
                String enteredType = (String) comboBox.getSelectedItem();
                Join_LoginDAOImpl signUpIn = new Join_LoginDAOImpl(JoinScreenDoctor.this);
                if (enteredNick.equals("") || enteredAccount.equals("") ||
                        enteredPassword.equals("") || enteredTel.equals("")) {
                    JOptionPane.showMessageDialog(null, "모든 칸을 입력해주세요.",
                            "경고", JOptionPane.ERROR_MESSAGE);
                }else {
                    Join_LoginVO vo = new Join_LoginVO();
                    vo.setName(enteredNick);
                    vo.setId(enteredAccount);
                    vo.setPw(enteredPasswordString);
                    vo.setType(1);
                    vo.setPhnum(enteredTel);
                    vo.setSpeciality(enteredType);
                    joinButton.setEnabled(false);
                    signUpIn.joinServiceDoctor(vo);
                }
            }
        });
        getContentPane().add(joinButton);

        JButton backButton = new JButton("뒤로가기");
        backButton.setBounds(150, 280, 85, 23);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                setVisible(false);
            }
        });
        getContentPane().add(backButton);
        setVisible(true);
    }

    public JButton getJoinButton() {
        return joinButton;
    }
}
