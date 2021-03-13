import org.junit.validator.ValidateWith;

import java.util.Comparator;
import java.util.ListIterator;

/**
 * @author Eric Deng
 *
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

    Comparator<T> comparator;

    /**
     * Creates an empty list that is associated with the specified comparator.
     * @param comparator2 Comparator to compare data elements
     */
    public SortedDoubleLinkedList(Comparator<T> comparator2){
        head=tail=null;
        comparator=comparator2;
    }

    /**
     * Inserts the specified element at the correct position in the sorted list.
     * @param data the data to be added to the list
     * @return a reference to the current object
     */
    public SortedDoubleLinkedList<T> add(T data){
        Node insertedNode= new Node(data);
        if(head==null){
            head=tail=insertedNode;

        }
        else{


            if(comparator.compare(data,tail.data)>0){ //if data is greater than the largest variable, go to the end

                Node temp=tail;
                tail.next=insertedNode;
                tail=tail.next;
                if(count==1){
                    head.next=tail;
                }
                tail.prev=temp;
            }
            else if(comparator.compare(data,head.data)<0){ //if data is less than the lowest, go to the front.
                Node temp=head;
                head.prev=insertedNode;
                head=head.prev;
                if(count==1){
                    tail.prev=head;
                }
                head.next=temp;
            }
            else{
                Node current=head;
                while(current.next!=null){
                    Node temp;
                    if(comparator.compare(data,current.next.data)<=0){//goes through list and checks if data is less
                                            // than any variable. If it is, place in its spot, move replaced var up.
                        temp=current.next;
                        current.next=insertedNode;

                        insertedNode.next=temp;
                        insertedNode.prev=current;
                        insertedNode.next.prev=current.next; //need to create previous pointers to all existing nodes
                        return this;
                    }

                    current=current.next;


                }
            }
        }
        count++;
        return this;

    }

    /**
     * This operation is invalid for a sorted list.
     * An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     * @throws UnsupportedOperationException  if method is called
     */
    @Override
    public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    /**
     * This operation is invalid for a sorted list.
     * An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     * @throws UnsupportedOperationException if method is called
     */
    @Override
    public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    /**
     * Implements the iterator by calling the super class iterator method.
     * @return an iterator positioned at the head of the list
     *
     */
    @Override
    public ListIterator<T> iterator(){
        return super.iterator();
    }

    /**
     * Implements the remove operation by calling the super class remove method.
     * @param data the data element to be removed
     * @param comparator the comparator to determine equality of data elements
     * @return data element or null
     */
    @Override
    public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
        return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
    }

}
