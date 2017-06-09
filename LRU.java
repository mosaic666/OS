public class LRU {
  private int theArray[];
  private int back;      //定义队尾
  private int currentSize;     //队列中存放元素个数
  private int maxSize=5;       //队列中能存放元素的个数
   
  public LRU(){
    theArray=new int[maxSize];
    back=0;
    currentSize=0;
  }
    public void queue(int a[]){
    	for(int i=0;i<a.length;i++){
    		enQueue(a[i]);
    	}
    }
  
  public void enQueue(int x){	   //入队
    beUsed(x);			//先判断是否已存在该页号，若存在，删除
    if(currentSize<maxSize){
      theArray[back]=x;
      back++;	
      currentSize++;
    }else if(currentSize==maxSize){	     //满了
      for(int i=0;i<maxSize-1;i++){
        theArray[i]=theArray[i+1];
      }
      theArray[maxSize-1]=x;
    }
  for(int i=0;i<currentSize;i++){
    System.out.print(theArray[i]);
  }
  System.out.println();
  }
  public void beUsed(int x){	    //判断是否已存在该页号,若存在，删除已有的
    for(int i=0;i<currentSize;i++){
      if(theArray[i]==x){
        for(int j=i;j<currentSize-1;j++){
          theArray[j]=theArray[j+1];
        }
        currentSize--;
        back--;
      }	
    }
  }
  public static void main(String[] args) {
    LRU lru=new LRU();
    int a[]={4,7,0,7,1,0,1,2,1,2,6};
  	lru.queue(a);
  }
}