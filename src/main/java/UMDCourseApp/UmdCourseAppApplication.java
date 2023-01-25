package UMDCourseApp;

//import Controller.RequestController;
import Objects.*;
import Services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

@SpringBootApplication
//@ComponentScan(basePackageClasses = RequestController.class)
public class UmdCourseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmdCourseAppApplication.class, args);

	}

}
