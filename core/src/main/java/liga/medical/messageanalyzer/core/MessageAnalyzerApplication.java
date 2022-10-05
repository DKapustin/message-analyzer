package liga.medical.messageanalyzer.core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"liga.medical.medicalmonitoring", "liga.medical.common.service"})
public class MessageAnalyzerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalMonitoringApplication.class, args);
    }
}