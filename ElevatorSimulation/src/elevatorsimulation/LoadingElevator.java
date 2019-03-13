
package elevatorsimulation;

/**

 @author cutte
 */
public class LoadingElevator implements ElevatorState
{   
    private Elevator e;
    
    public LoadingElevator(Elevator ele)
    {
        e = ele;
    }
    
    @Override
    public void run()
    {
        //throw error if doors aren't open?
        
        //get people off of current floor and turn them into passengers
            //check capacity of elevator before a person can join
        
        
        
        
    }
    
    @Override
    public String toString()
    {
        return "Loading";
    }
    
}