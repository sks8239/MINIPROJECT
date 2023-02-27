package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.EmpVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // sql문을 수행하기 위한 객체
    ResultSet rs = null;  // statement 동작에 대한 결과로 전달되는 db의 내용
    Scanner sc = new Scanner(System.in);

    public List<EmpVO> empSelect(){
        List<EmpVO> list = new ArrayList<>(); // 반환할 리스트를 위해 객체 생성
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM EMP";
            rs = stmt.executeQuery(sql); // select 문과 같이 여러개의 레코드(행)으로 결과가 반환될 때 사용

            while(rs.next()){ // 읽을 행이 있으면 참
                int no = rs.getInt("EMPNO");
                String name = rs.getString("ENAME");
                String job = rs.getString("JOB");
                int mgr = rs.getInt("MGR");
                Date date = rs.getDate("HIREDATE");
                int sal = rs.getInt("SAL");
                int comm = rs.getInt("COMM");
                int deptNo = rs.getInt("DEPTNO");
                EmpVO vo = new EmpVO(no,name,job,mgr,date,sal,comm,deptNo); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list.add(vo);
            }
            Common.close(rs); // 연결의 역순 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void empSelectPrint(List<EmpVO> list){
        for(EmpVO e : list){
            System.out.println("사원번호 : " + e.getEmpNO());
            System.out.println("이름 : " + e.getName());
            System.out.println("직책 : " + e.getJob());
            System.out.println("매니저 : " + e.getMgr());
            System.out.println("입사일 : " + e.getDate());
            System.out.println("급여 : " + e.getSal());
            System.out.println("성과급 : " + e.getComm());
            System.out.println("부서번호 : " + e.getDeptNo());
            System.out.println("--------------");
        }
    }
    public void empInsert(){
        System.out.println("사원 정보를 입력하세요 : ");
        System.out.print("사원번호(4자리) : ");
        int no = sc.nextInt();
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.print("직책 : ");
        String job = sc.next();
        System.out.print("매니저 : ");
        int mgr = sc.nextInt();
        System.out.print("입사일 : ");
        String date = sc.next();
        System.out.print("급여 : ");
        int sal = sc.nextInt();
        System.out.print("성과급 : ");
        int comm = sc.nextInt();
        System.out.print("부서번호 : ");
        int dept = sc.nextInt();

        String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES ("
                + no + ", " + "'"+ name + "'" + ", " + "'" +
                job + "'" + ", " + mgr + ", " + "'" + date + "'" + ", " + sal + ", " + comm + ", " + dept + ")";

        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System
        }
    }

}
