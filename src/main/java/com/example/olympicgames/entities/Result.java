package com.example.olympicgames.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "Results")
public class Result {

    @EmbeddedId
    private ResultId id;

    @MapsId("eventId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "medal")
    private String medal;

    @Column(name = "result")
    private Double result;

    public Result() { }

    public ResultId getId() {
        return id;
    }

    public void setId(ResultId id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "eventId='" + id.eventId + '\'' +
                " playerId='" + id.playerId + '\'' +
                '}';
    }

    @Embeddable
    static class ResultId implements Serializable {
        public int eventId;
        public int playerId;

        public ResultId() { }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result.ResultId resultId = (Result.ResultId) o;
            return eventId == resultId.eventId && playerId == resultId.playerId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(eventId, playerId);
        }
    }
}
