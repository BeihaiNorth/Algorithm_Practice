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
public class FitnessFunction {
    
    private FlightInfo flightInfo;
    
    public FitnessFunction(FlightInfo flightInfo){
        this.flightInfo = flightInfo;
    }
    
    public int calcFitness(int []solution){
        int totalPrice = 0;
        int cityNum = solution.length;
        int [][]ticketPriceGraph =  flightInfo.getTicketPrice();
        for (int i = 0; i < cityNum-1; i++) {
            int a = solution[i];
            int b = solution[i+1];
            int price = ticketPriceGraph[a][b];
            totalPrice += price;
        }
        //adding the ticket price going back to the starting city
        totalPrice += ticketPriceGraph[0][cityNum-1];
        return totalPrice;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }
    
    
}
