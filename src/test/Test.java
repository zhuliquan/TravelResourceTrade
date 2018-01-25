package test;

import java.util.Random;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.RandomEngine;
import cern.jet.random.sampling.WeightedRandomSampler;

public class Test {

	public Test() {
		
	}
	static void swap(A a) {
		a.a = 15;
	}
	public static void main(String[] args) {
		DoubleMatrix2D matrix;
        matrix = new DenseDoubleMatrix2D(3,4);
        //matrix = new SparseDoubleMatrix2D(3,4); // ϡ�����
        //matrix = new RCDoubleMatrix2D(3,4); // ϡ����ѹ������
        System.out.println("��ʼ����");
        System.out.println(matrix);
        System.out.println("���");
        matrix.assign(new double[][]{{1, 2, 3, 4},{5, 6, 7, 8},{9, 10, 11, 12}});
        System.out.println(matrix);
        System.out.println("ת��");
        DoubleMatrix2D transpose = Algebra.DEFAULT.transpose(matrix);
        System.out.println(transpose);
        System.out.println("����˷�");
        System.out.println(Algebra.DEFAULT.mult(matrix, transpose));
        System.out.println(Normal.staticNextDouble(1.0, 0.1));
		
	}
}
class A{
	int a;
	A(int a){
		this.a = a;
	}
}
