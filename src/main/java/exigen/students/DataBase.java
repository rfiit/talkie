package exigen.students;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private String BASENAME = "talkie";
    private String USERNAME = "talkieuser";
    private String USERPASSWORD = "talkiepassword";

    public DataBase()  {
        init();
    }

    private void init() {
        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to load the JDBC driver class");
        }
    }


    public boolean addAnswers(ArrayList<String> answers) {
        try {
            PreparedStatement st = getPreparedStatement("INSERT INTO answers  VALUES (?, '?');");
            st.getConnection().setAutoCommit(false);
            int i = 0;
            for (String s : answers) {
                st.setInt(1,i);
                st.setString(2,s);
                st.executeUpdate();
            }
            st.getConnection().commit();
            st.getConnection().setAutoCommit(true);
            st.getConnection().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BASENAME+"", USERNAME, USERPASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connect;
        }
    }

    public Statement getStatement() throws SQLException{
        Connection c = getConnection();
        Statement st = c.createStatement();
        return st;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        Connection c = getConnection();
        return c.prepareStatement(sql);
    }

    public String getAnswerByQuestion(String question, Statement st) {
        String result = null;
        try {
            st.execute("SELECT answer FROM answers, questions WHERE answers.id = questions.ansid AND questions.qmd5 = MD5('"+question+"');");
            ResultSet rs = st.getResultSet();
            if (rs.next()) result = rs.getString("answer");
            st.getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public String getAnswerByQuestion(String question) {
        try {
            return getAnswerByQuestion(question, getStatement());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addQuestionAndAnswer(String question, int answernumber) {
        try {
            Statement st = getStatement();
            //if (getAnswerByQuestion(question, st) != null) return false; // we need check it yet
            st.executeUpdate("INSERT INTO questions VALUES (MD5('"+question+"'), "+answernumber+");");
            st.getConnection().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<Integer, String> getAllAnswers() {
        try {
            HashMap<Integer, String> hm = new HashMap<Integer, String>();
            Statement st = getStatement();
            st.execute("SELECT * FROM answers");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                hm.put(rs.getInt("id"), rs.getString("answer"));
            }
            st.getConnection().close();
            return hm;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}