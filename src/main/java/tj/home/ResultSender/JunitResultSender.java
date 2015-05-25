package tj.home.ResultSender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

public class JunitResultSender {

	public static void main(String[] args) throws HttpException, IOException {
		
		
		// TODO Auto-generated method stub
		String url = args[0];
		String folder = args[1];
		
		HttpClient client = new HttpClient();
		File file = new File(folder);
		
		File filelist[] = file.listFiles();
		
				
	    PostMethod pMethod=new PostMethod(url);
	    int fileCounts=filelist.length;
	    Part[] parts=new Part[fileCounts+1];
	    System.out.println("FileCounts : "+fileCounts);
	    
	    // Insert String part first
	    StringPart sp = new StringPart("Name1","Kim taek Joo");	    
	    parts[0] = sp;
	    
	    int i = 1;
	    for (File f : filelist) {		    
		    FilePart filePart=new FilePart("Testfile",f);
		    parts[i]=filePart;
		    i++;
		    
		    System.out.println("File Name : ("+i+") "+f.getName());
	    }
		MultipartRequestEntity requestEntity=new MultipartRequestEntity(parts,pMethod.getParams());
		pMethod.setRequestEntity(requestEntity);
		
		client.executeMethod(pMethod);
		
		String body=pMethod.getResponseBodyAsString();
		System.out.println(body);		
		
		pMethod.releaseConnection();
	}

}
