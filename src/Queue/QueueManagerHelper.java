package Queue;

/**
 * Created by Tommaso Garuglieri on 04/12/2014.
 */
public class QueueManagerHelper {
    private static final int QUEUE_SIZE = 10;
    private static QueueManager queueManager;

    public static QueueManager getInstance() {
        if (queueManager == null)
            queueManager = new QueueManager(QUEUE_SIZE);
        return queueManager;
    }
}
