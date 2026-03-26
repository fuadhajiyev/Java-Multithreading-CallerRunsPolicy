package az.company;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Runnable vasitəsilə işləyir.");    }
}
