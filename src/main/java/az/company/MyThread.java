package az.company;

public class MyThread extends Thread {
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " Thread klası vasitəsilə işləyir.");
    }
}
