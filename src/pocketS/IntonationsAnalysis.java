package pocketS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntonationsAnalysis {

//    static String[][] intonationLevel = { { "THANK", "0", "0" }, { "YOU ", "57", "90" }, { "FOR", "91", "109" },
//            { "CALLING", "110", "145" }, { "VALLEY", "146", "171" }, { "BANK", "172", "214" }
//
//  };
	static String[][] intonationLevel = { {"THANK","0","41"},
		{"YOU","0","0"},
		{"FOR","42","57"},
		{"CALLING","58","98"},
		{"VALLEY","99","137"},
		{"BANK","138","198"}

};

    static int file1[];

    public static void main(String args[]) {

        try {
            System.out.println("File found");
            
            
            
            
//            
//            BufferedReader bf = new BufferedReader(new FileReader(
//                    "D:\\WorkSpace_Studio\\Models\\New folder (2)\\1470229924666 - Copy.txt"));
            BufferedReader bf = new BufferedReader(new FileReader(
                    "D:\\WorkSpace_Studio\\Models\\New folder (2)\\Audio02.txt"));
            List<Integer> list2 = new ArrayList<Integer>();
            String s;
            while ((s = bf.readLine()) != null) {
                // s=bf.readLine();
                list2.add(Integer.parseInt(s));

            }
            file1 = new int[list2.size()];
            for (int i = 0; i < list2.size(); i++) {
                file1[i] = Math.abs(list2.get(i));
            }
            System.out.println(file1.length);
            System.out.println("out 1 "+intonationLevelEvaluatorGlobalMaxima());//00
            System.out.println("out 2 "+intonationLevelEvaluatorAveragePerWord());//10
            System.out.println("out 3 "+intonationLevelEvaluatorMovingAverage());//10
            System.out.println("out 4 "+intonationLevelEvaluator100HighestAverage());//10
     //       System.out.println("out 5 "+intonationLevelEvaluator5());//10

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static String intonationLevelEvaluatorGlobalMaxima() {
        try {

            int counter = -1;
            int max = 0;
            int maxcounter = 0;

            // System.out.println(counterInMs);
            double[] sum = new double[intonationLevel.length];
            float[] average = new float[intonationLevel.length];
            int l = 0;
            int cout = 0;
            max = 0;
            float maxAverage = 0, k = 0;
            for (String[] s : intonationLevel) {
                System.out.println(s[0]);
                System.out.println(s[1]);
                System.out.println(s[2]);
                cout = 0;
                for (int y = (Integer.parseInt(s[1]) * 160); y < (Integer.parseInt(s[2]) * 160); y++) {
                    if (file1[y] > max) {
                        max = file1[y];
                        k = l;
                    }

                }
                System.out.println(intonationLevel[(int) k][0]);
                l++;
            }
            // return intonationLevel[(int)k][0];

            // System.out.println(max + " ");

            // for (int y = 0; y < sum.length; y++) {
            // System.out.println(intonationLevel[y][0]);
            // System.out.println("Sum" + sum[y]);
            // System.out.println("Average" + (average[y] / 10000000));
            //
            // }
            return intonationLevel[(int) k][0];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    static String intonationLevelEvaluatorAveragePerWord() {
        try {

            int counter = -1;
            int max = 0;
            int maxcounter = 0;

            // System.out.println(counterInMs);
            double[] sum = new double[intonationLevel.length];
            float[] average = new float[intonationLevel.length];
            int l = 0;
            int cout = 0;
            max = 0;
            float maxAverage = 0, k = 0;
            for (String[] s : intonationLevel) {
                System.out.println(s[0]);
                System.out.println(s[1]);
                System.out.println(s[2]);
                cout = 0;
                for (int y = (Integer.parseInt(s[1]) * 160); y < (Integer.parseInt(s[2]) * 160); y++) {
                    sum[l] = sum[l] + file1[y];
                }

                average[l] = (float) sum[l] / ((Integer.parseInt(s[2]) - Integer.parseInt(s[1]))*360);
                System.out.println(sum[l]);
                System.out.println(average[l]);
                
                if (average[l] > maxAverage) {
                    maxAverage = average[l];
                    k = l;
                }
                
                System.out.println(intonationLevel[(int) k][0]);
                l++;
            }
            // return intonationLevel[(int)k][0];

            // System.out.println(max + " ");

            // for (int y = 0; y < sum.length; y++) {
            // System.out.println(intonationLevel[y][0]);
            // System.out.println("Sum" + sum[y]);
            // System.out.println("Average" + (average[y] / 10000000));
            //
            // }
            return intonationLevel[(int) k][0];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    static String intonationLevelEvaluatorMovingAverage() {

        try {
            double[] sum = new double[(int) Math.ceil((float) file1.length / 100)];
            double[] average = new double[intonationLevel.length];
            int l = 0, l1 = 0;
            int cout = 0;
            int max = 0;
            double maxAverage = 0, k = 0;
            for (int i2 = 0; i2 < file1.length; i2++) {
                if (l >= 100) {
                    // System.out.println(sum[l1]);
                    if (sum[l1] > maxAverage) {
                        maxAverage = sum[l1];
                        k = l1;
                    }
                    l1++;
                    l = 0;
                }
                sum[l1] = sum[l1] + file1[i2];
                l++;
            }
            k = k * 100;
            int counterInMs = (int) (k / 160);
            System.out.println(counterInMs);

            for (String[] s : intonationLevel) {
                System.out.println(s[0]);
                System.out.println(s[1]);
                System.out.println(s[2]);
                cout = 0;
                // for (int y = (Integer.parseInt(s[1]) * 320); y <
                // (Integer.parseInt(s[2]) * 320); y++) {
                // if (cout == 0) {
                // cout++;
                // firstbyte = inputByte[y];
                // continue;
                // }
                // completeByte = Math.abs(((inputByte[y] << 8) + (firstbyte &
                // 0xFF)));
                // sum[l] = sum[l] + Math.pow(((inputByte[y] << 8) + (firstbyte
                // & 0xFF)),2);
                // if (completeByte > max) {
                // max = completeByte;
                // //k=l;
                //
                // // maxcounter = counter;
                // // System.out.println(max+" "+maxcounter);
                // }
                //
                // cout = 0;
                // }
                // System.out.println(intonationLevel[(int)k][0]);
                //
                // average[l] = (float) sum[l] / ((Integer.parseInt(s[2]) -
                // Integer.parseInt(s[1])) * 320);
                // if(average[l]>maxAverage)
                // {
                // maxAverage=average[l];
                // k=l;
                // }
                // l++;
                // }
                // //return intonationLevel[(int)k][0];
                //
                // // System.out.println(max + " ");
                //
                // for (int y = 0; y < sum.length; y++) {
                // System.out.println(intonationLevel[y][0]);
                // System.out.println("Sum"+sum[y]);
                // System.out.println("Average"+(average[y]/10000000));
                //
                // }
                // return intonationLevel[(int)k][0];
                if (counterInMs >= Integer.parseInt(s[1]) && counterInMs < Integer.parseInt(s[2])) {
                    // Toast.makeText(getApplicationContext(), s[0],
                    // Toast.LENGTH_LONG).show();
                    System.out.println(s[0]);
                    return s[0];
                    // break;
                }
            }
            //
            // }
            // double d=0;
            // for(int j:inputByteWord)
            // {
            // j=Math.abs(j);
            // d=d+j;
            // }
            //
            // }
            // track.setPlaybackHeadPosition(startIndex*100);
            // track.play();
            // // track1.play();
            // // track.setVolume(0);
            // long counter = 0, headerValue = 0;
            // int previousValue = 0;
            // ArrayList<Integer> arraylist = new ArrayList<Integer>();
            // while ((i = dis.read(temp, 0, minBufferSize)) > -1) {//&&
            // (track.getPlaybackHeadPosition()<(endIndex*160))) {
            // //if (counter>=((float)startIndex/7.35) &&
            // counter<=((float)endIndex/7.35))
            // // if(arraylist.size()>(startIndex*440) &&
            // arraylist.size()<(endIndex*440))
            // // track.write(temp, 0, i);
            // //
            // if (headerValue >= (((startIndex) * 160) - 1000) && headerValue <
            // (((endIndex) * 160) + 1000))
            // track.write(temp, 0, i);
            // headerValue += 1250;
            // //track.write(temp, 0, i);
            // for (int b : temp)
            // arraylist.add(b);
            // // if(arraylist.size()>(startIndex*160) &&
            // arraylist.size()<(endIndex*160))
            // // track.write(temp, 0, i);
            // // if(track.getPlaybackHeadPosition()>(startIndex*160))
            // // {
            // // track1.write(temp,0,i);
            // // }
            // counter++;
            // System.out.println("track playback" +
            // track.getPlaybackHeadPosition() + " " +
            // track.getBufferSizeInFrames() + " " + i + " " + counter + " " +
            // (track.getPlaybackHeadPosition() - previousValue));
            // previousValue = track.getPlaybackHeadPosition();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    public static class Pair implements Comparable<Pair> {
        public final int index;
        public final int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Pair other) {
            // multiplied to -1 as the author need descending sort order
            return -1 * Integer.valueOf(this.value).compareTo(other.value);
        }

    }

    static String intonationLevelEvaluator100HighestAverage() {
        try {
            Pair[] yourArray = new Pair[file1.length];
            for (int i2 = 0; i2 < yourArray.length; i2++) {
                yourArray[i2] = new Pair(i2 * 2, file1[i2]);
            }
            Arrays.sort(yourArray);
            for (int i2 = 0; i2 < 100; i2++) {
       //         System.out.println(yourArray[i2].value + " " + yourArray[i2].index);
            }

            double[] sum = new double[intonationLevel.length];
            float[] average = new float[intonationLevel.length];
            int l = 0;
            int cout = 0;
            int max = 0;
            int maxcout = 0;
            float maxAverage = 0;
            int k = 0;
            for (String[] s : intonationLevel) {
                System.out.println(s[0]);
                System.out.println(s[1]);
                System.out.println(s[2]);
                cout = 0;

                for (int i2 = 0; i2 < 100; i2++) {
                    if ((yourArray[i2].index / 320) >= Integer.parseInt(s[1])
                            && (yourArray[i2].index / 320) < Integer.parseInt(s[2])) {
                        l++;
                    }
                    // break;
                }
                if (l > max) {
                    max = l;
                    k = cout;
                }
                l = 0;
                cout++;
            }
            // return intonationLevel[(int)k][0];

            // System.out.println(max + " ");

            // for (int y = 0; y < sum.length; y++) {
            // System.out.println(intonationLevel[y][0]);
            // System.out.println("Sum"+sum[y]);
            // System.out.println("Average"+(average[y]/10000000));
            //
            // }
            System.out.println(intonationLevel[(int) k][0]);
            return intonationLevel[(int) k][0];
            // if (counterInMs >= Integer.parseInt(s[1]) && counterInMs <
            // Integer.parseInt(s[2])) {
            // //Toast.makeText(getApplicationContext(), s[0],
            // Toast.LENGTH_LONG).show();
            // System.out.println(s[0]);
            // return s[0];
            // //break;
            // }
            //
            // }
            // double d=0;
            // for(int j:inputByteWord)
            // {
            // j=Math.abs(j);
            // d=d+j;
            // }
            //
            // }
            // track.setPlaybackHeadPosition(startIndex*100);
            // track.play();
            // // track1.play();
            // // track.setVolume(0);
            // long counter = 0, headerValue = 0;
            // int previousValue = 0;
            // ArrayList<Integer> arraylist = new ArrayList<Integer>();
            // while ((i = dis.read(temp, 0, minBufferSize)) > -1) {//&&
            // (track.getPlaybackHeadPosition()<(endIndex*160))) {
            // //if (counter>=((float)startIndex/7.35) &&
            // counter<=((float)endIndex/7.35))
            // // if(arraylist.size()>(startIndex*440) &&
            // arraylist.size()<(endIndex*440))
            // // track.write(temp, 0, i);
            // //
            // if (headerValue >= (((startIndex) * 160) - 1000) && headerValue <
            // (((endIndex) * 160) + 1000))
            // track.write(temp, 0, i);
            // headerValue += 1250;
            // //track.write(temp, 0, i);
            // for (int b : temp)
            // arraylist.add(b);
            // // if(arraylist.size()>(startIndex*160) &&
            // arraylist.size()<(endIndex*160))
            // // track.write(temp, 0, i);
            // // if(track.getPlaybackHeadPosition()>(startIndex*160))
            // // {
            // // track1.write(temp,0,i);
            // // }
            // counter++;
            // System.out.println("track playback" +
            // track.getPlaybackHeadPosition() + " " +
            // track.getBufferSizeInFrames() + " " + i + " " + counter + " " +
            // (track.getPlaybackHeadPosition() - previousValue));
            // previousValue = track.getPlaybackHeadPosition();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    static String intonationLevelEvaluator5() {
        try {

            int counter = -1;
            int max = 0;
            int maxcounter = 0;

            // System.out.println(counterInMs);
            double[] sum = new double[intonationLevel.length];
            float[] average = new float[intonationLevel.length];
            int l = 0;
            int cout = 0;
            max = 0;
            float maxAverage = 0, k = 0;
            for (String[] s : intonationLevel) {
                System.out.println(s[0]);
                System.out.println(s[1]);
                System.out.println(s[2]);
                cout = 0;
                for (int y = (Integer.parseInt(s[1]) * 160); y < (Integer.parseInt(s[2]) * 160); y++) {
                    sum[l] = sum[l] + file1[y];
                }

                average[l] = (float) sum[l] / ((Integer.parseInt(s[2]) - Integer.parseInt(s[1])) * 160);
                if (s[0].equals("FOR")) {
                    average[l] *= 1.1;
                }
                if (average[l] > maxAverage) {
                    maxAverage = average[l];
                    k = l;
                }
                System.out.println(intonationLevel[(int) k][0]);
                l++;
            }
            // return intonationLevel[(int)k][0];

            // System.out.println(max + " ");

            // for (int y = 0; y < sum.length; y++) {
            // System.out.println(intonationLevel[y][0]);
            // System.out.println("Sum" + sum[y]);
            // System.out.println("Average" + (average[y] / 10000000));
            //
            // }
            return intonationLevel[(int) k][0];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }
}
