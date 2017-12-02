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
public class IndexHashTable implements IndexInterface
{
    Scanner inputDevice = new Scanner(System.in);
    private Citation[] citationIndex=new Citation[100];
    private HashNode[] hashTable= new HashNode[400];
    private String ignoreString="of,the,this,his,her,it,its,a,an,and,those,if,or,for";
    private String [] ignore =ignoreString.split(",");
    private int totalNumCitations;
    private int numCitations,numKeywords;
    private String file;
    private String formatType;
 public IndexHashTable(int numCitations)
 {
   this.totalNumCitations+=numCitations;
   if(totalNumCitations>100)
   {
      System.out.println("You have exceeded the total number of Citations allowed");
   }

 }
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
public void printCitationIndex()
    {
        //sortCitationIndex();
        if(numCitations==0)
        {
            System.out.println("There are no Citations currently stored in the Index");
        }
        if(formatType==null)
        {
         System.out.println("How would you like to format your citation today?(IEEE,ACM,APA)");
       String style = inputDevice.nextLine();
       switch(style)
       {
           case "IEEE":
           case "ieee":
               setFormatType("IEEE");
               break;
           case "ACM":
           case "acm":
               setFormatType("ACM");
               break;
           case "APA":
           case "apa":
               setFormatType("APA");
               break;
       }
        }

           String[] citationNames=new String[numCitations];
            if(formatType.equalsIgnoreCase( "ieee"))
            for(int i=0;i<numCitations;i++)
            {
                System.out.println(citationNames[i]=citationIndex[i].formatIEEE());
            }
        if(formatType.equalsIgnoreCase( "acm"))
            for(int i=0;i<numCitations;i++)
            {
                System.out.println( citationNames[i]=citationIndex[i].formatACM());
            }
        if(formatType.equalsIgnoreCase( "apa"))
            for(int i=0;i<numCitations;i++)
            {
                 System.out.println(citationNames[i]=citationIndex[i].formatAPA());
            }
        
    }
// Pre-condition - the index is not empty and has a format type
//Prints out all the citations in the index formatted according to its format type

public void printKeywords()
{
    for(int i=0;i<hashTable.length;++i)
    {
        HashNode current=hashTable[i];
        while(current!=null)
        {
            System.out.println(current.getName());
            current=current.getLink();
        }
    }
}
// Pre-condition - there are keywords in the Index
// Prints out all the keywords in the Index

public boolean isEmpty()
{
    if(numCitations==0)
        return true;
    else
        return false;
                
}
//Returns whether or not the Index is empty

public boolean isFull()
{
    if(numCitations==100)
        return true;
    else
        return false;
}
//Returns whether or not the Index is full

public int addCitation(Citation inCitation)
{
   
     if(numCitations==100)
     {
         System.out.println("You have exceeded the maximum number of Citations allowed.");
         return -1;
     }else{
     inCitation.updateID(numCitations+1);
     citationIndex[numCitations]=inCitation;
     updateKeywords(citationIndex[numCitations]);
     ++numCitations;
     return 0;
     }
}
public void updateKeywords(Citation inCitation)
{
    String[] citName;
    boolean pass;
    int numKeys =inCitation.getNumberOfKeywords();
    int ids = inCitation.getID();
    citName=inCitation.getName().split(" ");
    Keyword[] addedKeys = new Keyword[20];
    Keyword[] tempKey = inCitation.getKeywords();
    for(int j=0;j<numKeys;++j)
    {
        addedKeys[j]=tempKey[j];
    }
    for(int i=0;i<citName.length;++i)
    {
        pass=false;
        for(int j=0;j<ignore.length;++j)
        {
            if(citName[i].equalsIgnoreCase(ignore[j])||citName[i].length()<3)
                pass=true;
        }
        if(pass==false)
        {
            addedKeys[numKeys]= new Keyword(citName[i],ids);
            ++numKeys;
        }
    }
    
    
    int hashKey;
    for(int i=0;i<numKeys;++i)
        {
            hashKey = hashFunction(addedKeys[i].getName());
            int test=findKeywordInList(addedKeys[i]);
            if(test<0)
            {
                
                
                HashNode temp = new HashNode(addedKeys[i].getName());
                temp.addCitation(inCitation.getID()-1);
                temp.setLink(hashTable[hashKey]);
                hashTable[hashKey]=temp;
                
                ++numKeywords;
            }
            else if(test>0)
            {
                
                HashNode current=hashTable[hashKey];
                HashNode temp=current;
                while(current!=null)
                {
                    if(addedKeys[i].getName().equalsIgnoreCase(current.getName()))
                    {
                        temp=current;
                    }
                    current=current.getLink();
                }
                        temp.addCitation(inCitation.getID()-1);
                        
            }
            
        }
}
private int findKeywordInList(Keyword k)
    {
        int hashKey = hashFunction(k.getName());
        int result=-1;
        HashNode current = hashTable[hashKey];
        while(current!=null)
        {
            if(current.getName().equalsIgnoreCase(k.getName()))        
          {
              result=1;
          }
            current=current.getLink();
        }
        return result;
    }
// Adds a citation to the Index and returns -1 if unsuccessful and 0 otherwise
// The Index needs to keep track of which keywords match which citation
public int hashFunction(String key)
{
    int  hashNum=0;
    char a=key.toLowerCase().charAt(0);
    char b=key.toLowerCase().charAt(1);
    char c=key.toLowerCase().charAt(2);
    int ascii =98;
    if(a>98)
        hashNum +=100;
    if(a>106)
        hashNum +=100;
    if(a>114)
        hashNum +=100; 
    while(b>ascii)
    {
        hashNum+=10;
        if(ascii<104)
            ascii+=2;
        else
            ascii+=3;
    }
    ascii=98;
    while(c>ascii)
    {
        hashNum+=1;
        if(ascii<104)
            ascii+=2;
        else
            ascii+=3;
    }
   
    return hashNum;
    
}
 public void setFormatType(String s)
 {
     formatType =s;
 }
// Sets the format type to "IEEE", "APA" or "ACM"
 
 public String getFormatType()
 {
     return formatType;
 }
 // Returns the format type of the Index ("IEEE", "APA", "ACM")
 
    public  void formatIEEE(String path)
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
        for(int i=0;i<numCitations;++i)
        { 
            out.println(citationIndex[i].formatIEEE());
        } 
            out.flush();
            out.close();
    }
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

        for(int i=0;i<citationIndex.length;++i)
        { 
            out.println(citationIndex[i].formatACM());
        } 
            out.flush();
            out.close();
    }
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

        for(int i=0;i<citationIndex.length;++i)
        { 
            out.println(citationIndex[i].formatAPA());
        } 
            out.flush();
            out.close();
    }
 
 public void format(String path)
 {
     file=path;
     saveFile();
 }
  // Formats all the citations in the index in the format indicated by formatType
 // and saves it to the file indicated by path
 
 public void sortCitationIndex()
 { 
     mergeSort(0,numCitations-1);
     updateIDs();
 }
 private void updateIDs()
 {
        for(int i=0;i<hashTable.length;++i)
        {
            hashTable[i]=null;
        }
        Citation[] temp=citationIndex;
        
        int tempCount=numCitations;
        numCitations=0;
        for(int i=0;i<tempCount;++i)
        {
            addCitation(temp[i]);
        }

 }
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
            if(citationIndex[left].getName().compareToIgnoreCase(citationIndex[right].getName())>0)
            {
                temp[0] = citationIndex[right];
                for(int i =right-1;i>=left;i--)
                {
                    citationIndex[i+1]=citationIndex[i];
                    
                }
                citationIndex[left]=temp[0];
                
                right++;
                middle++;
                
            }
            left++;
            
        }
       }
    
    }
 // sorts the citations in the index alphabetically by name using either the merge
 // or quick sort
 
 public void sortKeywords()
 {
     
 }
 // sorts the keywords entered in alphabetical order by name using either the merge
 //or quick sort
 
 public int insertCitationInPos(Citation myCitation)
    {   
        sortCitationIndex();
        if(numCitations==100)
        {return -1;}
        else 
        {
        citationIndex[numCitations]=myCitation;
        citationIndex[numCitations].updateID(numCitations+1);
        for(int i=0;i<numCitations;++i)
        {
            if(citationIndex[numCitations].getName().compareToIgnoreCase(citationIndex[i].getName())<0)
            {
                Citation[] temp = new Citation[2];
                temp[0]=citationIndex[i];
                citationIndex[i]=citationIndex[numCitations];
                ++numCitations;
                for (int j=i+1;j<numCitations;++j)
                {
                    temp[1]=citationIndex[j];
                    citationIndex[j]=temp[0];
                    temp[0]=temp[1];
                }
                citationIndex[numCitations]=temp[0];
                citationIndex[i].updateID(i+1);
                updateKeywords(citationIndex[i]);
                i=numCitations;
            }
        }
        
        }
        
        updateIDs();
        
        return 0;
    }
 
 public int deleteCitation(String name)
 {      
      HashNode current;
      HashNode temp=null;
      String[] citationNames;
      String[] search=name.split(" ");
      String[]keep= new String[name.length()];
      int numKeys=0;
      int hashKey;
      int[] cits;
      boolean pass;
      for(int i=0;i<search.length;++i)
        {
            pass=false;
            for(int j=0;j<ignore.length;++j)
            {
                if(search[i].equalsIgnoreCase(ignore[j])||search[i].length()<3)
                pass=true;
            }
            if(pass==false)
            {
            keep[numKeys]= search[i];
            ++numKeys;
            }
        }
      pass=false;
     for(int j=0;j<numKeys;++j)
     {
         hashKey=hashFunction(keep[j]);
         current=hashTable[hashKey];
         while(current!=null)
         {
             if(current.getName().equalsIgnoreCase(keep[j]))
             {
                 temp=current;
                 current=null;
                 j=numKeys;
                 pass=true;
             }else
                 current=current.getLink();
         }
     }
     if(pass==true)
     {
         cits=temp.getCitations();
         for(int i=cits[0]+1;i<numCitations;++i)
         {
             citationIndex[i-1]=citationIndex[i];
         }
         --numCitations;
         updateIDs();
        return 0;
     }
     else
     {
         return -1;
     }
 }
 // Pre-condition: Thia citation exists in the index
 // This deletes the citation from the index and returns -1 if unsuccessful and 
// 0 otherwise
public int[] andSort(String[] ands)
{
    int[]citIds=new int[numCitations];
    int[]keepCits;
    int keep=0;
    int ids=0;
    int[] keys= new int[ands.length];
    for(int i=0;i<ands.length;++i)
    {
        keys[i]=hashFunction(ands[i]);
    }
    for(int j=0;j<keys.length;++j)
    {
        if(hashTable[keys[j]]==null){}
        else
        {
            HashNode current=hashTable[keys[j]];
            while(current!=null)
            {
                int cits[]=current.getCitations();
                
                    for(int t=0;t<cits.length;++t)
                    {
                        citIds[cits[t]]+=1;
                        
                    }
                
                current=current.getLink();
            }
        }
    }
   for(int i=0;i<numCitations;++i)
   {
       if(citIds[i]==ands.length)
       {
           ++keep;
       }
   }
   keepCits=new int[keep];
   int k=0;
   for(int i=0;i<numCitations;++i)
   {
       if(citIds[i]==ands.length)
       {
           keepCits[k]=i;
           ++k;
       }
       
   }
        
    return keepCits;

        
}
public int[] orSort(String[] ors)
{
   int[]citIds=new int[numCitations];
    int[]keepCits;
    int keep=0;
    int[] keys= new int[ors.length];
    for(int i=0;i<ors.length;++i)
    {
        keys[i]=hashFunction(ors[i]);
    }
    for(int j=0;j<keys.length;++j)
    {
        if(hashTable[keys[j]]==null){}
        else
        {
            HashNode current=hashTable[keys[j]];
            while(current!=null)
            {
                int cits[]=current.getCitations();
                
                    for(int t=0;t<cits.length;++t)
                    {
                        citIds[cits[t]]+=1;
                        
                    }
                
                current=current.getLink();
            }
        }
    }
   for(int i=0;i<numCitations;++i)
   {
       if(citIds[i]>0)
       {
           ++keep;
       }
   }
   keepCits=new int[keep];
   int k=0;
   for(int i=0;i<numCitations;++i)
   {
       if(citIds[i]>0)
       {
           keepCits[k]=i;
           ++k;
       }
       
   }
        
    return keepCits;

}
 public String[] searchByKeyword( String key)
 {
     boolean yes =false;
     int numCits=0;
     int[] cits;
     String[] citationNames;
     String andKeys[]=key.split("&");
     String orKeys[]=key.split("|");
     String phraseKeys[];
     if(andKeys.length>1)
     {
         cits=andSort(andKeys);
         yes=true;
     }
     else if(orKeys.length>1)
     {
         key.replace("|", " ");
         orKeys=key.split(" ");
         cits=orSort(orKeys);
         yes=true;
     }
     else
     {
         phraseKeys=key.split(" ");
         int[] temp=new int[phraseKeys.length];
         int[] hashKey=new int[phraseKeys.length];
         for(int i=0;i<phraseKeys.length;++i)
            {
                hashKey[i] =hashFunction(phraseKeys[i]);
            }
         for(int j=0;j<hashKey.length;++j)
         {
             if(hashTable[hashKey[j]]==null){}
             else
             {
                HashNode current=hashTable[hashKey[j]];
                while(current!=null)
                {
                
                int citId[]=current.getCitations();
                for(int t=0;t<citId.length;++t)
                {
                    temp[numCits]=citId[t];
                    ++numCits;
                }
                current=current.getLink();
                 }
             }
         }
         cits=new int[numCits];
         for(int i=0;i<numCits;++i)
         {
             cits[i]=temp[i];
         }
     
     
     }
     if(yes==true)
     {
         
         citationNames=new String[cits.length];
         
            if(formatType.equalsIgnoreCase( "ieee"))
            for(int i=0;i<cits.length;i++)
            {
                citationNames[i]=citationIndex[cits[i]].formatIEEE();
            }
        if(formatType.equalsIgnoreCase( "acm"))
            for(int i=0;i<cits.length;i++)
            {
                citationNames[i]=citationIndex[cits[i]].formatACM();
            }
        if(formatType.equalsIgnoreCase( "apa"))
            for(int i=0;i<cits.length;i++)
            {
                citationNames[i]=citationIndex[cits[i]].formatAPA();
            }
         
     }else
     {
         citationNames=new String[1];
         citationNames[0]="Sorry, no matches were found";
     }
     return citationNames;
  }
 // Searches the Keywords for the key provided and returns the CITATIONS that match
 // them formatted according to the format type
 // If there are no matching Citations return "There are no citations that match that 
 //keyword"
 
  public String[] searchByName( String key)
  {
      HashNode current;
      HashNode temp=null;
      String[] citationNames;
      String[] search=key.split(" ");
      String[]keep= new String[key.length()];
      int numKeys=0;
      int hashKey;
      int[] cits;
      boolean pass;
      for(int i=0;i<search.length;++i)
        {
            pass=false;
            for(int j=0;j<ignore.length;++j)
            {
                if(search[i].equalsIgnoreCase(ignore[j])||search[i].length()<3)
                pass=true;
            }
            if(pass==false)
            {
            keep[numKeys]= search[i];
            ++numKeys;
            }
        }
      pass=false;
     for(int j=0;j<numKeys;++j)
     {
         hashKey=hashFunction(keep[j]);
         current=hashTable[hashKey];
         while(current!=null)
         {
             if(current.getName().equalsIgnoreCase(keep[j]))
             {
                 temp=current;
                 current=null;
                 j=numKeys;
                 pass=true;
             }else
                 current=current.getLink();
         }
     }
     if(pass==true)
     {
         cits=temp.getCitations();
         citationNames=new String[cits.length];
         if(formatType.equalsIgnoreCase( "ieee"))
            for(int i=0;i<cits.length;i++)
            {
                citationNames[i]=citationIndex[cits[i]].formatIEEE();
            }
        if(formatType.equalsIgnoreCase( "acm"))
            for(int i=0;i<cits.length;i++)
            {
                citationNames[i]=citationIndex[cits[i]].formatACM();
            }
        if(formatType.equalsIgnoreCase( "apa"))
            for(int i=0;i<cits.length;i++)
            {
                citationNames[i]=citationIndex[cits[i]].formatAPA();
            }  
     }
     else
     {
         citationNames=new String[1];
         citationNames[0]="There are no citations with that name";
     }
     return citationNames;
      
  }
  // Searches the Citations by name for the key provided and returns the CITATIONS that match
 // them formatted according to the format type
 // If there are no matching Citations return "There are no citations with that 
 // name"
  
  public void clearIndex()
  {
      for(int i=0;i<numCitations;++i)
      {
          citationIndex[i]=null;
      }
      numCitations=0;
      for(int j=0;j<400;++j)
      {
          hashTable[j]=null;
      }
  }
  // Empties the index of all citations (and keywords)


}
