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
public class MemoryBlockList {
    private LinkedList<MemoryBlock> memoryBlockDirectory;
    private int memoryBlockSize;
    private int listSize;
    private int startAddress;

    public MemoryBlockList() {
        memoryBlockDirectory = new LinkedList<MemoryBlock>();
        
    }

    public MemoryBlockList(int memoryBlockSize, int listSize, int startAddress) {
        memoryBlockDirectory = new LinkedList<MemoryBlock>();
        int tempAdress = startAddress;
        MemoryBlock previousBlock = new MemoryBlock();
        for(int i=0;i<listSize;i++)
        {
             MemoryBlock memoryBlock = new MemoryBlock(memoryBlockSize, tempAdress);
             tempAdress = tempAdress+memoryBlockSize;
             memoryBlockDirectory.add(memoryBlock);
             if(i>0)
             {previousBlock.setNextBlock(memoryBlock);}
             previousBlock=memoryBlock;
             
        }
    }

    public LinkedList<MemoryBlock> getMemoryBlockDirectory() {
        return memoryBlockDirectory;
    }

    public void setMemoryBlockDirectory(LinkedList<MemoryBlock> memoryBlockDirectory) {
        this.memoryBlockDirectory = memoryBlockDirectory;
    }

    public int getMemoryBlockSize() {
        return memoryBlockSize;
    }

    public void setMemoryBlockSize(int memoryBlockSize) {
        this.memoryBlockSize = memoryBlockSize;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }
    
    
    
    
    
    
    
}
