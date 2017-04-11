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
    
    //哈希表
    private int[] hashArray;
    private int arraySize; //哈希表长度
    

    //哈希表初始化
    public Hashing(int size) {
        arraySize = size;
        hashArray = new int[arraySize];
    }
    
    public int hashFunc(int key){
        return key%arraySize;
    }
    
    public void insert(int key){

        int address = hashFunc(key);
        int mark = 1;
        while(hashArray[address] != 0){
//            address = hashFunc(++address);  //linear probing
            address = hashFunc(address + mark*mark);
            mark++;
        }
        System.out.println(mark);
        hashArray[address] = key;
    }
    
    public int search(int key) {
        int address = hashFunc(key);
        if(hashArray[address] == 0){
            return -1;
        }
        while(hashArray[address]!=key) {
            address = hashFunc(++address);
        }
        return address;
    }
    
    public void clear(){
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] =0;
        }
    }
    
    
    
    
}
