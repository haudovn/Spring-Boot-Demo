package mdm.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SystemPropertyUtils {
    private static final Logger _LOGGER = LoggerFactory.getLogger(SystemPropertyUtils.class);

    public static void printSystemProperites() {
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            _LOGGER.info(String.format("Key: %1$s, Value: %2$s", key.toString(), properties.get(key)));
        }
    }
}
