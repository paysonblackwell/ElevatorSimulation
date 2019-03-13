package elevatorsimulation;

import java.util.ArrayList;
import java.util.List;

/*
    Made up of floors and elevators
*/
public class Building
{
    private int floorCount;
    private int elevatorCount;
    private int peopleChance;
    private int slice;
    
    private List<Floor> floors;
    private List<Elevator> elevators;
    
    public Building()
    {
        this(10,2,5); // default settings, 10 floors and 2 elevators, 5% chance a person appears      
    }
    
    public Building(int f)
    {
        this(f,2,5); // default settings, 10 floors and 2 elevators, 5% chance a person appears      
    }
    
    public Building(int f, int e)
    {
        this(f,e,5); // default settings, 10 floors and 2 elevators, 5% chance a person appears      
    }
    
    public Building(int f, int e, int c)
    {
        floorCount = f;
        elevatorCount = e;
        peopleChance = c;
        
        slice = 0;
        
        
        floors = new ArrayList<Floor>();
        for(int i = 0; i < floorCount; i++)
        {
            floors.add(new Floor(floorCount, i,peopleChance));
        }
        
        elevators = new ArrayList<Elevator>();
        for(int i = 0; i < elevatorCount; i++)
        {
            elevators.add(new Elevator(this, floorCount,0));
        }      
    }
    
    
    //have another method called addDestination which takes the new floors that were pressed and adds them to a elevators destination list
    public void findDestination(Elevator e)
    {
        
        //first make sure to go to floors where passengers are
        if(e.hasPassengers())
        {
            e.goToPassengerDestination();
            //return;
        }
        
        
        //The rest of this only applies to elevators who have no current passengers     
        //find needed floors
        List<Floor> neededFloors = new ArrayList<Floor>();
        
        for(Floor f: floors)
        {
            if(f.hasPeople())
            {
                neededFloors.add(f);
            }
        }
        
        List<Floor> neededFloors2 = new ArrayList<Floor>();
        
        //get rid of floors that already have an elevator assigned to it  
        for(Floor f: neededFloors)
        {
            for(Elevator ele: elevators)
            {
                     //change current destination to a list of floors to check through
                    if(ele.getCurrentDestination() != f.getFloorNumber())
                    {
                        neededFloors2.add(f);
                    }                
            }
        }
        
        
        //update list with floors that aren't destinations
        neededFloors = neededFloors2;
        
        if(neededFloors.isEmpty())
        {
            // go to lobby when no button is waiting
            e.setCurrentDestination(0);
        }
        else
        {
            Floor closest = neededFloors.get(0);
            int distance =Math.abs(e.getCurrentFloor() - neededFloors.get(0).getFloorNumber());
            
            //pick the closest floor that needs an elevator
            for(int i = 1; i < neededFloors.size(); i++)
            {
                int currentDistance = Math.abs(e.getCurrentFloor() - neededFloors.get(i).getFloorNumber());
                if(currentDistance < distance)
                {
                    closest = neededFloors.get(i);
                    distance = currentDistance;
                }
            }
            
            e.setCurrentDestination(closest.getFloorNumber());          
        } 
        
    }
    
    public void run()
    {
        slice++;
        
        //Call run on floors & elevators
        for(int i = 0; i < floorCount; i++)
        {
            floors.get(i).run();
        }
        
        
        for(int i = 0; i < elevatorCount; i++)
        {
            elevators.get(i).run();
        }  
        
        
    }
    
    @Override
    public String toString()
    {
        String s = "";
        s+="Slice: "+Integer.toString(slice)+"\n";
        for(int i = floorCount - 1; i >= 0 ; i--)
        {
            s += "|";
            s += floors.get(i).toString();
            s += "|";
            
            s+= "--  ";
            
            //call toString on Elevators
            for(Elevator e: elevators)
            {
                s+= "|";
                if(e.getCurrentFloor() == i)
                {
                    s += e.toString();
                }
                else
                {
                    s+= "- - - - - - - -";
                }
                s+= "|";
            }
            s+= "  --";
            
            s += "||\n";
        }      
        
        return s;
    }
    
    
    
}
