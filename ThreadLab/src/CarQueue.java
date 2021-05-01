import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {

    ArrayList<Integer> theQueue;
    public CarQueue(){
        theQueue=new ArrayList<>();
        for(int i=0;i<5;i++){
            theQueue.add(i);
        }

    }

    public void addToQueue() {
      Runnable r= new Runnable() {
          @Override
          public void run() {
              try {
                Random rand=new Random();
                  //rand.nextInt(4)
                    theQueue.add(rand.nextInt(4));

                  Thread.sleep(1000);


              }
              catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      };

        Thread t= new Thread(r);
      t.start();
    }
    public int deleteQueue() {
        int val=theQueue.get(0);
        theQueue.remove(0);
        return val;
    }
}
