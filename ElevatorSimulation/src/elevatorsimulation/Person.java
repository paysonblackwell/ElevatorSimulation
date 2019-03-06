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
    
    
    public Person(int maxFloor, int currentFloor)
    {       
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
        }while(floorWanted == currentFloor);
        
        //get random letter for name?
        name = "A";
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
