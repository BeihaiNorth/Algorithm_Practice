/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import jdk.nashorn.internal.ir.ContinueNode;

/**
 *
 * @author leslie
 */
public class Block_Node2 {
    private int size;
    private int mark;
    private boolean available;
    
    private Block_Node2 leftChild;
    private Block_Node2 rightChild;
    
    public Block_Node2(int size, int mark){
        this.size = size;
        this.mark = mark;
        this.available = true;
        this.leftChild = null;
        this.rightChild = null;
        
    }
    
    //inorder traverse
    public void disableSubtree(Block_Node2 root){
        if (root != null) {
            disableSubtree(root.getLeftChild());
            root.setAvailable(false);
            disableSubtree(root.getRightChild());
        }
    }
    //inorder traverse
    public void enableSubtree(Block_Node2 root){
        if (root != null) {
            enableSubtree(root.getLeftChild());
            root.setAvailable(true);
            enableSubtree(root.getRightChild());
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    
    //if this node itself and all the sub nodes are available, then this node is available
    public boolean isAvailable() {
        if (available==false) {
            return false;
        }
        Block_Node2 nl = this.leftChild;
        Block_Node2 nr = this.rightChild;
        boolean rl = true;
        boolean rr = true;
        if(nl != null && nr != null) {            
            rl = nl.isAvailable();
            rr = nr.isAvailable();
        } 
        return rl&&rr;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    

    public Block_Node2 getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Block_Node2 leftChild) {
        this.leftChild = leftChild;
    }

    public Block_Node2 getRightChild() {
        return rightChild;
    }

    public void setRightChild(Block_Node2 rightChild) {
        this.rightChild = rightChild;
    }
    
    
           
}
