package java_practice.src;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksDemo {
    public static void main(String[] args){
        BankAccount bankAccount = new BankAccount();

        Runnable runnable = new Runnable() {
            public void run(){
                bankAccount.withDraw(50);
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                bankAccount.withDraw(50);
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable1);

        t1.start();
        t2.start();
    }
}

class BankAccount{
    private final Lock lock = new ReentrantLock();

    private int balance = 100;

    public void withDraw(int amount){
        System.out.println(Thread.currentThread().getName()+" Attempting to withDraw: "+amount);
        try {
            if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Attempting to Withdraw: " + amount);
                        Thread.sleep(3000);
                        balance = balance - amount;
                        System.out.println(Thread.currentThread().getName() + " completed Withdrawl: " + amount);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted while withdrawing");
                    }finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println("Insufficient balance");
                }
            }else{
                System.out.println("Could not accquire lock..");
            }

        }catch(InterruptedException e){
            System.out.println("BankAccount "+Thread.currentThread().getName()+" Interrupted");
        }
    }
}
