import java.util.Scanner;

public class bank {
	int n;
	int m;
	int available[]= new int[100];
	int max[][]=new int[100][100];
	int allocation[][]=new int[100][100];
	int need[][]=new int[100][100];
	int request[]=new int[100];
	Scanner scanner=new Scanner(System.in);  
    int thread=1;
    //输入
    public void getdb(){
    	System.out.println("输入资源有几类和进程数：");
    	n=scanner.nextInt();
    	m=scanner.nextInt(); 
    	System.out.println("输入n类资源的数目：");   
        for(int i=0;i<n;i++){  
            available[i]=scanner.nextInt();
        }
        for(int i=0;i<m;i++){  
            System.out.println("请输入进程"+i+"对"+n+"类资源的最大需求");  
            for(int j=0;j<n;j++){  
                max[i][j]=scanner.nextInt();  
            }  
        }
        for(int i=0;i<m;i++){  
            System.out.println("请输入进程"+i+"已分配的"+n+"类资源数");  
            for(int j=0;j<n;j++){  
                allocation[i][j]=scanner.nextInt();  
            }  
        }
        for(int i=0;i<m;i++){  
            for(int j=0;j<n;j++){  
                need[i][j]=max[i][j]-allocation[i][j];  
            }  
        }
        for(int i=0;i<n;i++){  
            for(int j=0;j<m;j++){  
                available[i]-=allocation[j][i];  
            }  
        }  

    }
    public void getThread(){  
        System.out.println("输入申请资源的进程");  
        int thread=scanner.nextInt();     //线程  
        if(thread<0||thread>m){  
            System.out.println("该线程不存在,请重新输入");  
            getThread();  
        }else{  
	            this.thread=thread;  
	            System.out.println("请输入申请的资源");  
	            for(int i=0;i<n;i++){  
	                request[i]=scanner.nextInt();  
	            }  
	            if(request[0]>need[thread][0]||request[1]>need[thread][1]||request[2]>need[thread][2]){  
	                System.out.println(thread+"线程申请的资源超出其需要的资源，请重新输入");  
	                getThread();  
	            }else{  
	                if(request[0]> available[0]||request[1]> available[1]||request[2]> available[2]){  
	                    System.out.println(thread+"线程申请的资源大于系统资源，请重新输入");  
	                    getThread();  
	                }  
	            }  
	            changeData(thread);  
	            if(check(thread)){  
	                 getThread();  
	            }else{  
	                recoverData(thread);  
	                getThread();  
	            }  
	  
	        }  
	    }
	public void changeData(int thread){  
         for(int i=0;i<n;i++){   
             available[i]-=request[i];  
             allocation[thread][i]+=request[i];  
             need[thread][i]-=request[i];  
         }         
   }
    public void recoverData(int thread){  
           for(int i=0;i<n;i++){   
                 available[i]+=request[i];  
                 allocation[thread][i]-=request[i];  
                 need[thread][i]+=request[i];  
          }   
        }
    public boolean check(int thread){  
         boolean finish[]=new boolean[m];  
         int work[]=new int[n];  
         int queue[]=new int[m];   //由于存放安全队列  
         int k=0;//安全队列下标  
         int j;  //要判断的线程  
         int i;  
         //是否分配的标志  
         for(i=0;i<m;i++)  
             finish[i]=false;  
         j=thread;  
         for(i=0;i<n;i++){  
             work[i]=available[i];  
         }  
         while(j<m){  
             for( i=0;i<n;i++){    
                 if(finish[j]){  
                     j++;  
                     break;  
                 }else if(need[j][i]>work[i]){  
          
                     j++;  
                     break;  
                 }else if(i==n-1){  
                     for(int z=0;z<n;z++){  
                         work[z]+=allocation[j][z];  
                     }  
                     finish[j]=true;  
                     queue[k]=j;  
                     k++;  
                     j=0;
                 }  
             }  
         }      
         for(int p=0;p<m;p++){  
             if(finish[p]=false){  
                 System.out.println("系统不安全，资源申请失败");  
                 return false;  
             }  
         }  
         System.out.println("资源申请成功，安全队列为：");  
         for(int q=0;q<m;q++){  
             System.out.println(queue[q]);  
         }   
         return true;  
     }
      public static void main(String[] args) {  
      bank bk=new bank();  
      bk.getdb();  
      bk.getThread();  
  
    }     
}