package java_practice.src;

class Counter {
    private int count=0;

    public void increment(){
        /*
        Use Synchronized keyword only on critical section
         */
        synchronized(this){
            count++;
        }
    }

    /*
    Can use synchronized keyword on function name itself
     */
//    public synchronized void increment(){
//        count++;
//    }

    public int getCount(){
        return count;
    }
}

public class CounterTest{
    public static void main(String[] args){
        Counter counter = new Counter();
        Thread1 thread1 = new Thread1(counter);
        Thread1 thread2 = new Thread1(counter);

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }

        System.out.println(counter.getCount());
    }
}

class Thread1 extends Thread{
    Counter counter;

    public Thread1(Counter counter){
        this.counter = counter;
    }

    public void run(){
        for(int i=0;i<1000;i++){
            counter.increment();
        }
    }
}
