package toll.tcm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	public ReadConfig() {
		File src=new File("./Configuration/config.properties");
		try
		{
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
				pro.load(fis);
		}catch(Exception e)
		{
			System.out.println("Exception is :"+e.getMessage());
		}
	}
	public String getTagReaderIAddress()
	{
		String tagreaderipaddress=pro.getProperty("tagReaderIPAddress");
		return tagreaderipaddress;
	}
	public String getApplicationPath()
	{
		String applicationpath=pro.getProperty("applicationPath");
		return applicationpath;
	}
	public String getUsername()
	{
		String username=pro.getProperty("username");
		return username;
	}
	public String getPassword()
	{
		String password=pro.getProperty("password");
		return password;
	}
	public String getAVCCOMPort()
	{
		String avccomport=pro.getProperty("portNameForAVC");
		return avccomport;
	}
	public String getExitAVCCOMPort()
	{
		String exitavccomport=pro.getProperty("portNameForExitAVC");
		return exitavccomport;
	}
	public String getInsertWeightCOMPort()
	{
		String insertweightcomport=pro.getProperty("portNameForWeight");
		return insertweightcomport;
	}
	public String getLaneIPAddress()
	{
		String tagserveraddress=pro.getProperty("laneIPAddress");
		return tagserveraddress;
	}
	public String getTagserverPort()
	{
		String tagserverport=pro.getProperty("serverPort");
		return String.valueOf(tagserverport);
	}
	public String getXEUsername()
	{
		String xeusername=pro.getProperty("XE");
		return xeusername;
	}
	public String getXEPassword()
	{
		String xepassword=pro.getProperty("XEPassword");
		return xepassword;
	}
	public String getExtentExist()
	{
		String is_extent_exist=pro.getProperty("IS_Extent_Exist");
		return is_extent_exist;
	}
}
