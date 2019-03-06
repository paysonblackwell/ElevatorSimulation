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
        System.out.println("Elevator is Open on floor: "+e.getCurrentFloor());
    }
    
    @Override
    public String toString()
    {
        return "Open";
    }
    
}
