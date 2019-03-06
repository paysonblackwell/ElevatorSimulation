package elevatorsimulation;

import java.util.List;

public class Elevator
{
    public enum State{Open, Shut}
    
    private int currentFloor;
    private int currentDestination;    
    private int maxFloorNumber;
    private List<Person> passengers;
    private State currentState;
    //Buttons?
    //
    
    
    public Elevator(int maxFloors, int startingFloor)
    {
        maxFloorNumber = maxFloors;
        currentFloor = startingFloor;     
        //get destination
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }
    
    public void run()
    {
        if(currentState == State.Open)
        {
            //check for people, then shut the door
            
            
        }
        else if(currentState == State.Shut)
        {
            // Keep moving towards destination until. Maybe check if someone pressed a button
            
            
        }
    }
}
