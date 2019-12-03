package Model;

public class Client {
    private int client_id;
    private String client_name;
    private String DOB;
    private String gender;
    private String phone_num;
    private String line1;
    private String line2;
    private String town;
    private String postcode;
    private String loyalty_card;

    public Client(int client_id, String client_name, String DOB, String gender,
                  String phone_num, String line1, String line2, String town,
                  String postcode, String loyalty_card){
        super();
        this.client_id = client_id;
        this.client_name = client_name;
        this.DOB = DOB;
        this.gender = gender;
        this.phone_num = phone_num;
        this.line1 = line1;
        this.line2 = line2;
        this.town = town;
        this.postcode = postcode;
        this.loyalty_card = loyalty_card;
    }

    public int getClient_id() {return client_id;}

    public void setClient_id(int client_id) {this.client_id = client_id;}

    public String getClient_name() {return client_name;}

    public void setClient_name(String client_name) {this.client_name = client_name;}

    public String getDOB() {return DOB;}

    public void setDOB(String DOB) {this.DOB = DOB;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public String getPhone_num() {return phone_num;}

    public void setPhone_num(String phone_num) {this.phone_num = phone_num;}

    public String getLine1() {return line1;}

    public void setLine1(String line1) {this.line1 = line1;}

    public String getLine2() {return  line2;}

    public void setLine2(String line2) {this.line2 = line2;}

    public String getTown() {return town;}

    public void setTown(String town) {this.town = town;}

    public String getPostcode() {return  postcode;}

    public void setPostcode(String postcode) {this.postcode = postcode;}

    public String getLoyalty_card() {return loyalty_card;}

    public void setLoyalty_card(String loyalty_card) {this.loyalty_card = loyalty_card;}

    public String toString(){
        String m = client_name + "\t" + DOB + "\t" + gender;
        return m;
    }

}
