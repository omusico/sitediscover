package com.androzic.sitediscover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataIO {

private static String fileName;
private static String myOut = "hello";
private static int numLine = 0; 
private static int numQuestions = 10;
private static int numPartsInQuestion = 8;


private static int randNum[] = new int [] {0,0,0,0,0,0,0,0,0,0};
//private static int randNum[] = new int [] {0,0,0,0,0,0,0,0,0,0};

private static String vitri1[][] = new String[][]
	{{ "","","","","","","",""},
	 { "","","","","","","",""},
 	 { "","","","","","","",""},
	 { "","","","","","","",""},
	 { "","","","","","","",""},
	 { "","","","","","","",""},
	 { "","","","","","","",""},
	 { "","","","","","","",""},
	 { "","","","","","","",""},
	 { "","","","","","","",""},
	};

public static void main(String[] args)
{
	fileName = "mytext.txt";
	numLine  = 0;
	try
	{	
		myOut = readFile(fileName);
		System.out.println(""+ myOut + "   " + String.valueOf(numLine));
		//Doc vao mang:
		if (numLine > 0) {
			for(int i = 0; i < numQuestions; i++) {
				Random rand = new Random(); 
				int value = rand.nextInt(numLine); 
				System.out.println(String.valueOf(value));
				randNum[i] = value;
				writeToArray(i, myOut, randNum[i]);
			}
		}
		
	 } catch (Exception e)
	{
		System.out.print("loi doc file");
	}

	
}

private static void writeToArray(int Lineth, String phrase, int atLine) {
	int stringLine = (atLine) * 2;
	String delims = "\n";
	String[] tokens = phrase.split(delims);
	System.out.println(tokens[stringLine]); //<-- doc dong thu 2 trong file

	//Gan gia tri mang vitri[Lineth] = gia tri  mang:
	String delim_ = ",";
	String[] textQuestion = tokens[stringLine].split(delim_);
	for(int j = 0; j < numPartsInQuestion; j++) {
		vitri1[Lineth][j] = textQuestion[j];
		System.out.println(vitri1[Lineth][j] + "  .... ");
	}
}


private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
	try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
	    numLine = numLine +1;
        }
	numLine = numLine /2+1;
        return sb.toString();
     } finally {
        br.close();
    }
}







}