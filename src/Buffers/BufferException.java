package Buffers;

/**
 * Java BufferException Class
 * Created by Tommaso Garuglieri - garuglieritommaso@gmail.com on 03/12/2014.
 */

public class BufferException extends Exception {

    public static final String EXCEPTION_OVERFLOW = "CircularBuffer Exception - Queue is full";
    public static final String EXCEPTION_UNDERFLOW = "CircularBuffer Exception - Queue is empty";

    public BufferException(String message) {
        super(message);
    }

    public BufferException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
