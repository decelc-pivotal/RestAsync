package jaxbmarshall;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="NewDataSet")
public class CountryCityPair {
	@XmlElement(name="Table")
	public List<CountryCity> table = new ArrayList<CountryCity>();
	public CountryCityPair(){
		
	}
	
}
