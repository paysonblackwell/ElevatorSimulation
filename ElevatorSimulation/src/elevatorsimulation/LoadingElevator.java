
package elevatorsimulation;

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
        Building b = e.getBuilding();
        
        //get direction we are headed
        Building.Direction d = e.getDirection();
        
        //Getting floor
        Floor f = b.getFloor(e.getCurrentFloor());
        
        //if we are on destination floor, direction doesn't matter!
        boolean doesDirMatter = false;//!(e.getCurrentDestination() == e.getCurrentFloor());      
        
        if(e.hasPassengers())
        {
            while(f.hasPeopleInDirection(e.getDirection()) && e.canAddAnotherPerson())
            {
                Person p = f.getOldestPerson(e.getDirection());
                if(p == null)
                {
                    break;
                }
                e.addPerson(p);
                f.removePerson(p);
            }
        }
        else
        {
            while(f.hasPeople() && e.canAddAnotherPerson())
            {
                Person p = f.getOldestPerson();
                if(p == null)
                {
                    break;
                }               
                e.addPerson(p);
                f.removePerson(p);
            }
        }
        
        
        
        /*
        if((p.getDirection() != currentDirection) && (currentFloor != currentDestination))
        {
            System.out.println("ERROR, Person got on wrong elevator!");
        }    
        */
        
        //ADD BACK IN!!!
        /*       
        // Get the person waiting the longest if direction doesn't matter
        if(doesDirMatter == false)
        {
            if(f.hasPeople() && e.canAddAnotherPerson())
            {
                Person p = f.getOldestPerson();
                e.addPerson(p);
                f.removePerson(p);
            }
        }
        
        //if not full, add people going in the same direction
        while(f.hasPeopleInDirection(d) && e.canAddAnotherPerson())
        {
            Person p = f.getOldestPerson(d);
            e.addPerson(p);
            f.removePerson(p);
        } 
        */
        
        e.shutDoor();
        
        //done loading people if there were any
        e.setCurrentState(Elevator.State.Shut);
        
        //find new destination if we didn't pick anyone up and on destination floor
        if(e.getCurrentDestination() == e.getCurrentFloor())
        {
            //replace...
            e.findNewDestination();
        }    
        
        //remove current floor from directions
        e.removeFloorDestination(e.getCurrentFloor());
        
    }
    
    @Override
    public String toString()
    {
        return "Loading";
    }
    
}