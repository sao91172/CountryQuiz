package com.example.countryquiz;

/**
 * POJO class for main table entry object
 */


public class Country {
    private long id;
    private String country;

    private String continent;


    /* default constructor */
    public Country(String country, String continent) {
        this.id = 1;
        this.country = country;
        this.continent = continent;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCountry() {return this.country;}//getCountry


    public String getContinent() {
        return this.continent;
    }//getContinent
}



