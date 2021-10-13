package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class MemberDAO {
    private static MemberDAO instance = new MemberDAO();

    public static MemberDAO getInstance(){
        return instance;
    }

    private Connection getConnection() throws Exception{
        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
        return ds.getConnection();

    }

}
