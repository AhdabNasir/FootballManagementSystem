/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballmanager;

import java.util.Comparator;

/**
 *
 * @author ahdabnasir
 */
public class CustomComparator implements Comparator<FootballClub> {
    
    @Override
    public int compare(FootballClub t, FootballClub t1) {
        
        if(t.getPoints() > t1.getPoints()) 
            return -1;
        else 
            if (t.getPoints() < t1.getPoints())
                return 1;
            else {
                int goalDif = t.getScoredGoalsCount()-t.getReceivedGoalsCount();
                int goalDif1 = t1.getScoredGoalsCount()-t1.getReceivedGoalsCount();
                if(goalDif > goalDif1)
                    return -1;
                else
                    if(goalDif < goalDif1)
                        return 1;
                    else return 0;
            }
        
    }
}
