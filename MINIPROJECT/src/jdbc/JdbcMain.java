package jdbc;

import jdbc.dao.MemberDAO;
import jdbc.vo.MemberVO;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MemberDAO dao = new MemberDAO();
        int loginState = 0;
        boolean Success = false;
        while (true) {
            System.out.println("====== [EMP Table Command] =======");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.print("[1]로그인, [2]회원가입 [3]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<MemberVO> list = dao.memberSelect();
                    MemberDAO memberdao = new MemberDAO();
                    String loginSuccess = memberdao.lolLogin(list);
                    System.out.println(loginSuccess);
                    if (loginSuccess.equals("성공")) {
                        Success = true;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("종료");
                    break;
            }
            break;
        }
        if (Success) {
            while (true) {
                System.out.println("=============");
                System.out.println("메뉴를 선택하세요 : ");
                System.out.print("[1]게임시작, [2]상점, [3]종료");
                int sel = sc.nextInt();
                switch (sel) {
                    case 1:
                        System.out.println("게임을 시작합니다.");

                        break;
                    case 2:
                        System.out.println("상점에 입장합니다.");
                        break;
                    case 3:
                        System.out.println("종료합니다");
                        break;
                }
                break;
            }
        }
    }
}
