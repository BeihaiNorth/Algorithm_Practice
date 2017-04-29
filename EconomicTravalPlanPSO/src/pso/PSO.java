/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import java.util.TimerTask;

/**
 *
 * @author leslie
 */
public class PSO extends TimerTask{
     
    Particle[] pars;    //particle swarm
    int gbest;          //record the current total lowest cost
    int pcount;         //total amount of particles
    FlightInfo flightInfo;

    public Particle[] getPars() {
        return pars;
    }

    public void setPars(Particle[] pars) {
        this.pars = pars;
    }

    public int getGbest() {
        return gbest;
    }

    public void setGbest(int gbest) {
        this.gbest = gbest;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }
    
    
    /** 
     * Initialization of particle swarm
     * @param n total number of particles 
     * @param startDay from 1 - 7 representing a day in a week
     */  
    public void init(int n, int cityNum, int startDay) {  
        pcount = n;  
        gbest = Integer.MAX_VALUE;  
        int index = -1;  //record the gbest
        pars = new Particle[pcount];
        flightInfo = new FlightInfo(10);
        FitnessFunction fitnessFunc = new FitnessFunction(flightInfo);
        
        //initialize paritcles and find gbest
        for (int i = 0; i < pcount; ++i) {  
            pars[i] = new Particle(fitnessFunc, cityNum, startDay);
            pars[i].setFitness(fitnessFunc.calcFitness(pars[i].getCityorder()));
//            System.out.println(pars[i].getFitness());
            if (gbest > pars[i].getFitness()) {
                gbest = pars[i].getFitness();
                System.out.println("gbest updated: "+gbest);
                index = i; 
            }
        }
        //initialize gbest solution for every particle
        Particle.gbest = pars[index].getCityorder();


    }

    
    @Override
    public void run() {  
        for (int i = 0; i < pcount; i++) {
            new Thread(pars[i],"particle"+i).start();
        }
    }
}
