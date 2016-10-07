package SpeakerBasedDetection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import edu.cmu.sphinx.speakerid.Segment;
import edu.cmu.sphinx.speakerid.SpeakerCluster;
import edu.cmu.sphinx.speakerid.SpeakerIdentification;

public class SpeakerAnalysis {
	static InputStream is, is1, is2, is3;
	static String filename = "D:\\WorkSpace_Studio\\Models\\Speaker Identification\\testFile";
	static String folderName = "D:\\WorkSpace_Studio\\Models\\Speaker Identification\\";
	static int count = 1;

	public static void main(String[] args) throws Exception {
		URL url = new File("D:\\WorkSpace_Studio\\Models\\Speaker Identification\\testFile7.wav").toURI().toURL();
		is = new FileInputStream(filename + "1.wav");
		is1 = new FileInputStream(filename + "2.wav");
		is2 = new FileInputStream(filename + "3.wav");
		is3 = new FileInputStream(filename + "4.wav");
		// SpeakerIdentification sd = new SpeakerIdentification();
		InputStream is4 = new SequenceInputStream(is2, is);
		InputStream is5 = new SequenceInputStream(is4, is2);
		try {

			Files.walk(Paths.get("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\" + (1))).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					System.out.println(filePath.getFileName());
					// is=new FileInputStream(filePath.toString());
					try {
						if (count == 1) {

							is = new FileInputStream(filePath.toString());
//							is.skip(44);
						} else {
							is2 = new FileInputStream(filePath.toString());
							is2.skip(44);
							is = new SequenceInputStream(is, is2);
						}
						count++;
					} catch (Exception e) {

					}

				}
			});
			SpeakerIdentification sd = new SpeakerIdentification();

			int read = 0;
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((read = is.read(bytes)) != -1) {
				baos.write(bytes, 0, read);
			}
			baos.flush();
			is = new ByteArrayInputStream(baos.toByteArray());
			is1 = new ByteArrayInputStream(baos.toByteArray());
			System.out.println("Done!");
			print(is.available());
			print(is1.available());
			printSpeakerIntervals(sd.cluster(is), "x");
			print(is.available());
			print(is1.available());
			OutputStream outputStream = new FileOutputStream(new File(filename + "10.wav"));
			is1.skip(44);
			while ((read = is1.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			outputStream.flush();
			System.out.println("Done!");
			// SpeakerIdentification sd = new SpeakerIdentification();

			printSpeakerIntervals(sd.cluster(is), "x");
			// .getFileName().toString());
		} catch (Exception e) {

		}
		// ArrayList<SpeakerCluster> clusters = sd.cluster(url.openStream());//
		// sd.cluster(url.openStream());
		// print(clusters.size());
		// printSpeakerIntervals(clusters, url.getPath());
	}

	public static void printSpeakerIntervals(ArrayList<SpeakerCluster> speakers, String fileName) {
		int idx = 0;
		for (SpeakerCluster spk : speakers) {
			idx++;
			// print(spk.getBicValue());
			// print(spk.getFeatureMatrix());

			ArrayList<Segment> segments = spk.getSpeakerIntervals();
			for (Segment seg : segments)

				System.out.println( SpeakerIdentificationDemo.time(seg.getStartTime()) + " "
						+ SpeakerIdentificationDemo.time(seg.getStartTime() + seg.getLength()) + " Speaker" + idx);
		}

	}

	public static void print(Object object) {
		System.out.println(object);
	}

}
