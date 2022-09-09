/*
 * Copyright 2022-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extensions.google.cloud.pubsub.api.transformers;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.ThreadSafe;
import com.hivemq.extensions.google.cloud.pubsub.api.model.InboundPubSubMessage;

/**
 * Implement this transformer for the programmatic creation of
 * {@link com.hivemq.extension.sdk.api.services.publish.Publish Publishes} from {@link InboundPubSubMessage}s. One
 * instance of the implementing class is created per reference in the google-cloud-pubsub-configuration.xml. The methods
 * of this interface may be called concurrently and must be thread-safe.
 * <p>
 * Your implementation of the PubSubToMqttTransformer must be placed in a Java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the "HiveMQ Enterprise Extension for Google Cloud Pub/Sub". In
 * addition, a {@code <pubsub-to-mqtt-transformer>} referencing the implementing class via its canonical name must be
 * configured in the {@code google-cloud-pubsub-configuration.xml} file.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@FunctionalInterface
public interface PubSubToMqttTransformer extends Transformer<PubSubToMqttInitInput> {

    /**
     * This callback is executed for every {@link InboundPubSubMessage} that the "HiveMQ Enterprise Extension for Google
     * Cloud Pub/Sub" polls from Google Pub/Sub according to the configured {@code <pubsub-subscriptions>}
     * in the {@code <pubsub-to-mqtt-transformer>} tag. It allows the publication of any number of
     * {@link com.hivemq.extension.sdk.api.services.publish.Publish Publishes} via the {@link PubSubToMqttOutput}
     * object. This method is called by multiple threads concurrently. Extensions are responsible for their own
     * exception handling and this method must not throw any {@link Exception}.
     *
     * @param pubSubToMqttInput  The {@link PubSubToMqttInput} contains the triggering {@link InboundPubSubMessage}.
     * @param pubSubToMqttOutput The {@link PubSubToMqttOutput} allows to
     *                           {@link PubSubToMqttOutput#setPublishes(java.util.List)}.
     *                           If no output is set, an empty List is used as default and the PubSub messages will not
     *                           be processed again, but ignored.
     * @since 4.9.0
     */
    @ThreadSafe
    void transformPubSubToMqtt(
            @NotNull PubSubToMqttInput pubSubToMqttInput, @NotNull PubSubToMqttOutput pubSubToMqttOutput);
}
