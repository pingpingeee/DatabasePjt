package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnswerList extends JFrame {


    public AnswerList() {
        setTitle("답변");
        setBounds(0, 0, 450, 600);
        getContentPane().setLayout(null);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 49, 400, 450);
        getContentPane().add(scrollPane);


        System.out.println("답변 나오게 해주세요.");

        JButton btnClose = new JButton("닫기");
        //btnClose.setIcon(new ImageIcon(BoardInsert.class.getResource("/images/icon_stop_01.png")));
        btnClose.setBounds(220, 500, 97, 23);
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

            }
        });
        getContentPane().add(btnClose);

        setVisible(true);
    }

}
