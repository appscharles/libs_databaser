package com.appscharles.libs.databaser.programs.server;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

/**
 * The type Server options.
 */
public class ServerOptions extends OptionsBase {

    /**
     * The Port.
     */
    @Option(name = "port",
            abbrev = 'p',
            help = "Port.",
            defaultValue = "")
    public String port;

    /**
     * The Test mode.
     */
    @Option(name = "testMode",
            abbrev = 't',
            help = "TestMode.",
            defaultValue = "false")
    public String testMode;

    /**
     * The Test timeout exit.
     */
    @Option(name = "testTimeoutExit",
            abbrev = 'e',
            help = "Timeout exit server for tests.",
            defaultValue = "5000")
    public String testTimeoutExit;
}
