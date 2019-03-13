package elevatorsimulation;

/**

 @author cutte
 */
public class UnloadingElevator implements ElevatorState
{   
    private Elevator e;
    
    public UnloadingElevator(Elevator ele)
    {
        e = ele;
    }
    
    @Override
    public void run()
    {
        //throw error if doors aren't open?
        
        //check for people to load? //real elevators load everytime...
            //close if not?
                  
        
    }
    
    @Override
    public String toString()
    {
        return "Unloading";
    }
    
}