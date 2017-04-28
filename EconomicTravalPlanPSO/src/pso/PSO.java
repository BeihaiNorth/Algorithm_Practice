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
    /** 
     * 粒子群 
     */  
    Particle[] pars;  
    int gbest;          //record the current total lowest cost
    int pcount;//粒子的数量
    FlightInfo flightInfo;
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
        FitnessFunction fitnessFunc = new FitnessFunction(flightInfo);
        
        //initialize paritcles and find gbest
        for (int i = 0; i < pcount; ++i) {  
            pars[i] = new Particle(fitnessFunc, cityNum, startDay);
            pars[i].setFitness(fitnessFunc.calcFitness(pars[i].getCityorder()));
            if (gbest > pars[i].getFitness()) {
                gbest = pars[i].getFitness();
                System.out.println("gbest updated: "+gbest);
                index = i;  
            }
        }
        //initialize gbest solution for every particle
        for (int i = 0; i < pcount; ++i) {  
            pars[i].setGbest(pars[index].getCityorder());
            new Thread(pars[i],"particle"+i);
        }  
    }  

    
    public void run() {  
        for (int i = 0; i < pcount; i++) {
            new Thread(pars[i],"particle"+i).start();
        }
//        System.out.println("particles position:"+"("+pars[0].getPosition()[0]+","+pars[0].getPosition()[1]+") "
//        +"("+pars[1].getPosition()[0]+","+pars[1].getPosition()[1]+") "
//        +"("+pars[2].getPosition()[0]+","+pars[2].getPosition()[1]+") "
//        +"("+pars[3].getPosition()[0]+","+pars[3].getPosition()[1]+") "
//        +"("+pars[4].getPosition()[0]+","+pars[4].getPosition()[1]+") "
//        +"("+pars[5].getPosition()[0]+","+pars[5].getPosition()[1]+") "
//        +"("+pars[6].getPosition()[0]+","+pars[6].getPosition()[1]+") "
//        +"("+pars[7].getPosition()[0]+","+pars[7].getPosition()[1]+") "
//        +"("+pars[8].getPosition()[0]+","+pars[8].getPosition()[1]+") "
//        +"("+pars[9].getPosition()[0]+","+pars[9].getPosition()[1]+") "
//        +"("+pars[10].getPosition()[0]+","+pars[10].getPosition()[1]+") "
//        +"("+pars[11].getPosition()[0]+","+pars[11].getPosition()[1]+") "
//        +"("+pars[12].getPosition()[0]+","+pars[12].getPosition()[1]+") "
//        +"("+pars[13].getPosition()[0]+","+pars[13].getPosition()[1]+") "
//        +"("+pars[14].getPosition()[0]+","+pars[14].getPosition()[1]+") "
//        +"("+pars[15].getPosition()[0]+","+pars[15].getPosition()[1]+") "
//        +"("+pars[16].getPosition()[0]+","+pars[16].getPosition()[1]+") "
//        +"("+pars[17].getPosition()[0]+","+pars[17].getPosition()[1]+") "
//        +"("+pars[18].getPosition()[0]+","+pars[18].getPosition()[1]+") "
//        +"("+pars[19].getPosition()[0]+","+pars[19].getPosition()[1]+") "
//        );
    }
}
