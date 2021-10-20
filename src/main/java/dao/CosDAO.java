package dao;


import dto.CosDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class CosDAO {

    private static CosDAO instance = new CosDAO();

    public static CosDAO getInstance() {
        return instance;
    }


    private Connection getConnection() throws Exception {

        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
        return ds.getConnection();

    }


    public CosDTO getCosInfo(String cosName) {

        CosDTO cosDTO = new CosDTO();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String sql = "select * from cos where cos = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cosName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cosDTO.setCosName(rs.getString("cos"));
                cosDTO.setCosLatitude(rs.getString("latitude"));
                cosDTO.setCosLongitude(rs.getString("longitude"));
                cosDTO.setCosDifficulty(rs.getString("difficulty"));
                cosDTO.setCosLength(rs.getString("length"));
                cosDTO.setCosTakeTime(rs.getString("taketime"));
                cosDTO.setCosLink(rs.getString("link"));
            }
        } catch (Exception e) {
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

        return cosDTO;
    }


    public List<CosDTO> getCosList(int start, int end) {
        List<CosDTO> list = new ArrayList<CosDTO>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String sql = "select * from cos where rownum>=? and rownum<=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();     // SQL문 실행

            while (rs.next()) {
                CosDTO cos = new CosDTO();
                cos.setCosName(rs.getString("cos"));
                cos.setCosLatitude(rs.getString("latitude"));
                cos.setCosLongitude(rs.getString("longitude"));
                cos.setCosDifficulty(rs.getString("difficulty"));
                cos.setCosLength(rs.getString("length"));
                cos.setCosTakeTime(rs.getString("taketime"));
                cos.setCosLink(rs.getString("link"));

                list.add(cos);
            }

        } catch (Exception e) {
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


    public int getCount() {

        int listCount = 0;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String sql = "select count(*) from cos";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();        // SQL문 실행

            if (rs.next()) {
                listCount = rs.getInt("count(*)");
            }
        } catch (Exception e) {
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

        return listCount;
    }


}