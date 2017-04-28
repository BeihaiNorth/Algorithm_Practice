/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author leslie
 */
public class Particle implements Runnable {
    
    private int []cityorder;    //current personal solution
    private int []pbest;        //personal best solution
    private int []gbest;        //global best solution
    private int distance;       //total distance for current solution, culculated by fitnessFunction
    private int startDay;       //1~7 represent Monday ~ Sunday
    private FitnessFunction fitnessFunction;
    private int fitness;        // represent current fitness according to current personal solution
    private ArrayList<Swapper> swapOrder;  //swap order/exchange sequence
    private FlightInfo flights;
    
    /*argument for calculation*/
    private static double w = 0.7; //inertia weight in the equation
    private static double c1 = 2; // positive constant parameter acceleration coefficients
    private static double c2 = 2;
    public static Random rand;
    
    
    /**
     * Constructor to initialize a particle
     * @param fitnessFunc
     * @param cityNum the total number of cities
     */
    public Particle(FitnessFunction fitnessFunc, int cityNum, int startDay){
        rand = new Random();
        cityorderInitialize(cityNum);
        flights = new FlightInfo(cityNum);
        pbest = cityorder;
        swapOrderIntialize(cityNum);
        this.fitnessFunction = fitnessFunc;
        this.startDay = startDay;
    }
    
    /**
     * Randomly generate a cityorder/solution, from 0[inclusive] to cityNum[exclusive]
     * @param cityNum total number of city
     * @return 
     */
    private int []cityorderInitialize(int cityNum){
        cityorder = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            cityorder[i]=i;
        }
        //Knuth random shuffle: 
        for (int i = 0; i < cityNum; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = cityorder[r];
            cityorder[r] = cityorder[i];
            cityorder[i] = swap;
        }
        return cityorder;
    }
    
    /**
     * Randomly generate swapOrder. The total number of Swapper random but always below cityNum.
     * @param cityNum
     * @return 
     */
    private ArrayList<Swapper> swapOrderIntialize(int cityNum){
        int swapperNum = rand.nextInt(cityNum);
        swapOrder = new ArrayList<Swapper>();
        //add "swapperNum" swapper into swaperOrder
        for (int i = 0; i < swapperNum; i++) {
            Swapper swapper = new Swapper(rand.nextInt(cityNum),rand.nextInt(cityNum));
            swapOrder.add(swapper);
        }
        return swapOrder;
    }


    /**
     * update the particle's swaporder and cityorder
     */
    private void update(){
        
        //update swaporder
        ArrayList<Swapper> newSwapOrder = new ArrayList<>();
        int length = swapOrder.size();
        if( length > cityorder.length){
            length = cityorder.length;
        }
        for (int i = 0; i < length; i++) {
            newSwapOrder.add(swapOrder.get(i));
        }
        
        ArrayList<Swapper> newSwapOrder1 =orderMinusMethod(pbest, cityorder);
        float r1 = rand.nextFloat();
        int length1 = (int)(swapOrder.size() * r1);
        if( length1 > cityorder.length){
            length1 = cityorder.length;
        }
        for (int i = 0; i < length1; i++) {
            newSwapOrder.add(newSwapOrder1.get(i));
        }
        
        ArrayList<Swapper> newSwapOrder2 =orderMinusMethod(gbest, cityorder);
        float r2 = rand.nextFloat();
        int length2 = (int)(swapOrder.size() * r1);
        if( length2 > cityorder.length){
            length2 = cityorder.length;
        }
        for (int i = 0; i < length1; i++) {
            newSwapOrder.add(newSwapOrder2.get(i));
        }
        
        swapOrder = newSwapOrder;
        
        //update cityorder
        for (Swapper swapper : swapOrder) {
            int a = swapper.getX();
            int b = swapper.getY();
            int temp = cityorder[a];
            cityorder[a] = cityorder[b];
            cityorder[b] = temp;
        }
        
        
        
        
        


    }
    
    private ArrayList<Swapper> orderMinusMethod(int[] order1, int []order2){
        ArrayList<Swapper>  list = new ArrayList<Swapper>();
        int[]temp = order2;
        Swapper s;
        int cityNum = cityorder.length;
        int index = -1;
        for (int i = 0; i < cityNum; i++) {
            if(temp[i] != order1[i]){
                //finding the index in order1 that indicated the same value as temp[i]
                for(int j = 0; j < cityNum; j++){
                    if (order1[j] == temp[i]) {
                        index = j;
                        break;
                    }
                }
                //exchage the value
                int temp2 = temp[i];
                temp[i] = order1[index];
                temp[index] = temp2;
                s = new Swapper(i, index);
                list.add(s);
            }
        }
        return list;
    }

    @Override
    public void run() {   
            String name = Thread.currentThread().getName();
//            System.out.println(name+" started'");

            update();
            
            fitness = fitnessFunction.calcFitness(cityorder);
            int pbestFitness = fitnessFunction.calcFitness(pbest);
            int gbestFitness = fitnessFunction.calcFitness(gbest);
//            System.out.println(name+ "pbest fitness:" + pbestFitness+ "  gbest fitness:" + gbestFitness);
//            System.out.println(name+ "fitness:" + fitness);
            if (fitness <= pbestFitness) {
                pbest = cityorder;
//                System.out.println(name+"pbest fitness :" + pbestFitness);
                if (fitness < gbestFitness) {
                    gbest = cityorder;
//                    System.out.println(name+"gbest fitness :" + gbestFitness);
                }
            }
//            System.out.println(name+" ended'");
    }

    public int[] getCityorder() {
        return cityorder;
    }

    public void setCityorder(int[] cityorder) {
        this.cityorder = cityorder;
    }

    public int[] getPbest() {
        return pbest;
    }

    public void setPbest(int[] pbest) {
        this.pbest = pbest;
    }

    public int[] getGbest() {
        return gbest;
    }

    public void setGbest(int[] gbest) {
        this.gbest = gbest;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }

    public void setFitnessFunction(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public ArrayList<Swapper> getSwapOrder() {
        return swapOrder;
    }

    public void setSwapOrder(ArrayList<Swapper> swapOrder) {
        this.swapOrder = swapOrder;
    }

    public FlightInfo getFlights() {
        return flights;
    }

    public void setFlights(FlightInfo flights) {
        this.flights = flights;
    }

    public static double getW() {
        return w;
    }

    public static void setW(double w) {
        Particle.w = w;
    }

    public static double getC1() {
        return c1;
    }

    public static void setC1(double c1) {
        Particle.c1 = c1;
    }

    public static double getC2() {
        return c2;
    }

    public static void setC2(double c2) {
        Particle.c2 = c2;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public static Random getRand() {
        return rand;
    }

    public static void setRand(Random rand) {
        Particle.rand = rand;
    }
    
    
    
    
}