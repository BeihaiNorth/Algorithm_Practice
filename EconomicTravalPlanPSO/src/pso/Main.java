/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author leslie
 */
public class Main {
    public static void main(String []arg)  
    {  
        PSO pso=new PSO();  
        pso.init(30, 10, 0); //particle number, total city number, start date
        System.out.println("pso initiated");
        TimerTask timerTask = pso;
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(timerTask, 0, 100); //period in milliseconds
        System.out.println("Timertask started");
        while (!isQualified(pso)) {
            System.out.println("still finding best solution...");
            continue;
        }
        System.out.println("finded.");
        timer.cancel();
    }
    
    public static boolean isQualified(PSO pso){
        int recordpbest = pso.pars[0].getFitness();
        for (int i = 1; i < pso.pcount; i++) {
            if (pso.pars[i].getFitness() != recordpbest) {
                return false;
            }
        }
        return true;
    }
}
