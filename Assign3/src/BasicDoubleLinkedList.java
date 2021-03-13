import java.util.*;

/**
 *
 * @author Eric Deng
 *
 */
public class BasicDoubleLinkedList<T> implements Iterable{

    Node head;
    Node tail;
    int count;




    protected class Node{
        Node next;
        Node prev;
        T data;

        public Node(T data){
            this.data=data;
            next=null;
            prev=null;
        }
        public Node(){
            this(null);

        }
    }





    public BasicDoubleLinkedList(){
        head=tail=null;
        count=0;

    }

    /**
     *  Adds an element to the end of the list.
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     */
    public BasicDoubleLinkedList<T> addToEnd(T data){


        Node insertedNode= new Node(data);
        if(head==null){
            head=tail=insertedNode;

        }

        else{
            Node temp;
            tail.next=insertedNode;
            temp=tail;
            tail=insertedNode;
            tail.prev=temp;

        }

        count++;
        return this;
    }

    /**
     * Adds element to the front of the list. Do not use iterators to implement this method.
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     */
    public BasicDoubleLinkedList<T> addToFront(T data){
        Node insertedNode= new Node(data);
        if(head==null){
            head=tail=insertedNode;

        }
        else
        {
            Node temp;
            head.prev=insertedNode;
            temp=head;
            head=insertedNode;
            head.next=temp;

        }


        count++;
        return this;

    }

    /**
     * Returns but does not remove the first element from the list.
     * If there are no elements the method returns null.
     * Do not implement this method using iterators.
     * @return the data element or null
     */
    public T getFirst(){
        if(head!=null)
        {
                return head.data;

        }
        return null;
    }

    /**
     * Returns but does not remove the last element from the list.
     * If there are no elements the method returns null.
     * Do not implement this method using iterators.
     * @return the data element or null
     */
    public T getLast(){
        if(tail!=null)
        {
            return tail.data;
        }
        return null;
    }

    /**
     *
     * Notice you must not traverse the list to compute the size.
     * This method just returns the value of the instance variable you use to keep track of size.
     * @return the size of the linked list
     */
    public int getSize(){
        return count;
    }

    /**
     * Removes the first instance of the targetData from the list.
     * @param targetData the data element to be removed
     * @param comparator the comparator to determine equality of data elements
     * @return data element or null
     */
    public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
        Node current=head;

        while(current!=null){
            if(comparator.compare(current.data,targetData)==0){
                if(current.equals(head)){
                    head=head.next;
                }
                else if(current.equals(tail)){
                    tail=tail.prev;
                }
                else
                if(current.prev!=null) {
                    current.prev.next = current.next;
                }
                if(current.next!=null) {
                    current.next.prev = current.prev;
                }





            }

            current=current.next;


        }
        count--;
        return this;
    }

    /**
     * Removes and returns the first element from the list.
     * If there are no elements the method returns null. Do not implement this method using iterators.
     * @return data element or null
     */
    public T retrieveFirstElement(){


        if(count-1>0) {
            Node temp = head;

          head = head.next;
          head.prev=null;
          count--;
          System.out.print(count);

          return temp.data;
        }
        else if(count==1){
            Node temp=head;
            head=tail=null;

            count=0;
            return temp.data;

        }
      else
          return null;

    }

    /**
     * Removes and returns the last element from the list.
     * If there are no elements the method returns null. Do not implement implement this method using iterators.
     * @return data element or null
     */
    public T retrieveLastElement(){
        if(count-1>0)
        {
            Node temp = tail;

            tail = tail.prev;
            tail.next=null;
            System.out.print(count);
            count--;
            System.out.print(count);
            return temp.data;
        }
        else if(count==1){
            Node temp=head;
            head=tail=null;

            count=0;
            return temp.data;

        }
        else
            return null;
    }




    private class LIterator<T> implements ListIterator<T> {

        Node cur=new Node();


        public LIterator(){

            cur.next=head;

        }


        @Override
        public boolean hasNext() {
            if(cur==null){
                cur=new Node();
                cur.next=head;
            }

            return cur.next!=null;
        }

        @Override
        public T next() throws NoSuchElementException{

            if(hasNext()){
                Node temp=cur.next;

                    cur=cur.next;

                return (T) temp.data;
            }
            else{
                throw new NoSuchElementException();
            }

        }

        @Override
        public boolean hasPrevious() {
        //iterator cur|cur.next


            return cur!=null;
        }

        @Override
        public T previous() throws NoSuchElementException{
            if(hasPrevious()){

                Node temp=cur;

                cur=cur.prev;

                return (T) temp.data;
            }
            else{
                throw new NoSuchElementException();
            }

        }

        @Override
        public int nextIndex() throws UnsupportedOperationException{ throw new UnsupportedOperationException();}

        @Override
        public int previousIndex() throws UnsupportedOperationException{ throw new UnsupportedOperationException();}

        @Override
        public void remove() throws UnsupportedOperationException{ throw new UnsupportedOperationException();}



        @Override
        public void set(Object o) throws UnsupportedOperationException{ throw new UnsupportedOperationException();}



        @Override
        public void add(Object o) throws UnsupportedOperationException{ throw new UnsupportedOperationException();}


    }

    /**
     *This method must be implemented using an inner class that implements ListIterator and defines the methods of hasNext(), next(), hasPrevious() and previous(). Remember that we should be able to call the hasNext() method as many times as we want without changing what is considered the next element.
     *
     * @return LIterator inner class
     * @Throws java.util.NoSuchElementException - Your next() method should throw NoSuchElementException if there are no more elements (at the end of the list and calling next() or at the beginning of the list and calling previous()).
     * java.lang.UnsupportedOperationException - You don't need to implement the ListIterator's remove(), add(), nextIndex() and previousIndex() and set() methods, throw UnsupportedOperationException if called.
     */
    @Override
    public ListIterator<T> iterator() throws NoSuchElementException, UnsupportedOperationException{

        return new LIterator();
    }

    /**
     * Returns an arraylist of the items in the list from head of list to tail of list
     * @return an arraylist of the items in the list
     */
    public ArrayList<T> toArrayList(){
        LIterator l=new LIterator();
        ArrayList<T> list= new ArrayList<>();

//        while(l.hasNext()){
//            list.add(l.next());
//        }
        Node current=head;
        while(current!=null){
            list.add(current.data);

            current=current.next;
        }
        return list;
    }

}