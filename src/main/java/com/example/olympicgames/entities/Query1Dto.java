package com.example.olympicgames.entities;

public class Query1Dto {
    public final int birthYear;
    public final long nPlayers;
    public final long nGoldMedals;

    public Query1Dto(int birthYear, long nPlayers, long nGoldMedals) {
        this.birthYear = birthYear;
        this.nPlayers = nPlayers;
        this.nGoldMedals = nGoldMedals;
    }

    @Override
    public String toString() {
        return "Query1Dto{" +
                "birthYear=" + birthYear +
                ", nPlayers=" + nPlayers +
                ", nGoldMedals=" + nGoldMedals +
                '}';
    }
}
