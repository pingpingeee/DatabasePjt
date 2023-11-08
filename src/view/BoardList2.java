package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.BoardDAO;
import control.BoardVO;
import model.BoardDAOImpl;
public class BoardList2 extends JFrame {
    private JTable table;
    private JTextField searchString;
    public BoardList2(List<BoardVO> vos) {
        setTitle("진료 상담 게시판");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(BoardList2.class.getResource("/images/icon_docs_01.png")));
        setBounds(new Rectangle(0, 0, 600, 350));
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 49, 560, 189);
        getContentPane().add(scrollPane);

        BoardDAO dao = new BoardDAOImpl() {
/*            @Override
            public boolean joinService(SignUp_InVO vo) {
                return false;
            }*/
        };
        List<BoardVO> list = vos;

        String[] colNames = new String[] {"글번호", "제목", "내용", "작성자", "작성일"};
        Object[][] rowDatas = new Object[list.size()][colNames.length];

        for (int i = 0; i < list.size(); i++) {
            rowDatas[i] = new Object[] {
                    list.get(i).getNum(),
                    list.get(i).getTitle(),
                    list.get(i).getContent(),
                    list.get(i).getName(),
                    list.get(i).getRegDate()
            };
        }
        table = new JTable();
        table.setModel(new DefaultTableModel(rowDatas,colNames) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, true, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(164);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                int rowNum = table.getSelectedRow();
                BoardVO vos = new BoardVO();
                vos = list.get(rowNum);

                new BoardUpdate(vos);
            }
        });
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("검색조건");
        lblNewLabel.setBounds(186, 20, 56, 15);
        getContentPane().add(lblNewLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"title", "content", "writer"}));
        comboBox.setBounds(244, 17, 74, 21);
        getContentPane().add(comboBox);

        searchString = new JTextField();
        searchString.setBounds(330, 17, 133, 21);
        getContentPane().add(searchString);
        searchString.setColumns(10);

        JButton btnSearch = new JButton("검색 및 새로고침");
        //btnSearch.setIcon(new ImageIcon(BoardList2.class.getResource("/images/icon_search_01.png")));
        btnSearch.setBounds(467, 16, 105, 23);
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDAO dao = new BoardDAOImpl();
                dao.search(String.valueOf(comboBox.getSelectedItem()), searchString.getText());

                setVisible(false);

            }
        });
        getContentPane().add(btnSearch);

        JLabel lblDesignByDanielkim = new JLabel("GUI Test");
        lblDesignByDanielkim.setForeground(Color.GRAY);
        lblDesignByDanielkim.setBounds(434, 286, 138, 15);
        getContentPane().add(lblDesignByDanielkim);

        JButton btnWrite = new JButton("글작성");
        //btnWrite.setIcon(new ImageIcon(BoardList2.class.getResource("/images/icon_edit_01.png")));
        btnWrite.setBounds(475, 248, 97, 23);
        btnWrite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new BoardInsert();

            }
        });

        getContentPane().add(btnWrite);

        setVisible(true);
    }
}
