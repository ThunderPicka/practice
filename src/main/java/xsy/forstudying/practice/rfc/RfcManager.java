package xsy.forstudying.practice.rfc;


import com.sap.conn.jco.*;
import com.sap.conn.jco.ext.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description: rfc调用管理类
 * @author jiangzl
 * @date 2020-12-21
 * @ClassName: RfcManager
 *
 */
public class RfcManager
{
    private static Logger logger = LoggerFactory.getLogger(RfcManager.class);

    private static String ABAP_AS_POOLED = "ABAP_AS_POOL";


    private static JCOProvider provider = null;

    private static JCoDestination destination = null;

    static {
        Properties properties = loadProperties();

        provider = new JCOProvider();

        // catch IllegalStateException if an instance is already registered
        try {
            Environment.registerDestinationDataProvider(provider);
        } catch (IllegalStateException e) {
            logger.debug(e.getMessage());
        }

        provider.changePropertiesForABAP_AS(ABAP_AS_POOLED, properties);
    }

    public static Properties loadProperties() {
        RfcManager manager = new RfcManager();
        Properties prop = new Properties();
        try {
            prop.load(manager.getClass().getResourceAsStream(
                    "/sap_conf.properties"));
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return prop;
    }

    public static JCoDestination getDestination() throws JCoException {
        if (destination == null) {
            destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        }
        return destination;
    }

    public static JCoFunction getFunction(String functionName) {
        JCoFunction function = null;
        try {
            function = getDestination().getRepository()
                    .getFunctionTemplate(functionName).getFunction();
        } catch (JCoException e) {
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return function;
    }

    public static void execute(JCoFunction function) {
        logger.debug("SAP Function Name : " + function.getName());
        JCoParameterList paramList = function.getImportParameterList();

        if (paramList != null) {
            logger.debug("Function Import Structure : " + paramList.toString());
        }

        try {
            function.execute(getDestination());
        } catch (JCoException e) {
            try
            {
                logger.info("Destination client:"+getDestination().getClient());
                logger.info("Destination serverHost:"+getDestination().getApplicationServerHost());
                logger.info("Destination destinationName:"+getDestination().getDestinationName());
            } catch (JCoException e1)
            {
                e1.printStackTrace();
            }
            logger.error("Destination error : " + e);
        }
        paramList = function.getExportParameterList();

        if (paramList != null) {
            logger.debug("Function Export Structure : " + paramList.toString());
        }
    }

    public static String ping() {
        String msg = null;
        try {
            getDestination().ping();
            msg = "Destination " + ABAP_AS_POOLED + " is ok";
        } catch (JCoException ex) {
            //msg = StringUtil.getExceptionTrace(ex);
            logger.info(ex.getMessage());
        }
        logger.debug(msg);
        return msg;
    }

    public static void main(String[] args) {
        RfcManager.ping();
    }
}

