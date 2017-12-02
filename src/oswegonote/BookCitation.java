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
public class BookCitation extends Citation 
{
    private String dateOfPublication;
    private String publisher;
    private String wherePublisher;
    
    public BookCitation(String name)
    {
        super(name);
    }
    public void setPublisherName(String name)
    {
        publisher=name;
    }
    public String getPublisherName()
    {
        return publisher;
    }
    public void setDateOfPublication(String date)
    {
        dateOfPublication=date;
    }
    public String getDateOfPublication()
    {
        return dateOfPublication;
    }
    public void setWherePublisher(String where)
    {
        wherePublisher =where;
    }
    public String getWherePublisher()
    {
        return wherePublisher;
    }
     @Override
     public  String formatIEEE() 
    {   
            String[] author=super.getAuthor();
            int ID=super.getID();
            String title=super.getName();
            String formatedID;
            String formatedDate;
            String formatedTitle;
            String formatedPublisher;
            String formatedAuthors=null;
            String outputFormat;
            formatedID="["+ID+"]";
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<author.length;++s)
           {
               if(author[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=author[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+", "; 
                   break;
               case 2:
                     ath=author[0];
                    authParts = ath.split(" ");
                    if(authParts.length==1)
                    {
                        lName = authParts[0];
                        authorsParta=lName+",";
                    }
                    else if(authParts.length==2)
                    {
                        fName=authParts[0];
                        lName= authParts[1];
                        authorsParta=fName.subSequence(0, 1)+"."+lName+",";
                    }
                    else
                    {
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                    }
                    ath=author[1];
                    authParts = ath.split(" ");
                    if(authParts.length==1)
                    {
                        lName = authParts[0];
                        authorsPartb=lName+",";
                    }
                    else if(authParts.length==2)
                    {
                        fName=authParts[0];
                        lName= authParts[1];
                        authorsPartb=fName.subSequence(0, 1)+"."+lName+",";
                    }
                    else
                    {
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                    }
                    
                    formatedAuthors=authorsParta+authorsPartb;
                    
                   break;
               case 3:
                   ath=author[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                    ath=author[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+"and ";
                    ath=author[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName+",";
                   break; 
           }
           //formating and printing variables
           String year;
            formatedTitle="<i>,"+title+".</i>";
            if(dateOfPublication.length()<6)
                year=dateOfPublication;
            else
                year = dateOfPublication.substring(6,dateOfPublication.length());
            String formatedWherePublished=wherePublisher+",";
            formatedPublisher=""+publisher+","; 
            formatedDate=""+year+".<br style=\"line-height:1px;\" />";
            outputFormat=formatedID+formatedAuthors+formatedTitle+formatedWherePublished+formatedPublisher+formatedDate;
            return outputFormat;

        } 
            
          

    @Override
    public String formatACM()
    {
            String[] author=super.getAuthor();
           
            String title = super.getName();
            String formatedDate;
            String formatedTitle;
            String formatedPublisher;
            String formatedAuthors=null;
            String outputFormat;
            int aCount=0;
           for(int s=0;s<author.length;++s)
           {
               if(author[s]!= null)
               {++aCount;}
           }
        String authorsParta;
        String authorsPartb;     
       switch(aCount)
           {
               case 1:
                String ath=author[0];
                String[] authParts = ath.split(" ");
                String fName = authParts[0];
                String mName = authParts[1];
                String lName = authParts[2];
                formatedAuthors=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName; 
                   break;
               case 2:
                ath=author[0];
                   authParts = ath.split(" ");
                   fName = authParts[0];
                   mName = authParts[1];
                   lName = authParts[2];
                   authorsParta=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName; 
                   ath=author[1];
                   authParts = ath.split(" ");
                   fName = authParts[0];
                   mName = authParts[1];
                   lName = authParts[2];
                   formatedAuthors=authorsParta+" & "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName;  
                   break;
               case 3:
                   ath=author[0];
                   authParts = ath.split(" ");
                   fName = authParts[0];
                   mName = authParts[1];
                   lName = authParts[2];
                   authorsParta=fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName; 
                   ath=author[1];
                   authParts = ath.split(" ");
                   fName = authParts[0];
                   mName = authParts[1];
                   lName = authParts[2];
                   authorsPartb=", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName; 
                   ath=author[2];
                   authParts = ath.split(" ");
                   fName = authParts[0];
                   mName = authParts[1];
                   lName = authParts[2];
                   formatedAuthors=authorsParta+authorsPartb+" & "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."+lName;  
                   break; 
           }

            formatedTitle="<i>. "+title+".</i><br style=\"line-height:0px;\" />";
            String year = dateOfPublication.substring(6,dateOfPublication.length());
            formatedPublisher=publisher+",";
            String formatedWherePublisher=wherePublisher+",";
            formatedDate=""+year+".<br style=\"line-height:1px;\" />"; 
            outputFormat=formatedAuthors+formatedTitle+formatedPublisher+formatedWherePublisher+formatedDate;
            return outputFormat;
          
        
            
    }
    @Override
    public String formatAPA()
    {
            String[] author=super.getAuthor();
            String title = super.getName();
            String formatedDate;
            String formatedTitle;
            String formatedPublisher;
            String formatedAuthors=null;
            String formatedPlace;
            String outputFormat;

           int aCount=0;
           for(int s=0;s<author.length;++s)
           {
               if(author[s]!= null)
               {++aCount;}
           }
           String authorsParta;
           String authorsPartb;
           switch(aCount)
           {
               case 1:
                    String ath=author[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String lName = authParts[2];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+"."; 
                   break;
               case 2:
                    ath=author[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[2];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+".,& ";
                    ath=author[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+".";
                   break;
               case 3:
                   ath=author[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[2];
                    authorsParta=lName+","+fName.subSequence(0, 1)+".,& ";
                    ath=author[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[2];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+".,& ";
                    ath=author[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+".";
                   break; 
           }
            String year = dateOfPublication.substring(6,dateOfPublication.length());
            formatedDate="("+year+")";
            formatedTitle="<i>."+title+"</i>.";
            formatedPlace=""+wherePublisher+":";
            formatedPublisher=""+publisher+".<br style=\"line-height:1px;\" />";  
            outputFormat=formatedAuthors+formatedDate+formatedTitle+formatedPlace+formatedPublisher;
            return outputFormat;
        
    }
}
