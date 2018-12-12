import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;  
import java.io.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;
import javax.xml.xpath.*;
import java.text.DecimalFormat;
        
public class DOMModificationDemoMain {


  public static void main(String[] args){
        if (args.length != 1) {
                System.out.println("Usage: java DOMModificationDemoMain <xmlfile>");
                System.exit(0);
    }


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
		//create a parser object
                DocumentBuilder builder = factory.newDocumentBuilder();
		//parse the document
                Document doc = builder.parse( new File(args[0]) );
		//get access to the root element (aka document element)
                Element el = doc.getDocumentElement();

                //*** Task 1: remove the book with title "Moby Dick" ***

		//create an XPath-Object
	        XPath xpath = XPathFactory.newInstance().newXPath();
		//specify the XPath expression
  	        String expression = "//book[title='Moby Dick']";
		//evaluate the XPath expression (with 1 node expected as the result)
                Node myNode = (Node)xpath.evaluate(expression, doc, XPathConstants.NODE);
		//remove this node (child node of the root element)
                el.removeChild(myNode);


                //*** Task 2: increase the price of "Sword of Honour" by 10% ***

		//specify the XPath expression
  	        expression = "//book[title='Sword of Honour']/price/text()";
		//evaluate the XPath expression (with 1 node expected as the result) - the returned node will be a text node, i.e. of type CharacterData
                CharacterData myTextNode = (CharacterData)xpath.evaluate(expression, doc, XPathConstants.NODE);

		//get and modify the old value
		Double price = new Double(myTextNode.getData()) * 1.1;

		//convert the new value to String (2 fraction digits) and set it
		DecimalFormat df = new DecimalFormat(".00");
		myTextNode.setData(df.format(price).replaceAll(",", "."));

		//print out the modified document
                System.out.println(prettyPrintXML(doc));
    }
    catch (XPathExpressionException xpe) {
            System.err.println(xpe);
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
  
	public static String prettyPrintXML(Node node) {
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer serializer;
		try {
			//create a new serializer
			serializer = tfactory.newTransformer();
			//set the pretty print properties (indent by 2 characters)
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			//set the output
			StringWriter output = new StringWriter();
			//transform
			serializer.transform(new DOMSource(node), new StreamResult(output));
			return output.toString();
		} catch (TransformerException e) {
			// this is fatal, just dump the stack and throw a runtime exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}