/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/*
 Keith Fosmire
 CSC 241 Assignment 5
 Due Date 12/9/2013
 
 */
public class IndexLinkedList implements IndexInterface{
    Scanner inputDevice = new Scanner(System.in);
    
    private IndexNode citationIndex;
    
    private IndexNode sort;
    private KeywordNode keywordIndex;
    private int numKeywords=0;
    private int numCitations=0;
    private Citation[] citations=new Citation[100];
    private String file;
    private String formatType;
    private int totalNumCitations;
    //constructor sets up new citation
    public IndexLinkedList(int totalNumCitations)
    {
        citationIndex =null;
      keywordIndex=null;
        this.totalNumCitations += totalNumCitations;
        if(totalNumCitations==99)
        {System.out.println("Citation Index is full");}
    }
     //attempts to add citation 
    public void setFile(String path)
    {
        this.file=path;
    }
        public void saveFile()
    {
      
      
        //switch determines methos to call
        switch(formatType)
        {
            case "IEEE":
            case "ieee":
                formatIEEE(file);
                break;
            case "ACM":
                formatACM(file);
                break;
            case "APA":
                formatAPA(file);
                break;
        }   
    }
    public int addCitation(Citation inCitation)
    {
        
        if(numCitations==99)
        {
            System.out.println("You have reached the maximum number of Citations");
            return -1;
        }
        inCitation.updateID(numCitations+1);
        updateKeywords(inCitation);
        IndexNode temp= new IndexNode(inCitation);
        temp.setLink(citationIndex);
        citationIndex=temp;
        ++numCitations;
        return 0;
        
    }
    private void updateIDs()
    {
        
        IndexNode current =citationIndex;
        int id=1;
        keywordIndex=null;
        numKeywords=0;
        while(current!=null)
        {
           
            current.getCitation().getID();
           
            current.getCitation().updateID(id);
            updateKeywords(current.getCitation());
            current=current.getLink();
            ++id;
        }
 }
    private void updateKeywords(Citation inCitation)
    {
        
        Keyword keyword[]=inCitation.getKeywords();
        int numWords=inCitation.getNumberOfKeywords();
        
        for(int i=0;i<numWords;++i)
        {
         
            int test=findKeywordInList(keyword[i]);
            if(test<0)
            {
                Keyword inKeyword =new Keyword(keyword[i].getName(),inCitation.getID());
                KeywordNode temp = new KeywordNode(inKeyword);
                temp.setLink(keywordIndex);
                keywordIndex=temp;
                
                ++numKeywords;
            }
            else if(test>0)
            {
                
                KeywordNode current=keywordIndex;
                KeywordNode temp=current;
                while(current!=null)
                {
                    if(keyword[i].getName().equalsIgnoreCase(current.getName()))
                    {
                        temp=current;
                    }
                    current=current.getLink();
                }
                Keyword key;
                        key=temp.getKeyword();
                        key.addID(inCitation.getID());
            }
            
        }
        
    }
    private int findKeywordInList(Keyword k)
    {
        Keyword test;
        int result=-1;
        KeywordNode current = keywordIndex;
        while(current!=null)
        {
            test=current.getKeyword();
            
            if(current.getName().equalsIgnoreCase(k.getName()))        
          {
              
              result=1;
          }
            current=current.getLink();
        }
       
        return result;
    }
    public boolean isEmpty()
    {
        if(citationIndex==null)
        {
            return true;
        }
        else
            return false;
    }
    public void setFormatType(String s)
    {
        formatType =s;
    }
    //returns format type
    public String getFormatType()
    {
        return formatType;
    }
     public void printKeywords()
    {
        Keyword temp;
       
        if(keywordIndex.getLink()==null)
        {System.out.println("There are no keywords in the index");}
        KeywordNode keys=keywordIndex;
        while(keys!=null)
        {
            
            temp=keys.getKeyword();
            System.out.println(keys.getName());
          
            keys=keys.getLink();
        }
       
    }

    
//public void printCitationIndex();
// Pre-condition - the index is not empty and has a format type
//Prints out all the citations in the index formatted according to its format type





public boolean isFull()
{
    if(numCitations==99)
    return true;
    else
        return false;
}
//Returns whether or not the Index is full


 
 public void formatIEEE(String path)
 {
     String fileAddr = path;
        PrintWriter out =null;//declaring new printwriter
        try{
        out =new PrintWriter(new FileWriter(fileAddr));//declaring new printwriter
        }
       catch (IOException e)
      {
          System.out.println(e.getMessage());
          System.exit(1);
       }

        //starts loop for saving in format
        
        IndexNode current;
       for(int i=0;i<numCitations;++i){
        current=citationIndex;
        while(current!=null)
                {
                     if(current.getCitation().getID()==i+1)
                    {
                    Citation temp =current.getCitation();
                    out.println(temp.formatIEEE());
                    current=current.getLink();
                    }else
                    current = current.getLink();
                }
       }
        
            out.flush();
            out.close();
            
 }
 // Formats all the citations in the index in IEEE format and saves it to the file
 // indicated by path
 
 public void formatACM(String path)
         {
     String fileAddr = path;
        PrintWriter out =null;//declaring new printwriter
        try{
        out =new PrintWriter(new FileWriter(fileAddr));//declaring new printwriter
        }
       catch (IOException e)
      {
          System.out.println(e.getMessage());
          System.exit(1);
       }

        //starts loop for saving in format
        IndexNode current;
        current=citationIndex;
        while(current!=null)
                {
                    Citation print =current.getCitation();
                    System.out.println(print.formatACM());
                    current = current.getLink();
                }
        
            out.flush();
            out.close();
 }
 // Formats all the citations in the index in ACM format and saves it to the file
 // indicated by path
  
 public void formatAPA(String path)
         {
     String fileAddr = path;
        PrintWriter out =null;//declaring new printwriter
        try{
        out =new PrintWriter(new FileWriter(fileAddr));//declaring new printwriter
        }
       catch (IOException e)
      {
          System.out.println(e.getMessage());
          System.exit(1);
       }

        //starts loop for saving in format
        IndexNode current;
        current=citationIndex;
        while(current!=null)
                {
                    Citation print =current.getCitation();
                    System.out.println(print.formatAPA());
                    current = current.getLink();
                }
        
            out.flush();
            out.close();
 }
 // Formats all the citations in the index in APA format and saves it to the file
 // indicated by path
 
 public void format(String path)
  {
      
        //switch determines methos to call
        switch(formatType)
        {
            case "IEEE":
            case "ieee":
                formatIEEE(path);
                break;
            case "ACM":
                formatACM(path);
                break;
            case "APA":
                formatAPA(path);
                break;
        }
    }
  // Formats all the citations in the index in the format indicated by formatType
 // and saves it to the file indicated by path
 public void mergeSort(int first, int last)
    {
        
        int middle, left, right;
        Citation[] temp = new Citation[1];
        if(first<last)
        {
            middle = (first+last)/2;
            mergeSort(first, middle);
            mergeSort(middle+1, last);
            left = first;
            right = middle + 1;
        
        while(left <= middle && right <= last)
        {
            if(citations[left].getName().compareToIgnoreCase(citations[right].getName())>0)
            {
                temp[0] = citations[right];
                for(int i =right-1;i>=left;i--)
                {
                    citations[i+1]=citations[i];
                    
                }
                citations[left]=temp[0];
                
                right++;
                middle++;
                
            }
            left++;
            
        }
       }
    
    }
 public void sortCitationIndex()
 {
     
     IndexNode  test;
     int count=0;
     test=citationIndex;
     while(test!=null)
     {
         citations[count]=test.getCitation();
         ++count;
         test =test.getLink();
     }
       
       mergeSort(0,count-1);
      int num =numCitations;
    
      IndexNode current=null;
 
      int id=num-1;
      for(int i=0;i<num;++i)
      {
          
        IndexNode temp= new IndexNode(citations[id]);
        temp.setLink(current);
        current=temp;
        --id;
     
      }
      citationIndex=current;
 
          updateIDs();     
        
 }

 public void printCitationIndex()
 {
          IndexNode test;
        
       for(int i=0;i<numCitations;++i){
            test=citationIndex;
        while(test!=null)
                {
                    if(test.getCitation().getID()==i+1)
                    {
                    Citation temp =test.getCitation();
                    if(formatType.equalsIgnoreCase("acm"))
                   { 
                       
                        System.out.println(temp.formatACM());
                   }
                   else if(formatType.equalsIgnoreCase("apa"))
                   { 
                       
                        System.out.println(temp.formatAPA());
                   }
                   else
                   {
                       
                        System.out.println(temp.formatIEEE());
                   }
                    test=test.getLink();
                    }else
                    test = test.getLink();
                }
       }
      
 }
 public int deleteCitation(String name)
 {
    boolean yes =false;
     
     
     sortCitationIndex();
     IndexNode current=citationIndex;
     IndexNode temp2=current;
     IndexNode temp=current;
    
    while(current!=null){
        
     if(name.length()<=current.getName().length())
     {
         
            if(name.equalsIgnoreCase(current.getName())||name.equalsIgnoreCase(current.getName().substring(0, name.length())))
            temp2=temp;
            yes=true;
         
     }
    
         
    temp=current;
    current=current.getLink();
    }
     if(yes==true){
     //System.out.println("Citations "+name+" deleted");
     current=temp2;
     temp=temp2.getLink();
     if(temp.getLink()==null)
         current.setLink(null);
     current.setLink(temp.getLink());
     --numCitations;
          
    int updateID=numCitations;   
    IndexNode update=citationIndex;
   while(update!=null)
    {
        update.getCitation().updateID(updateID);
        --updateID;
        update=update.getLink();
    } 
   updateIDs();
   return 0;
   
     }
        else
            System.out.println("Could not find "+name);
            
    
    
     return -1;
     
 }
 // Pre-condition: Thia citation exists in the index
 // This deletes the citation from the index and returns -1 if unsuccessful and 
// 0 otherwise
   public String[] searchByName( String key)
   {
       Citation [] citationNames=new Citation[numCitations];
       String[] citation;
       boolean yes =false;
       IndexNode current=citationIndex;
       int cites=0;
    
     while(current!=null)
     {
        
            String[] name=current.getCitation().getName().split(" ");
            String[] test =key.split(" ");
            for(int j=0;j<name.length;j++)
            {   for(int k=0;k<test.length;++k)
               {
                   if(test[k].equalsIgnoreCase("a")||test[k].equalsIgnoreCase("the")||test[k].equalsIgnoreCase("of"))
                   {}
            
                   else if(name[j].compareToIgnoreCase(test[k])==0||current.getCitation().getName().compareToIgnoreCase(test[k])==0)
                {
                    
                   citationNames[cites]=current.getCitation();
                   yes=true;
                   cites++;
                   k=test.length;
                   j=name.length;
                }
            }
            }
         current=current.getLink();
         }
         
     
     if(yes==true)
     {
         citation=new String[cites];
        for(int i=0;i<cites;++i)
        {
                   if(formatType.equalsIgnoreCase("acm"))
                   { 
                       citation[i]= citationNames[i].formatACM();
                        System.out.println(citationNames[i]);
                   }
                   else if(formatType.equalsIgnoreCase("apa"))
                   { 
                       citation[i]= citationNames[i].formatAPA();
                        System.out.println(citationNames[i]);
                   }
                   else
                   {
                       citation[i]= citationNames[i].formatIEEE();
                        //System.out.println(citationNames[i]);
                   }
            }
            
        }else
     {
         citation=new String[1];
         citation[0]="Citation was not found";
     }
     return citation;
         
     }
  // Searches the Citations by name for the key provided and returns the CITATIONS that match
 // them formatted according to the format type
 // If there are no matching Citations return "There are no citations with that 
 // name"
  
  public String[] searchByKeyword( String key)
  {
      String [] citationNames;
      boolean yes =false;
       KeywordNode current=keywordIndex;
     
     KeywordNode temp=current;
    
     while(current!=null)
     {
         if(current.getName().equalsIgnoreCase(key))
         {temp=current;
         yes=true;
         }
         current=current.getLink();
     }
     if(yes==true)
     {
        int[] cits= temp.getIds();
        int nums =temp.getNumId();
        citationNames =new String[nums];
        for(int i=0;i<nums;++i)
        {
           
            IndexNode currentC =citationIndex; 
            while(currentC!=null)
            {
                if(currentC.getCitation().getID()==cits[i])
                {
                   if(formatType.equalsIgnoreCase("acm"))
                   { 
                       citationNames[i]= currentC.getCitation().formatACM();
                        System.out.println(citationNames[i]);
                   }
                   else if(formatType.equalsIgnoreCase("apa"))
                   { 
                       citationNames[i]= currentC.getCitation().formatAPA();
                        System.out.println(citationNames[i]);
                   }
                   else
                   {
                       citationNames[i]= currentC.getCitation().formatIEEE();
                        //System.out.println(citationNames[i]);
                   }
                      
                   
                  
                }
                currentC=currentC.getLink();
            }
            
        }
            
         
     }else
     {
          citationNames=new String[1];
          citationNames[0]="No Citations found";
      }
        
   
     return citationNames;
  }
 // Searches the Keywords for the key provided and returns the CITATIONS that match
 // them formatted according to the format type
 // If there are no matching Citations return "There are no citations that match that 
 //keyword"
 
 public int insertCitationInPos(Citation myCitation)
 {
     sortCitationIndex();
     Citation aCitation=myCitation;
     String name =aCitation.getName();
     sortCitationIndex();
     IndexNode current=citationIndex;
     IndexNode temp=current;
     IndexNode inCit = new IndexNode(aCitation);
     while(name.compareToIgnoreCase(current.getName())>0)
     {
         temp=current;
         current=current.getLink();
     }
            current=temp;
            inCit.setLink(current.getLink());
            current.setLink(inCit);
   current=current.getLink();
   updateKeywords(current.getCitation());
   ++numCitations;
     updateIDs();
     
     return 0;
 }
 // Pre-condition : The citations are already sorted
 //This inserts a Citation in the correct position alphabetically into the Citation list
 // This is different from addCitation which just adds a citation to the end of the
 //list. This returns -1 if unsuccessful and 0 otherwise
  public void sortKeywords()
    {
       Keyword[] keywords =new Keyword[numKeywords];
        Keyword[] temp =new Keyword[1];
        int count=0;
        if(keywordIndex.getLink()==null)
        {System.out.println("There are no keywords in the index");}
        KeywordNode keys=keywordIndex;
        while(keys!=null)
        {
            keywords[count]=keys.getKeyword();
            keys=keys.getLink();
            ++count;
        }
        //keywords[count]=keys.getKeyword();
        for(int a=0;a<numKeywords;a++)
        {
            for(int b=a+1;b<numKeywords;b++)
            {   
                if(keywords[a].getName().compareToIgnoreCase(keywords[b].getName())<0)
                {
                   
                    temp[0] = keywords[a];
                    keywords[a]=keywords[b];
                    keywords[b]=temp[0];
                }
            }
        }
            keywordIndex=null;
            numKeywords=0;
             for(int f=0;f<keywords.length;++f)
             {
                KeywordNode tempa = new KeywordNode(keywords[f]);
                tempa.setLink(keywordIndex);
                keywordIndex=tempa;
                
                ++numKeywords;
             }
        
        
       
        
        
    }
  
  public void clearIndex()
  {
      keywordIndex=null;
      citationIndex=null;
      numCitations=0;
      System.out.println("Citation index is empty");
  }
  // Empties the index of all citatnuions (and keywords)
 

}
