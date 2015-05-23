package tj.home.ResultSender;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class JunitResultSender {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		String url = args[0];
		
		HttpClient client = new HttpClient();
				
	    PostMethod pMethod=new PostMethod(url);
	    int fileCounts=fileList.size();
	    Part[] parts=new Part[fileCounts];
	    for (int i=0; i < fileList.size(); i++) {
		    File f=(File)fileList.get(i);
		    FilePart filePart=new FilePart("Filedata" + i,f);
		    parts[i]=filePart;
	    }
		MultipartRequestEntity requestEntity=new MultipartRequestEntity(parts,pMethod.getParams());
		pMethod.setRequestEntity(requestEntity);
		
		client.executeMethod(pMethod);
		
		String body=pMethod.getResponseBodyAsString();
		System.out.println(body);
		
		
		pMethod.releaseConnection();
	}

}
