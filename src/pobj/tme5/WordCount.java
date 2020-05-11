package pobj.tme5;

import java.io.*;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {

	public void main(MultiSet<String> ms) {
		Chrono chrono = new Chrono();
		MultiSet<String> decorated = new MultiSetDecorator<String>(ms);
		wordcount(decorated);
		chrono.stop();
	}

	public static void wordcount(MultiSet<String> ms) {
		String file = "data/WarAndPeace.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				for (String word : line.split("\\P{L}+")) {
					if (word.equals(""))
						continue; // ignore les mots vides
					else {
						ms.add(word);
					}
				}
			}
			br.close();
		} catch (IOException io) {
			System.out.println(io.getMessage());
			io.printStackTrace();
		}
		List<String> list = ms.elements();
		list.sort(ms.getComparer());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
