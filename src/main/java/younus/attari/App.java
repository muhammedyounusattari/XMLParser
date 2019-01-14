package younus.attari;

import java.util.*;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import younus.attari.model.Employee;

public class App {
	static String file = "employee.xml";
	static String path = System.getProperty("user.dir") + "/src/main/resources/" + file;

	public static void main(String[] args) {
		formXMLUsingDOMParser();
		readXMLUsingDOMParser();

	}

	private static void formXMLUsingDOMParser() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = null;
		try {
			dbBuilder = dbFactory.newDocumentBuilder();
			Document document = dbBuilder.newDocument();

			Element rootElement = document.createElement("Employees");
			document.appendChild(rootElement);

			Element childElement = document.createElement("Employee");
			rootElement.appendChild(childElement);

			Element id = document.createElement("id");

			id.appendChild(document.createTextNode("1208"));
			childElement.appendChild(id);

			Element name = document.createElement("name");
			name.appendChild(document.createTextNode("Muhammed Younus Attari"));
			childElement.appendChild(name);

			Element desig = document.createElement("desig");
			desig.appendChild(document.createTextNode("Software Developer"));
			childElement.appendChild(desig);

			Element salary = document.createElement("salary");
			salary.appendChild(document.createTextNode("12345"));
			childElement.appendChild(salary);

			// creating and writing in XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(path));
			transformer.transform(domSource, streamResult);
			System.out.println(path + "created");

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

	}

	private static void readXMLUsingDOMParser() {

		File xmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			NodeList nodeList = document.getElementsByTagName("Employee");
			List<Employee> list = new ArrayList<>();

			for (int i = 0; i < nodeList.getLength(); i++) {
				list.add(getEmployee(nodeList.item(i)));
			}

			for (Employee employee : list)
				System.out.println(employee);

		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}

	private static Employee getEmployee(Node node) {
		Employee emp = new Employee();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			emp.setId(getTagValue("id", element));
			emp.setName(getTagValue("name", element));
			emp.setSal(getTagValue("salary", element));
			emp.setDesig(getTagValue("desig", element));
		}
		return emp;
	}

	private static String getTagValue(String name, Element element) {
		return element.getElementsByTagName(name).item(0).getChildNodes().item(0).getNodeValue();
	}
}
