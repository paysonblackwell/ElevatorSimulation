package elevatorsimulation;

import java.util.ArrayList;
import java.util.List;

/*
    Made up of floors and elevators
*/
public class Building
{
    public enum Direction{Up, Down};
    private int floorCount;
    private int elevatorCount;
    private int peopleChance;
    private int slice;
    
    private List<Floor> floors;
    private List<Elevator> elevators;
    private List<Person> deliveredPeople;
    
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
        
        //to store people who are finished
        deliveredPeople = new ArrayList<Person>();
    }
    
    
    public void addDeliveredPerson(Person p)
    {
        deliveredPeople.add(p);
    }
    
    public Floor getFloor(int floorNum)
    {
        return floors.get(floorNum);
    }
    
    
    //have another method called addDestination which takes the new floors that were pressed and adds them to a elevators destination list
    public void findDestination(Elevator e)
    {
        
        //first make sure to go to floors where passengers are
        if(e.hasPassengers())
        {
            e.goToPassengerDestination();
            return;
        }
        
        
        //The rest of this only applies to elevators who have no current passengers     
        
        //find needed floors
        List<Floor> neededFloors = getNeededFloors();
        
        
        if(neededFloors.isEmpty())
        {
            // go to lobby when no button is waiting
            e.setCurrentDestination(5);
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
            e.addFloorDestination(closest.getFloorNumber());
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
        
        //Add new people that appear to a specific elevator
        assignElevators();
        
        for(int i = 0; i < elevatorCount; i++)
        {
            //findDestination(elevators.get(i));
            elevators.get(i).run();
            
        }  
        
        
        
        
    }
    
    public void batchRun(int amount)
    {
        for(int i = 0; i<amount; i++)
        {
            this.run();
        }
    }
    
    public void assignElevators()
    {
        
        List<Floor> neededFloors = getNeededFloors();
        
        
        for(Floor f : neededFloors)
        { 
            Elevator closestElevator = null;
            //how to add a floor when all elevators are going in the opposite direction
            for(Elevator e: elevators)
            {
                if(e.getCurrentFloor() < f.getFloorNumber() && e.getDirection() == Direction.Up)
                {
                    if(closestElevator == null)
                    {
                        closestElevator = e;
                    }
                    else
                    {
                        if(closestElevator.getCurrentFloor() < e.getCurrentFloor())
                        {
                            closestElevator = e;
                        }
                    }
                }
                else if (e.getCurrentFloor() > f.getFloorNumber() && e.getDirection() == Direction.Down)
                {
                    if(closestElevator == null)
                    {
                        closestElevator = e;
                    }
                    else
                    {
                        if(closestElevator.getCurrentFloor() > e.getCurrentFloor())
                        {
                            closestElevator = e;
                        }
                    }
                }
            }
            
            if(closestElevator != null)
            {
                closestElevator.addFloorDestination(f.getFloorNumber());
                closestElevator.goToPassengerDestination();
            }           
        } 
    }
    
    public List<Floor> getNeededFloors()
    {
        
        //find all floors with people in them
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
            boolean hasFloor = false;
            for(Elevator ele: elevators)
            {
                     //change current destination to a list of floors to check through
                    if(ele.checkFloorButton(f.getFloorNumber()))
                    {
                        hasFloor = true;                      
                    }                
            }
            
            if(hasFloor == false)
            {
                neededFloors2.add(f);
            }
        }
        
        return neededFloors2;
    }
    
    
    public int averageTime()
    {
        int avg = 0;
        
        for(Person p: deliveredPeople)
        {
            avg += p.getStatistics();
        }
        if(deliveredPeople.size() != 0)
        {
            avg /= deliveredPeople.size();
        }
        
        return avg;
    }
    
    public int minTime()
    {
        int min = 0;
        
        for(int i = 0; i < deliveredPeople.size(); i++)
        {
            int current = deliveredPeople.get(i).getStatistics();
            if(i == 0)
            {
                min = current;
                continue;
            }
            if(current < min)
            {
                min = current;
            }
        }
        
        return min;
    }
    public int maxTime()
    {
        int max = 0;
        
        for(int i = 0; i < deliveredPeople.size(); i++)
        {
            int current = deliveredPeople.get(i).getStatistics();
            if(i == 0)
            {
                max = current;
                continue;
            }
            if(current > max)
            {
                max = current;
            }
        }
        
        return max;
    }
    
    
    public String getStatistics()
    {
        String s = "";
        
        s += "Total People Delivered: "+deliveredPeople.size() + "\t";
        s += "Average Waiting: "+averageTime()+"\n";
        
        s+= "Max Waited: "+maxTime()+"\t\t\t";
        s+= "Min Waited: "+minTime()+"\t";
        
        return s;
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
        
        s+= "\n"+getStatistics();
        
        s+= "\n";
        
        return s;
    }
    
    
    
}
