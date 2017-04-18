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
    double global_best;//全局最优解  
    int pcount;//粒子的数量  
    /** 
     * 粒子群初始化 
     * @param n 粒子的数量 
     * @param x destination x position
     * @param y destination y position
     */  
    public void init(int n, double x, double y) {  
        pcount = n;  
        global_best = 10000;  
        int index = -1;  
        pars = new Particle[pcount];
        double[] goal = {x, y};
        FitnessFunction fitnessFunc = new FitnessFunction(goal);
        
        for (int i = 0; i < pcount; ++i) {  
            pars[i] = new Particle(fitnessFunc);
            pars[i].setFitness(fitnessFunc.calcFitness(goal));
            if (global_best > pars[i].getFitness()) {
                global_best = pars[i].getFitness();  
                index = i;  
            }
            new Thread(pars[i],"particle"+i);
        }
        for (int i = 0; i < 2; ++i) {  
            Particle.gbest = pars[index].getPosition();
        }  
    }  

    
    public void run() {  
        for (int i = 0; i < pcount; i++) {
            new Thread(pars[i],"particle"+i).start();
        }
        System.out.println("particles position:"+"("+pars[0].getPosition()[0]+","+pars[0].getPosition()[1]+") "
        +"("+pars[1].getPosition()[0]+","+pars[1].getPosition()[1]+") "
        +"("+pars[2].getPosition()[0]+","+pars[2].getPosition()[1]+") "
        +"("+pars[3].getPosition()[0]+","+pars[3].getPosition()[1]+") "
        +"("+pars[4].getPosition()[0]+","+pars[4].getPosition()[1]+") "
        +"("+pars[5].getPosition()[0]+","+pars[5].getPosition()[1]+") "
        +"("+pars[6].getPosition()[0]+","+pars[6].getPosition()[1]+") "
        +"("+pars[7].getPosition()[0]+","+pars[7].getPosition()[1]+") "
        +"("+pars[8].getPosition()[0]+","+pars[8].getPosition()[1]+") "
        +"("+pars[9].getPosition()[0]+","+pars[9].getPosition()[1]+") "
        +"("+pars[10].getPosition()[0]+","+pars[10].getPosition()[1]+") "
        +"("+pars[11].getPosition()[0]+","+pars[11].getPosition()[1]+") "
        +"("+pars[12].getPosition()[0]+","+pars[12].getPosition()[1]+") "
        +"("+pars[13].getPosition()[0]+","+pars[13].getPosition()[1]+") "
        +"("+pars[14].getPosition()[0]+","+pars[14].getPosition()[1]+") "
        +"("+pars[15].getPosition()[0]+","+pars[15].getPosition()[1]+") "
        +"("+pars[16].getPosition()[0]+","+pars[16].getPosition()[1]+") "
        +"("+pars[17].getPosition()[0]+","+pars[17].getPosition()[1]+") "
        +"("+pars[18].getPosition()[0]+","+pars[18].getPosition()[1]+") "
        +"("+pars[19].getPosition()[0]+","+pars[19].getPosition()[1]+") "
        );
    }
}
