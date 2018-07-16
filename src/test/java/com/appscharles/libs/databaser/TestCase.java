package com.appscharles.libs.databaser;

import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.logger.configurators.Log4jConsole;
import org.apache.logging.log4j.Level;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 17:15
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class TestCase {

    @Before
    public void before(){
        new Log4j2Console(Level.TRACE).config();
       new Log4jConsole(org.apache.log4j.Level.INFO).config();
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

}
