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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

public class Main  {

    /**
     * @param args the command line arguments
     */

    public static void main (String[] args) throws Exception 
    {
        IndexArrayList indexes;
        Scanner inputDevice = new Scanner(System.in);
        String index;
        String key;
        String[] result;
        String choice=" ";
        //creates parse factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //creates sax parser
        SAXParser saxParser = factory.newSAXParser();  
        XMLReader parser = saxParser.getXMLReader();
        //creates handler object
        Handler handler=new Handler();
        parser.setContentHandler(handler);
        parser.parse("f:\\Citations.xml");
        
        while(!choice.equalsIgnoreCase("16")){
       System.out.println("                               ******Choose a function******\n"
        +"**********************************************************************************************************************\n"
        +"|| [1]printCitationIndex() [2]printKeywords() [3]isEmpty() [4]isFull() [5]addCitation(Citation inCitation)          ||\n"
        +"||         [6]setFormatType(String s) [7]getFormatType() [8]sortCitationIndex() [9]sortKeywords()                   ||\n"
        +"||  [10]insertCitationInPos(Citation myCitation) [11]deleteCitation(String name) [12]searchByKeyword( String key)   ||\n"
        +"||            [13]searchByName( String key) [14]clearIndex() [15]format(String path) [16] to quit                   ||\n"
        +"**********************************************************************************************************************\n");
        
        
        choice=inputDevice.nextLine();
        
        switch(choice)
        {
            case "1":handler.printCitationIndex(); 
                break;
            case "2":handler.printKeywords();
                break;
            case "3":System.out.println(handler.isEmpty());
                break;
            case "4":System.out.println(handler.isFull());
                break;
            case "5":handler.addCitation(createCitation());
                break;
            case "6": System.out.println("How would you like to format your citation today?(IEEE,ACM,APA)");
                      handler.setFormatType(inputDevice.nextLine());
                break;
            case "7":System.out.println(handler.getFormatType());
                break;
            case "8":handler.sortCitationIndex();
                break;
            case "9":handler.sortKeywords();
                break;
            case "10":System.out.println(handler.insertCitationInPos(createCitation()));
                break;
            case "11":System.out.println("Please enter the citation name to delete");
                      handler.deleteCitation(inputDevice.nextLine());
                break;
            case "12":System.out.println("Please enter your keyword");
                      String[] search=handler.searchByKeyword(inputDevice.nextLine());
                      for(int i=0;i<search.length;++i)
                       {
                         System.out.println(search[i]);
                       }
                break;
            case "13":System.out.println("Please enter your citation name");
                      String[] cits=handler.searchByName(inputDevice.nextLine());
                      for(int i=0;i<cits.length;++i)
                       {
                         System.out.println(cits[i]);
                       }
                break;
            case "14":handler.clearIndex();
                break;
            case "15":System.out.println("Please enter your file name and address");
                     handler.format(inputDevice.nextLine());
                break;
            
        }
        //choice="quit";
        }
       


    } 
    public static Citation createCitation()
    {
        Citation citation=null;
        Scanner inputDevice = new Scanner(System.in);
        String[] authors= new String[3];
        String titleOfJournal;
        String titleOfConference;
        String editor;
        String citationType;
        String conLocation;
        String save;
        String publicationDate;
        String publicationName;
        String wherePublisher;
        String publisher;
        String[] keywords = new String[5];
        String volNumber,issueNumber,startPage,endPage,confYear;
        int numAuthors;
        int numKeywords;
        System.out.println("Is this Citation for a Journal Article(J),Book(B),Conference Proceeding(C) or other(O)(or 'quit' to quit):");
        citationType=inputDevice.nextLine();
         //*******requesting publications name
        System.out.println("Please enter the publication name(or 'quit' to quit):");
        publicationName = inputDevice.nextLine();
        if(publicationName.equalsIgnoreCase("quit")||publicationName.equalsIgnoreCase("'quit'"))
        {
            System.exit(0);
        }
       
        //*******requesting number of authors
        System.out.println("How many authors are there? (max 3)");
        numAuthors = inputDevice.nextInt(); 
        while(numAuthors>3||numAuthors<1)//ensures correct number is entered
        {
            System.out.println("You must pick 1,2 or 3 authors.");
            numAuthors = inputDevice.nextInt();
        }
        //*******handles one author
        if(numAuthors==1)
        {
            String author;
            inputDevice.nextLine();//eats return
            System.out.println("What is the Author's name?(FirstName MI. LastName)(or 'quit' to quit)");
            author =inputDevice.nextLine();
             if(author.equalsIgnoreCase("quit")||author.equalsIgnoreCase("'quit'"))
                {
                    System.exit(0);
                }
            authors[0]= author;
        }
        else
        {
            inputDevice.nextLine();//eats return
            //*******handles more than one author using a switch 
            for(int a=0;a<numAuthors;a++)
            {   String author;
            
                switch(a)
                {
                    
                    case 0:
                        System.out.println("What is the first Author's name?(FirstName MI. LastName)(or 'quit' to quit)");
                        author =inputDevice.nextLine();
                        if(author.equalsIgnoreCase("quit")||author.equalsIgnoreCase("'quit'"))
                        {System.exit(0);}
                        authors[0]= author;
                        break;
                    case 1:
                         System.out.println("What is the second Author's name?(FirstName MI. LastName)(or 'quit' to quit)");
                         author =inputDevice.nextLine();
                        if(author.equalsIgnoreCase("quit")||author.equalsIgnoreCase("'quit'"))
                        {System.exit(0);}
                        authors[1]= author;
                        break;
                    case 2:
                         System.out.println("What is the third Author's name?(FirstName MI. LastName)(or 'quit' to quit)");
                         author =inputDevice.nextLine();
                        if(author.equalsIgnoreCase("quit")||author.equalsIgnoreCase("'quit'"))
                        {System.exit(0);}
                        authors[2]= author;
                        break;
                }
            }
        }
        System.out.println("How many keywords would you like to add?(max 5):");
        numKeywords = inputDevice.nextInt();
        while(numKeywords<0||numKeywords>5)
        {
            System.out.println("You have entered an invalid number of keywords.");
            System.out.println("Choose a number 1 to 5 Citations.");
            numKeywords = inputDevice.nextInt();  
            
        }
       inputDevice.nextLine();//eats enter key
       //Handles one keyword
       if(numKeywords == 1)
       {
           String keyword;
           System.out.println("Please enter your keyword.(or 'quit' to quit)");
           keyword=inputDevice.nextLine();
           if(keyword.equalsIgnoreCase("quit")||keyword.equalsIgnoreCase("'quit'"))
                         {System.exit(0);}
           keywords[0]=keyword;
       }else
           //handles multiple keyords using a switch
        for(int a=0;a<numKeywords;a++)
            {String keyword;
                switch(a)
                {
                    case 0:
                         System.out.println("What is the first keyword?(or 'quit' to quit)");
                         keyword =inputDevice.nextLine();
                         if(keyword.equalsIgnoreCase("quit")||keyword.equalsIgnoreCase("'quit'"))
                         {System.exit(0);}
                         keywords[0]=keyword;
                         break;
                    case 1:
                         System.out.println("What is the second keyword?(or 'quit' to quit)");
                         keyword =inputDevice.nextLine();
                         if(keyword.equalsIgnoreCase("quit")||keyword.equalsIgnoreCase("'quit'"))
                         {System.exit(0);}
                         keywords[1]=keyword;
                         break;
                    case 2:
                         System.out.println("What is the third keyword?(or 'quit' to quit)");
                         keyword =inputDevice.nextLine();
                         if(keyword.equalsIgnoreCase("quit")||keyword.equalsIgnoreCase("'quit'"))
                         {System.exit(0);}
                         keywords[2]=keyword;
                         break;
                    case 3:
                         System.out.println("What is the fourth keyword?(or 'quit' to quit)");
                         keyword =inputDevice.nextLine();
                         if(keyword.equalsIgnoreCase("quit")||keyword.equalsIgnoreCase("'quit'"))
                         {System.exit(0);}
                         keywords[3]=keyword;
                         break;
                    case 4:
                         System.out.println("What is the fifth keyword?(or 'quit' to quit)");
                         keyword =inputDevice.nextLine();
                         if(keyword.equalsIgnoreCase("quit")||keyword.equalsIgnoreCase("'quit'"))
                         {System.exit(0);}
                         keywords[4]=keyword;
                         break;
                }
        
           
            } 
       if(citationType.equalsIgnoreCase("j"))
       {
            System.out.println("Please enter the title of the periodical(or 'quit' to quit):");
            titleOfJournal = inputDevice.nextLine();
            if(titleOfJournal.equalsIgnoreCase("quit")||titleOfJournal.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the date of publication(in mm/dd/yyy format)(or 'quit' to quit):");
            publicationDate = inputDevice.nextLine();
            if(publicationDate.equalsIgnoreCase("quit")||publicationDate.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the volume number");
            volNumber = inputDevice.nextLine();
            System.out.println("Please enter the start page");
            startPage = inputDevice.nextLine();
            System.out.println("Please enter the end page");
            endPage = inputDevice.nextLine();
            System.out.println("Please enter the issue number(if no issue number enter 0)");
            issueNumber = inputDevice.nextLine();
           
            //Displaying Citation for users view
         System.out.println("This is your Citation: ");
         System.out.println("Name: "+ publicationName);
         System.out.println("Title of Periodical: "+ titleOfJournal);
         System.out.print("Author(s): "+ authors[0]);
         for(int at=1; at<numAuthors;++at )
         {
             System.out.print("," + authors[at]);
         }
         System.out.print("\n");
         System.out.println("Publication Date: "+ publicationDate);
         System.out.println("Volume Number: "+ volNumber);
         System.out.println("Issue Number: "+ issueNumber);
         System.out.println("Start Page: "+ startPage);
         System.out.println("End Page: "+ endPage);
         System.out.print("Keywords: "+ keywords[0]);
         for(int kw=1; kw<numKeywords;++kw )
         {
             System.out.print("," + keywords[kw]);
         }
         System.out.print("\n");
         //gives user option to save citation
         System.out.println("Would you like to save this?(yes or no or 'quit' to quit)");
         save = inputDevice.nextLine();
         //populating citation and adding to index
         if(save.equalsIgnoreCase("yes")||save.equalsIgnoreCase("'yes'"))
         {
             JournalArticle aCitation = new JournalArticle(publicationName);
             aCitation.setAuthor(authors, numAuthors);
             aCitation.setPublicationDate(publicationDate);
             aCitation.setTitleOfJournal(titleOfJournal);  
             aCitation.setVolNumber(volNumber);
             aCitation.setKeywords(keywords, numKeywords);
             aCitation.setIssueNumber(issueNumber);
             aCitation.setStartPage(startPage);
             aCitation.setEndPage(endPage);
             citation= aCitation;
            
           } 
         else {System.exit(0);}
        
       }
       if(citationType.equalsIgnoreCase("b"))
       {
            System.out.println("Please enter the date of publication(in mm/dd/yyy format)(or 'quit' to quit):");
            publicationDate = inputDevice.nextLine();
            if(publicationDate.equalsIgnoreCase("quit")||publicationDate.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the publishers name(or 'quit' to quit):");
            publisher = inputDevice.nextLine();
            if(publisher.equalsIgnoreCase("quit")||publisher.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Where was it published?(or 'quit' to quit):");
            wherePublisher = inputDevice.nextLine();
            if(wherePublisher.equalsIgnoreCase("quit")||wherePublisher.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
         System.out.println("This is your Citation: ");
         System.out.println("Name: "+ publicationName);
         System.out.print("Author(s): "+ authors[0]);
         for(int at=1; at<numAuthors;++at )
         {
             System.out.print("," + authors[at]);
         }
         System.out.print("\n");
         System.out.println("Date of Publication: "+ publicationDate);
         System.out.println("Name of Publisher: "+ publisher);
         System.out.println("Publication Place: "+ wherePublisher);
         System.out.print("Keywords: "+ keywords[0]);
         for(int kw=1; kw<numKeywords;++kw )
         {
             System.out.print("," + keywords[kw]);
         }
         System.out.print("\n");
         //gives user option to save citation
         System.out.println("Would you like to save this?(yes or no or 'quit' to quit)");
         save = inputDevice.nextLine();
         //populating citation and adding to index
         if(save.equalsIgnoreCase("yes")||save.equalsIgnoreCase("'yes'"))
         {
             BookCitation aCitation = new BookCitation(publicationName);
             aCitation.setAuthor(authors, numAuthors);
             aCitation.setDateOfPublication(publicationDate);
             aCitation.setKeywords(keywords, numKeywords);
             aCitation.setPublisherName(publisher);
             aCitation.setWherePublisher(wherePublisher);
             citation = aCitation;

           } 
         else {System.exit(0);}

       }
       if(citationType.equalsIgnoreCase("c"))
       {
            System.out.println("Please enter the title of the conference(or 'quit' to quit):");
            titleOfConference = inputDevice.nextLine();      
            if(titleOfConference.equalsIgnoreCase("quit")||titleOfConference.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the publishers name(or 'quit' to quit):");
            publisher = inputDevice.nextLine();
            if(publisher.equalsIgnoreCase("quit")||publisher.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the location of the conference(or 'quit' to quit):");
            conLocation = inputDevice.nextLine();
            if(conLocation.equalsIgnoreCase("quit")||conLocation.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the editors name(FirstName MI. LastName)(or 'quit' to quit):");
            editor = inputDevice.nextLine();
            if(editor.equalsIgnoreCase("quit")||editor.equalsIgnoreCase("'quit'"))
            {
                System.exit(0);
            }
            System.out.println("Please enter the year of the conference");
            confYear = inputDevice.nextLine();
            System.out.println("Please enter the start page");
            startPage = inputDevice.nextLine();
            System.out.println("Please enter the end page");
            endPage = inputDevice.nextLine();
            
            //Displaying Citation for users view
         System.out.println("This is your Citation: ");
         System.out.println("Name: "+ publicationName);
         System.out.print("Author(s): "+ authors[0]);
         for(int at=1; at<numAuthors;++at )
         {
             System.out.print("," + authors[at]);
         }
         System.out.print("\n");
         System.out.println("Title of Conference: "+ titleOfConference);
         System.out.println("Publisher: "+ publisher);
         System.out.println("Location of Conference: "+ conLocation);
         System.out.println("Conference Year: "+ confYear);
         System.out.println("Editors Name: "+ editor);
         System.out.println("Start Page: "+ startPage);
         System.out.println("End Page: "+ endPage);
         System.out.print("Keywords: "+ keywords[0]);
         for(int kw=1; kw<numKeywords;++kw )
         {
             System.out.print("," + keywords[kw]);
         }
         System.out.print("\n");
         //gives user option to save citation
         System.out.println("Would you like to save this?(yes or no or 'quit' to quit)");
         save = inputDevice.nextLine();
         //populating citation and adding to index
         if(save.equalsIgnoreCase("yes")||save.equalsIgnoreCase("'yes'"))
         {
             ConfProceedings aCitation = new ConfProceedings(publicationName);
             aCitation.setConferenceYear(confYear);
             aCitation.setPublisher(publisher);
             aCitation.setAuthor(authors, numAuthors);
             aCitation.setTitleOfConference(titleOfConference);
             aCitation.setKeywords(keywords, numKeywords);
             aCitation.setLocationOfConference(conLocation);
             aCitation.setEditor(editor);
             aCitation.setStartPage(startPage);
             aCitation.setEndPage(endPage);
             citation= aCitation;
           } 
         else {System.exit(0);}

       }
        if(citationType.equalsIgnoreCase("o"))
        {
         
         System.out.println("This is your Citation: ");
         System.out.println("Name: "+ publicationName);
         System.out.print("Author(s): "+ authors[0]);
         for(int at=1; at<numAuthors;++at )
         {
             System.out.print("," + authors[at]);
         }
         System.out.print("\n");
         System.out.print("Keywords: "+ keywords[0]);
         for(int kw=1; kw<numKeywords;++kw )
         {
             System.out.print("," + keywords[kw]);
         }
         System.out.print("\n");
         //gives user option to save citation
         System.out.println("Would you like to save this?(yes or no or 'quit' to quit)");
         save = inputDevice.nextLine();
         //populating citation and adding to index
         if(save.equalsIgnoreCase("yes")||save.equalsIgnoreCase("'yes'"))
         {
             Citation aCitation = new Citation(publicationName);
             aCitation.setAuthor(authors, numAuthors);
             aCitation.setKeywords(keywords, numKeywords);
             citation= aCitation;
           } 
         else {System.exit(0);}

       }
       
         
       return citation;
        
    }
         
        
    
   
}
