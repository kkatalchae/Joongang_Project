package dao;

import dto.CosDTO;
import dto.FacilitiesDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FacilitiesDAO {

    private static FacilitiesDAO instance = new FacilitiesDAO();

    public static FacilitiesDAO getInstance() {
        return instance;
    }


    private Connection getConnection() throws Exception {

        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
        return ds.getConnection();

    }

    public List<FacilitiesDTO> getInfo(String cosName){
        List<FacilitiesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            String sql = "select * from facilities where cos = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cosName);
            rs = pstmt.executeQuery();

            while (rs.next()){
                FacilitiesDTO dto = new FacilitiesDTO();
                dto.setCos(rs.getString("cos"));
                dto.setKind(rs.getString("kind"));
                dto.setLatitude(rs.getString("latitude"));
                dto.setLongitude(rs.getString("longitude"));

                list.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (Exception e) {
            }
            if (con != null) try {
                con.close();
            } catch (Exception e) {
            }
        }
        return list;

    }


}
