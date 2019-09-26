//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//MiListaEnlazada.java y NodoLE.java
import java.util.NoSuchElementException;

public class MiListaEnlazada<E> {
    private NodoLE<E> first,
            last;
    private int size;

    public MiListaEnlazada() {
        this.first=this.last = null;
        this.size = 0;
    }


    //TAREA
    public MiListaEnlazada(E[] valores) {
//		this.size=valores.length;
        insertAtFirst(valores[0]);
        for(int i = 0;i<valores.length;i++) {
            insertAt(valores[i],i);
        }
        insertAtLast(valores[valores.length-1]);
        //	this.size -= valores.length;
    }

    public E first1() throws NoSuchElementException{
        try {
            return this.first.dato;
        }catch(NullPointerException e){
            throw new NoSuchElementException("Ta vacia compa jaja");
        }
    }

    public E first() throws NoSuchElementException {
        try {
            return this.first.getDato();
        } catch(NullPointerException e) {
            throw new NoSuchElementException("No se puede obtener el primer dato de una lista vacía");
        }
    }


    public E last() throws NoSuchElementException {
        try {
            return this.last.getDato();
        } catch(NullPointerException e) {
            throw new NoSuchElementException("No se puede obtener el ultimo dato de una lista vacía");
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size==0;
    }




    public void insertAtFirst(E dato) {
        this.first = new NodoLE<>(dato,this.first);
        if(this.size==0) {
            this.last = this.first;
        }
        this.size++;
    }

    //TAREA


    public void insertAtLast(E dato) {
        NodoLE<E> nodo = new NodoLE<>(dato,null);
        if(this.size==0) {
            this.first = this.last = nodo;
        } else {
            NodoLE<E> ultimo = this.first;
            while(ultimo.next != null) {
                ultimo = ultimo.next;
            }
            ultimo.next = nodo;
            this.last = nodo;
        }
        this.size++;
    }


    //TAREA


    public void insertAt(E dato, int pos) throws NullPointerException, IndexOutOfBoundsException {


        try {
            if(pos==0) {
                insertAtFirst(dato);
            } else if (pos<size) {
                NodoLE<E> ultimo = this.first;
                int cont = 0;
                while(cont!=pos-1) {
                    cont++;
                    ultimo = ultimo.next;
                }
                size++;
                NodoLE<E> tempnode = ultimo.next;
                ultimo.next = new NodoLE<>(dato,tempnode);
            } else {
                throw new NullPointerException();
            }
        } catch(NullPointerException e) {
            System.out.println("ERROR");
            throw e;
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Error");
            throw e;
        }
    }


    public E removeFirst() {
        try {
            E dato = this.first();
            this.first = this.first.next;
            this.size--;
            if(size==0) {
                this.last= null;
            }
            return dato;
        } catch(NullPointerException e) {
            throw new NoSuchElementException("No se puede borrar el inicio de una lista vacía");
        }

    }
    public E removeAt(int pos) {
        if(size-1==pos) {
            return removeLast();
        }

        if(this.size<=1) {
            return this.removeFirst();
        }
        NodoLE<E> current = this.first;
        E dato;
        for(int i =0; i<pos;i++) {
            current = current.getNext();
        }
        dato = current.getNext().getDato();
        current.setNext(current.getNext().getNext());
        this.size--;
        return dato;
    }


    public E removeAt1(int pos) {
        if(size-1==pos) {
            return removeLast();
        }else if(this.size<=1) {

        }
        NodoLE<E> recorrer=this.first;
        for(int i=0;i<pos;i++) {
            recorrer=recorrer.next;
        }
        E dato=recorrer.next.dato;
        recorrer.next=recorrer.next.next;
        return dato;
    }



    public E removeLast() throws IndexOutOfBoundsException {
        if(size==0) {
            throw new IndexOutOfBoundsException("No se puede borrar el final de una lista vacía");
        } else {
            E dato = this.last();
            NodoLE<E> ultimo = this.first;
            for(int i =0;i<size-2;i++) {
                ultimo = ultimo.getNext();
            }
            ultimo.next = null;
            this.last = ultimo;
            this.size--;
            if(size==0) {
                this.first=null;
            }
            return dato;
        }

    }

    public void setAt1(E dato, int pos )throws IndexOutOfBoundsException{
        if(pos<0 || pos>size) {
            throw new IndexOutOfBoundsException("");
        }else {
            NodoLE<E> recorrer=this.first;
            for(int i=0;i<pos;i++) {
                recorrer=recorrer.next;
            }
            recorrer.dato=dato;
        }
    }


    public void setA1t(E dato, int pos) throws IndexOutOfBoundsException{
        if(pos<0 || pos>size) {
            throw new IndexOutOfBoundsException("Hey deja mi lisra");

        }else {
            NodoLE<E> recorrer=this.first;
            for(int i =0;i<pos;i++) {
                recorrer=recorrer.next;
            }
            recorrer.dato=dato;
        }
    }

    public void setAt(E dato, int pos) throws IndexOutOfBoundsException {
        if(pos<0 || pos>=this.size) {
            throw new IndexOutOfBoundsException("No se puede hacer el set en la posición " +pos+".");
        } else {
            NodoLE<E> current = this.first;
            for(int i=0;i<pos;i++) {
                current = current.getNext();
            }
            current.setDato(dato);
        }
    }

    public String toString() {
        String res="";
        NodoLE<E> ultimo = this.first;
        for(int i = 0;i<size;i++) {
            res+=ultimo.getDato() + ",";
            ultimo=ultimo.getNext();
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] arreglo = {2,3,1,3,4};

        MiListaEnlazada<Integer> lista = new MiListaEnlazada<>(arreglo);




        System.out.println(lista);
        lista.insertAt(2, 2);
        lista.removeFirst();
        lista.removeLast();
        System.out.println(lista);

    }

}

class NodoLE <E> {
    E dato;
    NodoLE<E> next;

    public NodoLE(E dato, NodoLE<E> next) {
        this.dato = dato;
        this.next = next;
    }

    public NodoLE(E dato) {
        super();
        //this(dato,null);
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoLE<E> getNext() {
        return next;
    }

    public void setNext(NodoLE<E> next) {
        this.next = next;
    }


}