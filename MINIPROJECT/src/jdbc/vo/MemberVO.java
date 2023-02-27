package jdbc.vo;

import java.sql.Date;

public class MemberVO {
    private String id;
    private String pwd;
    private String email;
    private String nickName;
    private Date birthDay;
    private int PhNumber;
    private int gold;
    private int buyGold;
    private String rank;

    public MemberVO(String id, String pwd, String email, String nickName, Date birthDay, int phNumber, int gold, int buyGold, String rank) {
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.nickName = nickName;
        this.birthDay = birthDay;
        PhNumber = phNumber;
        this.gold = gold;
        this.buyGold = buyGold;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getPhNumber() {
        return PhNumber;
    }

    public void setPhNumber(int phNumber) {
        PhNumber = phNumber;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getBuyGold() {
        return buyGold;
    }

    public void setBuyGold(int buyGold) {
        this.buyGold = buyGold;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
