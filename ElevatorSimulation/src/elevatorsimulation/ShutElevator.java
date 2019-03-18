package elevatorsimulation;


public class ShutElevator implements ElevatorState
{
    private Elevator e;
       
    public ShutElevator(Elevator ele)
    {
        e = ele;
    }
      
    @Override
    public void run()
    {
        // if true, we need to open and let people out/in
        if(e.checkFloorButton(e.getCurrentFloor()))
        {
            e.setCurrentState(Elevator.State.Open);        
        }
        else
        {
            // if we hit the top/bottom, make sure we have a new destination
            if(e.getCurrentFloor() == 0 || e.getCurrentFloor() == e.getMaxFloor()-1)
            {           
                e.findNewDestination();
            }

            //just in-case, shouldn't be necessary if everything works
            if(e.getCurrentFloor() == 0)
            {
                e.changeDirection(Building.Direction.Up);
            }
            
            e.moveInDirection();   
            
        } 
        
    }
    
    @Override
    public String toString()
    {
        return "Shut";
    }
    
}
