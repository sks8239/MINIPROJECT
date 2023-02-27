package jdbc.vo;

public class LoLChamVO {
    private String chp_name;
    private int chp_price;
    private String position;
    private String lane;


    public LoLChamVO(String chp_name, int chp_price, String position, String lane) {
        this.chp_name = chp_name;
        this.chp_price = chp_price;
        this.position = position;
        this.lane = lane;
    }

    public String getChp_name() {
        return chp_name;
    }

    public void setChp_name(String chp_name) {
        this.chp_name = chp_name;
    }

    public int getChp_price() {
        return chp_price;
    }

    public void setChp_price(int chp_price) {
        this.chp_price = chp_price;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }
}
