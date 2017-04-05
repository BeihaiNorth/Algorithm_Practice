/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import javax.naming.directory.SearchResult;

/**
 *
 * @author zhusuoge
 */
public class Tree_Block {

    private Block_Node root;
    private int size;
    private int startAddress;

    public Tree_Block(int size, int startAddress) {
        this.size = size;
        this.startAddress = startAddress;
        this.root = new Block_Node(size, startAddress);
        instantiate_Fulltree(root, size, startAddress);

    }

    public void instantiate_Fulltree(Block_Node root, int size, int startAddress) {
        if (size > 1) {
            Block_Node leftchild = new Block_Node(size / 2, startAddress);
            Block_Node rightchild = new Block_Node(size / 2, startAddress + size / 2);
            root.setLeftChild(leftchild);
            root.setRightChild(rightchild);
            instantiate_Fulltree(leftchild, size / 2, startAddress);
            instantiate_Fulltree(rightchild, size / 2, startAddress + size / 2);
        }
    }

    public Block_Node getRoot() {
        return root;
    }

    public void setRoot(Block_Node root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public void levelOutPut(Block_Node root) {

        if (root != null) {

            levelOutPut(root.leftChild);
            System.out.println(root.getSize() + "  " + root.getStartAddress());

            levelOutPut(root.rightChild);

        }

    }

    public Block_Node node_search(Block_Node root, int blocksize) {

        Stack<Block_Node> stack = new Stack<>();
        Block_Node startNode = root;
        while ((startNode != null) || (!stack.empty())) {
            if ((startNode != null) && (startNode.getSize() == blocksize) && (startNode.getBlockStatus().equals("unused")) && (NodeAvail(startNode))) {
                startNode.setBlockStatus("used");
                return startNode;
            }

            if ((startNode != null) && (startNode.getBlockStatus().equals("unused"))) {
                stack.push(startNode);
//                System.out.println(startNode.getSize() + " status " + startNode.getBlockStatus());
                startNode = startNode.getLeftChild();
            } else if (!stack.empty()) {
                startNode = stack.peek();
                stack.pop();
                startNode = startNode.getRightChild();
            } else if (stack.empty()) {
                return null;
            }
        }

        return null;

//            else 
//        {
//            Block_Node leftresult = new Block_Node();
//            leftresult =node_search(startNode.leftChild, blocksize); 
//            if(leftresult!=null)
//                return leftresult;
//            else 
//            {
//                Block_Node rightresult = new Block_Node();
//                rightresult =node_search(startNode.rightChild, blocksize); 
//                if(rightresult!=null)
//                return rightresult;
//            }
//        }
    }
public Block_Node Consecutive_reservation(Block_Node root, int blocksize)
{
     System.out.println("Merge two consecutive sub blocks into one block size of "+ blocksize);

     Block_Node startNode = root;
     Block_Node first_half;
     Block_Node second_half;
     Block_Node tmp;
     Stack<Block_Node> stack = new Stack<>();
     first_half = node_search(root, blocksize/2);
     while(first_half !=null)
     {
         stack.push(first_half);
         second_half = node_search(root, blocksize/2);
         if((second_half!=null)&&((first_half.getStartAddress()+first_half.getSize())==second_half.getStartAddress()))     
         {
             return first_half;
         }
         else if((second_half!=null)&&((first_half.getStartAddress()+first_half.getSize())!=second_half.getStartAddress()))
         {second_half.setBlockStatus("unused");
          first_half = node_search(root, blocksize/2);
         }
         else if(second_half==null)
         {
             first_half.setBlockStatus("unused");
             return null;
         }
             
     }
     while(!stack.isEmpty())
     {
         tmp =stack.pop();
         tmp.setBlockStatus("unused");
     }
     return null;
     
    
}
    
    public boolean NodeAvail(Block_Node focusNode) {

        Stack<Block_Node> stack = new Stack<>();
        Block_Node startNode = focusNode;
        while ((startNode != null) || (!stack.empty())) {
            if ((startNode != null) && (startNode.getBlockStatus().equals("used"))) {
                return false;
            }

            if (startNode != null) {
                stack.push(startNode);
//                System.out.println(startNode.getSize() + " status " + startNode.getBlockStatus());
                startNode = startNode.getLeftChild();
            } else {
                startNode = stack.peek();
                stack.pop();
                startNode = startNode.getRightChild();
            }
        }

        return true;
    }

}
