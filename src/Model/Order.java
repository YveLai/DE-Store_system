package Model;

public class Order {
    private int order_id;
    private String order_name;
    private String order_num;
    private String order_time;
    private String address;
    private String phone;

    public Order(int order_id, String order_name, String order_num, String order_time, String address, String phone){
        super();
        this.order_id = order_id;
        this.order_name = order_name;
        this.order_num = order_num;
        this.order_time = order_time;
        this.address = address;
        this.phone = phone;
    }

    public Order(String order_name, String order_num, String order_time, String address, String phone){
        super();
        this.order_name = order_name;
        this.order_num = order_num;
        this.order_time = order_time;
        this.address = address;
        this.phone = phone;
    }

    public Order(int order_id){
        super();
        this.order_id = order_id;
    }

    public Order(String order_name){
        super();
        this.getOrder_name();
    }

    public Order(){
        super();
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
