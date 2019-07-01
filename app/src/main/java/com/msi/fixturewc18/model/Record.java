
package com.hussain.fixturewc18.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    public void setLanguage(String language) {
        this.language = language;
    }

    public Record(String language) {
        super();
        this.language = language;
    }

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("teamA")
    @Expose
    private String teamA;
    @SerializedName("teamB")
    @Expose
    private String teamB;
    @SerializedName("matchPlay")
    @Expose
    private String  matchPlay;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("vanue")
    @Expose
    private String vanue;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("winner")
    @Expose
    private String winner;

    public Record() {
    }

    public Record(String id, String teamA, String teamB,String matchPlay, String group, String vanue, String result, String winner) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.matchPlay = matchPlay;
        this.group = group;
        this.vanue = vanue;
        this.result = result;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String  getMatchPlay() {
        return matchPlay;
    }

    public void setMatchPlay(String matchPlay) {
        this.matchPlay = matchPlay;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVanue() {
        return vanue;
    }

    public void setVanue(String vanue) {
        this.vanue = vanue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
