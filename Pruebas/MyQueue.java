//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//MyQueue.java

public class MyQueue<E> {
    private MiListaEnlazada<E> lista;

    public MyQueue() {
        this.lista = new MiListaEnlazada<>();
    }

    public int size() {
        return this.lista.size();
    }

    public boolean isEmpty() {
        return this.lista.isEmpty();
    }

    public void flush() {
        this.lista = new MiListaEnlazada<>();
        System.gc();
    }

    public void enqueue(E dato) {
        this.lista.insertAtLast(dato);
    }

    public E dequeue() {
        return this.lista.removeFirst();
    }

    public E next() {
        return this.lista.first();
    }



    public static void main(String[] args) {
        MyQueue<String> cola = new MyQueue<String>();
        cola.enqueue("J");
        cola.enqueue("C");
        cola.enqueue("O");
        cola.enqueue("A");
        cola.enqueue("R");
        cola.enqueue("S");


        while(!cola.isEmpty()) {
            System.out.print(cola.dequeue() + ",");

        }
    }

}
