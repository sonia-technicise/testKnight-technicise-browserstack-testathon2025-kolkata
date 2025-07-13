package runners;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.*;

public class BstackRunner {
    public WebDriver driver;
    protected WebDriverWait wait;

    public static String userName, accessKey;
    public static Map<String, Object> browserStackYamlMap;
    public static final String USER_DIR = "user.dir";

    public BstackRunner() {
        File file = new File(getUserDir() + "/browserstack.yml");
        this.browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
    }

    @BeforeEach
    public void setUp() throws Exception {
        String runEnv = System.getenv("RUN_ENV"); // "local" or null

        if ("local".equalsIgnoreCase(runEnv)) {
            System.out.println("[Runner] Running tests locally with Chrome GUI");
        
            // Set up ChromeDriver with GUI
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false); // Important: GUI mode
            options.addArguments("--remote-allow-origins=*"); // Fix CDP issue
            options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issue
            options.addArguments("--no-sandbox"); // Sometimes required in Linux
            options.addArguments("--start-maximized");
            options.addArguments("--user-data-dir=/tmp/chrome-profile"); // Avoid CDP 403
        
            driver = new ChromeDriver(options);
        }else {
            //  BrowserStack setup
            System.out.println("[Runner] Running tests on BrowserStack.");
            MutableCapabilities capabilities = new MutableCapabilities();

            userName = System.getenv("BROWSERSTACK_USERNAME") != null
                    ? System.getenv("BROWSERSTACK_USERNAME")
                    : (String) browserStackYamlMap.get("userName");

            accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null
                    ? System.getenv("BROWSERSTACK_ACCESS_KEY")
                    : (String) browserStackYamlMap.get("accessKey");

            if (userName == null || accessKey == null) {
                throw new IllegalStateException("Missing BrowserStack credentials. Set environment variables or check browserstack.yml.");
            }

            HashMap<String, Object> bStackOptions = new HashMap<>();
            bStackOptions.put("source", "junit5:sample-master:v1.2");
            capabilities.setCapability("bstack:options", bStackOptions);

            driver = new RemoteWebDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName, accessKey)),
                    capabilities
            );
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getUserDir() {
        return System.getProperty(USER_DIR);
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try {
            InputStream inputStream = Files.newInputStream(yamlFile.toPath());
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            map.putAll(config);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }
}
