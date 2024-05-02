package vn.edu.hcmuaf.FocusAppProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"vn.edu.hcmuaf.FocusAppProject", "Controler"})
public class FocusAppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocusAppProjectApplication.class, args);
	}

}
