package com.example.cleanhome;

public class HousePost {
    String userName;

    String houseId;

    String noOfRooms;

    String noOfBathRooms;

    String floorType;

    String address;

    byte[] image;

    String price;

    String date;

    public HousePost(String userName, String houseId, String noOfRooms, String noOfBathRooms, String floorType, String address, byte[] image, String price, String date) {

        this.userName = userName;

        this.houseId = houseId;

        this.noOfRooms = noOfRooms;

        this.noOfBathRooms = noOfBathRooms;

        this.floorType = floorType;

        this.address = address;

        this.image = image;

        this.price = price;

        this.date = date;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getHouseId() {

        return houseId;
    }

    public void setHouseId(String houseId) {

        this.houseId = houseId;
    }

    public String getNoOfRooms() {

        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {

        this.noOfRooms = noOfRooms;
    }

    public String getNoOfBathRooms() {

        return noOfBathRooms;
    }

    public void setNoOfBathRooms(String noOfBathRooms) {

        this.noOfBathRooms = noOfBathRooms;
    }

    public String getFloorType() {

        return floorType;
    }

    public void setFloorType(String floorType) {

        this.floorType = floorType;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public byte[] getImage() {

        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {

        this.price = price;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }
}
