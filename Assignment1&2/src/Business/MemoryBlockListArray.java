/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.LinkedList;
import jdk.nashorn.internal.ir.Block;

/**
 *
 * @author zhusuoge
 */
public class MemoryBlockListArray { 
    private ArrayList<MemoryBlockList> memoryBlockLists;
    private int arraySize;
    private int startAddress;
    private int listSize;
    private LinkedList<MemoryBlock> freeList;

    public MemoryBlockListArray() {
        memoryBlockLists = new ArrayList<MemoryBlockList>();
    }
    
    

    public MemoryBlockListArray(int arraySize, int startAddress,int listSize) {
        memoryBlockLists = new ArrayList<MemoryBlockList>();

        int tempAdress = startAddress;
        for(int i=0;i<arraySize;i++)
        {
          MemoryBlockList memoryBlockList = new MemoryBlockList((int)Math.pow(2, i), listSize, tempAdress);
          memoryBlockLists.add(memoryBlockList);
          tempAdress = tempAdress+(int)Math.pow(2, i)*listSize;
        }
        for (MemoryBlockList memoryBlockList : memoryBlockLists) {
            
        }
    }

    public MemoryBlock request(int blocksizepow)
            
    {
        MemoryBlock request = new MemoryBlock();
        if(memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().size()>0)
        {
            request= memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().getFirst();
            memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().removeFirst();
        }
        else if(memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().size()==0)
        {
            request= split(blocksizepow+1);
            if(request!=null)
            {
            memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().removeFirst();}
        }
        
        return request;
    }
    
    public MemoryBlock split(int blocksizepow)
    {
        MemoryBlock requestBlock = new MemoryBlock();
        int startDividePoint = 0;
        //search for the first blocklist that is available
        for(int i=blocksizepow;i<memoryBlockLists.size();i++)
        {
            if(memoryBlockLists.get(i).getMemoryBlockDirectory().size()>0)
            {
              startDividePoint=i;
              break;
            }   
        }
        
        //
        if (startDividePoint > 0)   //if larger block is available
        {
            for(int j = startDividePoint;j>=blocksizepow;j--)
            {
                divide(j);
            }
            requestBlock = memoryBlockLists.get(blocksizepow-1).getMemoryBlockDirectory().getFirst();    
        }
        else   //if not, start merging smaller ones
        {
            requestBlock=merge(blocksizepow-1);
        }
                    return requestBlock;
    }
    
    public void divide(int blocksizepow)
    {
        MemoryBlock request = new MemoryBlock();
        MemoryBlock subrequest1 = new MemoryBlock();
        MemoryBlock subrequest2 = new MemoryBlock();
        request= memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().getFirst();
        subrequest1.setStartAddress(request.getStartAddress());
        subrequest1.setSize(request.getSize()/2);
        subrequest2.setStartAddress(subrequest1.getStartAddress()+subrequest1.getSize());
        subrequest2.setSize(request.getSize()/2);
        subrequest1.setNextBlock(subrequest2);
        memoryBlockLists.get(blocksizepow-1).getMemoryBlockDirectory().add(subrequest1);
        memoryBlockLists.get(blocksizepow-1).getMemoryBlockDirectory().add(subrequest2);
        memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().removeFirst();
    }
    
    public MemoryBlock merge(int blocksizepow)  //blocksize pow is the target
    {
        int mergeStartPoint = -1;
        for(int i=blocksizepow-1;i>=0;i--)
        {
            if(memoryBlockLists.get(i).getMemoryBlockDirectory().size()*((int)Math.pow(2, i))>((int)Math.pow(2, blocksizepow)))
            {mergeStartPoint = i;
                break;
            }            
        }
        if (mergeStartPoint > -1)
        {
            MemoryBlock mergeBlock = new MemoryBlock();
            mergeBlock.setStartAddress(memoryBlockLists.get(mergeStartPoint).getMemoryBlockDirectory().getFirst().getStartAddress());
            mergeBlock.setSize((int)Math.pow(2, blocksizepow));
            memoryBlockLists.get(blocksizepow).getMemoryBlockDirectory().add(mergeBlock);
            for(int m =0;m<(((int)Math.pow(2, blocksizepow))/(((int)Math.pow(2, mergeStartPoint))));m++)
            {
            memoryBlockLists.get(mergeStartPoint).getMemoryBlockDirectory().removeFirst();
            }
            return mergeBlock;
        }
        else
        {
            return null;
        }
        
    }


    

    public ArrayList<MemoryBlockList> getMemoryBlockLists() {
        return memoryBlockLists;
    }

    public void setMemoryBlockLists(ArrayList<MemoryBlockList> memoryBlockLists) {
        this.memoryBlockLists = memoryBlockLists;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
    
    
    
    
}
