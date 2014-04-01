package com.mycompany.template.rule;

import org.junit.rules.ExternalResource;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * Created by azee on 4/1/14.
 */
public class EmbeddedMongoRule extends ExternalResource {
    public final static int DEFAULT_PORT = 27027;

    private final int port;

    private MongodProcess mongodProcess;

    private MongodExecutable mongodExecutable;

    public EmbeddedMongoRule() {
        this(DEFAULT_PORT);
    }

    public EmbeddedMongoRule(int port) {
        this.port = port;
    }

    public void before() throws Throwable {
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        mongodExecutable = runtime.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build());
        mongodProcess = mongodExecutable.start();
    }

    public void after() {
        mongodProcess.stop();
        mongodExecutable.stop();
    }


}

