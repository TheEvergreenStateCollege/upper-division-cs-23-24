package com.devincompany.app;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
public class TwoTieredVector<obj> {
    private int size;
    private int subSize;
    ArrayList<Deque<obj>> twoTieredVector = new ArrayList<Deque<obj>>();
    
    public TwoTieredVector() {
        this.size = 0;
        this.subSize = 0;
    }
    
    public void insert(obj element, int index){

        int newSize = size + 1;
        if (Math.ceil(Math.pow(newSize,1/2)) < (int)Math.ceil(Math.pow(size,1/2))){
            expand(index);
        }
    }
    public void delete(){

    }
    public void expand(int index){
        twoTieredVector.add(new ArrayDeque<obj>());
        int k = 0;
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < k; j++) {
                twoTieredVector.get(i-1).offerLast(twoTieredVector.get(i).pollFirst());
            }
        }
    }
    public void compress(){

    }

}
