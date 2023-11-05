package view;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.BoardDAO;
import control.BoardVO;
import model.BoardDAOImpl;

public class BoardUpdate extends JFrame {

    private JTextField title;
    private JTextField writer;

    public BoardUpdate(BoardVO vo) {

        final int num = vo.getNum();

        setBounds(new Rectangle(600, 0, 450, 600));
        setTitle("게시글");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(BoardInsert.class.getResource("/images/icon_world_01.png")));
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);

        title = new JTextField(vo.getTitle());
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);

        JTextArea textArea = new JTextArea(vo.getContent());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        JScrollPane scrollPane = new JScrollPane(textArea); // ScrollPane 추가
        scrollPane.setBounds(81, 53, 340, 169); // ScrollPane의 위치와 크기 설정
        getContentPane().add(scrollPane); // Frame에 ScrollPane 추가


        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 240, 57, 15);
        getContentPane().add(lblNewLabel_2);

        writer = new JTextField(vo.getName());
        writer.setBounds(81, 237, 116, 21);
        getContentPane().add(writer);
        writer.setColumns(10);

/*        JButton btnWrite = new JButton("글수정");
        //btnWrite.setIcon(new ImageIcon(BoardUpdate.class.getResource("/images/icon_repeat.png")));
        btnWrite.setBounds(81, 180, 97, 23);
        btnWrite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDAO dao = new BoardDAOImpl();
                BoardVO vo = new BoardVO();

                String titles = title.getText();
                String txtarea = textArea.getText();
                String name = writer.getText();
                vo.setNum(num);
                vo.setTitle(title.getText());
                vo.setContent(textArea.getText());
                vo.setName(writer.getText());

                dao.update(vo);

                setVisible(false);

            }
        });
        getContentPane().add(btnWrite);*/

        JButton btnDel = new JButton("글삭제");
        btnDel.setBounds(110, 280, 97, 23);
        btnDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:");

                if (password != null && !password.isEmpty()) {
                    BoardDAO dao = new BoardDAOImpl();
                    BoardVO vo = new BoardVO();

                    vo.setNum(num);
                    vo.setPass(password);

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