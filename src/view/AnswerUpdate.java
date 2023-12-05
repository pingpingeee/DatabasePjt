package view;

import control.AnswerDAO;
import control.AnswerVO;
import control.BoardDAO;
import control.BoardVO;
import model.AnswerDAOImpl;
import model.BoardDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnswerUpdate extends JFrame {
    private JTextField title;
    private JTextField writer;

    public AnswerUpdate(AnswerVO vo){
        final int num = vo.getAnsNum();

        setBounds(new Rectangle(600, 0, 450, 600));
        setTitle("답변");

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);

        title = new JTextField(vo.getTitle());
        title.setBounds(81, 22, 340, 21);
        title.setBackground(Color.white);
        title.setEditable(false);
        getContentPane().add(title);
        title.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);

        JTextArea textArea = new JTextArea(vo.getContent());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(81, 53, 340, 169);
        textArea.setBackground(Color.white);
        textArea.setEditable(false);
        getContentPane().add(scrollPane);


        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 240, 57, 15);
        getContentPane().add(lblNewLabel_2);

        writer = new JTextField(vo.getDocterId());
        writer.setBounds(81, 237, 116, 21);
        writer.setBackground(Color.white);
        writer.setEditable(false);
        getContentPane().add(writer);
        writer.setColumns(10);


        JButton btnDel = new JButton("답변삭제");
        btnDel.setBounds(110, 280, 97, 23);
        btnDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:");

                if (password != null) {
                    AnswerDAO dao = new AnswerDAOImpl();
                    AnswerVO vo = new AnswerVO();

                    vo.setAnsNum(num);
                    vo.setDoctorPw(password);

                    dao.delete(vo);

                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        getContentPane().add(btnDel);

        JButton btnClose = new JButton("닫기");
        //btnClose.setIcon(new ImageIcon(BoardInsert.class.getResource("/images/icon_stop_01.png")));
        btnClose.setBounds(220, 280, 97, 23);
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
