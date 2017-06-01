package hu.becseimiklos.prt.hw.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("hu.becseimiklos.prt.hw")
@EnableJpaRepositories("hu.becseimiklos.prt.hw.repository")
@EntityScan("hu.becseimiklos.prt.hw.entity")
public class Main extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private Stage mainStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainSite.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode));
        mainStage = stage;
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    public Stage getMainStage() {
        return mainStage;
    }
}
