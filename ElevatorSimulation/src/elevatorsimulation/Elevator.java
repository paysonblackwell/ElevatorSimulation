package elevatorsimulation;

import elevatorsimulation.Building.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
    Real elevators has a list of floors that have been pressed, 
    continues going in same direction until there is no more stops in that direction


*/
public class Elevator
{
    public enum State{Open, Shut, Loading, Unloading}
    
    
    
    
    private Direction currentDirection;
    private Building theBuilding;
    private int currentFloor;
    private int currentDestination;    
    private int maxFloorNumber;
    private List<Person> passengers;
    private int carryingCapacity;
    private State currentState;
    private boolean[] theButtons;
    private State theDoor;
    private ElevatorState elevatorState;
    
    
    //keep track of: total delievered....  
    public Elevator(Building b, int maxFloors, int startingFloor)
    {
        theBuilding = b;
        maxFloorNumber = maxFloors;
        currentFloor = startingFloor;     
        passengers = new ArrayList<Person>();
        carryingCapacity = 5;
        currentDirection = Direction.Up;

        //Fill up buttons as false
        theButtons = new boolean[maxFloorNumber+1];
     
        //start being shut
        currentState = State.Shut;
        theDoor = State.Shut;
        elevatorState = new ShutElevator(this);
        
        //find direction for this elevator to go
        findNewDestination();
    }
    
    public Building getBuilding()
    {
        return theBuilding;
    }
    
    public void openDoor()
    {
        theDoor = State.Open;
    }
    public void shutDoor()
    {
        theDoor = State.Shut;
    }
    
    public void addPerson(Person p)
    {           
        
        //Have new person press the wanted floor button
        theButtons[p.getDestination()] = true;   
        
        checkDestination(p.getDestination());
        goToPassengerDestination();
                
        passengers.add(p);
    }
    
    public boolean canAddAnotherPerson()
    {     
        return (carryingCapacity - passengers.size()  >= 1);
    }
    
    public void checkDestination(int newPress)
    {
        //changes destination to the newPress if it was further along
        
        if(currentDirection == Direction.Up)
        {
            if(newPress >= currentDestination)
            {
                currentDestination = newPress;
            }  
        }
        else if(currentDirection == Direction.Down)
        {
            if(newPress <= currentDestination)
            {
                currentDestination = newPress;
            }  
        }  
    }
    
    
    
    public void findNewDestination()
    {
        theBuilding.findDestination(this);
        //change currentMovement
        
        if(currentFloor < currentDestination)
        {
            currentDirection = Direction.Up;
        }
        else
        {
            currentDirection = Direction.Down;
        }
    }
    public void addFloorDestination(int id)
    {
        theButtons[id] = true;
    }
    
    public void removeFloorDestination(int id)
    {
        theButtons[id] = false;
    }
    
    
    public void goToPassengerDestination()
    {
        //sets destination to the next passenger destination
        int furthestFloor = 0;
        for(int i = 0; i <theButtons.length; i++)
        {
            if(theButtons[i] == true)
            {
                if(Math.abs(currentFloor - i) > furthestFloor)
                {
                    furthestFloor = i;
                }
            }
        }       
        currentDestination = furthestFloor;
        if(currentFloor - currentDestination <= 0)
        {
            currentDirection = Direction.Up;
        }
        else
        {
            currentDirection = Direction.Down;
        }
    }
    
    public void moveInDirection()
    {
        if(currentDirection == Direction.Up)
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
    
    public void changeDirection(Direction d)
    {
        currentDirection = d;
    }
    
    public Direction getDirection()
    {
        return currentDirection;
    }
    
    public int getMaxFloor()
    {
        return maxFloorNumber;
    }
    
    //Let people off if it is their destination
    public void gettingOff()
    {
        
        for(int i = 0; i < passengers.size(); i++)
        {
            if(passengers.get(i).getDestination() == currentFloor)
            {
                //Move person to finished list       
                theBuilding.addDeliveredPerson(passengers.get(i));
                passengers.remove(passengers.get(i));     
                i--;
            }
        }
        
        theButtons[currentFloor] = false;
    }
    
    public boolean checkFloorButton(int id)
    {
        return theButtons[id];
    }
    
    public void setCurrentState(State s)
    {
        currentState = s;
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
        
        

        str += "-"+elevatorState.toString()+"-";

        for(Person p: passengers)
        {
            str+= p.toString();
        }
        
        //Look at stize of the string and make sure it is the same size...
        
        
        str += "}: D="+currentDestination;
        
        
        return str;
    }
    
    public void run()
    {
        
        //Should make the objects have static methods instead?           
        if(currentState == State.Open)
        {           
            elevatorState = new OpenElevator(this);      
        }
        else if(currentState == State.Shut)
        {
            elevatorState = new ShutElevator(this);      
        }
        else if(currentState == State.Loading)
        {
            elevatorState = new LoadingElevator(this);    
        }
        else if(currentState == State.Unloading)
        {
            elevatorState = new UnloadingElevator(this); 
        }
        
        elevatorState.run();
    }
}
