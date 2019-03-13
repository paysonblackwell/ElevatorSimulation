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
       // System.out.println("Elevator is Shut on floor: "+e.getCurrentFloor());
        
        //Move to current goal
        if(e.getCurrentFloor() == e.getCurrentDestination())
        {
            //change state to open
            //e.setcurrentState(Elevator.State.Open);
            e.findNewDestination();
        }
        else if(e.getCurrentDestination() > e.getCurrentFloor())
        {
            //going up
            e.moveInDirection(true);
        }
        else
        {
            //doing down
            e.moveInDirection(false);
        }
        
    }
    
    @Override
    public String toString()
    {
        return "Shut";
    }
    
}
