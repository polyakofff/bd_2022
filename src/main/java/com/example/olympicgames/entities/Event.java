package com.example.olympicgames.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "Events")
public class Event {
    @Id
    @Column(name = "event_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "eventtype")
    private String eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "olympic_id")
    private Olympics olympics;

    @Column(name = "is_team_event")
    @Type(type="numeric_boolean")
    private boolean isTeamEvent;

//    @Column(name = "is_team_event")
//    @Access(AccessType.PROPERTY)
//    public int getIsTeamEvent() {
//        return isTeamEvent ? 1 : 0;
//    }
//
//    @Column(name = "is_team_event")
//    @Access(AccessType.PROPERTY)
//    private void setIsTeamEvent(int value) {
//        isTeamEvent = value == 1;
//    }

    public Event() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Olympics getOlympics() {
        return olympics;
    }

    public void setOlympics(Olympics olympics) {
        this.olympics = olympics;
    }

    public boolean isTeamEvent() {
        return isTeamEvent;
    }

    public void setTeamEvent(boolean teamEvent) {
        isTeamEvent = teamEvent;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                '}';
    }
}
