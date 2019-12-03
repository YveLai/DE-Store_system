package Model;

public class Shoppingrecords {
    private int id;
    private String payer;
    private String age;
    private String gender;
    private String product;
    private String price;
    private String Enabling;

    public Shoppingrecords(int id, String payer, String age, String gender,
                           String product, String price, String Enabling) {
        super();
        this.id = id;
        this.payer = payer;
        this.age = age;
        this.gender = gender;
        this.product = product;
        this.price = price;
        this.Enabling = Enabling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEnabling() {
        return Enabling;
    }

    public void setEnabling(String Enabling) {
        this.Enabling = Enabling;
    }

    public String toString() {
        String m = "Payer:" + payer + "\t"
                + "Age:" + age + "\t"
                + "Gender" + gender + "\t"
                + "Product name: " + product + "\t"
                + "Price:" + price + "\t"
                + "Enabling" + Enabling + "\t";
        return m;
    }
}
