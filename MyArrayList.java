package tasks;

import extra.ListInterface;

public class MyArrayList implements ListInterface {
    //можем зробити і з іншими типами даних, не тільки int, Ви тіки скажіть
    private static final int INIT_CAPACITY = 10;//стандартна вмісність
    // при перевищенні ліміту по елементах

    private int capacity;//вмісність
    private int[] array;//масив даних
    private int elements = 0;//к-сть елементів

    //два конструктори
    public MyArrayList() {
        capacity = INIT_CAPACITY;
        array = new int[capacity];
    }
    //конструктор з задаваємою вмісністю
    public MyArrayList(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }

    @Override
    public void add (int val){//додавання елемента
        //додавання в кінець списку
        if(array.length <= elements){
            arrayIncrease();
        }
        array[elements] = val;
        elements++;
    }

    @Override
    public void add (int index, int val) throws IndexOutOfBoundsException {
        //тут же можна і виконати додавання в початок, вказавши 0 як індекс
        try {
            if(elements==0)
                add(val);
            if (index > elements) {
                throw new IndexOutOfBoundsException("Помилка! " +
                        "Індекс виходить за межі(розмір) списку (" + elements + ")");

            }
            if (index == elements) {//додавання в кінець списку(+зсув останнього елементу вправо)
                if (elements + 1 == capacity)
                    arrayIncrease();
                array[elements] = array[elements-1];
                array[elements-1] = val;
            }
            else {
                if (array.length <= elements) {
                    arrayIncrease();
                }
                int[] tempArr = new int[array.length + 1];
                if (index >= 0)
                    System.arraycopy(array, 0, tempArr, 0, index);
                tempArr[index] = val;
                if (array.length + 1 - (index + 1) >= 0)
                    System.arraycopy(array, index, tempArr, index + 1,
                            array.length + 1 - (index + 1));
                array = tempArr;
                elements++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    public int get(int index) {
        try {
            if (array.length <= index - 1)
                return array[index];
            else
                throw new IndexOutOfBoundsException("Помилковий індекс!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void remove(int index){
        //видалення елемента за індексом, по суті і є видаленням початкового, кінцевого та середнього елементів
        try {
            if (index > elements - 1)
                throw new IndexOutOfBoundsException("Помилка! " +
                        "Індекс виходить за межі(розмір) списку (" + elements + ")");
            int[] tempArr = new int[array.length];
            if (index >= 0)
                System.arraycopy(array, 0, tempArr, 0, index);
            if (array.length - (index + 1) >= 0)
                System.arraycopy(array, index + 1, tempArr, index + 1 - 1,
                        array.length - (index + 1));
            array = tempArr;
            elements--;
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void set(int index, int val){
        if(index <= array.length-1){
            if(index== array.length)
                arrayIncrease();
            array[index] = val;
        }
    }

    public int getCapacity() {
        return array.length;
    }

    public void setCapacity(int capacity) {
        if(capacity<elements)
            System.err.println("Задана вмісніть менша за інуюучий розмір!");
        else
            this.capacity = capacity;
    }

    @Override
    public int length(){
        return elements;
    }

    @Override
    public int getIVal(int index){
        return array[index];
    }

    @Override
    public int indexOf(int val){
        //повертає індекс елемента, якщо не знайде цього елемента поверне -1
        for (int i = 0; i < array.length; i++)
            if (array[i]==val)
                return i;
        return -1;
    }

    public boolean contains(int val){
        return (indexOf(val) >= 0);
    }

    private void arrayIncrease(){
        //коефіцієнт, на який збільшуватиметься масив(у скільки раз)
        float INCR_COEFF = 2;
        capacity *= INCR_COEFF;
        int[] newArr = new int[capacity];
        System.arraycopy(array, 0, newArr, 0, array.length);
        array = newArr;
    }

    @Override
    public void printList() {
        if (array.length == 0)
            System.out.println("List is empty");
        else {
            System.out.println("Contents of array list:");
            for (int i : array)
                System.out.print(i+" ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        String output = "";

        if (elements!=0)
            for(int i: array)
                output+= "["+i+"]";
        else
            output="[empty]";
        return output;
    }
}