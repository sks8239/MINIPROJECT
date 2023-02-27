package jdbc.vo;

public class UserLoLSkinVO {
    private String id;
    private String chp_name;
    private String sk_name;
    private int sk_price;

    public UserLoLSkinVO(String id, String chp_name, String sk_name, int sk_price) {
        this.id = id;
        this.chp_name = chp_name;
        this.sk_name = sk_name;
        this.sk_price = sk_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChp_name() {
        return chp_name;
    }

    public void setChp_name(String chp_name) {
        this.chp_name = chp_name;
    }

    public String getSk_name() {
        return sk_name;
    }

    public void setSk_name(String sk_name) {
        this.sk_name = sk_name;
    }

    public int getSk_price() {
        return sk_price;
    }

    public void setSk_price(int sk_price) {
        this.sk_price = sk_price;
    }
}
