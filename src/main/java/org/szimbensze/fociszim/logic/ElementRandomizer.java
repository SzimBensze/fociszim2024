package org.szimbensze.fociszim.logic;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class ElementRandomizer<E> {

    private final NavigableMap<Double, E> map = new TreeMap<>();
    private double total = 0;

    public void add(double weight, E element) {
        if (weight <= 0 || map.containsValue(element))
            return;
        total += weight;
        map.put(total, element);
    }

    public E next() {
        double value = ThreadLocalRandom.current().nextDouble() * total;
        return map.ceilingEntry(value).getValue();
    }

}
