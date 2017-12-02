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
public class JournalArticle extends Citation
{
    private String titleOfJournal;
    private String publicationDate;
    private String volNumber;
    private String issueNumber;
    private String startPage;
    private String endPage;
    
    public JournalArticle(String name)
    {
        super(name);
    }
    public void setTitleOfJournal(String title)
    {
        titleOfJournal=title;
    }
    public String getTitleOfJournal()
    {
        return titleOfJournal;
    }
    public void setPublicationDate(String date)
    {
        publicationDate=date;
    }
    public String getPublicationDate()
    {
        return publicationDate;
    }
    public void setVolNumber(String vol)
    {
        volNumber=vol;
    }
        public String getVolNumber()
    {
        return volNumber;
    }
    public void setIssueNumber(String issue)
    {
        issueNumber=issue;
    }
    public String getIssueNumber()
    {
        return issueNumber;
    }
    public void setStartPage(String start)
    {
        startPage=start;
    }
    public String getStartPage()
    {
        return startPage;
    }
    public void setEndPage(String end)
    {
        endPage =end;
    }
    public String getEndPage()
    {
        return endPage;
    }

     @Override
     public  String formatIEEE()
    {   
            String[] auth=super.getAuthor();
            String name = super.getName();
            String formatedAuthors=null;
            int ID=super.getID(); 
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<auth.length;++s)
           {
               if(auth[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           String formatedID="["+ID+"]";
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=auth[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+","; 
                   break;
               case 2:
                    ath=auth[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=auth[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+",";
                   break;
               case 3:
                   ath=auth[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".,";
                    ath=auth[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=auth[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+",";
                   break; 
           }
           //formating and printing variables
            String formatedName="\""+name+"\"";
            String formatedTitle="<i>,"+titleOfJournal+",</i>";
            String formatedVol="vol."+volNumber+",";
            String formatedIssueNumber="no."+issueNumber+",";
            String formatedPages="pp."+startPage+"-"+endPage+","; 
            String[] date =publicationDate.split(" ");
            String month=date[0];
            String year = date[1];
            String formatedDate=""+month.subSequence(0, 3)+year+".<br style=\"line-height:1px;\" />";
            
            outputFormat=formatedID+formatedAuthors+formatedName+formatedTitle+formatedVol+formatedIssueNumber+formatedPages+formatedDate;
            return outputFormat;

        } 
          @Override
          public  String formatAPA()
    {   
            String[] auth=super.getAuthor();
            String name = super.getName();
            String formatedAuthors=null;
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<auth.length;++s)
           {
               if(auth[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=auth[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+","; 
                   break;
               case 2:
                    ath=auth[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=auth[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break;
               case 3:
                   ath=auth[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                    ath=auth[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".and ";
                    ath=auth[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break; 
           }
           //formating and printing variables
            String year = publicationDate.substring(6,publicationDate.length());
            String formatedDate="("+year+").";
            String formatedName=""+name+".";
            String formatedTitle="<i>"+titleOfJournal+",</i>";
            String formatedVol=""+volNumber+",";
            String formatedIssueNumber;
            if(issueNumber.equals(""))
            { formatedIssueNumber="";}
            else
            { formatedIssueNumber="("+issueNumber+"),";}
            String formatedPages=""+startPage+"-"+endPage+".<br style=\"line-height:1px;\" />";
            outputFormat=formatedAuthors+formatedDate+formatedName+formatedTitle+formatedVol+formatedIssueNumber+formatedPages;
            return outputFormat;

        }
            @Override
            public  String formatACM()
    {   
      
            String[] auth=super.getAuthor();
            String name = super.getName();
            String formatedAuthors=null;
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<auth.length;++s)
           {
               if(auth[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           String authorsParta;
           String authorsPartb;
           
           switch(aCount)
           {
               case 1:
                    String ath=auth[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String mName = authParts[1];
                    String lName = authParts[2];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+"."; 
                   break;
               case 2:
                    ath=auth[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".,";
                    ath=auth[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break;
               case 3:
                   ath=auth[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsParta=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".,";
                    ath=auth[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".,";
                    ath=auth[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    mName = authParts[1];
                    lName = authParts[2];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+"."+mName.subSequence(0, 1)+".";
                   break; 
           }
           //formating and printing variables
            String formatedName=""+name+".";
            String formatedTitle="<i>"+titleOfJournal+",</i>";
            String formatedVol=""+volNumber+",";
            String formatedIssueNumber="("+issueNumber+").";
            String formatedPages=""+startPage+"-"+endPage+".<br style=\"line-height:1px;\" />";
            outputFormat=formatedAuthors+formatedName+formatedTitle+formatedVol+formatedIssueNumber+formatedPages;
            return outputFormat;

        }
            

    
}
