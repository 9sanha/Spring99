package hello.core.order;

public class Order {

    private Long memverId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public void setMemverId(Long memverId) {
        this.memverId = memverId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getMemverId() {
        return memverId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public Order(Long memverId, String itemName, int itemPrice, int discountPrice) {
        this.memverId = memverId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int caculatePrice(){
        return itemPrice-discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memverId=" + memverId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }



}
