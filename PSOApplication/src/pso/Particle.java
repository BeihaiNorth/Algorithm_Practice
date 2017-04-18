/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import java.util.Random;

/**
 *
 * @author leslie
 */
public class Particle implements Runnable {

    private double []v; //velocity, 2 dimention
    private double []position; // position
    private double []pbest; //personal best position
    public static double []gbest; //global best position
    private double fitness;
    private FitnessFunction fitnessFunction;
    
    private static double w = 0.7; //inertia weight in the equation
    private static double c1 = 2; // positive constant parameter acceleration coefficients
    private static double c2 = 2;
    private static double lowerv = -5;
    private static double upperv = 5;
    private static double lowerp = -20;
    private static double upperp = 20;
    public static Random rnd;
    
    
    //initialize position, pbest, v
    public Particle(FitnessFunction fitnessFunc){
        rnd = new Random();
        position = new double[2];
        position[0] = rnd.nextDouble()*(upperp - lowerp) + lowerp;
        position[1] = rnd.nextDouble()*(upperp - lowerp) + lowerp;
        pbest = position;
        v = new double[2];
        v[0] = rnd.nextDouble()*(upperv - lowerv) + lowerv;
        v[1] = rnd.nextDouble()*(upperv - lowerv) + lowerv;
        this.fitnessFunction = fitnessFunc;
    }

    /*
    * calculate current fitness
    */
//    public double fitnessFunc(double x, double y){
//        fitness = (position[0]-x)*(position[0]-x) + (position[1]-y)*(position[1]-y);
//        return fitness;
//    }

    //update position and velocity
    private void update(){
//        System.out.println("updating");
        for (int i = 0; i < 2; i++) {
            v[i] = w * v[i] + c1 * rnd.nextDouble() * ( pbest[i] - position[i])
                    + c2 * rnd.nextDouble() * (gbest[i] - position[i]);
            if(v[i] > upperv){
                v[i] = upperv - 0.01;
            }
            if(v[i] < lowerv){
                v[i] = lowerv;
            }
            position[i] = position[i] + v[i];  
            if (position[i] > upperp) {  
                position[i] = upperp - 0.01;  
            }  
            if (position[i] < lowerp) {  
                position[i] = lowerp;  
            }
        }
    }

    @Override
    public void run() {   
            String name = Thread.currentThread().getName();
//            System.out.println(name+" started'");
            update();
            
            fitness = fitnessFunction.calcFitness(position);
            double pbestFitness = fitnessFunction.calcFitness(pbest);
            double gbestFitness = fitnessFunction.calcFitness(gbest);
//            System.out.println(name+ "pbest fitness:" + pbestFitness+ "  gbest fitness:" + gbestFitness);
//            System.out.println(name+ "fitness:" + fitness);
            if (fitness <= pbestFitness) {
                pbest = position;
//                System.out.println(name+"pbest fitness :" + pbestFitness);
                if (fitness < gbestFitness) {
                    gbest = position;
//                    System.out.println(name+"gbest fitness :" + gbestFitness);
                }
            }
//            System.out.println(name+" ended'");
    }

    public double[] getV() {
        return v;
    }

    public void setV(double[] v) {
        this.v = v;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double[] getPbest() {
        return pbest;
    }

    public void setPbest(double[] pbest) {
        this.pbest = pbest;
    }

    public double[] getGbest() {
        return gbest;
    }

    public void setGbest(double[] gbest) {
        this.gbest = gbest;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }

    public void setFitnessFunction(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }
    
    
    
    
}
