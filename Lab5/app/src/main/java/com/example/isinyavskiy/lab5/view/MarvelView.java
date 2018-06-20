package com.example.isinyavskiy.lab5.view;


import com.example.isinyavskiy.lab5.model.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the country view interface.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface MarvelView {

    void characterReady(ArrayList<Character> characters);
    void fail(String message);
}
