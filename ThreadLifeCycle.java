package java_practice.src;

public class ThreadLifeCycle {
    public static void main(String[] args) throws InterruptedException {
        World world = new World();
        Thread thread = new Thread(world);
        System.out.println(Thread.currentThread().getName() + "  " + thread.getState());
        thread.start();
        System.out.println(Thread.currentThread().getName() + "  " + thread.getState());
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "  " + thread.getState());
        thread.join(); //main method will wait for thread to finished
        System.out.println(Thread.currentThread().getName() + "  " + thread.getState());
    }
}

class World implements Runnable{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("World " + Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/*
main  NEW
main  RUNNABLE
World Thread-0 RUNNABLE
main  TIMED_WAITING
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
World Thread-0 RUNNABLE
main  TERMINATED

Process finished with exit code 0
 */
