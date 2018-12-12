import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;  
import java.io.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;


public class EmployeeInserter {

	static String file = "employees.xml";

	public static void main(String[] args){
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

			System.out.println(prettyPrintXML(doc));
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