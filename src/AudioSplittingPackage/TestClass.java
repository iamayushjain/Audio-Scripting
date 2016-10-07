package AudioSplittingPackage;

import java.util.ArrayList;

public class TestClass {
	static ArrayList<Integer> classificationAmplitudeList, amplitudeList, startIndexList, endIndexList;
	static int sampleDurationRate = 4;

	public static void main(String args[]) {
		classificationAmplitudeList = new ArrayList<Integer>();
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(1);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);
		classificationAmplitudeList.add(0);

		// audioSpliting();
		amplitudeList = new ArrayList<Integer>();
		classificationAmplitudeList = new ArrayList<Integer>();
		startIndexList = new ArrayList<Integer>();
		endIndexList = new ArrayList<Integer>();

		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		amplitudeList.add(2);
		startIndexList.add(0);
		startIndexList.add(6);
		startIndexList.add(9);
		endIndexList.add(4);
		endIndexList.add(7);
		endIndexList.add(20);
		getVoicedAreas();

	}

	public static void getVoicedAreas() {
		int voiceAreaCounter = 0, start = 0, flag = 0;
		for (int i = 0; i < startIndexList.size(); i++) {
			if (startIndexList.get(i) == 0) {

			} else {
				voiceAreaCounter++;
				AudioSplittingScript.print(voiceAreaCounter);
				AudioSplittingScript.print(start + " " + (startIndexList.get(i) - 1));

			}
			if (endIndexList.get(i) != amplitudeList.size()) {
				start = endIndexList.get(i) + 1;
				if (i == (startIndexList.size() - 1)) {
					voiceAreaCounter++;
					AudioSplittingScript.print(voiceAreaCounter);
					AudioSplittingScript.print(start + " " + (amplitudeList.size()));
				}
			} else {

			}
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
					AudioSplittingScript.print(startIndex + " " + endIndex);
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
			if ((endIndex == classificationAmplitudeList.size()) && (flag == 1)) {
				AudioSplittingScript.print(startIndex + " " + endIndex);
			}
		}
	}

}
