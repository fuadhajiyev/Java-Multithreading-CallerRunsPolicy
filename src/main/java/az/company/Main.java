package az.company;


import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
// 1. SƏRHƏDLİ NÖVBƏ (RAM-ı qoruyan qalxan)
        // ArrayBlockingQueue istifadə edirik ki, 5-dən artıq tapşırıq gözləyə bilməsin.
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);

        // 2. MÜHƏRRİKİ ÖZÜMÜZ YIĞIRIQ
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,                      // corePoolSize: Həmişə aktiv qalan 3 işçi
                3,                      // maximumPoolSize: Maksimum işçi sayı
                0L,                     // keepAliveTime: Boş qalan işçinin gözləmə vaxtı
                TimeUnit.MILLISECONDS,  // Vaxtın ölçü vahidi
                queue,                  // Bayaq yaratdığımız 5 yerlik sərhədli növbə
                new ThreadPoolExecutor.CallerRunsPolicy() // QƏHRƏMANIMIZ: Rədd edilmə siyasəti!
        );

        // 3. SİSTEMİ "STRESS TESTƏ" SALIRIQ
        // Hovuzun ümumi tutumu: 3 işçi + 5 növbə = 8 yer. Biz isə 15 tapşırıq göndəririk.
        for (int i = 1; i <= 15; i++) {
            final int taskId = i;

            System.out.println("Tapşırıq " + taskId + " göndərilir...");

            executor.submit(() -> {
                // Bu kod bloku tapşırığın özüdür
                try {
                    // İşi simulyasiya edirik (hər tapşırıq 2 saniyə çəkir)
                    Thread.sleep(2000);
                    System.out.println("✅ Tapşırıq " + taskId + " bitdi. İcra edən: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Hovuzu düzgün bağlayırıq
        executor.shutdown();


    }
}