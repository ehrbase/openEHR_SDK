/*
 * Copyright (c) 2024 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

/**
 * @author Stefan Spiska
 */
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

@SuppressWarnings({"rawtypes", "unchecked"})
public class EHRbasePostgresContainer extends GenericContainer {

    protected static final Integer PORT = 5432;
    protected static final String CONTAINER_NAME = "postgres";
    private static final String IMAGE = "ehrbase/ehrbase-v2-postgres:16.2";
    private static final String POSTGRES_MAPPING_USER = "postgres";
    private static final String POSTGRES_MAPPING_PW = "postgres";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public EHRbasePostgresContainer(Network network) {
        super(IMAGE);

        withNetwork(network);

        // Wait 2 times for the given log message cause after the init script was executed this message
        // will be displayed again and only then the postgres is finally ready.
        waitingFor(new LogMessageWaitStrategy()
                .withRegEx(".*database system is ready to accept connections.*\\s")
                .withTimes(2)
                .withStartupTimeout(Duration.of(60, SECONDS)));

        addExposedPort(PORT);

        withNetworkAliases(CONTAINER_NAME);

        withLogConsumer(new Slf4jLogConsumer(log));
    }

    @Override
    protected void configure() {
        addEnv("POSTGRES_USER", POSTGRES_MAPPING_USER);
        addEnv("POSTGRES_PASSWORD", POSTGRES_MAPPING_PW);
        addEnv("EHRBASE_USER_ADMIN", EHRbaseContainer.EHRBASE_ADMIN_USER);
        addEnv("EHRBASE_PASSWORD_ADMIN", EHRbaseContainer.EHRBASE_ADMIN_USER);
        addEnv("EHRBASE_USER", EHRbaseContainer.EHRBASE_USER);
        addEnv("EHRBASE_PASSWORD", EHRbaseContainer.EHRBASE_PASSWORD);
    }
}
