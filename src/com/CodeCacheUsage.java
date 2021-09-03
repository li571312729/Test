package com;

import java.io.File;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.tools.attach.VirtualMachine;

public class CodeCacheUsage {

    private static final String CONNECTOR_ADDRESS = "com.sun.management.jmxremote.localConnectorAddress";

    public static void main(String[] args) throws Exception {

        VirtualMachine vm = VirtualMachine.attach("30448");
        JMXConnector connector = null;
        try {
            String connectorAddress = vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);

            if (connectorAddress == null) {
                String agent = vm.getSystemProperties().getProperty("java.home")
                        + File.separator
                        + "lib"
                        + File.separator + "management-agent.jar";
                vm.loadAgent(agent);

                connectorAddress = vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);
            }

            JMXServiceURL url = new JMXServiceURL(connectorAddress);
            connector = JMXConnectorFactory.connect(url);
            MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
            ObjectName name = new ObjectName("java.lang:type=MemoryPool,name=Code Cache");
            System.out.println(mbeanConn.getAttribute(name, "Usage"));
        } finally {
            if(connector != null)
                connector.close();
            vm.detach();
        }
    }
}
