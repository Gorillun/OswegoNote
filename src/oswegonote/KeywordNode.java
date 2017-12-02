/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;

/**
 *
 * @author keith
 */
public class KeywordNode 
{
    IndexNode citations;
    private KeywordNode link;
    private Keyword keyword;
    
    
    public KeywordNode(Keyword newKeyword)
    {
        keyword=newKeyword;
        link=null;
    }
    public Keyword getKeyword()
    {
        return keyword;
    }
    public void setKeyword(Keyword newKeyword)
    {
        this.keyword= newKeyword;
    }
    public void setLink(KeywordNode newNode)
    {
        this.link =newNode;
    }
    public String getName()
    {
        if(keyword==null)
        {return "";}
        return keyword.getName();
    }
    public KeywordNode getLink()
    {
        return this.link;
        
    }
   public void setCitationLink(IndexNode inLink)
   {
       inLink.setLink(citations);
       citations=inLink;
   }
   public IndexNode getCitationLink()
   {
       return citations;
   }
    public int[] getIds()
    {
        int[]i=new int[0];
        
        if(keyword==null)
        { return  i ;}
        return keyword.getCitationsID();
    }
    public int getNumId()
    {
       return keyword.getNumCitationIDs();
    }
}
