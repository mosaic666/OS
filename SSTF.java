import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math.*;
public class SSTF{
	int work,m,n,k;
	double sum=0;
	int sequence[]= new int[100];
	int result[]= new int [100];
	Scanner scanner=new Scanner(System.in);
	//输入数据
	public void getdb(){
    	System.out.println("请输入读写头起始位置");
    	work = scanner.nextInt();
    	System.out.println("请输入磁盘访问序列个数");
    	m = scanner.nextInt();
        k=m;
    	System.out.println("请依次输入磁盘访问序列");
    	for(int i=0;i<m;i++){  
            sequence[i]=scanner.nextInt();
        }
    }
    public void way(){
        for (int j=0;j<m;j++) {
            int test[]= new int[k];
        	for(int i=0;i<k;i++){
        		test[i]= Math.abs(sequence[i]-work);
        	}
        	n=getMinIndex(test);
        	sum+=test[n];
        	result[j] = sequence[n];
        	work = sequence[n];
        	sequence = ArraysDelete(sequence,n);
            k--;
    	}
        for(int i = 0; i < m; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println("平均寻道长度为"+sum/m);
    }
     public static int getMinIndex(int[] arr){  
        int minIndex = 0;  
        for(int i = 1; i < arr.length; i++){  
            if(arr[i] < arr[minIndex]){  
                minIndex = i;  
            }  
        }  
        return minIndex;  
    }
    public int[] ArraysDelete(int[] arr ,int a){
        for (int i=a;i<m-1;i++) {
            arr[i]=arr[i+1];
        }
        return arr;
    }
    public static void main(String[] args) {
    	SSTF sstf=new SSTF();  
      	sstf.getdb();
      	sstf.way();  
    }
}
