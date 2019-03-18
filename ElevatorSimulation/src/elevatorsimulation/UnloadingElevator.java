package elevatorsimulation;

import elevatorsimulation.Building.Direction;

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
        
        
        //get people off at current floor
        e.gettingOff();
          
        //real elevators load everytime... maybe don't call loading everytime?
        e.setCurrentState(Elevator.State.Loading);
        
    }
    
    @Override
    public String toString()
    {
        return "Unloading";
    }
    
}