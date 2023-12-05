package view;

import control.AnswerDAO;
import control.AnswerVO;
import control.BoardDAO;
import control.BoardVO;
import model.AnswerDAOImpl;
import model.BoardDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AnswerList extends JFrame {

    private JTable table;
    public AnswerList() {
        setTitle("답변");
        setBounds(0, 0, 550, 400);
        getContentPane().setLayout(null);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 49, 500, 250);
        getContentPane().add(scrollPane);

        AnswerDAO dao = new AnswerDAOImpl();
        List<AnswerVO> list = dao.selectAnswers();

        String[] colNames = new String[] {"답변번호", "제목", "의사이름", "등록일"};
        Object[][] rowDatas = new Object[list.size()][colNames.length];

        for (int i = 0; i < list.size(); i++) {
            rowDatas[i] = new Object[]{
                    list.get(i).getAnsNum(),
                    list.get(i).getTitle(),
                    list.get(i).getDocterId(),
                    list.get(i).getRegDate()
            };
        }

        table = new JTable();

        table.setModel(new DefaultTableModel(rowDatas, colNames){
           boolean[] columnEditables = new boolean[]{
                   false, false,false, false
           };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(45);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                AnswerVO vos = new AnswerVO();
                vos = list.get(rowNum);

                //상세 추가
                new AnswerUpdate(vos);
            }
        });
        scrollPane.setViewportView(table);

        JButton btnClose = new JButton("닫기");
        //btnClose.setIcon(new ImageIcon(BoardInsert.class.getResource("/images/icon_stop_01.png")));
        btnClose.setBounds(220, 300, 97, 23);
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
