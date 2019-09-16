package com.java1;
//扩展成Integer实例
public class IntegerMatrix extends GenericMatrix<Integer>{
    @Override     //覆写父类抽象方法
    protected Integer add(Integer o1,Integer o2) {
        return o1 + o2;
    }
    
    @Override
    protected Integer multiply(Integer o1,Integer o2) {
        return o1*o2;
    }
    
    @Override
    protected Integer zero() {
        return 0;
    }
}