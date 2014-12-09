package Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cccp on 08/12/2014.
 */
public class InputKeyboard implements InputInteface {
    @Override
    public String getInput() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
