package com.inc.nitya;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


/*2.1) Write the example output of the above program first.*/

/*Output in Console:
--------------------------------------------
{MAY=3, APRIL=1, FEBRUARY=1}
In MAY month, 3 file/files are created.
In APRIL month, 1 file/files are created.
In FEBRUARY month, 1 file/files are created.
--------------------------------------------
*/





/*2.2) Then write the actual implementation.*/

public class SecondProgram {
	public static void main(String[] args) throws IOException {
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Please enter the folder path that you want to scan: ");
			String str = sc.next();
			SecondProgram.directory(new File(str));	// You may please replace this path to some valid folder path which is present in your system.
		}
	}
	
	public static void directory(File dir) throws IOException {
	    File[] files = dir.listFiles();
	    List<Month> list = new ArrayList<>();
	    for (File file : files) {
	        if (file.listFiles() != null) {
	            directory(file);     
	        }
	        Path path = Paths.get(file.getAbsolutePath());
		    BasicFileAttributes attr;
		    attr = Files.readAttributes(path, BasicFileAttributes.class);
		    Month month = attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).getMonth();
		    list.add(month);
	    }
	    Map<Month, Long> map = list.stream().collect(Collectors.groupingBy(v->v, Collectors.counting()));
	    System.out.println(map);
	    Iterator<Map.Entry<Month, Long>> itr = map.entrySet().iterator();
	    while(itr.hasNext()) {
	    	Map.Entry<Month, Long> entry= itr.next();
	    	System.out.println("In "+entry.getKey()+" month, "+entry.getValue()+" file/files are created.");
	    }
	}
}
