import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;  
import java.io.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;


public class EmployeeInserter {

	static String file = "ex4_dom/employees.xml";

	public static void main(String[] args) throws TransformerException{
		if (args.length != 3) {
			System.out.println("Usage: java EmployeeInserter <firstName> <lastName> <workingHours>");
			System.exit(0);
		}

		String firstName = args[0];
		String lastName = args[1];
		String workingHours = args[2];

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse( new File( file ) );

			Element rootElement = doc.getDocumentElement();
			Element newEmployee = createNewEmployee(doc, firstName, lastName, workingHours);

			//***** Insert code here... *****
			rootElement.appendChild(newEmployee);
			System.out.println(prettyPrintXML(doc));
			// write the modified content into an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(file));
			transformer.transform(source, result);
	 
			System.out.println("Done");
		}
		catch(ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		}
		catch(SAXException se) {
			System.out.println(se.getMessage());
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public static Element createNewEmployee( Document doc, String firstName, 
			String lastName, String workingHours ) {

		Element newEmployee = doc.createElement("employee");
		Element newFirstName = doc.createElement("firstName"); 
		Element newLastName = doc.createElement("lastName"); 
		Element newworkingHours = doc.createElement("workingHours");
		newFirstName.setTextContent(firstName);
		newLastName.setTextContent(lastName);
		newworkingHours.setTextContent(workingHours);
		newEmployee.appendChild(newFirstName);
		newEmployee.appendChild(newLastName);
		newEmployee.appendChild(newworkingHours);
		//***** Insert code here... *****	

		return newEmployee;
	}


	public static String prettyPrintXML(Node node) {
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer serializer;
		try {
			serializer = tfactory.newTransformer();
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			StringWriter output = new StringWriter();
			serializer.transform(new DOMSource(node), new StreamResult(output));
			return output.toString();
		} catch (TransformerException e) {
			// this is fatal, just dump the stack and throw a runtime exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}