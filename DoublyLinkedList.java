package tasks;

import extra.ListInterface;

public class DoublyLinkedList implements ListInterface {
    class Node{
        int val;
        Node previous;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(Node beforeNewNode, int val, Node afterNewNode) {
            previous=beforeNewNode;
            this.val=val;
            next=afterNewNode;
        }
    }

    //кінець і початок
    Node head, tail = null;
    int elements;

    @Override
    public void add(int val)
    {
        Node temp = new Node(val);
        Node last = head;
        temp.next = null;
        if (head == null) {
            temp.previous = null;
            head = temp;
            elements++;
            return;
        }
        while (last.next != null)
            last = last.next;
        last.next = temp;
        temp.previous = last;
        elements++;
    }

    @Override
    public void add(int index, int val) {
        if (index < 0 || index > elements)
            return;
        Node temp = new Node(val);
        if (head == null) {
            head = temp;
            tail = temp;
            elements++;
        }
        else if (index == 0) {
            temp.next = head;
            head.previous = temp;
            head = temp;
            elements++;
        }
        else if (index == elements) {
            temp.previous = tail;
            tail.next = temp;
            tail = temp;
            elements++;
        }
        else {
            Node nodeRef = head;
            for (int i = 1; i < index; i++)
                nodeRef = nodeRef.next;
            temp.next = nodeRef.next;
            nodeRef.next = temp;
            temp.previous = nodeRef;
            temp.next.previous = temp;
            elements++;
        }
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= elements) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.val;
        }
    }

    @Override
    public void set(int index, int val){
        remove(index);
        add(index, val);
    }

    @Override
    public void remove(int index) {
        try {
            if (index < 0 || index >= elements) {
                throw new IndexOutOfBoundsException("incorrect index (remove() DLL");
            }
            if (index == 0) {
                head = head.next;
                head.previous = null;
                elements--;
            } else if (index == elements - 1) {
                tail = tail.previous;
                tail.next = null;
                elements--;
            } else {
                Node current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                Node previous = current.previous;
                Node next = current.next;
                previous.next = current.next;
                next.previous = previous;
                elements--;
            }
        }
        /*
        catch(IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

         */
        catch(NullPointerException e){
            System.err.println("remove() DLL NPE)");
        }
    }

    @Override
    public int length(){
        return elements;
    }

    @Override
    public int getIVal(int index){
        return get(index);
    }

    @Override
    public int indexOf(int val){
        Node temp = head;
        int index=0;
        while(temp.next!=null){
            if(temp.val==val)
                return index;
            temp=temp.next;
            index++;
        }
        return -1;
    }

    public void printList() {  //вивід
        //елемент вказує на початок
        Node current = head;
        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Nodes of doubly linked list: ");
        while(current != null) {
            //виводимо кожен елемент по одному
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}