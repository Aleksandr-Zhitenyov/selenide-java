package demoqa.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:local.properties",
        "system:properties",
        "system:env"})
public interface ITestConfig extends Config {

    @Config.Key("login")
    String login();

    @Config.Key("password")
    String password();
}

