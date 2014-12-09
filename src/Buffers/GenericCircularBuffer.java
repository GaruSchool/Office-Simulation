package Buffers;

import java.util.ArrayList;

/**
 * Java GenericCircularBuffer Class
 * Created by Tommaso Garuglieri - garuglieritommaso@gmail.com on 03/12/2014.
 */

public class GenericCircularBuffer<T> {

    private static final int defaultCapacity = 5;
    private final int maxSize;
    private T[] array;
    private int coda = 0;
    private int testa = 0;


    public GenericCircularBuffer() {
        this(defaultCapacity);
    }

    public GenericCircularBuffer(int capacity) {
        maxSize = capacity;
        array = (T[]) new Object[maxSize];
    }

    public int size() {
        if (isEmpty())
            return 0;
        if (testa > coda)
            return testa - coda;
        return maxSize - coda + testa;
    }

    public boolean isEmpty() {
        return (testa == coda);
    }

    public boolean isFull() {
        int diff = testa - coda;
        return (diff == -1 || diff == (maxSize - 1));
    }

    public void add(T item) throws BufferException {
        if (isFull()) {
            throw new BufferException(BufferException.EXCEPTION_OVERFLOW);
        } else {
            array[testa] = item;
            testa = (testa + 1) % maxSize;
        }
    }

    public T remove() throws BufferException {
        T item;
        if (isEmpty()) {
            throw new BufferException(BufferException.EXCEPTION_UNDERFLOW);
        } else {
            item = array[coda];
            array[coda] = null;
            coda = (coda + 1) % maxSize;
        }
        return item;
    }

    public T get() throws BufferException {
        if (!isEmpty())
            return array[coda];
        else
            throw new BufferException(BufferException.EXCEPTION_UNDERFLOW);
    }

    public void clear() {
        coda = 0;
        testa = 0;
        array = (T[]) new Object[maxSize];
    }

    public String toString() {
        String string = "Array of " + this.size() + " Elements";
    /*    for (T obj : array) {
            if (obj.toString() != null)
                string += obj.toString();
        }
*/
        return string;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> collection = new ArrayList<T>();
        for (T obj : array)
            collection.add(obj);
        return collection;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getCurrentIndex() {
        return this.testa;
    }
}
