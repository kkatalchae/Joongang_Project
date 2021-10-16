package dao;

import dto.CosDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CosDAO {

    private static CosDAO instance = new CosDAO();

    public static CosDAO getInstance(){
        return instance;
    }

    private Connection getConnection() throws Exception{
        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
        return ds.getConnection();

    }

    public CosDTO getCosInfo(String cosName){

        CosDTO cosDTO = new CosDTO();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();

            String sql = "select * from cos where cos = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cosName);
            rs = pstmt.executeQuery();

            if(rs.next()){
                cosDTO.setCosName("cos");
                cosDTO.setCosName("latitude");
                cosDTO.setCosName("longitude");
                cosDTO.setCosName("difficulty");
                cosDTO.setCosName("length");
                cosDTO.setCosName("taketime");
                cosDTO.setCosName("link");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(Exception e) {}
            if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
            if(con != null) try {con.close();}catch(Exception e) {}
        }

        return cosDTO;
    }
}
