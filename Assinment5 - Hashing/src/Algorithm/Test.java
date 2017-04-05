/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author leslie
 */
public class Test {
    // randomly generate n non-repear int numbers/keys of (0, n) 
    public static void keyGenerator(int n, int[] keyArray){
        int count = 0;  
        while(count < n) {  
            int num = (int) (Math.random() * (n));  
            boolean flag = true;  
            for (int j = 0; j < count; j++) {  
                if(num == keyArray[j]){  
                    flag = false;  
                    break;  
                }  
            }  
            if(flag){  
                keyArray[count] = num;  
                count++;  
            }  
        }  
    }
    public static void main(String[] args) throws IOException {
        int key;
        int size = 10;             //size of hash table
        double loadFactor = 0.6;
        
        int n = (int) (size * loadFactor);
        int[] keyArray = new int[n];
        
        Hashing hashing = new Hashing(size);
        
        // randomly generate n non-repear int numbers/keys of (0, n)  
        Test.keyGenerator(n, keyArray);
        
        //insert into hashing
        //running 10 times
        long totalruntime = 0;
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            for (int j = 0;j < n; j++) {
                hashing.insert(keyArray[j]);
            }
            long thisRunTime = System.nanoTime() - startTime;
            totalruntime += thisRunTime;
        }
        long runtime = totalruntime/10;
        System.out.println(runtime);
    }
}
