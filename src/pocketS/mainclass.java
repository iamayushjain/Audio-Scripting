package pocketS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechAligner;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.decoder.Decoder;
import edu.cmu.sphinx.result.Nbest;
import edu.cmu.sphinx.speakerid.Segment;
import edu.cmu.sphinx.util.props.ConfigHandler;

public class mainclass {
	static int count = 0;
	static int folderName = 0;
	// static {
	// System.loadLibrary("pocketsphinx_jni");
	// }

	static Configuration c;
	static Decoder ps;

	static FileInputStream stream;
	static String[] wordsFromDict;
	static String[] phonFromDict;
	static String gramFile = "menu_kids_1_1.gram";
	static String mp3File = "audiofile.mp3";

	static void out(Object s) {
		System.out.println(s);
	}

	static PrintWriter writer1;

	static void pocketReco(String filepath, String fileName) {

		c = new Configuration();

		try {

			c.setAcousticModelPath("file:/D:\\WorkSpace_Studio\\Models\\Clustering\\syncfaultDict\\en-us-ptm");
			out(c.getAcousticModelPath());
			c.setDictionaryPath("file:/D:\\WorkSpace_Studio\\Models\\Clustering\\syncfaultDict\\cmudict-en-us.dict");
			// c.setGrammarName("FORECAST");
			c.setUseGrammar(true);
			c.setGrammarName("menu" + folderName);
			c.setGrammarPath("file:/D:\\WorkSpace_Studio\\Models\\Clustering\\syncfaultDict\\");
			out(c.getGrammarPath());

			c.setSampleRate(8000);
			StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(c);

			out("FINE HERE2");
			InputStream is = new FileInputStream(new File(filepath));
			out("FINE HERE3");
			is.skip(44);

			recognizer.startRecognition(is);

			SpeechResult result = recognizer.getResult();
			out(result.getHypothesis());
			writer1.append(fileName + "\t" + result.getHypothesis() + "\r\n");

			recognizer.stopRecognition();

			out("FINE HERE");
		} catch (Exception e) {
			e.printStackTrace();
			writer1.close();
			out("Catch" + "HERE");
			System.exit(1);
		}
		ps = new Decoder();

		out("Finished configuring decoder object...");
	}

	public static void main(String args[]) {
		try {
			writer1 = new PrintWriter("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\result_new_predefinedCase_"
					+ (folderName + 1) + ".txt", "UTF-8");
			Files.walk(Paths.get("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\" + (folderName + 1))).forEach(
					filePath -> {
						if (Files.isRegularFile(filePath)) {
							System.out.println(filePath.getFileName());

							pocketReco(filePath.toString(), filePath.getFileName().toString());
							out(count);
							count++;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			writer1.close();
			System.exit(1);
		}
		writer1.close();
		out(count);

	}
}