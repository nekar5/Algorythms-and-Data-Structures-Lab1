import tasks.*;
import extra.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("SLL:");
        testClass(new SinglyLinkedList());
        System.out.println();

        System.out.println("DLL:");
        testClass(new DoublyLinkedList());
        System.out.println();

        System.out.println("AL:");
        testClass(new MyArrayList());
        System.out.println();

    }

    public static void testClass(ListInterface testList){
        Random rand = new Random();
        Stopwatch timer = new Stopwatch();
        int startInd = 0;
        int middleInd = 500;

        int toFind = 567;

        for(int i=0; i<1000; i++)
            testList.add(i, rand.nextInt(1000));

        int endInd = testList.length()-1;

        //Вставка:
        System.out.println("[Вставка:]");

        timer.start();
        testList.add(startInd, rand.nextInt());
        System.out.println("у початок: "+ timer.getElapsedTime());

        timer.start();
        testList.add(endInd, rand.nextInt());
        System.out.println("у кінець: "+ timer.getElapsedTime());

        timer.start();
        testList.add(middleInd, rand.nextInt());
        System.out.println("у середину: "+ timer.getElapsedTime() +"\n");

        //Видалення
        System.out.println("[Видалення:]");

        timer.start();
        testList.remove(startInd);
        System.out.println("початок: "+ timer.getElapsedTime());

        timer.start();
        testList.remove(endInd);
        System.out.println("кінець: "+ timer.getElapsedTime());

        timer.start();
        testList.remove(middleInd);
        System.out.println("серединa: "+ timer.getElapsedTime() +"\n");

        //Заміна
        System.out.println("[Заміна:]");

        timer.start();
        testList.set(startInd, rand.nextInt());
        System.out.println("початок: "+ timer.getElapsedTime());

        timer.start();
        testList.set(endInd, rand.nextInt());
        System.out.println("кінець: "+ timer.getElapsedTime());

        timer.start();
        testList.set(middleInd, rand.nextInt());
        System.out.println("серединa: "+ timer.getElapsedTime() +"\n");

        //Сума елементів
        System.out.println("[Сума елементів:]");
        timer.start();
        long sum = 0;
        for(int i=0;i<testList.length()-1; i++){
            sum+= testList.getIVal(i);
        }
        System.out.println(sum);
        System.out.println("Час: "+ timer.getElapsedTime() +"\n");

        //Пошук індексу елементу за значенням
        System.out.println("[Пошук індексу елементу за значенням:]");
        timer.start();
        System.out.println("Елемент: "+ toFind +"\n"+
                "Індекс: "+testList.indexOf(toFind)+"  ( -1  =>  немає )");
        System.out.println("Час: "+ timer.getElapsedTime() +"\n");
    }
}
