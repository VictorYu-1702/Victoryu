package com.java1;

public class RationalMatrix {   //�жϾ��������Ƿ����
	public static boolean isRational_add(Object[][] matrix1, Object[][] matrix2){
		//������ӱ�������������������������ͬ
        if((matrix1.length != matrix2.length) ||(matrix1[0].length != matrix2[0].length)){
           return false;
        }
        return true;
    }
    public static boolean isRational_multiply(Object[][] matrix1, Object[][] matrix2){
    	//��������˱�������ǰһ�������������ں�һ����������
        if(matrix1[0].length != matrix2.length)
        {
        	return false;
        }
        return true;
    }

}
