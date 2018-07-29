package mdm.demo.controller;

import mdm.demo.model.User;
import mdm.demo.util.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "${download.pattern.url}")
public class DownloadController {

    @Value("${user-template:user-user-template.xls}")
    private final String _TEMPLATE_NAME_FILE = "user-template.xls";

    private final String _USER_FILE = "User";

    private File getTemplateFile() throws URISyntaxException {
        return new File(DirectoryUtils.getXLSTemplateDir(), _TEMPLATE_NAME_FILE);
    }

    @RequestMapping(value = "template", produces = "application/vnd.ms-excel")
    public byte[] downloadTemplateFile() throws Exception {
        try {
            File file = getTemplateFile();
            if (file.exists() && file.canRead()) {
                _LOGGER.info("Can access this file");
                InputStream inputStream = new FileInputStream(file);
                return IOUtils.toByteArray(inputStream);
            } else {
                _LOGGER.error("Doesn't exist the file" + file.getAbsolutePath());
                return null;
            }
        } catch (Exception e) {
            _LOGGER.error("Can not download file", e);
            return null;
        }
    }

    @RequestMapping(value = "/user/{num}", produces = "application/vnd.ms-excel")
    public void downloadFile(@PathVariable(value = "num") int num, HttpServletResponse response) throws IOException, Exception {
        try {
//            String pathFile = DirectoryUtils.getXLSTemplateDir();
//            String nameFile = String.format("%1$s%2$s%3$d%4$s%5$s", _USER_FILE, StringPoolUtils.UNDERLINE, num, StringPoolUtils.PERIOD, ExtensionFileUtils.XLS_EXTENSION);
//            File file = new File(pathFile, nameFile);
//            if (!file.exists()) {
//                Files.createFile(file.toPath());
//            }
//            _LOGGER.info("Can access this file");
            User user = UserUtils.getStaticUserForTest();
            File file = ExcelUtils.writeListValuesToOneRow(UserUtils.getListValues(user), getTemplateFile());
            InputStream inputStream = new FileInputStream(file);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            _LOGGER.error("Can not download file", e);
            throw e;
        }
    }

    private final Logger _LOGGER = LoggerFactory.getLogger(DownloadController.class);

}