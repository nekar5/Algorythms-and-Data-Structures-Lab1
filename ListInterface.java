package extra;

public interface ListInterface {
    void add(int val);
    void add(int index, int val);

    int get(int index);

    void set(int index, int val);
    //void set(Object obj_new, Object int obj);

    void remove(int index);

    String toString();

    void printList();

    int length();

    //task methods
    int getIVal(int index);

    int indexOf(int val);
}
