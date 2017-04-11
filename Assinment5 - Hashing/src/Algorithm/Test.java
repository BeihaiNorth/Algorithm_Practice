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
    // randomly generate n non-repear int numbers/keys between (0, n) 
    public static void keyGenerator(int n, int[] keyArray){
        int count = 0;  
        while(count < keyArray.length) {
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
    
    public static void autoResizing(){
        double loadFactor = 0.6;
        int repeattime = 1000;
        int keyrange = 5000;
        int[] keyArray = new int[3000];
        Test.keyGenerator(keyrange, keyArray);
        int hashArraySize = 500;
        Hashing hashing = new Hashing(hashArraySize);
        
        int mark = 0;
        int resizemark = 0;
        long totalruntime = 0;
        
        // insert 5000 integer
        while (mark < 3000) {
            double loadfator = 0;
            mark = 0;
            while(loadfator < 0.6 && mark<3000){
//                long startTime = System.nanoTime();
                hashing.insert(keyArray[mark]);
//                long thisRunTime = System.nanoTime() - startTime;
//                totalruntime += thisRunTime;
//                long runtime = totalruntime/repeattime;
//                System.out.println(runtime);
                mark++;
                loadfator = ((double)mark)/hashArraySize;
            }
            hashArraySize = hashArraySize*2;
            hashing = new Hashing(hashArraySize);
            loadfator=0;  
        
        }
    }
    
    public static void main(String[] args) throws IOException {
//        double loadFactor = 0.6;
//        int repeattime = 1000;
//        
//        for (int size = 10;  size <= 2800; size+=10) {
//            int n = (int) (size * loadFactor);
//            int[] keyArray = new int[n];
//            int keyrange = size *2;
//            Hashing hashing = new Hashing(size);
//            //running 10 times for average
//            long totalruntime = 0;
//            for (int i = 0; i < repeattime; i++) {
//                // randomly generate n non-repear int numbers/keys of (0, n)  
//                Test.keyGenerator(keyrange, keyArray);
//                long startTime = System.nanoTime();
//                for (int j = 0;j < n; j++) {
//                    hashing.insert(keyArray[j]);
//                }
//                long thisRunTime = System.nanoTime() - startTime;
//                totalruntime += thisRunTime;
//                hashing.clear();
//            }
//            long runtime = totalruntime/repeattime;
//            System.out.println(runtime);
//        }
        double loadFactor = 1;
        int repeattime = 1000;
        int size = 1000;
        
//        for (int size = 10;  size <= 2800; size+=10) {
            int n = (int) (size * loadFactor);
            int[] keyArray = new int[n];
            int keyrange = size *2;
            Hashing hashing = new Hashing(size);
            //running 10 times for average
            long totalruntime = 0;
//            for (int i = 0; i < repeattime; i++) {
                // randomly generate n non-repear int numbers/keys of (0, n)  
                Test.keyGenerator(keyrange, keyArray);
//                long startTime = System.nanoTime();
                for (int j = 0;j < n; j++) {
                    hashing.insert(keyArray[j]);
//                    System.out.println(j);
                }
//                long thisRunTime = System.nanoTime() - startTime;
//                totalruntime += thisRunTime;
//                hashing.clear();
//            }
//            long runtime = totalruntime/repeattime;
//            System.out.println(runtime);
//        }
    }
    
}

