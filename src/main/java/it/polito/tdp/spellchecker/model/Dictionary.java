package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private String lingua = "";
	List<String> italiano = new LinkedList<String>();
	List<String> inglese = new LinkedList<String>();
	
	public void loadDictionary(String language) {
		
		try {
			FileReader fr = new FileReader("src/main/resources/" + language);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				if (language.compareTo("Italian.txt") == 0) {
					italiano.add(word);
				} else if (language.compareTo("English.txt") == 0) {
					inglese.add(word);
				}
			}
			lingua = language;
			br.close();
			} catch (IOException e){
			   System.out.println("Errore nella lettura del file");
			}
	
	}	
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		List<RichWord> l = new LinkedList<RichWord>();
		
		for (String s : inputTextList) {
			if (lingua.compareTo("Italian.txt") == 0) {
				RichWord r;
				if (italiano.contains(s)) {
					r = new RichWord(s, true);
				} else {
					r = new RichWord(s, false);
				}
				l.add(r);
			} else if (lingua.compareTo("English.txt") == 0) {
				RichWord r;
				if (inglese.contains(s)) {
					r = new RichWord(s, true);
				} else {
					r = new RichWord(s, false);
				}
				l.add(r);
			}
		}
		
		return l;

	}
}

