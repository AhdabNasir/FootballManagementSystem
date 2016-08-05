/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballmanager;

import java.util.Date;

/**
 *
 * @author ahdabnasir
 */
public class Match {
    
    private FootballClub teamA;
    private FootballClub teamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;
    
    
    public FootballClub getTeamA() {
        return teamA;
    }
    
    public FootballClub getTeamB() {
        return teamB;
    }
    
    public int getTeamAScore(){
        return teamAScore;
    }
    
    public int getTeamBScore(){
        return teamBScore;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setTeamA(FootballClub teamA) {
        this.teamA = teamA;
    }
    
    public void setTeamB(FootballClub teamB) {
        this.teamB = teamB;
    }
    
    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }
    
    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
