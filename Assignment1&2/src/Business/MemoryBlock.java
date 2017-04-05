/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.LinkedList;

/**
 *
 * @author zhusuoge
 */
public class MemoryBlock {
    
   private int size;
   private MemoryBlock nextBlock;
   private int startAddress;
   private boolean status;

    public MemoryBlock(int size, int startAddress) {
        nextBlock = new MemoryBlock();
        this.status = true;
        this.size = size;
        this.startAddress = startAddress;
        
    }

    public MemoryBlock() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
   
   

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MemoryBlock getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(MemoryBlock nextBlock) {
        this.nextBlock = nextBlock;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }
    
  
}
