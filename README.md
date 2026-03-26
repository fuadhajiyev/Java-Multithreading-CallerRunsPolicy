**"Partlamaq" və Rejection Policy (Rədd Edilmə Siyasəti**
İndi isə keçək sənin əsas sualına. Təsəvvür et ki, bizim 3 işçimiz (Thread) və 5 nəfərlik gözləmə zalımız (Queue) var.

3 tapşırıq gəldi: 3 işçi dərhal onları götürüb işləməyə başladı.

Əlavə 5 tapşırıq gəldi: İşçilər məşğul olduğu üçün bu 5 tapşırıq gözləmə zalına (növbəyə) oturdu. Hər şey əladır.

Birdən 9-cu tapşırıq gəldi! İşçilər doludur, növbədə (zallarda) yer yoxdur. Bəs Java indi bu təzə gələn tapşırığı neyləsin?

Bax bu anda Rejection Policy (Rədd Edilmə Siyasəti) işə düşür. Java-nın bizə təklif etdiyi 4 ssenari var:

**1. AbortPolicy (Default - Partlamaq!):**
Əgər sən heç nə konfiqurasiya etməsən, Java dərhal RejectedExecutionException xətası atacaq. Yəni proqram "partlayacaq" və 9-cu tapşırıq ləğv olunacaq. Bayaq "ExecutorService-i partlatmaq" deyərkən məhz bu xətanı nəzərdə tuturdum.

**2. CallerRunsPolicy (Özün et!):**
Bu ən maraqlı və təhlükəsiz siyasətdir. İşçilər deyir ki: "Mən doluyam, ey Main Thread, bu işi mənə sən göndərmisən, zəhmət çək özün icra et!". Beləliklə, tapşırığı göndərən əsas axın (Main thread) bloklanır və tapşırığı özü icra edir. Bu həm də sistemə yeni tapşırıqların gəlməsini yavaşladır (Throttle effect).

**3. DiscardPolicy (Səssizcə atmaq):**
9-cu tapşırıq heç bir xəta (Exception) verilmədən səssizcə zibil qutusuna atılır. Çox təhlükəlidir, çünki məlumat itkisindən xəbərin belə olmur.

**4. DiscardOldestPolicy (Köhnəni qurban vermək):**
Gözləmə növbəsindəki ən köhnə (ən çox gözləyən) tapşırıq zibilə atılır və onun yerinə bu yeni gələn 9-cu tapşırıq qoyulur.

**Kritik Detal (newFixedThreadPool tələsi):**
Sən Executors.newFixedThreadPool(3) yaradanda, onun arxasındakı növbə (Queue) limitsizdir (LinkedBlockingQueue). Yəni milyonlarla tapşırıq gəlsə belə, o heç vaxt Rejection Policy-yə çatmır, növbəyə yığır və sonda RAM dolduğu üçün sistem çökür (OOM).

