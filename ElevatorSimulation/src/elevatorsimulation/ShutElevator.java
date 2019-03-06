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
        System.out.println("Elevator is Shut on floor: "+e.getCurrentFloor());
    }
    
    @Override
    public String toString()
    {
        return "Shut";
    }
    
}
