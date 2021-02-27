import java.util.ArrayList;
import java.util.Objects;

public class NotationStack<T> implements StackInterface<T>{
    ArrayList<T> theStack;
    int count;
    int size;
    private static final int DEFAULT_SIZE=50;


    public NotationStack(int size) {
        this.size=size;
        theStack = new ArrayList<>(size);
        count=0;
   }
    public NotationStack() {
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
    public T pop() throws StackUnderflowException {
        if(!isEmpty())
        {
            T val=theStack.get(--count);
            theStack.remove(count);
            return val;
        }
        else
        {
            throw new StackUnderflowException();

        }
    }

    @Override
    public T top() throws StackUnderflowException {
        if(!isEmpty()) {
            return theStack.get(count- 1);
        }
        else
        {
            throw new StackUnderflowException();
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if(!isFull())
        {

            count++;
            return theStack.add(e);
        }
        else{
            throw new StackOverflowException();
        }
    }

    @Override
    public String toString() {
        String result="";
        for (int i=0; i<count; i++)
        {
            result+=theStack.get(i);
        }
        return result;
    }
    @Override
    public String toString(String delimiter) {
        String result="";
        for (int i=0; i<count-1; i++)
        {
            result+= theStack.get(i)+delimiter;
        }
        result+=theStack.get(count-1);
        return result;
    }

    @Override
    public void fill(ArrayList list) {
        ArrayList<T> tempStack=list;
        theStack.addAll(tempStack);
        count+= list.size();

    }


}
