package com.deysofts.covidlive;

public class helper {

    public String total_cases;
    public String new_cases;
    public String total_deaths;
    public String recovered;
    public helper() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public helper(String total_cases, String new_cases,String total_deaths,String recovered) {
        this.total_cases = total_cases;
        this.new_cases= new_cases;
        this.total_deaths=total_deaths;
        this.recovered=recovered;
    }

    public String getTotal_cases()
    {
        return total_cases;
    }
    public String getNew_cases()
    {
        return new_cases;
    }
    public void setNew_cases()
    {

    }
    public String getTotal_deaths()
    {
        return total_deaths;
    }
    public String getRecovered()
    {
        return recovered;
    }
    public void setName()
    {

    }
    public void setAttendance()
    {

    }

}