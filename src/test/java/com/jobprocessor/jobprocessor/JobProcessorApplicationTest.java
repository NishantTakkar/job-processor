package com.jobprocessor.jobprocessor;

import com.jobprocessor.jobprocessor.request.JobRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobProcessorApplicationTest {

	@Before
	public void beforeTest(){

	}

	@Test
	public void contextLoads() throws IOException {

		JobRequest jobRequest=new JobRequest();
		URL url=new URL("http://www.oracle.com/");
		//jobRequest.setUrlList(Arrays.asList(url));
		URL url1 = new URL("https://localhost:8080/submit");
		URLConnection con = url1.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		http.connect();
	}

}
