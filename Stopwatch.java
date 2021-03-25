package extra;

public class Stopwatch {
    long tStart;
    long tEnd;

    public Stopwatch(){
        tStart=0;
        tEnd=0;
    }

    public void start(){
        tStart = System.nanoTime();
    }

    public long getElapsedTime(){
        tEnd = System.nanoTime();
        return (tEnd-tStart);
    }
}
