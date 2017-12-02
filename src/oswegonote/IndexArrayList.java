/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oswegonote;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;


/**
 *
 * @author keith
 */
public class IndexArrayList implements IndexInterface {
    Scanner inputDevice = new Scanner(System.in);
    ArrayList<Citation> citationIndex=new ArrayList(100);
    ArrayList<Keyword> keywordIndex=new ArrayList(250);
    
    private int numKeywords=0;
    private int numCitations=0;
    private String file;
    private String formatType;
    private int totalNumCitations;
    //constructor sets up new citation
    public IndexArrayList(int totalNumCitations)
    {
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
        if(numCitations==100)
        {
            System.out.println("You have reached the maximum number of Citations");
            return -1;
        }
        else{
        
        inCitation.updateID(numCitations+1);
        citationIndex.add(numCitations, inCitation);
       ++numCitations;
        updateKeywords(inCitation);
        
        
       
        return 0;
        }
    }
    private void updateIDs()
    {   
        keywordIndex.clear();
        
        numKeywords=0;
        int i=0;
        while(i<numCitations)
        {
            citationIndex.get(i).updateID(i+1);
            updateKeywords(citationIndex.get(i));
            i++;
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
                
                Keyword newKeyword = new Keyword(keyword[i].getName(),inCitation.getID());
               
                keywordIndex.add(numKeywords,newKeyword );
                ++numKeywords;
                
            }
            else if(test>0)
            {
                keywordIndex.get(test).addID(inCitation.getID());
                
            }
            
        }
        
    }
    private int findKeywordInList(Keyword k)
    {
        
        int result=-1;
        
        for(int i=0;i<numKeywords;++i)
        {
            String testName=keywordIndex.get(i).getName();
            
            if(testName.equalsIgnoreCase(k.getName()))        
          {
              result=i;
          }
        }
       
        return result;
    }
    public boolean isEmpty()
    {
        if(numCitations==0)
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
        for(int i=0;i<numKeywords;++i)
        {
            System.out.println(keywordIndex.get(i).getName());
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
          System.out.println("file path does not exist");
          System.exit(1);
       }

        //starts loop for saving in format
        
        
       for(int i=0;i<numCitations;++i)
       {
                    out.println(citationIndex.get(i).formatIEEE());
             
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
          System.out.println("file path does not exist");
          System.exit(1);
       }

        //starts loop for saving in format
        
        
       for(int i=0;i<numCitations;++i)
       {
                    out.println(citationIndex.get(i).formatACM());
             
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
          System.out.println("file path does not exist");
          System.exit(1);
       }

        //starts loop for saving in format
        
        
       for(int i=0;i<numCitations;++i)
       {
                    out.println(citationIndex.get(i).formatAPA());
             
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
            if(citationIndex.get(left).getName().compareToIgnoreCase(citationIndex.get(right).getName())>0)
            {
                temp[0] = citationIndex.get(right);
                for(int i =right-1;i>=left;i--)
                {
                    citationIndex.set(i+1,citationIndex.get(i));
                    
                }
                citationIndex.set(left,temp[0]);
                
                
                right++;
                middle++;
                
            }
            left++;
            
        }
       }
    
    }
 public void sortCitationIndex()
 {
       mergeSort(0,numCitations-1);
       updateIDs();     
        
 }
 public void printCitationIndex()
 {
      for(int i=0;i<numCitations;++i)
        {
                   if(formatType.equalsIgnoreCase("acm"))
                   { 
                        System.out.println(citationIndex.get(i).formatACM());
                   }
                   else if(formatType.equalsIgnoreCase("apa"))
                   { 
                        System.out.println(citationIndex.get(i).formatAPA());
                   }
                   else
                   {
                        System.out.println(citationIndex.get(i).formatIEEE());
                   }
            }
 }

 public int deleteCitation(String name)
 {
    boolean yes=false;
    int delete=-1;
    sortCitationIndex();
    for(int i=0;i<numCitations;++i)
    {
        if(name.length()<=citationIndex.get(i).getName().length())
        {
            if(name.equalsIgnoreCase(citationIndex.get(i).getName())||name.equalsIgnoreCase(citationIndex.get(i).getName().substring(0, name.length())))
            {
                delete=i;
                i=numCitations;
                yes=true;
            }
        }
    }
    if(yes==true)
    {
        
            citationIndex.remove(delete);
            --numCitations;
            updateIDs();
            return 0;
        
    }
    else
        return -1;
 }
 // Pre-condition: Thia citation exists in the index
 // This deletes the citation from the index and returns -1 if unsuccessful and 
// 0 otherwise
   public String[] searchByName( String key)
   {
       Citation [] citationNames=new Citation[numCitations];
       String[] citation;
       int cites=0;
       boolean yes =false;
        for(int i=0;i<numCitations;++i)
        {
        
            String[] name=citationIndex.get(i).getName().split(" ");
            String[] test =key.split(" ");
            
            for(int j=0;j<name.length;j++)
            {
               for(int k=0;k<test.length;++k)
               {
                   if(test[k].equalsIgnoreCase("a")||test[k].equalsIgnoreCase("the")||test[k].equalsIgnoreCase("of")||test[k].equalsIgnoreCase("for"))
                   {}
                   else if(name[j].compareToIgnoreCase(test[k])==0||citationIndex.get(i).getName().compareToIgnoreCase(test[k])==0)
                {
                    
                   citationNames[cites]=citationIndex.get(i);
              
                   ++cites;
                   yes=true;
                   j=name.length;
                   k=test.length;
                }
               }
            }
        }
     if(yes==true)
     {
         citation=new String[cites];
        for(int i=0;i<cites;++i)
        {
                   if(formatType.equalsIgnoreCase("acm"))
                   { 
                       citation[i]= citationNames[i].formatACM();
                        //System.out.println(citationNames[i]);
                   }
                   else if(formatType.equalsIgnoreCase("apa"))
                   { 
                       citation[i]= citationNames[i].formatAPA();
                        //System.out.println(citationNames[i]);
                   }
                   else
                   {
                       citation[i]= citationNames[i].formatIEEE();
                        //System.out.println(citationNames[i]);
                   }
            }
            return citation;
        }else 
     {
         citation=new String[1];
         citation[0]="Citation was not found";
     
     return citation;
     } 
     }
  // Searches the Citations by name for the key provided and returns the CITATIONS that match
 // them formatted according to the format type
 // If there are no matching Citations return "There are no citations with that 
 // name"
  
  public String[] searchByKeyword( String key)
  {
      String [] citationNames;
      boolean yes =false;
      int[] citeIds=new int[numCitations];
      int actCites=0;
    for(int i=0;i<numKeywords;++i)
     {
         if(keywordIndex.get(i).getName().equalsIgnoreCase(key))
         {
            citeIds=keywordIndex.get(i).getCitationsID();
            actCites=keywordIndex.get(i).getNumCitationIDs();
            yes=true;
           
         }
     }
         
    
     if(yes==true)
     {
         citationNames = new String[actCites];
        for(int i=0;i<actCites;++i)
        {
            
            
                   if(formatType.equalsIgnoreCase("acm"))
                   { 
                       citationNames[i]= citationIndex.get(citeIds[i]-1).formatACM();
                        System.out.println(citationNames[i]);
                   }
                   else if(formatType.equalsIgnoreCase("apa"))
                   { 
                       citationNames[i]= citationIndex.get(citeIds[i]-1).formatAPA();
                        System.out.println(citationNames[i]);
                   }
                   else
                   {
                       citationNames[i]= citationIndex.get(citeIds[i]-1).formatIEEE();
                        //System.out.println(citationNames[i]);
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
     Citation temp1=myCitation;
     Citation temp2;
     String name =myCitation.getName();
     sortCitationIndex();
     
     int set=numCitations+1;
     for(int i=0;i<numCitations;++i)
     {
         if(name.compareToIgnoreCase(citationIndex.get(i).getName())>0)
     {
         temp1=citationIndex.get(i);
         
     }
     }
     ++numCitations;
     
        int j=temp1.getID();
        if(j==-1||j==numCitations-1)
        {citationIndex.add(numCitations-1, temp1); }
        else{
         citationIndex.set(j-1,myCitation);
       
        while(j<numCitations-1)
        {
            temp2=citationIndex.get(j);
            citationIndex.set(j,temp1);
            temp1=temp2;
            ++j;
        }
        citationIndex.add(j-1,temp1);
        }
        updateIDs();
     
     return 0;
     }
    
     
     

     


 // Pre-condition : The citations are already sorted
 //This inserts a Citation in the correct position alphabetically into the Citation list
 // This is different from addCitation which just adds a citation to the end of the
 //list. This returns -1 if unsuccessful and 0 otherwise
  public void sortKeywords()
    {
        
        Keyword[] temp =new Keyword[2];
        for(int a=0;a<numKeywords;a++)
        {
            for(int b=a+1;b<numKeywords;b++)
            {   
                if(keywordIndex.get(a).getName().compareToIgnoreCase(keywordIndex.get(b).getName())>0)
                {
                   
                    temp[0] = keywordIndex.get(a);
                    temp[1] = keywordIndex.get(b);
                    keywordIndex.set(a,temp[1]);
                    keywordIndex.set(b,temp[0]);
                }
            }
        }
       
        
    }
 

 
 
 
 


  
  public void clearIndex()
  {
      keywordIndex.clear();
      citationIndex.clear();
      numCitations=0;
      System.out.println("Citation index is empty");
  }
  // Empties the index of all citatnuions (and keywords)
 

}
