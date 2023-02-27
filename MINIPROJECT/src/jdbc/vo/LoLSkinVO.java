package jdbc.vo;

public class LoLSkinVO {
    private String chp_name;
    private String sk_name;
    private int sk_price;

    public LoLSkinVO(String chp_name){
        this.chp_name = chp_name;
    }

    public LoLSkinVO(String chp_name, String sk_name, int sk_price) {
        this.chp_name = chp_name;
        this.sk_name = sk_name;
        this.sk_price = sk_price;
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