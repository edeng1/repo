import java.util.ArrayList;
import java.util.Objects;

public class NotationQueue<T> implements QueueInterface<T>{
    ArrayList<T> theQueue;
    int size;
    int count;
    private static final int DEFAULT_SIZE=50;


    public NotationQueue(int size) {
        theQueue=new ArrayList<>(size);
        this.size=size;
        count=0;
    }
    public NotationQueue() {
        this(DEFAULT_SIZE);
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public boolean isFull() {
        return count==size;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if(!isEmpty())
        {
            T val=theQueue.get(0);
            theQueue.remove(0);
            count--;
            return val;
        }
        else
            throw new QueueUnderflowException();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(!isFull())
        {
            count++;
            return theQueue.add(e);

        }
       else
           throw new QueueOverflowException();
    }
    @Override
    public String toString() {
        String result="";
        for (int i=0; i<count; i++)
        {
            result+=theQueue.get(i);
        }
        return result;

    }

    @Override
    public String toString(String delimiter) {
        String result="";
        for (int i=0; i<count-1; i++)
        {

            result+=theQueue.get(i)+delimiter;

        }
        result+=theQueue.get(count-1);
        return result;

    }

    @Override
    public void fill(ArrayList list) {
        count+=list.size();
        ArrayList<T> tempStack=list;
        theQueue.addAll(tempStack);
    }


}
