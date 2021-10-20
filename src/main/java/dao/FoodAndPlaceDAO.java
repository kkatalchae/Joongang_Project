package dao;

import dto.FoodAndPlaceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FoodAndPlaceDAO {

    private static FoodAndPlaceDAO instance = new FoodAndPlaceDAO();

    public static FoodAndPlaceDAO getInstance() {
        return instance;
    }


    private Connection getConnection() throws Exception {

        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
        return ds.getConnection();

    }

    public List<FoodAndPlaceDTO> getList(String cos){
        List<FoodAndPlaceDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con= getConnection();

            String sql = "select * from foodandplace where cos = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cos);
            rs = pstmt.executeQuery();

            if(rs.next()){
                FoodAndPlaceDTO dto = new FoodAndPlaceDTO();
                dto.setCos(rs.getString("cos"));
                dto.setName(rs.getString("name"));
                dto.setTel(rs.getString("tel"));
                dto.setAddress(rs.getString("address"));
                dto.setTime(rs.getString("time"));
                dto.setTrans(rs.getString("trans"));

                list.add(dto);

            }


        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
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
