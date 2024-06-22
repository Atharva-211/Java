package com.moodtracker;

public class Mood {
    private int id;
    private String mood;

    public Mood(String mood) {
        this.mood = mood;
    }

    public String getMood() {
        return mood;
    }
}
