package deque;

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

    public void addFirst(T item){
        sentinel.next = new Node(item,sentinel,sentinel.next);
        sentinel.next.next.before = sentinel.next;
        ++size;
    }

    public void addLast(T item){
        sentinel.before = new Node(item,sentinel.before,sentinel);
        sentinel.before.before.next = sentinel.before;
        ++size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item);
            System.out.print(' ');
            p=p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty())   return null;
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.before = sentinel;
        --size;
        return temp;
    }

    public T removeLast(){
        if(isEmpty()) return null;
        T temp = sentinel.before.item;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        --size;
        return temp;
    }

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

    private T getRecursive_help(Node p,int index){
        if(index>=size) return null;
        if(index==0){
            return p.item;
        }
        return  getRecursive_help(p.next,index-1);
    }
    public T getRecursive(int index){
        return getRecursive_help(sentinel.next,index);
    }

    public LinkedListDeque(){
        sentinel = new Node();
        size = 0;
    }
}
