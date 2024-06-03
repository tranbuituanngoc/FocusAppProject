package vn.edu.hcmuaf.FocusAppProject.Util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

@Component
public class EmailTemplateUtil {

    public String getEmailTemplate(String fileName, Map<String, String> values) throws FileNotFoundException {
        String filePath = "src/main/resources/templates/email_template/" + fileName+".html";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();

        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }

        String emailTemplate = sb.toString();

        for (Map.Entry<String, String> entry : values.entrySet()) {
            emailTemplate = emailTemplate.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return emailTemplate;
    }
}