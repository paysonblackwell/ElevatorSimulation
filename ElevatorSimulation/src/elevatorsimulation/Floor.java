package elevatorsimulation;

import elevatorsimulation.Building.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Floor
{
    //list of people per floor
    private List<Person> people;
    
    //change to up and down people list?
    
    
    private int floorNumber;
    private int maxFloorNumber;
    
    private int maxPeople = 3;
    private static Random rand;
    private int encounterChance;
    
    //add buttons that people will press
    private boolean upButton;
    private boolean downButton;
    
    
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
    
    public boolean hasPeopleInDirection(Direction d)
    {
        if(d == Direction.Up)
        {
            return upButton;
        }
        else
        {
            return downButton;
        }
    }
    
    public Person getOldestPerson(Direction d)
    {
        //get oldest person in direction
        int index = 0;
        Person old;
        do
        {
            if(index >= people.size())
            {
                return null;
            }
            old = people.get(index);
            index++;
        }while(old.getDirection() != d);
        
        for(Person p : people)
        {
            if(p.getStatistics() > old.getStatistics() && (p.getDirection() == d))
            {
                old = p;
            }
        }      
        return old;
    }
    
    public Person getOldestPerson()
    {
        //get oldest person
        Person old = people.get(0);
        
        for(Person p : people)
        {
            if(p.getStatistics() > old.getStatistics())
            {
                old = p;
            }
        }      
        return old;
    }
    
    public void removePerson(Person p)
    {
        people.remove(p);
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
                Person p = new Person(maxFloorNumber, floorNumber);
                pressButton(p);
                
                people.add(p);
            }
        }
        
        // for stat tracking
        for(Person p : people)
        {
            p.run();
        }
        
        
    }
    
    public void pressButton(Person p)
    {
        if(p.getDirection() == Direction.Up)
        {
            upButton = true;
        }else
        {
            downButton = true;
        } 
    }
    
    public boolean hasUpButtonBeenPressed()
    {
        return upButton;
    }
    
    public boolean hasDownButtonBeenPressed()
    {
        return downButton;
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
