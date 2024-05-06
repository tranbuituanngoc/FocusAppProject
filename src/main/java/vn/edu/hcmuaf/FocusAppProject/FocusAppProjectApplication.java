package vn.edu.hcmuaf.FocusAppProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"vn.edu.hcmuaf.FocusAppProject", "controller", "security", "service", "repository","models","dto"})
public class FocusAppProjectApplication {

 public static void main(String[] args) {
  SpringApplication.run(FocusAppProjectApplication.class, args);
 }

}