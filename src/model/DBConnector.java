package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class DBConnector {

        private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
        private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        private static final String USERID = "DatabasePjt";
        private static final String USERPWD = "1234";

        private static final int MAX_CONNECTIONS = 10;

        private static List<Connection> connections = new ArrayList<>();

        static {
            try {
                Class.forName(DRIVER_NAME);
                //요청 들어오면 커넥션 생성
                for (int i = 0; i < MAX_CONNECTIONS; i++) {
                    Connection conn = DriverManager.getConnection(URL, USERID, USERPWD);
                    connections.add(conn);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() throws SQLException {
            if (connections.isEmpty()) {
                throw new SQLException("커넥션꽉찼다이");
            }
            return connections.remove(0);
        }

        //디비연결해서 불러오고 나서 반환해야함
        public static void releaseConnection(Connection conn) {
            try {
                if (conn != null && !conn.isClosed()) {
                    connections.add(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
/*    public static Connection getConnection() {
            try {
                Class.forName(DRIVER_NAME);
                conn = DriverManager.getConnection(URL, USERID, USERPWD);
                System.out.println("Connection 성공");
            } catch (ClassNotFoundException e) {
                System.out.println("드라이버없음");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("디비연결실패");
                e.printStackTrace();
            }
        return conn;
    }*/
    }
