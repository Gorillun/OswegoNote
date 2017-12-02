/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;

/**
 *
 * @author keith
 */
public class IndexNode 
{
    private IndexNode link;
    private IndexNode prelink;
    private Citation citation;
    private String name;
    public IndexNode(Citation newCitation)
    {
        citation=newCitation;
        
        link=null;
        prelink =null;
    }
    public Citation getCitation()
    {
        return citation;
    }
    public void setCitation(Citation newCitation)
    {
        this.citation = newCitation;
    }
    public void setLink(IndexNode newNode)
    {
        this.link =newNode;
    }
    public String getName()
    {
        if(citation==null)
        {return "";}
        else
        return citation.getName();
    }
    public IndexNode getLink()
    {
        return this.link;
    }
    public void setPreLink(IndexNode newNode)
    {
        this.prelink=newNode;
    }
    public IndexNode getPreLink()
    {
        return this.prelink;
    }
}
