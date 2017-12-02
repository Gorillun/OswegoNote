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
import java.util.Scanner;
import java.io.*;
public class IndexArray implements IndexInterface
{   
    //declaring variables
    Scanner inputDevice = new Scanner(System.in);
    private Keyword[] keywordIndex = new Keyword[250];
    private int numKeywords;
    private int numCitations=0;
    private Citation[] citationIndex=new Citation[100];
    private String file;
    private String formatType;
    private int totalNumCitations;
    //constructor sets up new citation
    public IndexArray(int totalNumCitations)
    {
        this.totalNumCitations += totalNumCitations;
        if(totalNumCitations==99)
        {System.out.println("Citation Index is full");}
    }
    //attempts to add citation 
    public int addCitation(Citation inCitation)
    {
        
        
        citationIndex[numCitations]= inCitation;
        citationIndex[numCitations].updateID(numCitations+1);
        ++numCitations;
        if(numCitations>totalNumCitations)
        {
            System.out.println("You have reached the maximum number of Citations");
            return -1;
        }
       updateKeywords(inCitation);
        return 0;
        
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
                keywordIndex[numKeywords]=new Keyword(keyword[i].getName(),inCitation.getID());
                inCitation.setKeywordIndex(numKeywords);
                
                ++numKeywords;
            }
            else
            {
                keywordIndex[test].addID(inCitation.getID());
                inCitation.setKeywordIndex(test);
            }
        }
        
    }
    private int findKeywordInList(Keyword k)
    {
        int result=-1;
        for(int i=0;i<numKeywords;++i)
        {
          if(keywordIndex[i].getName().equalsIgnoreCase(k.getName()))        
          {result=i;}
        }
        return result;
    }
    //sorts keywords for insertion
    public void insertionSort()
    {
       String key;
        Keyword[] temp =new Keyword[1];
        for(int a=0;a<numKeywords;a++)
        {
            for(int b=a+1;b<numKeywords;b++)
            {   
                if(keywordIndex[a].getName().compareToIgnoreCase(keywordIndex[b].getName())>0)
                {
                   
                    temp[0] = keywordIndex[a];
                    keywordIndex[a]=keywordIndex[b];
                    keywordIndex[b]=temp[0];
                }
            }
        }
       
        
        
    }
//binary search that splits between citation or keyword searches
    public String[] searchByKeyword(String key)
    {
         String[] citationNames;

            insertionSort();//preparing for binary search
            int result=keywordBinarySearch(0,numKeywords,key);
            if(result==-1)
            {
                citationNames=new String[1];
                citationNames[0]= "no matches";
                return citationNames;
            }
            int numKeyCits=keywordIndex[result].getNumCitationIDs();
            int[] ids;
            ids = keywordIndex[result].getCitationsID();
            citationNames =new String[numKeyCits];
        if(formatType.equalsIgnoreCase( "ieee"))
            for(int i=0;i<numKeyCits;i++)
            {
                citationNames[i]=citationIndex[ids[i]-1].formatIEEE();
            }
        if(formatType.equalsIgnoreCase( "acm"))
            for(int i=0;i<numCitations;i++)
            {
                citationNames[i]=citationIndex[ids[i]-1].formatACM();
            }
        if(formatType.equalsIgnoreCase( "apa"))
            for(int i=0;i<numCitations;i++)
            {
                citationNames[i]=citationIndex[ids[i]-1].formatAPA();
            }
        return citationNames;
        
    
    }
    public String[] binarySearch(String index, String key)
    {
        String[] citationNames;
        if(index.equals("none"))
        {
            insertionSort();//preparing for binary search
            int result=keywordBinarySearch(0,numKeywords,key);
            if(result==-1)
            {
                citationNames=new String[1];
                citationNames[0]= "no matches";
                return citationNames;
            }
            int numKeyCits=keywordIndex[result].getNumCitationIDs();
            //int numIds=keywordIndex[result].getNumCitationIDs();
            int[] ids;
            ids = keywordIndex[result].getCitationsID();
            citationNames =new String[numKeyCits];
            Citation[] tempCits = new Citation[numKeyCits];
            for(int i=0;i<numKeyCits;i++)
            {
                int x =ids[i]-1;
                tempCits[i]=citationIndex[x];
                
            }
            citationIndex=tempCits;
            for(int i =0;i<numKeyCits;i++)
            {
                 
                 citationIndex[i].updateID(i);
                 
            }
            
           numCitations=numKeyCits;
           mergeSort(0,numCitations-1);//preparing for output
           upDate();
            
        //output for user
        if(formatType.equalsIgnoreCase( "ieee"))
            for(int i=0;i<numCitations;i++)
            {
                citationNames[i]=citationIndex[i].formatIEEE();
            }
        if(formatType.equalsIgnoreCase( "acm"))
            for(int i=0;i<numCitations;i++)
            {
                citationNames[i]=citationIndex[i].formatACM();
            }
        if(formatType.equalsIgnoreCase( "apa"))
            for(int i=0;i<numCitations;i++)
            {
                citationNames[i]=citationIndex[i].formatAPA();
            }
        return citationNames;
          }     
        if(key.endsWith("none"))
            //searching citation
         mergeSort(0,numCitations-1);//preparing for binary search
         upDate();
         int[] result=citationBinarySearch(index);
         if(result.length==0)
            {
                citationNames=new String[1];
                citationNames[0]= "no matches";
                return citationNames;
            }
         else
         { 
             //output for user
            Citation[] tempCits = new Citation[result.length];
            for(int i=0;i<result.length;i++)
            {
                int x =result[i];
                tempCits[i]=citationIndex[x];
                
            }
            citationIndex=tempCits;
            for(int i =0;i<result.length;i++)
            {
                 
                 citationIndex[i].updateID(i);
                 
            }
            
           numCitations=result.length;
           mergeSort(0,numCitations-1);//preparing for output
           upDate();
            citationNames=new String[result.length];
            if(formatType.equalsIgnoreCase( "ieee"))
            for(int i=0;i<result.length;i++)
            {
                citationNames[i]=citationIndex[i].formatIEEE();
            }
        if(formatType.equalsIgnoreCase( "acm"))
            for(int i=0;i<result.length;i++)
            {
                 citationNames[i]=citationIndex[i].formatACM();
            }
        if(formatType.equalsIgnoreCase( "apa"))
            for(int i=0;i<result.length;i++)
            {
                 citationNames[i]=citationIndex[i].formatAPA();
            }
        return citationNames;
          } 
             
         }
      
    //called from binary search for keyword
    private void upDate()
    {
        for(int i=0;i<numCitations;i++)
        {
            int old=citationIndex[i].getID();
            citationIndex[i].updateID(i+1);
            int newId =citationIndex[i].getID();
            int[] ind=citationIndex[i].getKeywordIndex();
            int numInd = citationIndex[i].getNumberOfKeywords();
            for(int j =0;j<numInd;j++)
            {
                int t =ind[j];
                keywordIndex[t].changeID(old, newId);
                
            }
        }
    }
    
    private int keywordBinarySearch(int first, int last, String key)
    {
        if(first<last)
        {
            int mid = first +(last-first)/2;
            if(keywordIndex[mid].getName().compareToIgnoreCase(key)>0)
            {
                return keywordBinarySearch(first,mid, key);
            }
            else if(keywordIndex[mid].getName().compareToIgnoreCase(key)<0)
            {
                return keywordBinarySearch(mid+1,last,key);
            }
            else
                return mid;     
        }
        return -1;
    }
    //called from binarysearch for citation search
    private int[] citationBinarySearch(String key)
    {
        int cit=0;
        int[] citMatch=new int[numCitations];
        for(int i =0;i<numCitations;++i)
        {
            boolean match=false;
            String[] name=citationIndex[i].getName().split(" ");
            for(int j=0;j<name.length;j++)
            {
               if(name[j].compareToIgnoreCase(key)==0||citationIndex[i].getName().compareToIgnoreCase(key)==0)
                {
                   match=true;
                }
            }
           if(match==true)
           {
               citMatch[cit]=i;
               ++cit;
           }
        }
        int[] citReturn=new int[cit];
        for(int s=0;s<cit;s++)
        {
            citReturn[s]=citMatch[s];
        }
           return citReturn; 
    }
    public String[] searchByName(String key)
    {
        mergeSort(0,numCitations-1);
       Citation [] citationNames=new Citation[numCitations];
       String[] citation;
       int cites=0;
       boolean yes =false;
        for(int i=0;i<numCitations;++i)
        {
        
            String[] name=citationIndex[i].getName().split(" ");
            String[] test =key.split(" ");
            
            for(int j=0;j<name.length;j++)
            {
               for(int k=0;k<test.length;++k)
               {
                   if(test[k].equalsIgnoreCase("a")||test[k].equalsIgnoreCase("the")||test[k].equalsIgnoreCase("of"))
                   {}
                   else if(name[j].compareToIgnoreCase(test[k])==0||citationIndex[i].getName().compareToIgnoreCase(test[k])==0)
                {
                    
                   citationNames[cites]=citationIndex[i];
              
                   ++cites;
                   yes=true;
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
            return citation;
        }else 
     {
         citation=new String[1];
         citation[0]="Citation was not found";
     
     return citation;
     } 
     }
    //there are 2 loops so I have a O(n^2) merge sort
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
    public void printKeywords()
    {
        if(numKeywords==0)
        {
          System.out.println("The keyword index is currently empty");
        }
        for(int i=0;i<numKeywords;++i)
        {   
            
            System.out.println(keywordIndex[i].getName());
            
        }
    }
    public void printCitationIndex()
    {
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
        mergeSort(0,numCitations-1);//preparing for output
           upDate();
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
    //sets format type
    public void sortCitationIndex()
    {
        mergeSort(0,numCitations-1);
        upDate();
    }
    public void sortKeywords()
    {
        insertionSort();
    }
    public int insertCitationInPos(Citation myCitation)
    {
        mergeSort(0,numCitations-1);
        upDate();
        
        if(numCitations==100)
        {return -1;}
        else if(numCitations<100)
        {
        citationIndex[numCitations]=myCitation;
        citationIndex[numCitations].updateID(numCitations+1);
        updateKeywords(citationIndex[numCitations]);
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
        } else
            {
                addCitation(myCitation);
            }
           
        
        upDate();
        
        return 0;
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
    public boolean isFull()
    {
        if(numCitations==100)
        {
            return true;
        }
        else
            return false;
    }
    public int deleteCitation(String name)
    {
        
        mergeSort(0,numCitations-1);
        upDate();
        int[] delete=citationBinarySearch(name);       
        int[] ids=new int[numCitations-delete.length];
        int j=0;
        int count=0;
        if(delete.length==0)
            return -1;
        else{
        for(int d=0;d<delete.length;++d)
        {
                citationIndex[delete[d]]=null;
                ++count;
        }   
         for(int i=0;i<numCitations;++i)
         {
             
             if(citationIndex[i]==null)
             {}
             else
             {
                 
                 ids[j]=i;
                 ++j;
             }
         }  
         numCitations= numCitations-count;
         for(int n=0;n<numCitations;++n)
         {
             citationIndex[n]=citationIndex[ids[n]];
         }  
                
        }    
        
        upDate();
        return 0;
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
    //sets file name
    public void setFile(String file)
    {
        this.file=file;
    }
    public void format(String path)
    {
       
      
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
    //saves file based on format type
    public void test()
    {
        IndexLinkedList index = new IndexLinkedList(numCitations);
        String[] testA =new String[2];
        testA[0]="Keith m Fosmire";
        testA[1]="General h Patton";
        String[] testK = new String[2];
        testK[0]="fucker";
        testK[1]="asshole";
        Citation test = new Citation("Hoople Heads");
        test.setAuthor(testA,2);
        test.setKeywords(testK,2);
       // int ok=insertCitationInPos(test);
        for(int l=0;l<numCitations;++l)
        {
        index.addCitation(citationIndex[l]);
        }
        index.insertCitationInPos(test);
//        index.display();
        //if(ok==0)
        //{System.out.println("Inserted correctly");}
        int gone=deleteCitation("x-ray");
        if(gone==0)
        {System.out.println("Delete successful");}
    }
    public void saveFile()
    {
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
    public  void formatIEEE(String path)
    {       
        String fileAddr = path;
        PrintWriter out =null;//declaring new printwriter
        try{
        out =new PrintWriter(new FileWriter(fileAddr));//declaring new printwriter
        }
       catch (IOException e)
      {
          System.out.println("The file path does not exist, please check your address");
          //formatIEEE(inputDevice.nextLine());
          System.exit(0);
       }

        //starts loop for saving in format
        for(int i=0;i<numCitations;++i)
        { 
            out.println(citationIndex[i].formatIEEE());
        } 
            out.flush();
            out.close();
    }
    public void clearIndex()
    {
        for(int i=0;i<numCitations;++i)
        {
            citationIndex[i]=null;
        }
        for(int i=0;i<numKeywords;++i)
        {
            keywordIndex[i]=null;
        }
        numCitations=0;
        numKeywords=0;
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
          System.out.println("The file path does not exist, please check your address");
          //formatIEEE(inputDevice.nextLine());
          System.exit(0);
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
          System.out.println("The file path does not exist, please check your address");
          //formatIEEE(inputDevice.nextLine());
          System.exit(0);
       }

        for(int i=0;i<citationIndex.length;++i)
        { 
            out.println(citationIndex[i].formatAPA());
        } 
            out.flush();
            out.close();
    }
}
