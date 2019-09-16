package com.java1;

public class RationalMatrix {   //判断矩阵运算是否合理
	public static boolean isRational_add(Object[][] matrix1, Object[][] matrix2){
		//矩阵相加必须满足两个矩阵行列数都相同
        if((matrix1.length != matrix2.length) ||(matrix1[0].length != matrix2[0].length)){
           return false;
        }
        return true;
    }
    public static boolean isRational_multiply(Object[][] matrix1, Object[][] matrix2){
    	//两矩阵相乘必须满足前一个矩阵列数等于后一个矩阵行数
        if(matrix1[0].length != matrix2.length)
        {
        	return false;
        }
        return true;
    }

}
