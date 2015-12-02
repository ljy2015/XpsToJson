package com.yiban.datacenter.XpsToJson;

import java.io.File;   
import java.io.FileWriter;   
import java.io.IOException;   
import java.io.Writer;   
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Iterator;   
import java.util.List;

import org.dom4j.Document;   
import org.dom4j.DocumentException;   
import org.dom4j.DocumentHelper;   
import org.dom4j.Element;   
import org.dom4j.io.SAXReader;   
import org.dom4j.io.XMLWriter; 

public class XMLParse {
	
	public static boolean ParseXML(String fileName){
	File inputXml=new File(fileName);   
	SAXReader saxReader = new SAXReader();   
	try {   
		Document document = saxReader.read(inputXml);   
	
		Element employees=document.getRootElement();   
		int occur=0;
		for(Iterator i = employees.elementIterator(); i.hasNext();){   
			occur+=1;

			System.out.println(occur);
			Element employee = (Element) i.next();   
			
			if(occur==1)
				continue;
			
			System.out.println(employee.asXML());
			
			
			
			for(Iterator j = employee.elementIterator(); j.hasNext();){   
				
				Element node=(Element) j.next();   
				
				//System.out.println(node.getText());
				
				System.out.println(node.attributeCount());
				
				org.dom4j.Attribute attribute=node.attribute("UnicodeString");
				if(attribute!=null)
					System.out.println(attribute.getName()+":"+attribute.getText());
				
//				List<String> attributes=node.attributes();
//				for(Iterator k=attributes.iterator();k.hasNext();){
//					System.out.println(k.next().equals(" UnicodeString="));
//				}



				//System.out.println(node.getName()+":"+node.getStringValue());   
			}   
		}   
	} catch (DocumentException e) {   
		System.out.println(e.getMessage());   
	}    

	return false;
	}   

}
