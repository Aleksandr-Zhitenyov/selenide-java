package demoqa.config;

import org.aeonbits.owner.ConfigFactory;

public class TestConfig {
    private static final ITestConfig testConfig = ConfigFactory.create(ITestConfig.class);

    public static String getLogin() {
        return testConfig.login();
    }

    public static String getPassword() {
        return testConfig.password();
    }
}
