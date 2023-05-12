package org.book.chapter9.util;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MongoDBRunner {

    private MongodExecutable mongodExecutable;

    public MongoDBRunner() {
        try {
            String bindIp = "localhost";
            int port = 12345;

            ImmutableMongodConfig mongodConfig = MongodConfig
                    .builder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                    .build();

            MongodStarter starter = MongodStarter.getDefaultInstance();
            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
