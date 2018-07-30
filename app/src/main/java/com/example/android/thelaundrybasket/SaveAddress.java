package com.example.android.thelaundrybasket;
import java.io.Serializable;

public class SaveAddress{
    String Home,HouseNo,LandMark,Address,City,Area,Subarea;

    public SaveAddress(String home, String houseNo, String landMark, String address, String city, String area, String subarea) {
        Home = home;
        HouseNo = houseNo;
        LandMark = landMark;
        Address = address;
        City = city;
        Area = area;
        Subarea = subarea;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(String houseNo) {
        HouseNo = houseNo;
    }

    public String getLandMark() {
        return LandMark;
    }

    public void setLandMark(String landMark) {
        LandMark = landMark;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getSubarea() {
        return Subarea;
    }

    public void setSubarea(String subarea) {
        Subarea = subarea;
    }
}
