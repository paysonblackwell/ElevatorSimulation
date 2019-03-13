package elevatorsimulation;

import java.util.ArrayList;
import java.util.List;
/*
    Real elevators has a list of floors that have been pressed, 
    continues going in same direction until there is no more stops in that direction


*/
public class Elevator
{
    public enum State{Open, Shut, Loading, Unloading}
    
    private Building theBuilding;
    private int currentFloor;
    private int currentDestination;    
    private int maxFloorNumber;
    private List<Person> passengers;
    private State currentState;
    private List<Integer> destinations;
    
    private boolean buttonPressed;
    private boolean doorPosition; //true == open, false == closed
    
    //keep track of: total delievered....
    
    public Elevator(Building b, int maxFloors, int startingFloor)
    {
        theBuilding = b;
        maxFloorNumber = maxFloors;
        currentFloor = startingFloor;     
        passengers = new ArrayList<Person>();
        destinations = new ArrayList<Integer>();
        
        
        //replace with list of floors whose buttons have been pressed
        buttonPressed = false;
        //get destination
        
        
        
        //start being shut
        currentState = State.Shut;
        
        //find direction for this elevator to go
        theBuilding.findDestination(this);
        
            
        
    }
    
    public void addPerson(Person p)
    {
        passengers.add(p);
    }
    
    public void findNewDestination()
    {
        theBuilding.findDestination(this);
    }
    
    
    public void goToPassengerDestination()
    {
        //sets destination to the next passenger destination
        
        
    }
    
    public void moveInDirection(boolean dir)
    {
        if(dir)
        {
            currentFloor++;
        }
        else
        {
            currentFloor--;
        }
    }
    
    public boolean hasPassengers()
    {
        return !passengers.isEmpty();
    }
    
    public int getCurrentDestination()
    {
        return currentDestination;
    }
    
    public void setCurrentDestination(int d)
    {
        currentDestination = d;
    }
    
    public void gettingOff()
    {
        
        for(int i = 0; i < passengers.size(); i++)
        {
            if(passengers.get(i).getDestination() == currentFloor)
            {
                //Move person?
                
            }
        }
    }
    
    public void setcurrentState(State s)
    {
        currentState = s;
    }
    
    public void openDoors()
    {
        doorPosition = true;
    }
    
    public void shutDoors()
    {
        doorPosition = false;
    }
            

    public int getCurrentFloor()
    {
        return currentFloor;
    }
    
    
    @Override
    public String toString()
    {
        String str = "";
        
        str += "{";
        
        
        
        if(passengers.isEmpty())
        {
            str += "-------------";
        }
        else
        {
            for(Person p: passengers)
            {
                str+="["+p.getName()+"]";
            }
        }
        //Look at stize of the string and make sure it is the same size...
        
        
        str += "}";
        
        
        return str;
    }
    
    public void run()
    {
        
        //Should make the objects have static methods instead?     
        
        if(currentState == State.Open)
        {
            ShutElevator op = new ShutElevator(this);
            op.run();         
        }
        else if(currentState == State.Shut)
        {
            ShutElevator sh = new ShutElevator(this);
            sh.run();       
        }
        else if(currentState == State.Loading)
        {
            LoadingElevator lo = new LoadingElevator(this);
            lo.run();      
        }
        else if(currentState == State.Unloading)
        {
            UnloadingElevator un = new UnloadingElevator(this);
            un.run();    
        }
    }
}
