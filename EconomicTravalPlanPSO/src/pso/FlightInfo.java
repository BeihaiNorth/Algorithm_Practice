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
    
    private String cityname[] = {"Boston","Chicago","Dallas","Las Vegas","Los Angeles","New York","Orlando","Philadelphia","Seattle","Washington"};// city names
    
    private int ticketPrice[][] = {{0,150,310,360,280,204,241,244,271,170},
                                    {150,0,239,244,249,122,114,363,348,239},
                                    {310,239,0,243,220,229,259,250,264,250},
                                    {360,244,243,0,99, 340,400,340,200,300},
                                    {280,249,220,99, 0,440,286,266,150,330},
                                    {204,122,229,340,440,0,241,430,230,190},
                                    {241,114,259,400,286,241,0,400,428,100},
                                    {244,363,250,340,266,430,400,0,399,360},
                                    {271,348,264,200,150,230,428,399,0,321},
                                    {170,239,250,300,330,190,100,360,321,0},};//undirected graph for flight ticket price between cities
    
    public FlightInfo(int citynum){
        
//        cityname = new String[citynum];
//        ticketPrice = new int[citynum][citynum];
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
