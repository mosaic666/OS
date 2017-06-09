import java.util.Scanner;
import java.lang.Math.*;
public class CSCAN{
	int m,work,direction,small=0;
	double sum=0;
	int sequence[];
	int sresult[];
	int bresult[];
	Scanner scanner=new Scanner(System.in);
	//输入数据
	public void getdb(){
		System.out.println("请输入磁盘访问序列个数");
    	m = scanner.nextInt();
    	sequence=new int[m];
    	System.out.println("请依次输入磁盘访问序列");
    	for(int i=0;i<m;i++){  
            sequence[i]=scanner.nextInt();
        }
    	System.out.println("请输入读写头起始位置");
    	work = scanner.nextInt();
    	System.out.println("请输入磁头移动方向    0:磁道减少方向   1：磁道增加方向");
    	direction = scanner.nextInt();
    }
    public void way(){
    	//冒泡排序
    	int temp=0;
    	for (int i=0;i<sequence.length;i++) {
    		for (int j=i;j<sequence.length;j++) {
    			if(sequence[i]>sequence[j]){
    			temp=sequence[i];
    			sequence[i]=sequence[j];
    			sequence[j]=temp;
    			}
    		}	
    	}
    	for (int i=0;i<sequence.length;i++) {
    		if (sequence[i]<work) {
    			small++;
    		}
    	}
    	sresult = new int[small];
    	for (int i=0;i<small;i++) {
    		sresult[i] = sequence[i];
    	}
    	bresult = new int[sequence.length-small];
    	for (int i=0;i<sequence.length-small;i++) {
    		bresult[i] = sequence[i+small];
    	}
    	//计算平均寻到长度
    	for(int i=0;i<bresult.length-1;i++){  
            sum+=Math.abs(bresult[i]-bresult[i+1]);  
        }  
        for(int i=0;i<sresult.length-1;i++){  
            sum+=Math.abs(sresult[i]-sresult[i+1]);  
        }  
    	if (direction==1) {
    		for (int i=0;i<bresult.length;i++) {
    		System.out.print(bresult[i]+"    ");	
    		}
    		for (int i=0;i<sresult.length;i++) {
    		System.out.print(sresult[i]+"    ");			
    		}
    		sum+=Math.abs(sresult[0]-bresult[bresult.length-1])+Math.abs(work-bresult[0]);
    		System.out.println();
    		System.out.println("平均寻道时间为"+sum/m);
    	}
    	else{
    		for (int i=sresult.length-1;i>=0;i--) {
    		System.out.print(sresult[i]+"    ");			
    		}
    		for (int i=bresult.length-1;i>=0;i--) {
    		System.out.print(bresult[i]+"    ");	
    		}
    		sum+=Math.abs(sresult[0]-bresult[bresult.length-1])+Math.abs(work-sresult[sresult.length-1]);
    		System.out.println();
    		System.out.println("平均寻道长度为"+sum/m);
    	}
    }
    public static void main(String[] args) {
    	CSCAN cscan =new CSCAN();
    	cscan.getdb();
    	cscan.way();
    }
}