/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Random;

/**
 *
 * @author zhusuoge
 */
public class Main {
    public static void main(String[] args){
        
        MemoryBlockListArray  m = new MemoryBlockListArray(11, 0, 100);
        float faliedNum = 0;   //count total number of failed requests
        for (int i = 0; i < 1000; i++) {
            Random seed = new Random();
            int rand = seed.nextInt(11);     //generate random int between [0, 11)
            MemoryBlock b = m.request(rand);
            if (b == null) {
                faliedNum++;
            }
        }
        
        //print the total number of failed requests:
        System.out.println("Total failed requests:  "  +faliedNum);
        System.out.println("Failed rate:  " + faliedNum/10 +"%");
        
        //print the usage of blocks
        System.out.println("     Used     Use rate");
        int pow = 0;
        for(MemoryBlockList mList:m.getMemoryBlockLists()){
            int remain = mList.getMemoryBlockDirectory().size();
            float userate = (100-remain)/1;
            System.out.println("2^"+pow+":  "+(100-remain)+"      "+userate+"%" );
            pow++;
        }
    } 
    
}
