package java_practice.src;

public class ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        /*
        Creates a new thread
        Assigns the Runnable task to it
        Sets the thread name
         */
        Thread thread = new Thread(myThread, "Max_Priority_Thread");
        thread.start();
        thread.interrupt();
        thread.join();
        System.out.println(Thread.currentThread().getName() + "  Hellooo....");
    }
}

class MyThread implements Runnable {
    public void run(){
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted...." + e);
            }
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            System.out.println("NAME: " + Thread.currentThread().getName() + " Count: " + i + " Priority " + Thread.currentThread().getPriority());
        }
    }
}

/* METHODS
Start()
run()
sleep()
join()
getPriority()
setPriority()
interrupt
*/

/*
MIN_PRIORITY 1
MAX_PRIORITY 10
NORM_PRIORITY 5
*/

/*NOTE:
Runnable = task
Thread = execution unit
 */
