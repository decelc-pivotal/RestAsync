package jaxbmarshall;

import javax.xml.bind.annotation.XmlElement;

public class CountryCity {
	@XmlElement(name="City")
	public String city;
	@XmlElement(name="Country")
	public String country;
	public CountryCity(){
		
	}
}
