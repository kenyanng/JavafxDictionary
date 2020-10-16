package sample;

import javax.swing.*;
import java.io.*;
import  java.util.Scanner;
/*
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.sun.xml.internal.xsom.impl.scd.Iterators;

 */

public  class DictionaryManagement extends Dictionary {
	public static void insertFromCommandline() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap vao so n :");
		int n = sc.nextInt();
		Scanner sv = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			String target = sv.nextLine();
			String explain = sv.nextLine();
			Word newWord = new Word(target, explain);
			dic.add(newWord);
		}
	}

	public static void insertFromFile() throws IOException,
			FileNotFoundException, UnsupportedEncodingException {
		FileInputStream file = new FileInputStream("C:\\Users\\LENOVO\\IdeaProjects\\JavafxDictionary\\src\\sample\\a.txt");
		Reader re = new InputStreamReader(file, "UTF-8");
		Scanner x = new Scanner(re);
		String target = "";
		String explain = "";
		while (x.hasNext()) {
			String fulltext = x.nextLine();
			int index = fulltext.indexOf('|');
			if (fulltext.startsWith("@")) {
				target = fulltext.substring(1, index).trim().toLowerCase();
			} else 	{
				target = fulltext.substring(0, index).trim().toLowerCase();
			}
			explain = fulltext.substring(index + 1).trim().toLowerCase();
			Word newWord = new Word(target, explain);
			dic.add(newWord);
		}

		x.close();
	}

	public int dictionaryLookup(String target) throws IOException {
		int index = -1;
		int i = 0;
		while (i < dic.size()) {
			if (target.equals(dic.get(i).word_target)) {
				index = i;
				i = dic.size();
			} else i++;
		}
		return  index;
	}
	public boolean add(String target,String explain) {
		int i = 0;
		boolean index = false;
		while (i < dic.size()) {
			if(target.equals(dic.get(i).word_target)) {
				index = true;
				i = dic.size();
			} else {
				i++;
			}
		}
		Word newWord = new Word(target, explain);
		return index;

	}



	public boolean remove(String target) {
		int i = 0;
		boolean index = false;
		while (i < dic.size()) {
			if (target.equals(dic.get(i).word_target)) {
				dic.remove(i);
				i = dic.size();
				index = true;
			} else i++;
		}
		return index;
	}

	public boolean edit(String targetbase,String target,String explain) {
		int i = 0;
		boolean index =false;
		while (i < dic.size()) {
			if (targetbase.equals(dic.get(i).word_target)) {
				dic.get(i).word_target = target;
				dic.get(i).word_explain = explain;
				index = true;
				i = dic.size();
			} else i++;
		}
		return index;
	}

	public void dictionaryExportToFile() throws IOException {
		FileOutputStream  file = new
				FileOutputStream("C:\\Users\\LENOVO\\IdeaProjects\\JavafxDictionary\\src\\sample\\a.txt");
		System.out.println("Loading............");
		String str;
		for (int i = 0; i < dic.size(); i++) {
			str = dic.get(i).getword() + "\n" ;
			byte[] strfile = str.getBytes();
			file.write(strfile);
		}
		file.close();
	}
}