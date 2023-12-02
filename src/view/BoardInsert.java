package view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.BoardDAO;
import control.BoardVO;
import model.BoardDAOImpl;

public class BoardInsert extends JFrame {
    private JTextField title;
    private JTextField writer;
    private JTextField pass;

    public BoardInsert() {
        setBounds(new Rectangle(600, 0, 450, 400));
        setTitle("게시글등록");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(BoardInsert.class.getResource("/images/icon_editer.png")));
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);

        title = new JTextField("");
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);

        JTextArea textArea = new JTextArea("");
        textArea.setLineWrap(true);
        textArea.setRows(5);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(81, 53, 340, 169);
        getContentPane().add(scrollPane);

/*        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 240, 57, 15);
        getContentPane().add(lblNewLabel_2);

        writer = new JTextField("");
        writer.setBounds(81, 237, 116, 21);
        getContentPane().add(writer);
        writer.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("비밀번호");
        lblNewLabel_3.setBounds(12, 270, 57, 15);
        getContentPane().add(lblNewLabel_3);

        pass = new JTextField("");
        pass.setBounds(81, 267, 116, 21);
        getContentPane().add(pass);
        pass.setColumns(10);*/

        JButton btnWrite = new JButton("작성완료");
        //btnWrite.setIcon(new ImageIcon(BoardInsert.class.getResource("/images/icon_edit_01.png")));
        btnWrite.setBounds(81, 310, 116, 23);
        btnWrite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String titles = title.getText();
                String txtarea = textArea.getText();

                if(titles.isEmpty() || txtarea.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 칸을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                } else {
                    BoardDAO dao = new BoardDAOImpl();
                    BoardVO vo = new BoardVO();
                    vo.setTitle(titles);
                    vo.setContent(txtarea);
                    dao.insert(vo);

                    //JOptionPane.showMessageDialog(null, "게시글이 작성되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
            }
        });
        getContentPane().add(btnWrite);

        JButton btnClose = new JButton("닫기");
        //btnClose.setIcon(new ImageIcon(BoardInsert.class.getResource("/images/icon_stop_01.png")));
        btnClose.setBounds(209, 310, 97, 23);
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