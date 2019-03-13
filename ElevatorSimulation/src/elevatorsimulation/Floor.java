package elevatorsimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Floor
{
    //list of people per floor
    private List<Person> people;
    private int floorNumber;
    private int maxFloorNumber;
    
    private int maxPeople = 2;
    private static Random rand;
    private int encounterChance;
    
    public Floor(int maxFloor, int floorNum, int chance)
    {
        maxFloorNumber = maxFloor;
        floorNumber = floorNum;
        encounterChance = chance; //encounterChance: a number 1 - 100 showing the likely hood of how often someone wants to change floors
        
        people = new ArrayList<Person>();
    }
    
    public boolean hasPeople()
    {
        return !people.isEmpty();
    }
    
    public int getFloorNumber()
    {
        return floorNumber;
    }
    
    public void run()
    {
        //If we aren't full on people, see if a person will show up
        
        if(people.size() <= maxPeople)
        {
            //Chance for a person to show up
            if(rand == null)
            {
                rand = new Random();
            }

            
            
            //more likely to appear if the floor is the ground floor
            int chance = rand.nextInt(300) + 1;       
            
            if(floorNumber == 0)
            {
                chance = rand.nextInt(50) + 1;     
            }
            
            if(chance <= encounterChance)
            {
                people.add(new Person(maxFloorNumber, floorNumber));
            }
        }
        
    }
    
    @Override
    public String toString()
    {
        String s = "|";
        
        s+= floorNumber +":";
        
        for(int p = 0; p < maxPeople; p++)
        {
            if(people.size() <= p)
            {
                s+= "[ ]=( )";
            }
            else
            {
                s+=people.get(p).toString();
            }
            
        }
        
        s+= "|";
        
        return s;
    }
}
