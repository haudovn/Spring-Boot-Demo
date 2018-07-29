package mdm.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;

public final class DirectoryUtils {
    private static final Logger _LOGGER = LoggerFactory.getLogger(DirectoryUtils.class);

    private static final String _ROOT_DIR = System.getProperty("user.dir");

    private static final String _OUTPUT_NAME = "OutPut";

    private static final String _XLS = "xls";

    private static final String _TEMPLATES = "templates";

    private static final String _FILE_TEMPLATE = "file-templates";

    public static String getRootDir() {
        return _ROOT_DIR;
    }

    public static String getOutputDir() {
        return String.format("%1$s%2$s%3$s", getRootDir(), File.separator, _OUTPUT_NAME);
    }

    public static String getFileTemplateDir() {
        String temp = String.format("%1$s%2$s%3$s", getOutputDir(), File.separator, _FILE_TEMPLATE);
        _LOGGER.info(temp);
        return temp;
    }

    public static String getXLSTemplateDir() {
        String temp = String.format("%1$s%2$s%3$s", getFileTemplateDir(), File.separator, _XLS);
        _LOGGER.info(temp);
        return temp;
    }
}
