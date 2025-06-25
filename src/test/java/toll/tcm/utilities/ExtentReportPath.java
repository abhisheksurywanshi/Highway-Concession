package toll.tcm.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import toll.tcm.testCases.BaseClass;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
public class ExtentReportPath extends BaseClass {

	public static String ExtentReportPath() {
		
//		try {
//            File inputFile = new File(System.getProperty("user.dir")+"/extent-config.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(inputFile);
//            doc.getDocumentElement().normalize();
//
//            NodeList nList = doc.getElementsByTagName("reportName");
//            if (nList.getLength() > 0) {
//                Node nNode = nList.item(0);
//                nNode.setTextContent(Toll_Name);
//
//                // Write the updated document to file
//                TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                Transformer transformer = transformerFactory.newTransformer();
//                DOMSource source = new DOMSource(doc);
//                StreamResult result = new StreamResult(new File(System.getProperty("user.dir")+"/extent-config.xml"));
//                transformer.transform(source, result);
//
//                System.out.println("reportName updated to: " + Toll_Name);
//            } else {
//                System.out.println("reportName element not found.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		Calendar calendar = Calendar.getInstance();
        String year =String.valueOf(calendar.get(Calendar.YEAR));
        
        
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
       
        
        String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
       
		 String path=System.getProperty("user.dir")+"\\ExtentReport\\"+year+"\\"+month+"\\"+dayOfMonth+"\\"+timeStamp+"_index.html";
		 return path;
	}

}
