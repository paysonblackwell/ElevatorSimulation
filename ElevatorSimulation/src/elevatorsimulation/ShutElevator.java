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
        
        // if true, we need to open and let people out/in
        if(e.checkFloorButton(e.getCurrentFloor()))
        {
            e.setCurrentState(Elevator.State.Open);
        }
        else
        {
            if(e.getCurrentFloor() == 0 || e.getCurrentFloor() == e.getMaxFloor()-1)
            {           
                e.findNewDestination();
            }

            
            e.moveInDirection();   
            
        }
        
        if(e.getCurrentFloor() == 0)
        {
            e.changeDirection(Building.Direction.Up);
        }
        
             
        
    }
    
    @Override
    public String toString()
    {
        return "Shut";
    }
    
}
