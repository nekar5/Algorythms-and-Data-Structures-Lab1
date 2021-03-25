package tasks;

import extra.ListInterface;

public class SinglyLinkedList implements ListInterface {
    public Node head = null;
    public Node tail = null;
    int elements=0;

    @Override
    public void add(int val) {
        if (head == null) {
            head = new Node(val);
            head.next=tail;
        }

        Node temp = new Node(val);
        Node tempCur = head;

        if (tempCur != null) {
            while (tempCur.getNext() != null) {//ідем у кінець
                tempCur = tempCur.getNext();
            }
            tempCur.setNext(temp);//next кінця = наш елемент
            tail=temp;
        }
        elements++;
    }

    @Override
    public void add(int index, int val) {
        Node temp = new Node(val);
        Node tempCur = head;

        //check for NPE
        if (tempCur != null) {
            for (int i = 0; i < index && tempCur.getNext() != null; i++) {
                tempCur = tempCur.getNext();
            }
        }
        else{
            add(val);
            return;
        }

        temp.setNext(tempCur.getNext());

        tempCur.setNext(temp);

        elements++;
    }

    @Override
    public int get(int index) {
        try {
            if(index<0)
                throw new Exception("incorrect index");
            Node temp = null;
            if (head != null) {
                temp = head.getNext();
                for (int i = 0; i < index; i++) {
                    if (temp.getNext() == null)
                        break;
                    temp = temp.getNext();
                }
                return temp.getData();
            } else
                throw new Exception("not found");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public void set(int index, int val_new){
        remove(index);
        add(index, val_new);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > length()) {
            System.err.println("Невірний індекс (remove() SLL)");
            return;
        }
        if (head != null) {
            Node temp = head;
            for (int i = 0; i < index; i++) {
                if (temp.getNext() == null)
                    return;
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
            elements--;
        }
    }

    @Override
    public String toString() {
        String output = "";
        if (head != null) {
            Node temp = head.getNext();
            while (temp != null) {
                output += "[" + temp.getData() + "]";
                temp = temp.getNext();
            }
        }
        return output;
    }

    public void printList() {
        //Node current will point to head
        Node current = head;

        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Contents of singly linked list list");
        while(current != null) {
            //Prints each node by incrementing pointer
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public int length(){
        return elements;
    }

    @Override
    public int getIVal(int index){
        //Node temp=(Node) get(index);
        //return temp.getData();
        return (Integer) get(index);
    }

    @Override
    public int indexOf(int val){
        Node temp=head;
        int index=0;
        while(temp.getNext()!=null){
            if(temp.getData()==val)
                return index;
            temp=temp.getNext();
            index++;
        }
        return -1;
    }

    private class Node {
        Node next;
        int val;

        public Node(int val) {
            next = null;
            this.val = val;
        }

        public Node(int val, Node next) {
            this.next = next;
            this.val = val;
        }

        public int getData() {
            return val;
        }

        public void setData(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}