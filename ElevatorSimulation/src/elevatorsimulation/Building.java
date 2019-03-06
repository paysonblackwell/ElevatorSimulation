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
    
    private List<Floor> floors;
    private List<Elevator> elevators;
    
    public Building()
    {
        this(10,2,10); // default settings, 10 floors and 2 elevators      
    }
    
    public Building(int f, int e, int c)
    {
        floorCount = f;
        elevatorCount = e;
        peopleChance = c;
        
        
        floors = new ArrayList<Floor>();
        for(int i = 0; i < floorCount; i++)
        {
            floors.add(new Floor(floorCount, i,peopleChance));
        }
        
        elevators = new ArrayList<Elevator>();
        for(int i = 0; i < floorCount; i++)
        {
            elevators.add(new Elevator(floorCount,0));
        }      
    }
    
    public void run()
    {
        //Call run on floors & elevators
        for(int i = 0; i < floorCount; i++)
        {
            floors.get(i).run();
        }
        
        
        for(int i = 0; i < floorCount; i++)
        {
            elevators.get(i).run();
        }  
        
        
    }
    
    @Override
    public String toString()
    {
        String s = "";
        
        for(int i = floorCount - 1; i >= 0 ; i--)
        {
            s += "|";
            s += floors.get(i).toString();
            s += "|";
            
            //call toString on Elevators
            
            s += "\n";
        }      
        
        return s;
    }
    
    
    
}
