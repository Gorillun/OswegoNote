/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;


public class Keyword 
{
    // declaring variables
    private String name;
    private int[] citationIDs= new int[100];
    private int numCitationIDs=0;
    //constructor creates new keywords
    public Keyword(String name, int ID)
    {
        
        this.name= name;
        citationIDs[0] = ID;
        ++numCitationIDs;
        
    }
    //adds IDs to existing keyword

    public void addID(int ID)
    {
        if(numCitationIDs>100)
        {System.out.println("You have exceeded the number of IDs allowed");}
        else
        {
            citationIDs[numCitationIDs]=ID;
            ++numCitationIDs;
        }
    }
    //changes IDs of existing keywords
    public void changeID(int oldID, int newID)
    {
        for(int x=0;x<numCitationIDs;++x)
        {
            if(citationIDs[x]==oldID)
            {
                citationIDs[x]=newID;
            }
        }
        
    }
    //returns array of keyword IDs
    public int[] getCitationsID()
    {
        return this.citationIDs;
    }
    public int getNumCitationIDs()
    {
        return this.numCitationIDs;
    }
    //returns name of keyword
   
    public String getName()
    {   
        return name;
    }
}
