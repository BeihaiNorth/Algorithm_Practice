/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;


import java.sql.Time;
import java.util.ArrayList;
import jdk.nashorn.internal.ir.RuntimeNode;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author leslie
 */
public class Tree_Block2 {
    private Block_Node2 root;
    private int size;
    
    public Tree_Block2(int size){
        this.size = size;
        this.root = new Block_Node2(size, size); // (size, mark)
        initiateTree(size, root);
    }

    public Block_Node2 getRoot() {
        return root;
    }

    public void setRoot(Block_Node2 root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    public void initiateTree(int size, Block_Node2 rootNode){
        if(size > 1){
            int rootMark = rootNode.getMark();
            Block_Node2 leftChild = new Block_Node2(size/2, rootMark-size/2);// (size, mark)
            Block_Node2 rightChild = new Block_Node2(size/2, rootMark+size/2);// (size, mark)
            rootNode.setLeftChild(leftChild);
            rootNode.setRightChild(rightChild);
            size=size/2;
            initiateTree(size, leftChild);
            initiateTree(size, rightChild);
        }
        
    }
    
    //inorder traverse test
    public void inOrder(Block_Node2 root){
        if (root != null) {
            inOrder(root.getLeftChild());
            System.out.println(root.getMark());
            inOrder(root.getRightChild());
        }
    }
    
    //search node by 'mark', in a tree with 'root'
    public Block_Node2 search(int mark, Block_Node2 root){
        if (root == null) {
            return null;
        }
        if (mark == root.getMark()) {
            return root;
        }else if (mark < root.getMark()) {
            root = root.getLeftChild();
            return search(mark, root);
        }else{
            root = root.getRightChild();
            return search(mark, root);
        }
    }
    
    public Block_Node2 request(int size){
        for(int i = 0; i < this.size/size; i++){
            int mark = size+i*(2*size);
            Block_Node2 result;
            result = search(mark, this.root);
            if (result.isAvailable()) {
                result.setAvailable(false);
                result.disableSubtree(result);
                return result;
            }
        }
        return null;
    }
    
    
    
}
