package com.example.olympicgames.entities;

public class Query3Dto {
    public final String playerName;
    public final String olympicId;

    public Query3Dto(String playerName, String olympicId) {
        this.playerName = playerName;
        this.olympicId = olympicId;
    }

    @Override
    public String toString() {
        return "Query3Dto{" +
                "playerName='" + playerName + '\'' +
                ", olympicId='" + olympicId + '\'' +
                '}';
    }
}
