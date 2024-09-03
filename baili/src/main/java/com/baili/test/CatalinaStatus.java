package com.baili.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.Socket;

public class CatalinaStatus
{
  protected static String configFile = "conf/server.xml";
  protected static String serverShutdown;
  protected static int serverPort;

  public static void main (String args[])
  {
    Document configDom = getXmlDom (configFile ());
    parseDocument (configDom);

    try
    {
      Socket localSocket = new Socket ("127.0.0.1", serverPort);
      System.err.println ("Server status:  UP");
      if ((args.length > 0) && (args[0].equalsIgnoreCase ("shutdown")))
      {
        System.out.println ("Tomcat shutdown initiated" );
        doShutdown (localSocket);
      }

      localSocket.close ();
    }
    catch (IOException e)
    {
      System.err.println ("Server status:  DOWN");
      System.exit(1);
    }
  }

  protected static File configFile ()
  {
    File confFile = new File (configFile);
    if (!confFile.isAbsolute())
      confFile = new File (System.getProperty ("catalina.base"), configFile);
    return (confFile);
  }

  public static Document getXmlDom (File fileName)
  {
    try
    {
      // Create a builder factory
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();

      // Create the builder and parse the file
      Document doc = factory.newDocumentBuilder ().parse (fileName);
      return doc;
    }
    catch (SAXException e)
    {
      // A parsing error occurred; the xml input is not valid
      e.printStackTrace ();
    }
    catch (ParserConfigurationException e)
    {
      e.printStackTrace ();
    }
    catch (IOException e)
    {
      e.printStackTrace ();
    }
    return null;
  }

  private static void parseDocument (Document configDom)
  {

    Element docEle = configDom.getDocumentElement ();
    serverPort = Integer.parseInt (docEle.getAttribute ("port"));
    serverShutdown = docEle.getAttribute ("shutdown");
  }
  
  private static void doShutdown (Socket localSocket)
  {
    try
    {
      OutputStream outStream = localSocket.getOutputStream ();

      for (int i = 0; i < serverShutdown.length (); i++)
        outStream.write (serverShutdown.charAt (i));
      outStream.flush ();
      outStream.close ();
    }
    catch (IOException e)
    {
      System.out.println ("ERROR: I/O Exception during server shutdown.");
      e.printStackTrace ();
    }
  }
  
  public void shutDownServer() {
  	Runtime runtime = Runtime.getRuntime();//返回与当前的Java应用相关的运行时对象
      //指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例
      Process process = null;
		try {
			process = runtime.exec("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\bin\\shutdown.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
      runtime.gc();//运行垃圾回收器
      String line = null;
      String content = "";
      BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
      try {
			while((line = br.readLine()) != null) {
			    content += line + "\r\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
      System.out.println(content);    	
	}
}
