package com.java1;
//��չ��Integerʵ��
public class IntegerMatrix extends GenericMatrix<Integer>{
    @Override     //��д������󷽷�
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