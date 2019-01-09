import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class ModifyXMLFile {
 
	public static void main(String args[]) {
 			if (args.length != 1) {
			System.out.println("Usage: java ReadXMLFile <filename>");
			System.exit(0);
		}

		String filename = args[0];
		
	   try {
	   	File fXmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		//add age element to the employee	
		Node employee = doc.getElementsByTagName("employee").item(0);
		Element AgeElement  = doc.createElement("age"); 
		AgeElement.setTextContent("12");
		employee.appendChild(AgeElement);
		
		NodeList employeecomponent= employee.getChildNodes();
		//***** Insert code here... *****
		
		/*
		for(int i = 0 ; i<employeecomponent.getLength(); i++) {
			Node firstname = employeecomponent.item(i); 
			System.out.println(firstname.getNodeName());
			System.out.println(firstname.getTextContent());
		
		}
		*/
		
		//update the value of working Hours
		NodeList employeelist = doc.getElementsByTagName("employee");
		
		for(int i = 0 ; i<employeelist.getLength() ; i++) {
			Node employeeEntity = employeelist.item(i) ; 
			NodeList employeecomponents = employeeEntity.getChildNodes();
			for(int j = 0 ;j< employeecomponents.getLength(); j++ ) {
				if(employeecomponents.item(j).getNodeName()=="workingHours") {
						employeecomponents.item(j).setTextContent(String.valueOf(i+12));
				}
			}
		}
		
		
 
		// write the modified content into an xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));
		transformer.transform(source, result);
 
		System.out.println("Done");
 
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
	}
}