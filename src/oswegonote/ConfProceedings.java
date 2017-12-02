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
public class ConfProceedings extends Citation
{
    private String titleOfConferenceProc;
    private String publisher;
    private String locationOfConference;
    private String editor;
    private String startPage;
    private String endPage;
    private String confYear;
    public ConfProceedings(String name)
    {
        super(name);
    }
    public void setTitleOfConference(String title)
    {
        titleOfConferenceProc=title;
    }
    public String getTitleOfConference()
    {
        return titleOfConferenceProc;
    }
    public void setPublisher(String name)
    {
        publisher=name;
    }
    public String getPublisher()
    {
        return publisher;
    }
    public void setLocationOfConference(String location)
    {
        locationOfConference=location;
    }
    public String getLocationOfConference()
    {
        return locationOfConference;
    }
    public void setEditor(String edit)
    {
        editor=edit;
    }
    public String getEditor()
    {
        return editor;
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
        endPage=end;
    }
    public String getEndPage()
    {
        return endPage;
    }
    public void setConferenceYear(String date)
    {
        confYear=date;
    }
    public String getConferenceYear()
    {
        return confYear;
    }
    @Override
     public  String formatIEEE()
    {   
            String[] author=super.getAuthor();
            String name = super.getName();
            String formatedAuthors="";
            int ID=super.getID(); 
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<author.length;++s)
           {
               if(author[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           String formatedID="["+ID+"]";
           String authorsParta="";
           String authorsPartb="";
           String authorsPartc="";
           switch(aCount)
           {
               case 1:
                    String ath=author[0];
                    String[] authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        formatedAuthors=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    formatedAuthors=formatedAuthors+authParts[authParts.length-1]+",";
                   break;
               case 2:
                    ath=author[0];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsParta=authorsParta+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsParta=authorsParta+authParts[authParts.length-1]+",";
                    
                    ath=author[1];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsPartb=authorsPartb+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsPartb=authorsPartb+authParts[authParts.length-1]+",";
                    
                    formatedAuthors=authorsParta+authorsPartb;
                    
                   break;
               case 3:
                   ath=author[0];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsParta=authorsParta+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsParta=authorsParta+authParts[authParts.length-1]+",";
                    ath=author[1];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsPartb=authorsPartb+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsPartb=authorsPartb+authParts[authParts.length-1]+",";
                    ath=author[1];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsPartc=authorsPartc+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsPartc=authorsPartc+authParts[authParts.length-1]+",";
                    formatedAuthors=authorsParta+authorsPartb+authorsPartc;
                   break; 
           }
           //formating and printing variables
            String formatedTitle="\""+name+"\"";
            //String formatedName="in <i>,"".,</i>";
            String formatedLocation="in "+locationOfConference+".,";
            String formatedYear=""+confYear+".<br style=\"line-height:1px;\" />";
            
            outputFormat=formatedID+formatedAuthors+formatedTitle+formatedLocation+formatedYear;
            return outputFormat;

        } 
        @Override
        public  String formatACM()
        {   
            String[] author=super.getAuthor();
            String name = super.getName();
            String formatedAuthors="";
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<author.length;++s)
           {
               if(author[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           String authorsParta="";
           String authorsPartb="";
           String authorsPartc="";
           switch(aCount)
           {
               case 1:
                    String ath=author[0];
                    String[] authParts = ath.split(" ");
                    String fName = authParts[0];
                    String lName = authParts[authParts.length-1];
                    formatedAuthors=lName+", "+fName.subSequence(0, 1)+","; 
                   break;
               case 2:
                    ath=author[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[authParts.length-1];
                    authorsParta=lName+", "+fName.subSequence(0, 1)+".and ";
                    ath=author[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[authParts.length-1];
                    formatedAuthors=authorsParta+lName+", "+fName.subSequence(0, 1)+".";
                   break;
               case 3:
                   ath=author[0];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[authParts.length-1];
                    authorsParta=lName+","+fName.subSequence(0, 1)+".and";
                    ath=author[1];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[authParts.length-1];
                    authorsPartb=lName+","+fName.subSequence(0, 1)+".and ";
                    ath=author[2];
                    authParts = ath.split(" ");
                    fName = authParts[0];
                    lName = authParts[authParts.length-1];
                    formatedAuthors=authorsParta+authorsPartb+lName+", "+fName.subSequence(0, 1)+".";
                   break; 
           }
           //formating and printing variables
            
            String formatedName=""+name+".";
            String formatedTitle="in <i>"+titleOfConferenceProc+",</i>";
            String formatedVol="("+locationOfConference+",";
            String formatedDate=""+confYear+"),";
            String formatedPublisher=""+publisher+",";
            String formatedPages=""+startPage+"-"+endPage+".<br style=\"line-height:1px;\" />";
            outputFormat=formatedAuthors+formatedDate+formatedName+formatedTitle+formatedVol+formatedPublisher+formatedPages;
            return outputFormat;

        }
            @Override
            public  String formatAPA()
    {   
            String[] auth=super.getAuthor();
            String name = super.getName();
            String formatedAuthors="";
            String outputFormat;
            //determining actual number of authors to print
           int aCount=0;
           for(int s=0;s<auth.length;++s)
           {
               if(auth[s]!= null)
               {++aCount;}
           }
           //using aCount to print num of authors correctly
           String authorsParta="";
           String authorsPartb="";
           String authorsPartc="";
           switch(aCount)
           {
               case 1:
                    String ath=auth[0];
                    String[] authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        formatedAuthors=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    formatedAuthors=authParts[authParts.length-1]+","+formatedAuthors;
                   break;
               case 2:
                    ath=auth[0];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsParta=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsParta=authParts[authParts.length-1]+","+authorsParta;
                    ath=auth[1];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsPartb=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsPartb=authParts[authParts.length-1]+","+authorsPartb;
                    formatedAuthors=authorsParta+authorsPartb;
                   break;
               case 3:
                   ath=auth[0];
                   authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsParta=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsParta=authParts[authParts.length-1]+","+authorsParta;
                    ath=auth[1];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsPartb=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsPartb=authParts[authParts.length-1]+","+authorsPartb;
                    ath=auth[2];
                    authParts = ath.split(" ");
                    for(int i=0;i<authParts.length-1;++i)
                    {
                        authorsPartc=formatedAuthors+authParts[i].subSequence(0, 1)+".";
                    }
                    authorsPartc=authParts[authParts.length-1]+","+authorsPartc;
                    formatedAuthors=authorsParta+authorsPartb+authorsPartc;
                   break; 
           }
           //formating and printing variables
            String formatedDate="("+confYear+").";
            String formatedName=""+name+".";
            String[] editorParts = editor.split(" ");
            String efName = editorParts[0];
            String elName = editorParts[2];
            String formatedEditor = "In "+efName.subSequence(0, 1)+","+elName+"(Ed.)";
            String formatedTitle="<i>"+titleOfConferenceProc+"</i>";
            String formatedPages="(pp."+startPage+"-"+endPage+").";
            String formatedConferenceLocation=""+locationOfConference+":";
            String formatedPublisher=""+publisher+".<br style=\"line-height:1px;\" />";
            
            outputFormat=formatedAuthors+formatedDate+formatedName+formatedEditor+formatedTitle+formatedPages+formatedConferenceLocation+formatedPublisher;
            return outputFormat;

        }
}
