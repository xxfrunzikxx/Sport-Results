package com.xxfrunzikxx.sportresults;

/**
 * Created with IntelliJ IDEA.
 * User: Grigoriy Petrov (Frunzik)
 * Date: 2013, 10
 * Time: 11:22
 */
public class Match {
    String sport;
    String category;
    String tournament;
    String teamOne;
    String teamTwo;
    String score;

    public Match(String sport, String category, String tournament, String teamOne, String teamTwo, String score) {
        this.sport = sport;
        this.category = category;
        this.tournament = tournament;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.score = score;
    }

    @Override
    public String toString() {
        //                System.out.println(i + ": " + sportName + " | " + categoryName + " | " + tournamentName + " | " + team1Name + " | " + team2Name + " | " + score);

        return "Match{" +
                sport + " | " +
                category + " | " +
                tournament + " | " +
                teamOne + " | " +
                teamTwo + " | " +
                score +
                '}';
    }
}
