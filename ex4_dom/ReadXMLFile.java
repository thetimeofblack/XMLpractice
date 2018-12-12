import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
public class ReadXMLFile {
 
	public static void main(String args[]) {
		/*	
		if (args.length != 1) {
			System.out.println("Usage: java ReadXMLFile <filename>");
			System.exit(0);
		}
		 */
		String filename = "ex4_dom/employees.xml";
 
	  try {
 
		File fXmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		Element rootelement = doc.getDocumentElement(); 
		System.out.println("RootElement: "+rootelement.getNodeName());
		 NodeList nList= doc.getElementsByTagName("employee");
		 
		for(int i= 0 ;i <nList.getLength() ; i++) {
			Element node = (Element)nList.item(i);
			System.out.println(node.getNodeName());
			System.out.println("FirstName: "+getTagValue("firstName",node));
			System.out.println("LastName: "+getTagValue("lastName",node));
			System.out.println("workingHours: "+getTagValue("workingHours",node));
			
		}
			
		
		
		
	  } catch (Exception e) {
		e.printStackTrace();
	  }
  }
  
 
  private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
 
        Node nValue = (Node) nlList.item(0);
 
	return nValue.getNodeValue();
  }
 
}