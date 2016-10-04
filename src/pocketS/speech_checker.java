package pocketS;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Arrays;

import pocketS.FastFourierTransform;
class speech_cheaker
{
static int CHUNK_SIZE=4096;
	public static void main(String rgs[])
	{
		try
		{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\1\\1464742516_1_1_397.wav"));
		in.skip(44);
		int read;
		byte[] buff = new byte[1024];
		while ((read = in.read(buff)) > 0)
		{
		    out.write(buff, 0, read);
		}
		out.flush();
		byte[] audioBytes = out.toByteArray();
		out(Arrays.toString(audioBytes));
		final int totalSize = audioBytes.length;

		int amountPossible = totalSize/speech_cheaker.CHUNK_SIZE;

		//When turning into frequency domain we'll need complex numbers:
		Complex[][] results = new Complex[amountPossible][];

		//For all the chunks:
		for(int times = 0;times < amountPossible; times++) {
		    Complex[] complex = new Complex[speech_cheaker.CHUNK_SIZE];
		    for(int i = 0;i < speech_cheaker.CHUNK_SIZE;i++) {
		        //Put the time domain data into a complex number with imaginary part as 0:
		        complex[i] = new Complex(audioBytes[(times*speech_cheaker.CHUNK_SIZE)+i], 0);
		    }
		    //Perform FFT analysis on the chunk:
		    for (Complex c : complex) {
	            System.out.println(c);
	        }
	 
		    FastFourierTransform.fft(complex);
		       System.out.println("Results:");
		        for (Complex c : complex) {
		            System.out.println(c);
		        }
		 
		}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	static void out(Object o)
	{
		System.out.println(o);
	}
}