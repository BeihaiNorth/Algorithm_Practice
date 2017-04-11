/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author zhusuoge
 */
public class Block_Node {
    
    private int timer;
    private int size;
    private int startAddress;
    private String blockStatus;

    Block_Node leftChild;
    Block_Node rightChild;

    public Block_Node() {
    }

    public Block_Node(int size, int startAddress) {
        this.size = size;
        this.startAddress = startAddress;
        blockStatus = "unused";
        timer = 3;
    }

    public int getSize() {
        return size;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
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

    public String getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }

    public Block_Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Block_Node leftChild) {
        this.leftChild = leftChild;
    }

    public Block_Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Block_Node rightChild) {
        this.rightChild = rightChild;
    }

}
