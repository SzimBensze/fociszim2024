package org.szimbensze.fociszim.logic;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class which acts as a map with a key of chance weight and a custom value. It is used to generate random elements such as events and stoppage time.
 * @param <E> The type of the variable that is chosen when randomizing.
 */
public class ElementRandomizer<E> {

    private final NavigableMap<Double, E> map = new TreeMap<>();
    private double total = 0;

    /**
     * Adds a new value with a weight for the map.
     * @param weight The weight of chance. The higher the number the more likely it will be chosen.
     * @param element The element that can be selected.
     */
    public void add(double weight, E element) {
        if (weight <= 0 || map.containsValue(element))
            return;
        total += weight;
        map.put(total, element);
    }

    /**
     * Uses ThreadLocalRandom to return a random element.
     * @return Returns a random value from the map.
     */
    public E next() {
        double value = ThreadLocalRandom.current().nextDouble() * total;
        return map.ceilingEntry(value).getValue();
    }

}
