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

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
public class Handler extends DefaultHandler 

{
    IndexHashTable aIndex;
    Citation[]aCitation=new Citation[20];
    String tempString, name, publisher,publicationDate,titleOfJournal,volNumber,issueNumber,conferenceYear;
    String citationType,conferenceLocation,editor, formattingStyle,wherePublisher,startPage,endPage,filePath;
    String[] authors =new String[3];
    String[] keyWords =new String[5];
    boolean save;
    int numAuthors,numKeywords,numCitations;
    int citationNum=0;
public Handler()throws Exception
{

}
 @Override
    public void startElement(String nameSpaceURL, String localName, String qualifiedName, Attributes attributes)
    {
        //setting citation types
     if(qualifiedName.equalsIgnoreCase("book"))
     {  
       citationType="book";
       ++numCitations;
     }
     if(qualifiedName.equalsIgnoreCase("JournalArticle"))
     {
         citationType="JournalArticle";
         ++numCitations;
     }
     if(qualifiedName.equalsIgnoreCase("Unpublished"))
     {
         citationType="Unpublished";
         ++numCitations;
     }
     if(qualifiedName.equalsIgnoreCase("ConferenceProceedings"))
     {
         citationType="ConferenceProceedings";
         ++numCitations;
     }
     //resets numAuthors and prepares for input
     if(qualifiedName.equalsIgnoreCase("authors"))
     {
         numAuthors =0;
     }
     //resets numKeywords amd prepares for input
     if(qualifiedName.equalsIgnoreCase("Keywords"))
     {
         numKeywords =0;
     }
     //gets page numbers from attributes
     if(qualifiedName.equalsIgnoreCase("Pages"))
     {
        startPage=attributes.getValue("StartPage");
        endPage =attributes.getValue("EndPage");
     }
     }
    
    @Override
    public void endElement(String nameSpaceURL, String localName, String qualifiedName )
    {
      //looks for appropriate endElement to place tempString in correct variable
       if(qualifiedName.equalsIgnoreCase("Name"))
       {name=tempString;}
       if(qualifiedName.equalsIgnoreCase("Publisher"))
       {publisher=tempString;}
       if(qualifiedName.equalsIgnoreCase("PublicationDate"))
       {publicationDate=tempString;}
       if(qualifiedName.equalsIgnoreCase("PublicationPlace"))
       {wherePublisher=tempString;}
       if(qualifiedName.equalsIgnoreCase("ConferenceYear"))
       {conferenceYear=tempString;}
       if(qualifiedName.equalsIgnoreCase("author"))
       {
           authors[numAuthors]=tempString;
           ++numAuthors;
       }
       if(qualifiedName.equalsIgnoreCase("Keyword"))
       {
           keyWords[numKeywords]=tempString;
           ++numKeywords;
       }
       if(qualifiedName.equalsIgnoreCase("TitleOfJournal"))
       {titleOfJournal=tempString;}
       if(qualifiedName.equalsIgnoreCase("volNumber"))
       {volNumber=tempString;}
       if(qualifiedName.equalsIgnoreCase("IssueNumber"))
       {issueNumber=tempString;}
       if(qualifiedName.equalsIgnoreCase("ConferenceLocation"))
       {conferenceLocation=tempString;}
       if(qualifiedName.equalsIgnoreCase("Editor"))
       {editor=tempString;}
       if(qualifiedName.equalsIgnoreCase("FormattingStyle"))
       {formattingStyle=tempString;}
       //using Citation to trigger the build
       if(qualifiedName.equalsIgnoreCase("Citation"))
       {
           buildCitation();
       }
      if(qualifiedName.equalsIgnoreCase("FilePath"))
       {filePath=tempString;}
      //uses index to trigger adding,formatting and saving citations
      if(qualifiedName.equalsIgnoreCase("Index"))
       { aIndex = new IndexHashTable(numCitations);
        for(int x=0;x<numCitations;++x)
        {aIndex.addCitation(aCitation[x]);}
        aIndex.setFormatType(formattingStyle);
        aIndex.setFile(filePath);
        aIndex.saveFile();}
    }
    @Override
    public void characters(char cha[], int start, int end )
    {
        tempString= new String(cha,start,end);
    }
    public  String[] searchByKeyword(String key)
    {
        return aIndex.searchByKeyword( key);
    }
    public String[] searchByName( String key)
    {
        return aIndex.searchByName(key);
    }
    public int deleteCitation(String name)
    {
        return aIndex.deleteCitation(name);
    }
    public void clearIndex()
    {
        aIndex.clearIndex();
    }
    public int addCitation(Citation myCitation)
    {
        return aIndex.addCitation(myCitation);
    }
    public int insertCitationInPos(Citation myCitation)
    {
        
        return aIndex.insertCitationInPos(myCitation);
    }
    public void format(String path)
    {
        aIndex.format(path);
    }
    public void sortKeywords()
    {
        aIndex.sortKeywords();
    }
    public void sortCitationIndex()
    {
        aIndex.sortCitationIndex();
    }
    public void printCitationIndex()
    {
        aIndex.printCitationIndex();
    }
    public void printKeywords()
    {
        aIndex.printKeywords();
    }
    public void setFile(String f)
    {
        aIndex.setFile(f);
        aIndex.saveFile();
    }
    public boolean isEmpty()
    {
        return aIndex.isEmpty();
    }
     public boolean isFull()
    {
        return aIndex.isFull();
    }
     public void setFormatType(String type)
     {
         aIndex.setFormatType(type);
     }
     public String getFormatType()
     {
         return aIndex.getFormatType();
     }
    public void buildCitation()
    {
       if(citationType.equals("book"))
       {
             BookCitation citation= new BookCitation(name);
             citation.setAuthor(authors, numAuthors);
             citation.setDateOfPublication(publicationDate);
             citation.setKeywords(keyWords, numKeywords);
             citation.setPublisherName(publisher);
             citation.setWherePublisher(wherePublisher);
             aCitation[citationNum]=citation;
             //reseting autor and keyword arrays for next iteration of the loop
             for(int x=0;x<3;++x)
                {
                authors[x]=null;
                }
             for(int x=0;x<5;++x)
               {
                keyWords[x]=null;
               } 
             ++citationNum;
           
       }
       if(citationType.equals("JournalArticle"))
       {
           JournalArticle citation = new JournalArticle(name);
             citation.setAuthor(authors, numAuthors);
             citation.setPublicationDate(publicationDate);
             citation.setTitleOfJournal(titleOfJournal);  
             citation.setVolNumber(volNumber);
             citation.setKeywords(keyWords, numKeywords);
             citation.setIssueNumber(issueNumber);
             citation.setStartPage(startPage);
             citation.setEndPage(endPage);
             aCitation[citationNum]=citation;
             //reseting autor and keyword arrays for next iteration of the loop
             for(int x=0;x<3;++x)
                {
                authors[x]=null;
                }
             for(int x=0;x<5;++x)
               {
                keyWords[x]=null;
               } 
             ++citationNum;
       }
       if(citationType.equals("Unpublished"))
       {
           Citation citation = new Citation(name);
             citation.setAuthor(authors, numAuthors);
             citation.setKeywords(keyWords, numKeywords);
             aCitation[citationNum]=citation;
             //reseting autor and keyword arrays for next iteration of the loop
             for(int x=0;x<3;++x)
                {
                authors[x]=null;
                }
             for(int x=0;x<5;++x)
               {
                keyWords[x]=null;
               } 
             ++citationNum;
       }
       if(citationType.equals("ConferenceProceedings"))
       {
             ConfProceedings citation = new ConfProceedings(name);
             citation.setConferenceYear(conferenceYear);
             citation.setPublisher(publisher);
             citation.setAuthor(authors, numAuthors);
             citation.setKeywords(keyWords, numKeywords);
             citation.setLocationOfConference(conferenceLocation);
             citation.setEditor(editor);
             citation.setStartPage(startPage);
             citation.setEndPage(endPage);
             aCitation[citationNum]=citation;
             //reseting author and keyword arrays for next iteration of the loop
             for(int x=0;x<3;++x)
                {
                authors[x]=null;
                }
             for(int x=0;x<5;++x)
               {
                keyWords[x]=null;
               } 
             ++citationNum;
       }
       
    }
    
    
}
