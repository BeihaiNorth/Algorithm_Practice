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
    private double []goal;
    
    public FitnessFunction(double []goal){
        this.goal = goal;
    }

    public double[] getGoal() {
        return goal;
    }

    public void setGoal(double[] goal) {
        this.goal = goal;
    }
    
    
    /**
     * 
     * @param p the position of a particle
     * @return the distance the particle is form the final goal
     */
    public double calcFitness(double []p){
        double sum = 0;
        for (int i = 0; i < 2; i++) {
            sum += Math.pow(p[i]-this.goal[i], 2);
        }
        sum = Math.sqrt(sum);
        return sum;
    }
}
