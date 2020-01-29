package com.example.myapplication;

public class TestClass {

    private String adatString;
    private long adatNumber;

    public TestClass(String adatString, long adatNumber) {
        this.adatString = adatString;
        this.adatNumber = adatNumber;
    }

    public TestClass() {
    }

    public String getAdatString() {
        return adatString;
    }

    public long getAdatNumber() {
        return adatNumber;
    }

    @Override
    public String toString() {
        return adatString + " " + adatNumber;
    }
}
