package AudioSplittingPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class RawAudioToAmplitude {

	public static ArrayList<Integer> rawToAmplitude(String audioFilePath) {

		try {

			File filelength = new File(audioFilePath);
			InputStream dis = new FileInputStream(filelength);
			ArrayList<Integer> arraylist = new ArrayList<Integer>();
			int max = 0, cout = 0, firstbyte = 0, completeByte = 0;

			long counter = -1, maxcounter = 0;
			int minBufferSize = 2592;

			int i = 0;
			byte[] temp = new byte[minBufferSize];
			ArrayList<Integer> amplitudeList= new ArrayList<Integer>();
			while ((i = dis.read(temp, 0, minBufferSize)) > -1) {
				for (int b : temp) {
					counter++;
					arraylist.add(b);
					// b = Math.abs(b);

					if (cout == 0) {
						cout++;
						firstbyte = b;
						continue;
					}

					completeByte = ((b << 8) + (firstbyte & 0xFF));
					// writer.append(completeByte + ",");
					// Log.d("completeByte", completeByte + "");
					System.out.println(completeByte + "");
					amplitudeList.add(completeByte);
					completeByte = Math.abs(completeByte);
					// arraylist.add(completeByte);
					if (completeByte > max) {
						max = completeByte;
						maxcounter = counter;
						// System.out.println(max+" "+maxcounter);
					}

					cout = 0;

				}

			}
			return amplitudeList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {
		 String audioFilePath="C:\\Users\\user\\Music\\000000002.raw";
		rawToAmplitude(audioFilePath);
	}
}
