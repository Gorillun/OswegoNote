/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;

/*
 Keith Fosmire
 CSC 241 Assignment 5
 Due Date 12/9/2013
 
 */
public class HashNode {
    private HashNode link;
    private HashNode prelink;
    private KeywordNode keywordIndex;
    
    private int[] citations =new int[100];
    private int id;
    private int numCitations=0;
    private String name;
    public HashNode(String name)
    {
        this.name=name;
        
        link=null;
        prelink =null;
    }
    public int[] getCitations()
    {
        int[] cits=new int[numCitations];
        for(int i=0;i<numCitations;++i)
        {
            cits[i]=citations[i];
        }
        return cits;
    }
    public void addCitation(int index)
    {   boolean exists=false;
        for(int i=0;i<numCitations;++i)
        {
            if(citations[i]==index)
                exists=true;
        }
        if(exists==false){
        citations[numCitations]=index;
        ++numCitations;}
    }
    
    public void addKeyword(KeywordNode inNode)
    {
        KeywordNode temp = inNode;
        temp.setLink(keywordIndex);
        keywordIndex=temp;
    }
    public void setLink(HashNode newNode)
    {
        this.link =newNode;
    }
    public String getName()
    {
        return name;
    }
    public HashNode getLink()
    {
        return this.link;
    }
    public void setPreLink(HashNode newNode)
    {
        this.prelink=newNode;
    }
    public HashNode getPreLink()
    {
        return this.prelink;
    }
    
}
