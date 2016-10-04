package pocketS;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class RAWtoAmplitude {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     
		try
		{
			
		
         
         //FileOutputStream fout=new FileOutputStream()
         File filelength = new File("C:\\Users\\user\\Music\\000000002.raw");
         InputStream dis = new FileInputStream(filelength);
         ArrayList<Integer> arraylist = new ArrayList<Integer>();
         int max = 0, cout = 0, firstbyte = 0, completeByte = 0;

         long counter = -1, maxcounter = 0;
         int minBufferSize = 2592;

         int i = 0;
         byte[] temp = new byte[minBufferSize];

         while ((i = dis.read(temp, 0, minBufferSize)) > -1) {
             for (int b : temp) {
                 counter++;
                 arraylist.add(b);
                 //  b = Math.abs(b);

                 if (cout == 0) {
                     cout++;
                     firstbyte = b;
                     continue;
                 }

                 completeByte = ((b << 8) + (firstbyte & 0xFF));
               //  writer.append(completeByte + ",");
                 //Log.d("completeByte", completeByte + "");
                 System.out.println(completeByte+"");
                 completeByte = Math.abs(completeByte);
                 //arraylist.add(completeByte);
                 if (completeByte > max) {
                     max = completeByte;
                     maxcounter = counter;
                     //  System.out.println(max+" "+maxcounter);
                 }

                 cout = 0;

             }
            
         }
         
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}

}
}

