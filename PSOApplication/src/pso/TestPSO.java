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
public class TestPSO {
    public static void main(String []arg)  
    {  
        PSO pso=new PSO();  
        pso.init(20, 10, 10); //particle number, goal
        System.out.println("pso initiated");
        TimerTask timerTask = pso;
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(timerTask, 0, 1);
        System.out.println("Timertask started");
        while (!isQualified(pso)) {            
            continue;
        }
        timer.cancel();
    }
    
    public static boolean isQualified(PSO pso){
        for (int i = 0; i < pso.pcount; i++) {
            
            if (pso.pars[i].getV()[0]>0.001 || pso.pars[i].getV()[1]>0.001) {
                return false;
            }
        }
        return true;
    }
}
