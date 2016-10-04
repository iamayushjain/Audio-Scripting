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
import edu.cmu.sphinx.api.Context;
import edu.cmu.sphinx.api.SpeechAligner;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.decoder.Decoder;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Nbest;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.result.WordResult;
import edu.cmu.sphinx.speakerid.Segment;
import edu.cmu.sphinx.util.TimeFrame;
import edu.cmu.sphinx.util.props.ConfigHandler;

public class Phontic_level {
static int count=0;
	static int folderName=0; 
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
	static void pocketReco(String filepath,String fileName)
	{

		c = new Configuration();
		
		try {
			
			c.setAcousticModelPath("file:/D:\\WorkSpace_Studio\\Models\\Clustering\\syncfaultDict_11case\\en-us-ptm");
			out(c.getAcousticModelPath());
			c.setDictionaryPath("file:/D:\\WorkSpace_Studio\\Models\\Clustering\\syncfaultDict_11case\\cmudict-en-us.dict");
			//c.setGrammarName("FORECAST");
		
			//c.setSampleRate(8000);
			InputStream is = new FileInputStream(new File(filepath));
			is.skip(44);
			Context context = new Context(c);
			//context.
	      context.setLocalProperty("decoder->searchManager", "allphoneSearchManager");
	      context.setSpeechSource(is);
	      
			Recognizer recognizer = context.getInstance(Recognizer.class);
			
		
			//StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(c);

			out("FINE HERE2");
			out("FINE HERE3");
			recognizer.allocate();
	        Result result;
	        while ((result = recognizer.recognize()) != null) {
	            SpeechResult speechResult = new SpeechResult(result);
	            System.out.format("Hypothesis: %s\n", speechResult.getHypothesis());
	            writer1.append(fileName+"\t"+speechResult.getHypothesis());
	            System.out.println("List of recognized words and their times:");
	            for (WordResult r : speechResult.getWords()) {
	                System.out.println(r);
	                writer1.append("\t"+r+"\r\n");
	            }
	        }
	        recognizer.deallocate();
//			recognizer.startRecognition(is);
//			
//			
//			 SpeechResult result = recognizer.getResult();
//			 out(result.getHypothesis());
//			 writer1.append(fileName+"\t"+result.getHypothesis()+"\r\n");
//			
//			 recognizer.stopRecognition();
//						 
			 
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
		try
		{
			writer1 = new PrintWriter("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\phones_new_11case_"+(folderName+1)+".txt", "UTF-8");
			Files.walk(Paths.get("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\"+(folderName+1))).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		        System.out.println(filePath.getFileName());
		        
		        pocketReco(filePath.toString(),filePath.getFileName().toString());
		        out(count);
		        count++;
		    }
		});
	}
		catch(Exception e)
		{
			e.printStackTrace();
			writer1.close();
			System.exit(1);
		}
		writer1.close();
		out(count);

			
		}
}