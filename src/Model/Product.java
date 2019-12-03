package Model;

public class Product {
    private int product_id;
    private String product_name;
    private String product_price;
    private String product_num;
    private String notes;

    public Product(int product_id, String product_name, String product_num,
                   String product_price, String notes){
        super();
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_num = product_num;
        this.notes = notes;
    }
    public Product(String product_name, String product_num,
                   String product_price, String notes){
        super();
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_num = product_num;
        this.notes = notes;
    }

    public Product() {
        super();
    }

    public Product(int product_id) {
        super();
        this.product_id = product_id;
    }

    public Product(String product_name) {
        super();
        this.product_name = product_name;
    }

    public int getProduct_id() {return product_id;}
    public void setProduct_id(int product_id) {this.product_id = product_id;}

    public String getProduct_name() {return product_name;}
    public void setProduct_name(String product_name) {this.product_name = product_name;}

    public String getProduct_price() {return product_price;}
    public void setProduct_price(String product_price) {this.product_price = product_price;}

    public String getProduct_num() {return product_num;}
    public void setProduct_num(String product_num) {this.product_num = product_num;}

    public String getNotes() {return notes;}
    public void setNotes(String notes) {this.notes = notes;}

}
