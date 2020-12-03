import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class keyWordSearch {
	public static int total;
	public static int trash;
	public static HashMap<String, Integer> keyWordCount = new HashMap<>();
	public static String filePath = "";
	// Decided to use ALL of the Java Keywords to search for
	public static String[] keywordArr = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "continue", "const", "default", "do", "double", "else", "enum", "exports", "extends", "final",
			"finally", "float", "for", "goto", "if", "implements", "imports", "instanced", "int", "interface", "long",
			"module", "native", "new", "package", "private", "protected", "public", "requires", "return", "short",
			"static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try",
			"var", "void", "volatile", "while" };

	public static void main(String[] args) {

		Timer stopwatch = new Timer();
		stopwatch.start(); 		// start timer
		
		readFile();
		keywordCount();
		System.out.println();
				
		stopwatch.stop(); 		// stop timer
		
		total = total - trash;
		System.out.println("Key words in file with frequency: " + keyWordCount.toString());

		System.out.println();
		System.out.println("Total number of lines of code: " + total);
		System.out.println();
		System.out.println("Time taken for program to run: " + stopwatch.getElapsedTime() + " milliseconds");
	}

	public static void readFile() {
		filePath = chooseFile();
	}

	public static String chooseFile() {

		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("java", "txt");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new java.io.File("/Users"));
		fc.setDialogTitle("Select a File: ");
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath();
		} else {

			return "selected cancel";
		}
	}

	public static void keywordCount() {
		total = 0;
		trash = 0;

		if (!filePath.equals("selected cancel")) {
			LineNumberReader reader = null;

			try {

				reader = new LineNumberReader(new FileReader(new File(filePath)));
				String str;

				while ((str = reader.readLine()) != null) {

					str = str.replaceAll("\\s+", "");
					total++;

					if (str.equals("")) {
						trash++;
					}

					if ((str.length() >= 2) && (str.startsWith("//"))) {
						trash++;
					} else if (str.contains("/*")) {
						trash++;
						while (((str = reader.readLine()) != null) && !(str.endsWith("*/"))) {
							total++;
							trash++;

						}
						total++;
						trash++;

					}

					for (int i = 0; i < keywordArr.length; i++) {
						if (keyWordCount.containsKey(keywordArr[i]) == false) {
							keyWordCount.put(keywordArr[i], 0);
						}
						
						if (str == null) {
							return;
						}
						else if (str.contains(keywordArr[i])) {
							keyWordCount.put(keywordArr[i], keyWordCount.get(keywordArr[i])+1);
						}

					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else {
			return;
		}

	}
}