package Helpers;

/**
 * Created by cccp on 09/12/2014.
 */
public class InputDelayed implements InputInteface {
    public static int DEFAULT_DELAY = 1000;
    private int delay = DEFAULT_DELAY;

    public InputDelayed() {}

    public InputDelayed(int delay) {
        this.delay = delay;
    }

    @Override
    public String getInput() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
