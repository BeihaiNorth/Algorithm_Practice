/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

//import java.lang.Thread;
import java.util.Random; 
import java.util.TimerTask;

/**
 * 粒子类 
 * 求解函数 f(x)=x1^2+(x2-x3)^2 的最大值 
 * @author leslie
 */
public class Particle1 implements Runnable{
    public double[] pos;//粒子的位置，求解问题多少维，则此数组为多少维  
    public double[] v;//粒子的速度，维数同位置  
    public double fitness;//粒子的适应度  
    public double[] pbest;//粒子的历史最好位置  
    public static double[] gbest;//所有粒子找到的最好位置  
    public static Random rnd;  
    public static int dims;  
    public static double w;  //静态成员
    public static double c1;  
    public static double c2;
    double pbest_fitness;//历史最优解  
    /** 
     * 返回low—uper之间的数 
     * @param low 下限 
     * @param uper 上限 
     * @return 返回low—uper之间的数 
     */  
    private double rand(double low, double uper) {  
        rnd = new Random();
        return rnd.nextDouble() * (uper - low) + low;  
    }  
    /** 
     * 初始化一个粒子 
     * @param dim 表示粒子的维数 
     */  
    public void initial(int dim) {  
        pos = new double[dim];  
        v = new double[dim];  
        pbest = new double[dim];  
        fitness = -1e6;  
        pbest_fitness = -1e6;  
        dims = dim;  
        for (int i = 0; i < dim; ++i) {  
            pos[i] = rand(-10, 10);  
            pbest[i] = pos[i];  
            v[i] = rand(-20, 20);  
        }  
    }  
    /** 
     * 评估函数值,同时记录历史最优位置 
     */  
    public void evaluate() {  
        fitness = pos[0] * pos[0] + (pos[1] - pos[2]) * (pos[1] - pos[2]);  
        if (fitness > pbest_fitness) {  
            for (int i = 0; i < dims; ++i) {  
                pbest[i] = pos[i];  
            }  
        }  
    }  
    /** 
     * 更新速度和位置 
     */  
    public void updatev() {  
        for (int i = 0; i < dims; ++i) {  
            v[i] = w * v[i] + c1 * rnd.nextDouble() * (pbest[i] - pos[i])  
                    + c2 * rnd.nextDouble() * (gbest[i] - pos[i]);  
            if (v[i] > 20) {  
                v[i] = 20;  
            }  
            if (v[i] < -20) {  
                v[i] = -20;  
            }  
            pos[i] = pos[i] + v[i];  
            if (pos[i] > 10) {  
                pos[i] = 10;  
            }  
            if (pos[i] < -10) {  
                pos[i] = -10;  
            }  
        }  
    }  

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
   
   
}
