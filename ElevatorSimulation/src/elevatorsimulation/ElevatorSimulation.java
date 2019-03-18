package elevatorsimulation;
/*
    TODO:
        
        Elevator checks floor even when in wrong direction (most apparent when it is a single elevator)
            Does this since every person is added to an elevator when they spawn, they get assigned to different elevators if there more than one
    
    */
public class ElevatorSimulation
{  
    public static void main(String[] args)
    { 
        Building b = new Building(); // default settings, 10 floors and 2 elevators, 5% chance a person appears    
        //Building b = new Building(25, 3, 3);
        
        b.batchRun(1000000);
        
        while(true)
        {
            
            b.run();        
            System.out.println(b.toString());  
            
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.toString());
            }
        }
                    
    }
    
}
/*Example Output:
Slice: 468
24:[ ][ ](0,0)|                 |                |                |                |
23:[ ][ ](0,0)|                 |                |                |                |
22:[ ][V](0,1)|                 |                |                |                |
21:[ ][V](0,1)|                 |                |                |                |
20:[ ][V](0,1)|                 |                |                |                |
19:[ ][ ](0,0)|                 |                |                |                |
18:[ ][ ](0,0)|                 |                |                |                |
17:[ ][V](0,2)|                 |                |                |                |
16:[ ][ ](0,0)|                 |                |                |                |
15:[A][V](2,1)|                 |                |                |                |
14:[A][ ](1,0)|                 |                |                |                |
13:[ ][ ](0,0)|                 |                |                |                |
12:[ ][ ](0,0)|                 |                |                |                |
11:[ ][ ](0,0)|                 |                |                |                |
10:[A][ ](1,0)|                 |                |                |                |
09:[ ][ ](0,0)|                 |                |                |                |
08:[ ][ ](0,0)|                 |                |                |                |
07:[A][ ](1,0)|                 |                |                |                |
06:[ ][ ](0,0)|                 |                |                |                |
05:[ ][ ](0,0)|                 |                |#2:V[  ](3) CLOSING DOORS    |                |
04:[A][ ](4,0)| #0:V ][ (2) MOVING DOWN to 0    |                |                |#3:V ][ (1) OPENING DOORS    |
03:[A][ ](2,0)|                 |                |                |                |
02:[A][ ](3,0)|                 |                |                |                |
01:[A][ ](5,0)|                 |#1:V ][ (4) OPENING DOORS    |                |                |
00:[ ][ ](0,0)|                 |                |                |                |

Total passengers delivered: 178            Total time taken: 2680192ms
Last delivery time: 36970ms            Max delivery time: 38243ms
Average delivery time: 15.057 seconds




*/