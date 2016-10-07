package AudioSplittingPackage;

import java.io.PrintWriter;
import java.util.ArrayList;

public class AudioSplittingScript {

	public static String audioFilePath = "C:\\Users\\user\\Music\\vijayAudioConv.wav";
	public static String resultFilePath = "D:\\WorkSpace_Studio\\Models\\Audio Spliting Script\\AudioSplit03.txt";
	public static ArrayList<Integer> amplitudeList, startIndexList, endIndexList;
	public static int highestAmplitude;
	public static int minAreaPercentage = 35;
	public static float minArea;
	public static ArrayList<Integer> classificationAmplitudeList;
	public static int sampleRate = 16000;
	public static int sampleDurationRate = sampleRate*3; // 10% of sample rate
	public static PrintWriter writer;
	public static String outputWriter;

	public static void main(String args[]) {
		init();
		amplitudeList = WavAudioToAmplitude.rawToAmplitude(audioFilePath);
		// print("amplitudeList: " + amplitudeList.toString());
		print("Size of Amplitude: " + amplitudeList.size());
		highestAmplitude = getHighestAmplitude();
		print("highestAmplitude: " + highestAmplitude);

		minArea = (float) (minAreaPercentage * highestAmplitude) / 100;
		print("minArea: " + minArea);
		classifactionAmplitude();
		// print("classificationAmplitudeList:" +
		// classificationAmplitudeList.toString());
		print("Size of Classification Amplitude List: " + classificationAmplitudeList.size());

		audioSpliting();
		print("Silence Start List: " + startIndexList.toString());
		print("Silence End List : " + endIndexList.toString());
		print("Voiced Areas:");
		getVoicedAreas();
		print(outputWriter);
		writer.write(outputWriter);
		writer.close();
	}

	public static void init() {
		amplitudeList = new ArrayList<Integer>();
		classificationAmplitudeList = new ArrayList<Integer>();
		startIndexList = new ArrayList<Integer>();
		endIndexList = new ArrayList<Integer>();
		try {
			outputWriter = "";
			writer = new PrintWriter(resultFilePath, "UTF-8");
		} catch (Exception e) {
			print("File not found Error");
		}
	}

	public static void print(Object object) {
		System.out.println(object);
	}

	public static int getHighestAmplitude() {
		int max = 0;
		for (int i : amplitudeList) {
			i = Math.abs(i);
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public static void classifactionAmplitude() {
		for (int i : amplitudeList) {
			i = Math.abs(i);
			if (i >= minArea)
				classificationAmplitudeList.add(1);
			else
				classificationAmplitudeList.add(0);
		}
	}

	public static void audioSpliting() {
		int count = 0, flag = 0, startIndex = 0, endIndex = 0;

		for (int i = 0; i < classificationAmplitudeList.size(); i++) {
			if (classificationAmplitudeList.get(i) == 0) {
				count++;
				if (count == 1) {
					startIndex = i;
				}
			} else {
				if (flag == 1) {
					print(startIndex + " " + endIndex);
					startIndexList.add(startIndex);
					endIndexList.add(endIndex);

				} else {

				}
				count = 0;
			}
			if (count >= sampleDurationRate) {
				flag = 1;
			} else {
				flag = 0;
			}
			endIndex = i;
			if (((endIndex + 1) == classificationAmplitudeList.size()) && (flag == 1)) {
				print(startIndex + " " + endIndex);
				startIndexList.add(startIndex);
				endIndexList.add(endIndex);

			}
		}
	}

	public static String getTimeInSec(int sampleNumber) {

		return String.format("%.3f", ((float) sampleNumber / 16000));

	}

	public static void getVoicedAreas() {
		int voiceAreaCounter = 0, start = 0, flag = 0;
		for (int i = 0; i < startIndexList.size(); i++) {
			if (startIndexList.get(i) == 0) {

			} else {
				voiceAreaCounter++;
				print("Voice " + voiceAreaCounter);
				print(start + " " + (startIndexList.get(i) - 1));
				outputWriter += "Voice " + voiceAreaCounter + "\t" + getTimeInSec(start) + "\t"
						+ getTimeInSec((startIndexList.get(i) - 1)) + "\r\n";

			}
			if (endIndexList.get(i) != amplitudeList.size()) {
				start = endIndexList.get(i) + 1;
				if (i == (startIndexList.size() - 1)) {
					voiceAreaCounter++;
					print("Voice " + voiceAreaCounter);
					print(start + " " + (amplitudeList.size()));
					outputWriter += "Voice " + voiceAreaCounter + "\t" + getTimeInSec(start) + "\t"
							+ getTimeInSec((amplitudeList.size())) + "\r\n";
				}
			} else {

			}
		}
	}

}
