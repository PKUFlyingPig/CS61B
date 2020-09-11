/**  first part of project1A
 *   Deque implemented by Linked List
 *  @author FlyingPig
 */
public class LinkedListDeque <T>{
    public class Node{
        public T item;
        public Node pre;
        public Node next;

        public Node(T n, Node ppre, Node nnext){
            item = n;
            pre = ppre;
            next = nnext;
        }

        public Node(Node ppre, Node nnext){
            pre = ppre;
            next = nnext;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public boolean isEmpty(){
       return size == 0;
    }

    public int size(){
        return size;
    }

    public void addFirst(T item){
        Node newList = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = newList;
        sentinel.next = newList;
        size ++;
    }

    public void addLast(T item){
        Node newList = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = newList;
        sentinel.pre = newList;
        size ++;
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T ret = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size --;
        return ret;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        T ret = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size --;
        return ret;
    }

    public T get(int index){
        if (index >= size){
            return null;
        }
        Node ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    private T getRecursiveHelp(Node start, int index){
        if (index == 0) {
            return start.item;
        }else {
            return getRecursiveHelp(start.next, index --);
        }
    }
    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }

    public void printDeque(){
        Node ptr = sentinel.next;
        while (ptr != sentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
}
