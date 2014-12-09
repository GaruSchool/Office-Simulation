package Helpers;

/**
 * Created by cccp on 08/12/2014.
 */
public class InputHelper {
    public static final int INPUT_INTERFACE_KEYBOARD = 0;
    public static final int INPUT_INTERFACE_DELAYED = 1;

    public static InputInteface getInputInterface(int interfaceType) {
        if (interfaceType == INPUT_INTERFACE_KEYBOARD)
            return new InputKeyboard();
        else if (interfaceType == INPUT_INTERFACE_DELAYED)
            return new InputDelayed();
        return null;
    }

}
