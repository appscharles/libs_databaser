package com.appscharles.libs.databaser;

import com.appscharles.libs.logger.configurators.Log4j2Console;
import org.apache.logging.log4j.Level;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * The type Test case.
 */
public class TestCase {

    @Before
    public void before(){
        new Log4j2Console(Level.INFO).config();
     //  new Log4jConsole(org.apache.log4j.Level.INFO).config();
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

}
