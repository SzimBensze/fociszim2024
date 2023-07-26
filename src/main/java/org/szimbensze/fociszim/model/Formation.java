package org.szimbensze.fociszim.model;

public enum Formation {

    FX3("X-X-X", 4, 5, 4),
    FX4("X-X-X-X", 3, 4, 6),
    FX5("X-X-X-X-X", 4, 7, 2),
    F235("2-3-5", 4, 2, 7),
    F343("3-4-3", 11, 6, -4),
    F352("3-5-2", 8, 4, 1),
    F361("3-6-1", 0, 11, 2),
    F433("4-3-3", 6, 7, 0),
    F442("4-4-2", 7, 7, -1),
    F451("4-5-1", 2, 9, 2),
    F460("4-6-0", 5, 12, -4),
    F532("5-3-2", -2, 4, 11),
    F541("5-4-1", -5, 6, 12),
    F3331("3-3-3-1", 3, 13, -3),
    F3412("3-4-1-2", 12, 3, -2),
    F3421("3-4-2-1", 13, 5, -5),
    F4141("4-1-4-1", -4, 8, 9),
    F4222("4-2-2-2", 4, 4, 5),
    F4231("4-2-3-1", 7, 1, 5),
    F4312("4-3-1-2", 3, 1, 9),
    F4411("4-4-1-1", 2, 6, 5),
    F5212("5-2-1-2", -4, 4, 13),
    F5221("5-2-2-1", 6, -1, 8),
    F32221("3-2-2-2-1", -1, 4, 10),
    F41212("4-1-2-1-2", 9, 4, 0),
    F42121("4-2-1-2-1", 4, 10, -1),
    F42211("4-2-2-1-1", 10, -2, 5);

    public final String inputString;
    public final Integer addedAtk;
    public final Integer addedMid;
    public final Integer addedDef;

    Formation(String inputString, Integer addedAtk, Integer addedMid, Integer addedDef) {
        this.inputString = inputString;
        this.addedAtk = addedAtk;
        this.addedMid = addedMid;
        this.addedDef = addedDef;
    }
}
