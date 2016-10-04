package pocketS;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileRefine {
	static int i=0;
public static void main(String args[])
{
	try
	{
		
	Files.walk(Paths.get("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\All files")).forEach(filePath -> {
	    if (Files.isRegularFile(filePath)) {
	        System.out.println(filePath.getFileName());
	        i++;
	        fileRefining(filePath.getFileName().toString());
	        
	        
	    }
	});
	System.out.println(i);
	
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
static void fileRefining(String name)
{
	File source;
	File dest;
	for(int j=1;j<=11;j++)
	{
		if(name.contains("_1_"+j+"_"))
		{
			source = new File("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\All files\\"+name);
			dest = new File("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\"+j+"\\"+name);
			
	    	   if(source.renameTo(new File("D:\\WorkSpace_Studio\\Models\\Clustering\\files\\"+j+"\\"+name))){
	    		System.out.println("File is moved successful!");
	    	   }else{
	    		System.out.println("File is failed to move!");
	    	   }
		}
	}
}

}
