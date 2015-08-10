package com.benoj.runningStats.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({ "Pos", "Bib", "Athlete", "Gender", "Category", "Club", "Locals","Finish" })
public class RaceResult {
    private final int position;
    private final int bib;
    private final String athlete;
    private final String gender;
    private final String category;
    private final String club;
    private final String local;
    private final String finish;

    @JsonCreator
    public RaceResult(@JsonProperty("Pos") final int position,
                      @JsonProperty("Bib") final int bib,
                      @JsonProperty("Athlete") final String athlete,
                      @JsonProperty("Gender") final String gender,
                      @JsonProperty("Category") final String category,
                      @JsonProperty("Club") final String club,
                      @JsonProperty("Locals") final String local,
                      @JsonProperty("Finish") final String finish) {

        this.position = position;
        this.bib = bib;
        this.athlete = athlete;
        this.gender = gender;
        this.category = category;
        this.club = club;
        this.local = local;
        this.finish = finish;
    }
    @JsonProperty("Pos")
    public int getPosition() {
        return position;
    }

    @JsonProperty("Bib")
    public int getBib() {
        return bib;
    }

    @JsonProperty("Athlete")
    public String getAthlete() {
        return athlete;
    }

    @JsonProperty("Gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("Category") final
    public String getCategory() {
        return category;
    }

    @JsonProperty("Club")
    public String getClub() {
        return club;
    }

    @JsonProperty("Locals")
    public String getLocal() {
        return local;
    }

    @JsonProperty("Finish")
    public String getFinish() {
        return finish;
    }
}
