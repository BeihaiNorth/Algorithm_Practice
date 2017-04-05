/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

/**
 *
 * @author leslie
 */
public class Hashing {
    private int[] hashArray;
    private int arraySize;
    

    public Hashing(int size) {
        arraySize = size;
        hashArray = new int[arraySize];
    }
    
    public int hashFunc(int key){
        return key%arraySize;
    }
    
    public void insert(int key){

        int address = hashFunc(key);
        while(hashArray[address] != 0){
            ++address;          // linear probing
            address %= arraySize;
        }
        hashArray[address] = key;
    }
    
    public int search(int key) {
        int address = hashFunc(key); 
        while(hashArray[address] != 0) {
            if(hashArray[address] == key)
                return address;
            ++address;
            address %= arraySize;
        }
        return -1;              // search fail
}
    
    
    
    
}
