package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int capacity;

    // front point at an empty place which do not store any data.
    // Circular queue implementation.
    private int front;
    private int rear;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque(){
        capacity = 8;
        // The starting size of your array should be 8.
        items = (T[]) new Object[capacity];
        //  For arrays of length 16 or more, your usage factor should always be at least 25%.
        front = 0;
        rear = 0;
    }

    private void resize(int newCapacity){
        // Store the old array.
        T[] temp = items;
        // Create a new array.
        items = (T[]) new Object[newCapacity];
        if(front<=rear){
            System.arraycopy(temp, front, items, 0, rear-front+1);
            // update rear
            rear = rear-front;
        }else{
            System.arraycopy(temp, front, items, 0, capacity-front);
            System.arraycopy(temp, 0, items, capacity-front, rear+1);
            // update rear
            rear = rear-front+capacity;
        }
        // front=0.
        front = 0;
        // update the capacity
        capacity = newCapacity;
    }

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
     * @param item
     */
    public void addFirst(T item){
        if((front-1+capacity)%capacity==rear){
            resize(capacity*2);
        }
        items[front] = item;
        front = (front-1+capacity)%capacity;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     * @param item
     */
    public void addLast(T item){
        if((rear+1)%capacity==front){
            resize(capacity*2);
        }
        rear = (rear+1)%capacity;
        items[rear] = item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    public boolean isEmpty(){
        return front==rear;
    }

    /**
     * Returns the number of items in the deque.
     * @return
     */
    public int size(){
        return (rear-front+capacity)%capacity;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     */
    public void printDeque(){
        for(int i=0;i<size();++i){
            System.out.print(items[(i+front+1)%capacity]+" ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeFirst(){
        if(size()==0) return null;
        if(capacity>=16){
            if (size()<capacity/4) {
                resize(capacity/2);
            }
        }

        front = (front+1)%capacity;
        return items[front];
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * @return
     */

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeLast(){
        if(size()==0) return null;
        if(capacity>=16){
            if (size()<capacity/4) {
                resize(capacity/2);
            }
        }
        T ans = items[rear];
        rear = (rear-1+capacity)%capacity;
        return ans;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index){
        if(index>=size()) return null;
        return items[(front+1+index)%capacity];
    }



}
