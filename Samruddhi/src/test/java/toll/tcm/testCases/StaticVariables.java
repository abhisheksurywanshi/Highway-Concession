package toll.tcm.testCases;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import io.appium.java_client.windows.WindowsDriver;
import toll.tcm.Hardware.GetAVCData;
import toll.tcm.Hardware.ImageVerification;
import toll.tcm.utilities.ReadConfig;

public class StaticVariables extends JFrame {
	static ReadConfig readconfig=new ReadConfig();
	public static String url=readconfig.getApplicationPath();
	public static String tagReaderIPaddress=readconfig.getTagReaderIAddress();
	public static String username=readconfig.getUsername();
	public static String password=readconfig.getPassword();
	public static String LaneIPAddress=readconfig.getLaneIPAddress();
	public static String Port=readconfig.getTagserverPort();
	public static String XEUsername=readconfig.getXEUsername();
	public static String XEPassword=readconfig.getXEPassword();
	
	public static WebDriverWait wait;

	public static DesiredCapabilities capabilities;
	public static WindowsDriver driver;
	static String AvcWindow;
	protected static OutputStream outputStreamForAVC, outputStreamForExitAVC ,outputStreamForWeight, outputStreamForTag;
	
	protected static CommPortIdentifier portIdentifierForAVC, portIdentifierForExitAVC, portIdentifierForWeight, portIdentifierForTag;
	protected static SerialPort serialPortForAVC,serialPortForExitAVC,serialPortForWeight,serialPortForTag;		
	protected static Random random;
	protected static String path,Is_ETCAutoLogin;
	
	protected static boolean visible=false;
	protected static String Weight;
	protected static Socket socket;
	protected static XSSFWorkbook xw;
	protected static XSSFSheet sheet;	
	
	protected static InputStream inputStreamForTag,inputStreamForAVC;
	protected static String DataBaseurl = "jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE"; // Replace with your database URL
	protected static String DatBaseusername = XEUsername; // Replace with your username1
	protected static String DatBasepassword = XEPassword; // Replace with your password
	protected static int truck;
	protected static int mav3;
	protected static int mav4;
	protected static int mav5;
	protected static int mav6,mav;
	protected static int ExemptTypes;
	protected static int SchedualClassType;
	protected static int OtherCashKey;
	protected static int NonSchedualClassType;
	protected static int PaymentTypes;
	protected static Map<String, String> SchedualClassTypes=  new HashMap<String, String>();
	
	
	protected static  Map<String, String> NonSchedualClassTypes= new HashMap<String, String>();
	protected static  Map<String, String> OtherCashKeys= new HashMap<String, String>();
	protected static  Map<String, String> ClassCodes= new HashMap<String, String>();
	protected static  Map<String, String> getPaymentTypeIsReference= new HashMap<String, String>();
	protected static  Map<String, String> TollWimClassDetails= new HashMap<String, String>();
	protected static  Multimap<String, String> IsOverWeightApplicable=  ArrayListMultimap.create();
	protected static  Multimap<String, String> PaymentSubType=  ArrayListMultimap.create();
	protected static String[] keysArrayVehicleCode;
	protected static String[] keysArraygetPaymentTypeIsReference;
	protected static String[] keysArrayForSchedualVehicle;
	protected static String[] keysArrayForNonSchedualVehicle;
	protected static String[] keysArrayForOtherCashKeys;
	protected static String[] keysArrayForTolWimClassDetail;
	protected static String[] keysArrayForIsOverWeightApplicable;
	protected static String[] keysArrayForIsExemptRemarkKent;
	protected static ArrayList paymentTypes;
	protected static ArrayList <String> ClassArrayForTollWimClassDetail =new ArrayList<String>();
	protected static ArrayList <Integer> ToWeight =new ArrayList<Integer>();
	protected static  ArrayList <Integer> ToWeightArrayList=new ArrayList<Integer>();
	protected static  ArrayList <Integer> FromWeightArrayList=new ArrayList<Integer>();
	protected static ArrayList <String>AvailableExemptCar=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptLCV=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptTruck=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptBus=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptMAV3=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptMAV4=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptMAV5=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptMAV6=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptMAV7=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptOSV=new ArrayList<String>();
	protected static ArrayList <String>AvailableExemptMAV=new ArrayList<String>();
	protected static ArrayList <String>BlackListedTags=new ArrayList<String>();
	protected static ArrayList <String>GetOperatorList=new ArrayList<String>();
	protected static Map<String, String> CARIsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> LCVIsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> BUSIsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> TRUCKIsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV3IsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV4IsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV5IsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV6IsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, String> OSVIsCaptureFlags = new HashMap<String, String>();
	protected static Map<String, Map<String, String>> allVehicleCaptureFlags = new HashMap<String, Map<String, String>>();
	
	protected static Map<String, String> CARIsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> LCVIsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> BUSIsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> TRUCKIsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV3IsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV4IsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV5IsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> MAV6IsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, String> OSVIsExemptRemarkFlags = new HashMap<String, String>();
	protected static Map<String, Map<String, String>> allVehicleIsExemptRemarkFlags = new HashMap<String, Map<String, String>>();
	protected static Map<String, String> IsExemptRemarkKent = new HashMap<String, String>();
	
	protected static Map<String, String> CashIsReferenceFlags = new HashMap<String, String>();
	protected static Map<String, String> CardIsReferenceFlags = new HashMap<String, String>();
	protected static Map<String, String> WalletIsReferenceFlags = new HashMap<String, String>();
	protected static Map<String, Map<String, String>> allReferenceFlags = new HashMap<String, Map<String, String>>();
	
	protected static ArrayList <String>VehicleCDWiseExemptType=new ArrayList<String>();
	protected static ArrayList <String>PaymentType=new ArrayList<String>(); 
	protected static WebDriver webdriver;
	protected static ImageVerification i;
	protected static String Home_Page_Window,In_Out,IP_Address ,Lane_ID,Toll_ID,AVC_COM,Exit_Avc_Com_No,Lane_CD,WIM_COM_NO,Toll_Name,Is_LSDU,ExemptKey,Is_ETC_Popup="N" , Is_Card_Ref,Is_ManualInsertWeight="N",Is_0Weight_Insert="N",Is_Weight_Delete,Is_Profile_IP,Is_Exempt_Remark;
	protected  static String COMPortNameForAVC;
	protected  static String COMPortNameForExitAVC;
	public static String COMPortNameForWeight;
	public static boolean IsAutoLane=false;
	protected static String hexData;
	protected static byte[] dataBytes;
	protected static String FirstFreeConvoy;
	protected static String toReturnAVCClass;
	protected static String [] VClassForFrCnvy;
	protected static String [] ExemptType;
	protected static String FourDigitVRN;
	protected static String timeStamp;
	protected static int randomFreeConvoy;
	protected static DecimalFormat twodigits;
	protected static String AVCCLASS;
	protected static String AVCRandom;
	protected static String LastTransaction;
	protected static int Before_IAVC,After_IAVC;
	protected static int subClassStart,subClassEnd,randomsubClass;
	protected static String MOPType;	
	protected static int randomVRN;
	protected static GetAVCData getdata;
	protected static Connection connection;
	protected  static String IsWimAvailable;
	protected static String currentShiftAlias;
	public Map<String, String> itemList = new HashMap<String, String>();
	public Multimap<String, String> itemMultiMap =  ArrayListMultimap.create();
	protected static ArrayList <String>FastagVehicleMasterETCClass=new ArrayList<String>();
	public Map<String,String> FastagVehicleMaster= new HashMap<String, String>();
	  public static ExtentReports extent = new ExtentReports();
	  
	public static ExtentSparkReporter spark= new ExtentSparkReporter("index.html");
	 
	 
	 
}
