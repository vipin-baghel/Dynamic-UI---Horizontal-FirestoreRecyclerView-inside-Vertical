package com.king.value;

public class Items {
    private String Name , Imgurl, Price;


    public Items(){
        //Empty Constructor needed Do not delete
    }


    public Items(String name, String Imgurl , String price) {
        this.Name = name;
        this.Imgurl = Imgurl;
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public String getImgurl(){
        return Imgurl;
    }

    public String getPrice() {
        return Price;
    }

}
