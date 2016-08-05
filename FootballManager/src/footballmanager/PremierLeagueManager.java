/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ahdabnasir
 */
public class PremierLeagueManager implements LeagueManager {
    
    private final int numberOfClubs;
    
    private final ArrayList<FootballClub> league;
    private final Scanner scanner;
    private final ArrayList<Match> matches;
    
    public PremierLeagueManager(int numberOfClubs) {
        
        this.numberOfClubs = numberOfClubs;
        league = new ArrayList<>();
        matches = new ArrayList<>();
        scanner = new Scanner(System.in);
        displayMenu();
    }
    
    
    private void displayMenu() {
        
        while(true) {
            System.out.println("Premier League menu: ");
            System.out.println("Create new team and add it to league (press 1)");
            System.out.println("Delete existing team from league (press 2)");
            System.out.println("Display Statistics for team (press 3)");
            System.out.println("Display the Premier League Table (press 4)");
            System.out.println("Add a Played Match (press 5)");
            System.out.println("Display Calendar and Find Match (press 6)");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
        }
            
            switch(command) {
                case 1 :
                   addTeam();
            break;
                case 2 :
                    deleteTeam();
                    break;
                case 3 :
                    displayStatistics();
                  break;
                case 4 :
                    displayLeagueTable();
                  break;
                case 5:
                    addPlayedMatch();
                   break;
                case 6:
                    displayCalendar();
                   break;
            default:
            System.out.println("Wrong Command");
        }
        
    } 
   }
    
    private void addTeam(){
        if(league.size() == numberOfClubs) {
            System.out.println("Can't add more clubs to league");
            return;
        }
        
        FootballClub club = new FootballClub();
        System.out.println("Insert Club Name: ");
        String line = scanner.nextLine();
        club.setName(line);
        
        if(league.contains(club)){
            System.out.println("This club is already in the league");
            return;
        }
        System.out.println("Insert Club Location: ");
        line = scanner.nextLine();
        club.setLocation(line);
        league.add(club);   
    }
    
    private void deleteTeam() {
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();
         for(FootballClub club : league) {
             if(club.getName().equals(line)){
                 league.remove(club);
                 System.out.println("Club "+ club.getName()+" removed");
                 return;
             }
         }
         System.out.println("No such club in league");
    }
    
    private void displayStatistics() {
        
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();
         for (FootballClub club : league) {
             if(club.getName().equals(line)){
                 System.out.println("Club " + club.getName()+ " matches won: " + club.getWinCount());
                 System.out.println("Club " + club.getName()+ " matches lost: " + club.getDefeatCount());
                 System.out.println("Club " + club.getName()+ " matches draw: " + club.getDrawCount());
                 System.out.println("Club " + club.getName()+ " scored goals: " + club.getScoredGoalsCount());
                 System.out.println("Club " + club.getName()+ " recieved goals: " + club.getReceivedGoalsCount());
                 System.out.println("Club " + club.getName()+ " points: " + club.getPoints());
                 System.out.println("Club " + club.getName()+ " matches played: " + club.getMatchesPlayed());
                 return;
             }
         }
         System.out.println("No such club in league");
    } 
    
    private void displayLeagueTable() {
        
        Collections.sort(league, new CustomComparator());
        for(FootballClub club : league) {
            System.out.println("Club: " + club.getName()+" Points: "+ club.getPoints()+" goal difference: "+ (club.getScoredGoalsCount()-club.getReceivedGoalsCount()));
    }    
  }
    
    private void addPlayedMatch(){
        System.out.println("Enter date (format mm-dd-yyyy): ");
        String line = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("MM-dd-yyyy").parse(line);
        } catch (ParseException ex) {
            System.out.println("You have to enter date in format mm-dd-yyyy");
            return;
        }
        System.out.println("Enter Home Team: ");
        line = scanner.nextLine();
        FootballClub home = null;
          for(FootballClub club : league){
              if(club.getName().equals(line))
                  home = club;
          }  
          if (home == null) {
              System.out.println("No such club in league");
              return;
          }
          System.out.println("Enter Away Team: ");
          line = scanner.nextLine();
          FootballClub away = null;
           for(FootballClub club : league){
              if(club.getName().equals(line))
                  away = club;
          } 
           if (away == null) {
              System.out.println("No such club in league");
              return;
          }
           
           System.out.println("Enter home team goals: ");
           line = scanner.nextLine();
           int homeGoals = -1;
             try {
                 homeGoals = Integer.parseInt(line);                
             } catch (Exception e) { 
    }
         if (homeGoals == -1) {
             System.out.println("You have to enter number of goals");
             return;
         }
         
         System.out.println("Enter away team goals: ");
           line = scanner.nextLine();
           int awayGoals = -1;
             try {
                 awayGoals = Integer.parseInt(line);                
             } catch (Exception e) { 
    }
         if (awayGoals == -1) {
             System.out.println("You have to enter number of goals");
             return;
         }
         
         
         Match match = new Match();
         match.setDate(date);
         match.setTeamA(home);
         match.setTeamB(away);
         match.setTeamAScore(awayGoals);
         match.setTeamBScore(homeGoals);
         matches.add(match);
         home.setScoredGoalsCount(home.getScoredGoalsCount()+homeGoals);
         away.setScoredGoalsCount(away.getScoredGoalsCount()+awayGoals);
         home.setRecievedGoalsCount(home.getReceivedGoalsCount()+awayGoals);
         away.setRecievedGoalsCount(away.getReceivedGoalsCount()+homeGoals);
         home.setMatchesPlayed(home.getMatchesPlayed()+1);
         away.setMatchesPlayed(away.getMatchesPlayed()+1);
         
         if (homeGoals > awayGoals) {            
             home.setPoints(home.getPoints()+3);
             home.setWinCount(home.getWinCount()+1);
             away.setDefeatCount(away.getDefeatCount()+1);
         }
         
         else if (homeGoals < awayGoals) {            
             away.setPoints(away.getPoints()+3);
             away.setWinCount(away.getWinCount()+1);
             home.setDefeatCount(home.getDefeatCount()+1);
         }
         else {
             home.setPoints(home.getPoints()+1);
             away.setPoints(away.getPoints()+1);
             home.setDrawCount(home.getDrawCount()+1);
             away.setDrawCount(away.getDrawCount()+1);
         }      
    } 
    
    private void displayCalendar() {
        
        System.out.println("Enter year: ");
        String line = scanner.nextLine();
         int Y = -7777;
           try {
               Y = Integer.parseInt(line);
           } catch (Exception e) { 
    }
         if (Y == -7777) {
             System.out.println("You have to enter a year");
             return;
         }
    
          System.out.println("Enter Month: ");
          line = scanner.nextLine();
          int M = 0;
           try {
               M = Integer.parseInt(line);
           } catch (Exception e) { 
    }
         if (M == 0) {
             System.out.println("You have to enter a month");
             return;
         }
         
         String[] months = {
            "",
             "January", "February", "March",
             "April", "May", "June",
             "July", "August", "September",
             "October", "November", "December"  
         };
         
         int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
         
         if (M == 2 && isLeapYear(Y)) days[M] = 29;
         
         System.out.println("    " + months[M] + " " + Y);
         System.out.println("S  M  Tu  W  Th  F  S");
         
         int d = day(M, 1, Y);
         String space = "";
         
         for (int i = 0; i < d; i++)
             System.out.print("   ");
         for (int i = 1; i <= days[M]; i++) {
             if (i<10)
                 System.out.print(i +"  ");
             else 
                 System.out.print(i+" ");
             if (((i + d) % 7 == 0) || (i == days[M])) System.out.println();
         }
         
         System.out.println("Enter day: ");
         line = scanner.nextLine();
         int D = 0;
           try {
               D= Integer.parseInt(line);
           }  catch (Exception e) {             
           }
       if (D == 0 || days[M] < D) {
           System.out.println("You have t enter day in month");
           return;
       }  
       
       Calendar cal = Calendar.getInstance();
       cal.set(Y, M-1, D);
       for (Match m : matches) {
           Calendar cal2 = Calendar.getInstance();
           cal2.setTime(m.getDate());
            if (cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) || cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                System.out.println(m.getTeamA().getName()+ " "+m.getTeamAScore() + " : "+ m.getTeamBScore()+ " "+ m.getTeamB().getName());
            }
       }   
    } 
    
    public int day(int M, int D, int Y) {
        int y = Y - (14 - M) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = M + 12 * ((14-M) / 12) - 2;
        int d = (D + x + (31*m)/12) % 7;
        return d;
    }
    
    public boolean isLeapYear(int year) {
        
        if ((year % 4 ==0) && (year % 100 !=0 )) return true;
        if (year % 400 == 0) return true;
        return false;  
    }
} 
    
    
    
    

