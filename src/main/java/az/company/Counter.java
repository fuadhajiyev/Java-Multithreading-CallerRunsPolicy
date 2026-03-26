package az.company;


class Counter {
    private int count = 0;

    // "synchronized" açar sözü bu metodu "kilidləyir" (Lock).
    // Yəni, bir Thread bu metoda daxil olanda, digər Thread-lər qapıda gözləməli olur.
    // Əgər burdan "synchronized" sözünü silsən, yekun nəticə heç vaxt stabil olaraq 2000 çıxmayacaq.
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}