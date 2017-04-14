package jaxbmarshall;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
	public CountryCityPair createCountryCityPair(){
		return new CountryCityPair();
	}
	public CountryCity createCountryCity(){
		return new CountryCity();
	}
}
