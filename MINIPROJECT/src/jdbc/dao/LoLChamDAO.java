package jdbc.dao;

import jdbc.util.Common;
import jdbc.vo.LoLChamVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoLChamDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // SQL 문을 수행하기 위한 객체
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용
    ResultSet rs2 = null;
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
        System.out.println("구매하실 챔피언 이름을 입력해 주세요 : ");
        String ChamBuyName = sc.next();
        String sql = "INSERT INTO CHAMPION_BUY SELECT * FROM CHAMPION WHERE CHP_NAME = " + "'"+ ChamBuyName + "'";
        System.out.println(ChamBuyName+"을 구입 완료 했습니다.");

        try {
            conn =  Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql); // 영향을 받는 값이 1개이므로 1이 들어온다.
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }


}
