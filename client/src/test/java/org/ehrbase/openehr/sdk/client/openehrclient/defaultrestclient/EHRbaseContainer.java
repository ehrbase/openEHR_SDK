/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.lifecycle.Startable;

@SuppressWarnings({"rawtypes", "unchecked"})
public class EHRbaseContainer extends GenericContainer {

    public static final Integer PORT = 8080;

    protected static final String EHRBASE_ADMIN_USER = "ehrbase";

    protected static final String EHRBASE_ADMIN_PASSWORD = "ehrbase";

    protected static final String EHRBASE_USER = "ehrbase_restricted";

    protected static final String EHRBASE_PASSWORD = "ehrbase_restricted";

    private static final String IMAGE = "ehrbase/ehrbase:2.0.0";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public EHRbaseContainer(Network network, List<Startable> startables) {
        super(IMAGE);
        withNetwork(network);
        waitingFor(new LogMessageWaitStrategy()
                .withRegEx(".*Started EhrBase in .*\\s")
                .withTimes(1)
                .withStartupTimeout(Duration.of(120, SECONDS)));
        addExposedPort(PORT);
        addExposedPort(5005);
        dependsOn(startables);
        withLogConsumer(new Slf4jLogConsumer(log));
    }

    @Override
    protected void configure() {
        addEnv(
                "SPRING_DATASOURCE_URL",
                "jdbc:postgresql://"
                        + EHRbasePostgresContainer.CONTAINER_NAME
                        + ":"
                        + EHRbasePostgresContainer.PORT
                        + "/ehrbase");
        addEnv("DB_USER_ADMIN", EHRBASE_ADMIN_USER);
        addEnv("DB_PASS_ADMIN", EHRBASE_ADMIN_PASSWORD);
        addEnv("DB_USER", EHRBASE_USER);
        addEnv("DB_PASS", EHRBASE_PASSWORD);
        addEnv("JAVA_TOOL_OPTIONS", "-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n");
        addEnv("admin-api.active", "true");
    }
}
