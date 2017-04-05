/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 *
 * @author zhusuoge
 */
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int treeSize = 128;
        Tree_Block tree_Block = new Tree_Block(treeSize, 0);
        
        Block_Node p = tree_Block.getRoot();
//        tree_Block.levelOutPut(p);
     // random requests   
        Random seed = new Random();
        Deque<Block_Node> result = new ArrayDeque<>();
        
        
        for (int i = 0; i < 10; i++) {    //number of requests
            System.out.println("For the No." + (i + 1) + " request:");
            int reqIndex = seed.nextInt((int) (Math.log(treeSize * 0.2) / Math.log(2)) + 1);     //focus on size of lower 20% of the tree
            System.out.println("Requested size is 2^" + reqIndex );
            int reqSize = (int) Math.pow(2, reqIndex);
            
            System.out.println("Block Size" + "  "+ "Start Address"+ "  "+"Ending Address");
            
            Block_Node search = tree_Block.node_search(p, reqSize);
            if (search != null) {
                System.out.println(search.getSize() + "            " + search.getStartAddress()+ "            " +(search.getSize()+search.getStartAddress()-1));
                System.out.println();
                result.add(search);
            } else {
                System.out.println("not found");
                System.out.println();
            }
            for(Block_Node m:result)
            {
                m.setTimer(m.getTimer()-1);
                if(m.getTimer()==0)
                {
                    Block_Node b=result.pollFirst();
                    System.out.println("free block"+b.getSize()+"  "+b.getStartAddress());
                    b.setBlockStatus("unused");
                }
            }
        }
        
        
        //BST method
        int bstSize=8;
        int rootMark=bstSize;
        Block_Node2 root = new Block_Node2(bstSize, rootMark);
        Tree_Block2 bstTree = new Tree_Block2(bstSize);
        
        Block_Node2 n1;
        Block_Node2 n2;
        Block_Node2 n3;
        Block_Node2 n4;
        n1 = bstTree.request(4);
        n2 = bstTree.request(2);
        n3 = bstTree.request(2);
        n4 = bstTree.request(4);
        System.out.println("Request size: 4");
        System.out.println("mark:"+n1.getMark()+"     size:"+n1.getSize());
        System.out.println("Request size: 2");
        System.out.println("mark:"+n2.getMark()+"     size:"+n2.getSize());
        System.out.println("Request size: 2");
        System.out.println("mark:"+n3.getMark()+"     size:"+n3.getSize());
        System.out.println("Request size: 4");
        if (n4 == null) {
            System.out.println("this request failed");
        }else{
        System.out.println("mark:"+n4.getMark()+"     size:"+n4.getSize());
        }
        
        //test
        //bstTree.inOrder(bstTree.getRoot());  
        
//        int mark = 8;
//        System.out.println("search for mark" + mark);
//        Block_Node2 result;
//        result = bstTree.search(mark, bstTree.getRoot());
//        System.out.println("Result:");
//        System.out.println("size:" + result.getSize());
//        System.out.println("Mark:" + result.getMark());

        

    }
    
}
