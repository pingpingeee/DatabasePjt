package view;

import control.AnswerVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorProfile extends JFrame {
    public DoctorProfile(AnswerVO vo){
        setTitle("의사프로필");
        setBounds(new Rectangle(0, 0, 300, 200));
        getContentPane().setLayout(null);


        JLabel name = new JLabel("이름");
        name.setBounds(40, 25, 57, 15);
        getContentPane().add(name);

        JTextField nameBox = new JTextField(vo.getDoctorName());
        nameBox.setBounds(100, 22, 130, 21);
        nameBox.setBackground(Color.white);
        nameBox.setEditable(false);
        getContentPane().add(nameBox);
        nameBox.setColumns(10);

        JLabel desc = new JLabel("진료과");
        desc.setBounds(40, 50, 57, 15);
        getContentPane().add(desc);

        JTextField descBox = new JTextField(vo.getDoctorTel());
        descBox.setBounds(100, 47, 130, 21);
        descBox.setBackground(Color.white);
        descBox.setEditable(false);
        getContentPane().add(descBox);
        descBox.setColumns(10);

        JLabel tel = new JLabel("전화번호");
        tel.setBounds(40, 75, 57, 15);
        getContentPane().add(tel);

        JTextField telBox = new JTextField(vo.getDoctorDesc());
        telBox.setBounds(100, 72, 130, 21);
        telBox.setBackground(Color.white);
        telBox.setEditable(false);
        getContentPane().add(telBox);
        telBox.setColumns(10);

        JButton cancel = new JButton("닫기");
        cancel.setBounds(95, 110, 93, 23);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });
        getContentPane().add(cancel);

        setVisible(true);
    }
}
