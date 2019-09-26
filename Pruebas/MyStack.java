//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//MyStack.java

public class MyStack<E> {
    MiListaEnlazada<E> lista;

    public MyStack() {
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

    public void push (E dato) {
        this.lista.insertAtFirst(dato);
    }

    public E pop() {
        return this.lista.removeFirst();
    }

    public E top() {
        return this.lista.first();
    }
    public static void main(String[] args){
        MyStack<String> pila= new MyStack<String>();
        pila.push("j");
        pila.push("j1");
        pila.push("j2");
        pila.push("j3");




        while(!pila.isEmpty()) {
            System.out.print(pila.pop() + ",");
        }

    }
}
