/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

/**
 *
 * @author leslie
 */
public class FlightInfo {
    
    private String cityname[];// city names
    
    private int ticketPrice[][];//undirected graph for flight ticket price between cities
    
    public FlightInfo(int citynum){
        cityname = new String[citynum];
        ticketPrice = new int[citynum][citynum];
    }

    public String[] getCityname() {
        return cityname;
    }

    public void setCityname(String[] cityname) {
        this.cityname = cityname;
    }

    public int[][] getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int[][] ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    
    
    
    
}
