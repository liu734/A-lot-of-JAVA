/**
* HTML-to-txt Converter
* Write a program that takes a simple HTML file as input, formats its contents, and writes the formatted 
* contents to an output file.
*
* @Liu, Jingye
*
* @recitation 03 Tyler Holzer
*
* @date Oct 11,2011
*/


import java.util.*; //packages
import java.io.*;


public class Project2{

public static void main (String [] args){

Scanner s=new Scanner(System.in);

System.out.print("Enter name of the input file: "); //prompt user to enter
String inputFile=s.nextLine(); //scan the input file name
File in= new File(inputFile);  //create object File in


if(!in.exists()){ //check the file if exists
  System.out.println(inputFile+" does not exist.");
	  System.exit(0); //doesn't exist then exit
}

System.out.print("Enter name of the output file: "); //prompt uset to enter the output file name
String outputFile=s.nextLine();  //scan the output file name
File out= new File(outputFile);  //create object File out

if(!out.exists()){
  System.out.println(outputFile+" does not exist, and create one.");	//doesn't exist then tell user will create one
}


String output=""; //the string formatted output will be stored

try {
    s = new Scanner(in); //try scan the input file 
	while(s.hasNext()){  //and store every lines in string output 
    output=output+s.nextLine(); 
	}
}


catch (Exception e){ //catch exception when haven't found 
      System.exit(0);
}

finally {if (in != null) s.close();} //if scanner is not null, then close
  


int index1 =output.indexOf("<body>"); //get the first index of the <body> in string output 
int index2 =output.indexOf("</body>"); //get the first index of the </body> 



output=output.substring(index1+6,index2); //get a substring between <body> and </body>

index1=output.indexOf("<a"); //get the first index of the <a
while (index1>=0){ //when has <a in text
index2=output.indexOf(">", index1); //get the first index of > after <a
output=output.substring(0,index1)+output.substring(index2+1); //cut the text between <a and >
index1=output.indexOf("<a"); //get the first index of the <a
}

output=output.replaceAll("</a>",""); //replace the tags with the "\n", "\n\n" , "", dashs and so on.
output=output.replaceAll("<p>","\n\n");
output=output.replaceAll("</p>","\n\n");
output=output.replaceAll("<br>","\n");
output=output.replaceAll("<hr>","\n-------------------------\n");
output=output.replaceAll("<h1>","\n\n");
output=output.replaceAll("</h1>","\n\n");
output=output.replaceAll("<h2>","\n\n");
output=output.replaceAll("</h2>","\n\n");


while(output.indexOf("\n ")>=0){ //clear the all spaces after the \n in text to align left the paragraph
output=output.replaceAll("\n ","\n");

}

while(output.indexOf("\n\n\n")>=0){ //remove the additional line breaks and make the the max line break 1
output=output.replaceAll("\n\n\n","\n\n");
}

while(output.indexOf("\n")==0){ //remove the space line at the beginning of the text
output=output.replaceFirst("\n","");

}




System.out.println(output); //display the formatted contents on the console

PrintWriter o=null; //initiate the PrintWriter

try
{
	o= new PrintWriter (new FileOutputStream(out)); //print the formatted string into File out
	o.print(output);
}
catch (FileNotFoundException e) //Exception
{
	System.exit(0);
}

finally {if (o != null) o.close();} //if scanner is not null, then close PrintWriter




}
}