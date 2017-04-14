
package hello;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import hello.wsdl.GetCitiesByCountry;
import hello.wsdl.GetCitiesByCountryResponse;

public class QuoteClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);

	@Async
	public Future<GetCitiesByCountryResponse>  getQuote(String ticker, long timeout) throws InterruptedException {

		GetCitiesByCountry request = new GetCitiesByCountry();
		request.setCountryName("US");
		//request.setSymbol(ticker);

		log.info("Requesting quote for " + ticker);

		GetCitiesByCountryResponse response = (GetCitiesByCountryResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.webservicex.net/globalweather.asmx",
						request,
						new SoapActionCallback("http://www.webserviceX.NET/GetCitiesByCountry"));

        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(timeout);
        
		return new AsyncResult<>(response);
	}

}
