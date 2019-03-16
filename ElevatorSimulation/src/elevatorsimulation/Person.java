package elevatorsimulation;

import elevatorsimulation.Building.Direction;
import java.util.Random;

/*
    A person wants to go to a certain floor

    Should a person have a name?
*/
public class Person
{
    private int floorWanted; 
    private int currentFloor;    
    private int maxFloorNumber;
    private String name;
    
    private static Random rand;
    //Keep track of statistics
    private int numberOfCycles;
    
    //wanted direction
    Direction dir;
    
    public Person(int maxFloor, int cFloor)
    {           
        //more likely to go to ground floor
        maxFloorNumber = maxFloor;
        this.currentFloor = cFloor;
        if(rand == null)
        {
            rand = new Random();
        }
        
        //Make sure destination isn't the floor we are on
        do
        {
            floorWanted = rand.nextInt((maxFloor));
            
            //switch floorWanted for ground floor
            if(rand.nextInt(10) > 3)
            {
                floorWanted = 0;
            }
            
        }while(floorWanted == currentFloor);
         
        
        //set up the direction wanted
        if(floorWanted > currentFloor)
        {
            dir = Building.Direction.Up;
        }
        else
        {
            dir = Building.Direction.Down;
        }      
        
        //get random letter for name?
        name = "A";       
        numberOfCycles = 0;
    }  
    
    public Direction getDirection()
    {
        return dir;
    }
    
    public void run()
    {
        numberOfCycles++;
    }
    
    public String getName()
    {
        return name;
    }
    
    
    public void setFloor(int f)
    {
        currentFloor = f;
    }
    public int getDestination()
    {
        return floorWanted;
    }
    
    @Override
    public String toString()
    {
        return "["+name+"]=("+floorWanted+")";
    }
    
    public int getStatistics()
    {
        return numberOfCycles;
    } 
}
