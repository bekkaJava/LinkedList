import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    private Node<T> head;
    private int size = 0;


    public void insertFirst(T value) {

        Node node = new Node(value);

        if (head == null) {
            head = node;
        }else {
            head.prev = node;
            node.next = head;
            head = node;

        }
        size++;

    }

    public void insertLast(T value) {

        Node node = new Node(value);

        if (isEmpty()) {
            insertFirst(value);
        } else {
            Node last = get(size - 1);
            last.next = node;
            node.prev = last;
            size++;
        }
    }

    public void insert(int index, T value) {
        if (index == 0) {
            insertFirst(value);
            return;
        }
        if (index == size) {
            insertLast(value);
            return;
        }

        Node node = new Node(value);
        Node before = get(index -1);

        node.next = before.next;
        node.next.prev = node;
        node.prev = before;
        before.next = node;

        size++;

    }

    public boolean isEmpty() {
        return size ==0 ;
    }
    private Node get(int index) {
        if (index < 0 || index >= size){
            throw  new IndexOutOfBoundsException("Index is ut of the bound");
        }

        Node temp = head;

        for (int i = 0; i < index; i++) {

            temp = temp.next;
        }
        return temp;

    }

    public T deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        T value =  head.value;

        if (size == 1) {
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return value;
    }


    public T deleteLast() {

        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        if (size == 1) {
            return deleteFirst();
        }

        Node<T> node = get(size -2);
        T value =  node.next.value;
        node.next = null;
        size--;
        return  value ;


    }


    public void display() {
        Node temp = head;

        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }

    }

    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (index == 0) {
            return deleteFirst();
        }

        if (index == size - 1) {
            return deleteLast();
        }

        Node<T> before = get(index - 1);
        T value =  before.next.value;
        before.next = before.next.next;

        if (before.next != null) {
            before.next.prev = before;
        }

        size--;
        return value;
    }


    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }
    }


}
