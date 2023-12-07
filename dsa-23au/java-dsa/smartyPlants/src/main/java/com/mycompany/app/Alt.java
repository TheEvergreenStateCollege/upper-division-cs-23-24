package com.mycompany.app;

//Identifiers needed for everything?

///while the file says it's a fastq in its name
//the actual .suffix says .fasta, I should probably install both... but that means writing code for both
//import fasta from bio library 


//create classes for file, reader and entries within file 

//entries within file
/*
public class runLine {
    private String firstLine;
    private String[] metadata;
    private String sequence; 
    private String runName; //I don't know how to do this one yet, probably set from entry in metadata[0]

    public runLine(firstLine, sequence){
        this.firstLine = firstLine;
        this.sequence = sequence;
        this.metadata = //regular expressions happen here
    }

    public String[] getEntry(runName){
        return this.metadata; //this should actually print each new item in the seperated metdata on its own line
        //that might use a loop?
    }

    public String getSequence(runName){
        return this.sequence;
    }
    
    //make report of all items in file
    public seeEntries(){
        System.out.println(runLine.getEntry());
        System.out.println("mRNA Sequence:");
        System.out.println(runLine.getSequence());        
    }

    //make setters to put into the reader?
    //that the reader will use to put the entries in...
}

//get the lines and put them in the right place
public class filesReader(){
    FastaReader fastaReader = new FastaReader(); //something with the library...
    List<runLine> entryItems = new ArrayList<>(); //Arraylist must be imported still!
     
    //put for-loop reader here 
    //it is very important that these are constructed correctly in order to keep each line of genetic data matched with its metadata
    //each pair of two lines, new object instance
    //store each object in array
    
    //it's probably possible to read the file name attatched to each entry read from file itself, maybe more regex extraction 

}

//class for reporting to user probably
///iterate through array of runLine objects after toArray from list and print getEntry 
*/