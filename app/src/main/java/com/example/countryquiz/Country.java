package com.example.countryquiz;

/**
 * POJO class for main table entry object
 */


public class Country {
    private long id;
    private String country;
    //private String question;
    private String continent;

    /* default constructor */
    public Country(){
        this.id = -1;
        this.country = null;
        this.continent = null;
        //this.question = null;
    }

    public Country(String country, String continent) {
        this.id = 1;
        this.country = country;
        this.continent = continent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }//getCountry

    public void setCountry(String country) {
        this.country = country;;
    }//setCountry

    public String getContinent() {
        return continent;
    }//getContinent

    public void setContinent(String continent) {
        this.continent = continent;
    }//setContinent

    public String toString() {

        return id + ": " + country + " " + continent + " "; //+ question;
    }//toString

}