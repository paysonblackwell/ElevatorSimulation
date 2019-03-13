package elevatorsimulation;


public class OpenElevator implements ElevatorState
{   
    private Elevator e;
    
    public OpenElevator(Elevator ele)
    {
        e = ele;
    }
    
    @Override
    public void run()
    {
        //System.out.println("Elevator is Open on floor: "+e.getCurrentFloor());
        e.openDoors();
        
        //set to unloading passengers
        //Looks to see if the floor pressed is in our internal list, if it is, then unload
        e.setcurrentState(Elevator.State.Loading);
        
        
        
    }
    
    @Override
    public String toString()
    {
        return "Open";
    }
    
}
