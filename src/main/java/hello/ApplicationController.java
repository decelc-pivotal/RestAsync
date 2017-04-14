package hello;

import java.io.StringReader;
import java.util.concurrent.Future;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.wsdl.GetCitiesByCountryResponse;
import jaxbmarshall.CountryCityPair;

@RestController
public class ApplicationController {

	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	QuoteClient quoteClient;
	
	@RequestMapping("/weather")
	public JSONArray weather() throws Exception {
		
        // Start the clock
        long start = System.currentTimeMillis();
        
	    Future<GetCitiesByCountryResponse> response1 = quoteClient.getQuote("US", 5000L);
		Future<GetCitiesByCountryResponse> response2 = quoteClient.getQuote("DE", 1000l);
		Future<GetCitiesByCountryResponse> response3 = quoteClient.getQuote("UK", 10000L);
		
        // Wait until they are all done
		while (!(response1.isDone() && response2.isDone() && response3.isDone())) {
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //10-millisecond pause between each check
        }
            
        // Print results, including elapsed time
        logger.info("########## Elapsed time: " + (System.currentTimeMillis() - start));
                        
        JSONArray results = new JSONArray();
        
        results.add(getPair(response1.get()));
        results.add(getPair(response2.get()));
        results.add(getPair(response3.get()));
        
        return results;
	}
	
	private CountryCityPair getPair(GetCitiesByCountryResponse response ) throws JAXBException, XMLStreamException, FactoryConfigurationError {
		
		JAXBContext jc = JAXBContext.newInstance(CountryCityPair.class.getPackage().getName());
		    
		return (CountryCityPair) jc.createUnmarshaller().unmarshal(XMLInputFactory.newFactory()
				.createXMLStreamReader(new StringReader(response.getGetCitiesByCountryResult())));
		
	}
}
