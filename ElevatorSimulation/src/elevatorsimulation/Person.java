package elevatorsimulation;

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
    
    
    
    
    public Person(int maxFloor, int currentFloor)
    {           
        //more likely to go to ground floor
        maxFloorNumber = maxFloor;
        this.currentFloor = currentFloor;
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
         
        //get random letter for name?
        name = "A";
        
        numberOfCycles = 0;
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
    
    //returns true for up, false if down
    public boolean getDirection()
    {
        if(currentFloor- floorWanted  > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    @Override
    public String toString()
    {
        return "["+name+"]=("+floorWanted+")";
    }
    
}
