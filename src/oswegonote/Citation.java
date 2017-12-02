/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;

/*
 Keith Fosmire
 CSC 241 Assignment 3
 Due Date 10/14/2013
 
 */
public class Citation 
{
    //declaring all variables as private
    private int ID, numKeywords,indexNum;
    private int[] keywordIndex=new int[250];
    private String name;
    private String[] authors =new String[3];
    private Keyword[] keywords =new Keyword[5];
    
    //contructor intitializes
    public Citation(String name)
    {
        this.ID = -1;
        this.name = name;
    }
    //sets new ID for keyword and citation
    public void updateID(int ID)
    {   int old=this.ID;
        this.ID = ID;
        for(int k=0;k<numKeywords;++k)
        {
            keywords[k].changeID(old, ID);
            
        }
       
    }
    //returns ID
    public int getID()
    {
        return ID;
    }
    //sets name
    public void setName(String name)
    {
        this.name = name;
    }
    public void setKeywordIndex(int index)
    {
        
        keywordIndex[indexNum]=index;
        indexNum++;
    }
    public int[] getKeywordIndex()
    {
        return keywordIndex;
    }
    //returns name
    public String getName()
    {
        return this.name;
    }
    //returns date
    //sets author
    public void setAuthor(String[] authors, int numAuthor)
    {
        for(int a=0;a<numAuthor;++a)
        {
            this.authors[a]=authors[a];
        } 
    }
    //returns author names
    public String[] getAuthor()
    {

        return authors;
    }
    //creates keyword objects
    public void setKeywords(String[] keywords, int numKeywords)
    {
        this.numKeywords=numKeywords;
        for(int k=0;k<numKeywords;++k)
        {
            String word= keywords[k];
            this.keywords[k]=new Keyword(word, ID);
        }    
    }
    public int getNumberOfKeywords()
    {
        return numKeywords;
    }
    public Keyword[] getKeywords()
    {
        return keywords;

    }
    //checks num of authors and adds if possible
    public void addAuthor(String author)
    {
        int aCount = 0;
        for(int s=0;s<authors.length;++s)
           {
               if(authors[s]!= null)
               {++aCount;}
           }
         switch(aCount)
         {
             case 1:
                 this.authors[1]=author;
                 break;
             case 2:
                 this.authors[2]=author;
                 break;
             case 3:
                 System.out.println("You have the maximum number of authors(3)!");
                 break;
                 
         }
    }
    //checks num of keywords and adds if possible
    public void addKeyword(String keyword)
    {
        int kCount = 0;
        for(int s=0;s<keywords.length;++s)
           {
               if(keywords[s]!= null)
               {++kCount;}
           }
         switch(kCount)
         {
             case 1:
                 this.keywords[1]=new Keyword(keyword,ID);
                 break;
             case 2:
                 this.keywords[2]=new Keyword(keyword,ID);
                 break;
             case 3:
                 this.keywords[3]=new Keyword(keyword,ID);
                 break;
             case 4:
                  this.keywords[4]=new Keyword(keyword,ID);
                 break;
             case 5:
                  System.out.println("You have reached the maximum of 5 keywords");
                 break;
         }
    }
              public  String formatIEEE() 
    {   

            String formatedID;
            String formatedTitle;
            String formatedPublisher;
            String formatedAuthors=null;
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<authors.length;++s)
           {
               if(authors[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=authors[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+", "; 
                   break;
               case 2:
                    ath=authors[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+" and ";
                    ath=authors[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                   break;
               case 3:
                   ath=authors[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                    ath=authors[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+"and ";
                    ath=authors[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                   break; 
           }
           //formating and printing variables
            formatedID ="["+ID+"]";
            formatedTitle="\""+name+",\"";
            formatedPublisher=" Unpublished.<br style=\"line-height:1px;\" />"; 
            outputFormat=formatedID+formatedAuthors+formatedTitle+formatedPublisher;
            return outputFormat;

        } 
            
          

   
    public String formatACM()
    {
 
            String formatedTitle;
            String formatedPublisher;
            String formatedAuthors=null;
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<authors.length;++s)
           {
               if(authors[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=authors[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."; 
                   break;
               case 2:
                    ath=authors[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=authors[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break;
               case 3:
                   ath=authors[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".,";
                    ath=authors[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=authors[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break; 
           }
           //formating and printing variables
            formatedTitle="<i>,"+name+".</i>";
            formatedPublisher="Unpublished.<br style=\"line-height:1px;\" />"; 
            outputFormat=formatedAuthors+formatedTitle+formatedPublisher;
            return outputFormat;
    }
    public String formatAPA()
    {
            String formatedTitle;
            String formatedPublisher;
            String formatedAuthors=null;
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<authors.length;++s)
           {
               if(authors[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=authors[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."; 
                   break;
               case 2:
                    ath=authors[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=authors[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break;
               case 3:
                   ath=authors[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".,";
                    ath=authors[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=authors[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break; 
           }
           //formating and printing variables
            formatedTitle="<i>"+name+".</i>";
            formatedPublisher="Unpublished.<br style=\"line-height:1px;\" />"; 
            outputFormat=formatedAuthors+formatedTitle+formatedPublisher;
            return outputFormat;
        
    }   
    
}
