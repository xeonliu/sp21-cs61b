package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node before;
        public Node next;
        public Node(T i, Node b, Node n){
            item = i;
            before = b;
            next = n;
        }
        public Node(){
            item = null;
            before = this;
            next = this;
        }
    }

    private Node sentinel;
    private int size;

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
     * @param item
     */
    public void addFirst(T item){
        Node previousFirst = sentinel.next;

        // Create new Node
        Node newFirst = new Node(item,sentinel,previousFirst);

        // Change sentinel
        sentinel.next = newFirst;

        // Change previousFirst
        previousFirst.before = newFirst;

        ++size;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     * @param item
     */
    public void addLast(T item){

        Node previousLast = sentinel.before;
        // build the new node.
        Node newLast = new Node(item,previousLast,sentinel);

        // change the before of sentinel
        sentinel.before = newLast;

        // change the next of the previous Last
        previousLast.next = newLast;

        ++size;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * Returns the number of items in the deque.
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     */
    public void printDeque(){
        Node p = sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item);
            System.out.print(' ');
            p=p.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeFirst(){
        if(isEmpty())   return null;
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.before = sentinel;
        --size;
        return temp;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeLast(){
        if(isEmpty()) return null;
        T temp = sentinel.before.item;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        --size;
        return temp;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index){
        if(index>=size){
            return null;
        }
        Node p = sentinel.next;
        for(int i=0;i<index;++i){
            p=p.next;
        }
        return p.item;
    }

    /**
     * The Deque objects we’ll make are iterable (i.e. Iterable<T>) so we must provide this method to return an iterator.
     * @return
     */
//    public Iterator<T> iterator(){
//
//    }

    /**
     * Returns whether or not the parameter o is equal to the Deque. o is considered equal if it is a Deque and if it contains the same contents (as goverened by the generic T’s equals method) in the same order. (ADDED 2/12: You’ll need to use the instance of keywords for this. Read here for more information)
     * @param obj
     * @return
     */
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

    /**
     *  Same as get, but uses recursion.
     * @param index
     * @return
     */
    public T getRecursive(int index){
        return getRecursive_help(sentinel.next,index);
    }

    private T getRecursive_help(Node p, int index){
        if(index>=size) return null;
        if(index==0){
            return p.item;
        }
        return  getRecursive_help(p.next,index-1);
    }

    public LinkedListDeque(){
        sentinel = new Node();
        size = 0;
    }
}
