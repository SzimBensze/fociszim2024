package org.szimbensze.fociszim.model;

import java.util.HashMap;
import java.util.Map;

public class Formations {
    private static final Map<String, Formation> formations = new HashMap<>();

    static {
        formations.put("2-3-5", new Formation ("2-3-5", 4, 2, 7));
        formations.put("3-4-3", new Formation("3-4-3", 11, 6, -4));
        formations.put("3-5-2", new Formation("3-5-2", 8, 4, 1));
        formations.put("3-6-1", new Formation("3-6-1", 0, 11, 2));
        formations.put("4-3-3", new Formation("4-3-3", 6, 7, 0));
        formations.put("4-4-2", new Formation("4-4-2", 7, 7, 1));
        formations.put("4-5-1", new Formation("4-5-1", 2, 9, 2));
        formations.put("4-6-0", new Formation("4-6-0", 5, 12, -4));
        formations.put("5-3-2", new Formation("5-3-2", -2, 4, 11));
        formations.put("5-4-1", new Formation("5-4-1", -5, 6, 12));
        formations.put("3-3-3-1", new Formation("3-3-3-1", 3, 13, -3));
        formations.put("3-4-1-2", new Formation("3-4-1-2", 12, 3, -2));
        formations.put("3-4-2-1", new Formation("3-4-2-1", 13, 5, -5));
        formations.put("4-1-4-1", new Formation("4-1-4-1", -4, 8, 9));
        formations.put("4-2-2-2", new Formation("4-2-2-2", 4, 4, 5));
        formations.put("4-2-3-1", new Formation("4-2-3-1", 7, 1, 5));
        formations.put("4-3-1-2", new Formation("4-3-1-2", 3, 1, 9));
        formations.put("4-4-1-1", new Formation("4-4-1-1", 2, 6, 5));
        formations.put("5-2-1-2", new Formation("5-2-1-2", -4, 4, 13));
        formations.put("5-2-2-1", new Formation("5-2-2-1", 6, -1, 8));
        formations.put("3-2-2-2-1", new Formation("3-2-2-2-1", -1, 4, 10));
        formations.put("4-1-2-1-2", new Formation("4-1-2-1-2", 9, 4, 0));
        formations.put("4-2-1-2-1", new Formation("4-2-1-2-1", 4, 10, -1));
        formations.put("4-2-2-1-1", new Formation("4-2-2-1-1", 10, -2, 5));
    }

    public static Formation getFormation(String formationName) {
        return formations.getOrDefault(formationName, getDefaultFormation(formationName));
    }

    private static Formation getDefaultFormation(String rawFormation) {
        int numLines = rawFormation.split("-").length;
        return switch (numLines) {
            case 3 -> new Formation("X-X-X", 4, 5, 4); // Default for "X-X-X"
            case 4 -> new Formation("X-X-X-X", 3, 4, 6); // Default for "X-X-X-X"
            case 5 -> new Formation("X-X-X-X-X", 4, 7, 2); // Default for "X-X-X-X-X"
            default -> new Formation("?-?-?", 0, 0, 0); // Default for any other case (e.g., invalid formation, no modifications)
        };
    }
}
