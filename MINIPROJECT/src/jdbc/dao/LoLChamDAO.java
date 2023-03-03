package jdbc.dao;


import jdbc.JdbcMain;
import jdbc.util.Common;
import jdbc.vo.LoLChamVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoLChamDAO {
    JdbcMain main = new JdbcMain();
    String currentID = main.getLoginSuccess();
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // SQL 문을 수행하기 위한 객체
    PreparedStatement pstmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    Scanner sc = new Scanner(System.in);
    public List<LoLChamVO> LoLChamSelect() {
        List<LoLChamVO> list = new ArrayList<>(); // 반환한 리스트를 위해 리스트 객체 생성

        try {
            conn = Common.getConnection(); // 연결을 가져옴
            stmt = conn.createStatement(); // stmt 에 쿼리문을 담아서 가져옴
            String sql = "SELECT * FROM CHAMPION";
            rs = stmt.executeQuery(sql); // rs에 쿼리문의 값이 담김, select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용

            while (rs.next()) { // 읽을 행이 있으면 참
                String chp_name = rs.getString("CHP_NAME");
                int chp_price = rs.getInt("CHP_PRICE");
                String position = rs.getString("POSITION");
                String lane = rs.getString(("LANE"));
                LoLChamVO vo = new LoLChamVO(chp_name, chp_price, position, lane); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list.add(vo); // 리스트에 추가
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void LoLSelectPrint(List<LoLChamVO> list) {
        for(LoLChamVO e: list){
            System.out.println("챔피언 이름 : " + e.getChp_name());
            System.out.println("챔피언 가격 : " + e.getChp_price());
            System.out.println("포지션 : " + e.getPosition());
            System.out.println("라인 : " + e.getLane());
            System.out.println("---------------------------------");
        }
    }

    public void LoLChamInsert() {
        List<LoLChamVO> list2 = new ArrayList<>();
        System.out.println("구매하실 챔피언 이름을 입력해 주세요 : ");
        String ChamBuyName = sc.next();
        try {
            conn = Common.getConnection(); // 연결을 가져옴
            stmt = conn.createStatement(); // stmt 에 쿼리문을 담아서 가져옴
            String buy_sql = "SELECT * FROM CHAMPION WHERE CHP_NAME = '" + ChamBuyName + "'";
            rs = stmt.executeQuery(buy_sql); // rs에 쿼리문의 값이 담김, select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용

            while (rs.next()) { // 읽을 행이 있으면 참
                String chp_nameb = rs.getString("CHP_NAME");
                int chp_priceb = rs.getInt("CHP_PRICE");
                String positionb = rs.getString("POSITION");
                String laneb = rs.getString(("LANE"));
                LoLChamVO vo1 = new LoLChamVO(chp_nameb, chp_priceb, positionb, laneb); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list2.add(vo1); // 리스트에 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO CHAMPION_BUY(CHPNO,ID,CHP_NAME,CHP_PRICE,POSITION,LANE) VALUES(SEQ_CHPSEQ.NEXTVAL,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            for(LoLChamVO e: list2){
                pstmt.setString(1, currentID);
                pstmt.setString(2, e.getChp_name());
                pstmt.setInt(3, e.getChp_price());
                pstmt.setString(4, e.getPosition());
                pstmt.setString(5, e.getLane());
            }

            pstmt.executeUpdate();
            System.out.println(ChamBuyName + "을 구입 완료 했습니다.");
        } catch (Exception e) {
            System.out.println("이미 구매한 챔피언 입니다.");
            LoLChamDAO lolchamdao = new LoLChamDAO();
            lolchamdao.LoLChamInsert();
        }

        String glod_sql = "UPDATE USER_INFO SET GOLD = GOLD - ?, BUY_GOLD = NVL(BUY_GOLD,0) + ? WHERE ID = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(glod_sql);
            for(LoLChamVO e: list2){
                pstmt.setInt(1, e.getChp_price());
                pstmt.setInt(2, e.getChp_price());
                pstmt.setString(3, currentID);
            }
            pstmt.executeUpdate();
            System.out.println("회원정보 업데이트 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        Common.close(pstmt);

    }
    public List<LoLChamVO> UserLoLChamDAO() {
        List<LoLChamVO> list3 = new ArrayList<>();
        System.out.println("=================================");
        try {
            conn = Common.getConnection(); // 연결을 가져옴
            stmt = conn.createStatement(); // stmt 에 쿼리문을 담아서 가져옴
            String buy_sql = "SELECT * FROM CHAMPION_BUY WHERE ID = '" + currentID + "'";
            rs = stmt.executeQuery(buy_sql); // rs에 쿼리문의 값이 담김, select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용
            while (rs.next()) { // 읽을 행이 있으면 참
                String chp_name = rs.getString("CHP_NAME");
                int chp_price = rs.getInt("CHP_PRICE");
                String position = rs.getString("POSITION");
                String lane = rs.getString(("LANE"));
                LoLChamVO vo = new LoLChamVO(chp_name, chp_price, position, lane); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list3.add(vo); // 리스트에 추가
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list3;
    }
    public void UserLoLSelectPrint(List<LoLChamVO> list3) {
        System.out.println("===========보유 중인 챔피언 목록===========");
        for(LoLChamVO e: list3){
            System.out.println("챔피언 이름 : " + e.getChp_name());
            System.out.println("포지션 : " + e.getPosition());
            System.out.println("라인 : " + e.getLane());
            System.out.println("---------------------------------");
        }
    }
}