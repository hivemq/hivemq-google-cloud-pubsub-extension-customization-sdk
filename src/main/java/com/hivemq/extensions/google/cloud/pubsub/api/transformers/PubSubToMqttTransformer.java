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
 * {@link com.hivemq.extension.sdk.api.services.publish.Publish} from
 * {@link InboundPubSubMessage}. One instance of the implementing class is
 * created per reference in the pubsub-configuration.xml. The methods of this interface may be called concurrently and
 * must be thread-safe.
 * <p>
 * Your implementation of the pubSubToMqttTransformer must be placed in a Java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the HiveMQ Enterprise Extension for PubSub. In addition a
 * {@code <pubsub-to-mqtt-transformer>} referencing the implementing class via its canonical name must be configured in
 * the {@code pubsub-extension.xml} file.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@FunctionalInterface
public interface PubSubToMqttTransformer extends Transformer<PubSubToMqttInitInput> {

    /**
     * This callback is executed for every {@link InboundPubSubMessage} that is
     * polled by the HiveMQ Enterprise Extension for Google Cloud Pub/Sub and matches the {@code <mqtt-to-pubsub-transformer>} tag
     * configured in the {@code <mqtt-topic-filters>}. It allows the publication of any number of
     * {@link com.hivemq.extension.sdk.api.services.publish.Publish Publishes} via the {@link PubSubToMqttOutput}
     * object. This method is called by multiple threads concurrently. Extensions are responsible for their own
     * exception handling and this method must not throw any {@link Exception}.
     *
     * @param pubSubToMqttInput  the {@link PubSubToMqttInput} contains the triggering
     *                           {@link InboundPubSubMessage}.
     * @param pubSubToMqttOutput the {@link PubSubToMqttOutput} allows to
     *                           {@link PubSubToMqttOutput#setPublishes(java.util.List)} provide a list of Publishes. If
     *                           no {@code pubSubToMqttOutput} is set, an empty List is used as default and the PubSub
     *                           messages will not be processed again, but ignored.
     * @since 4.9.0
     */
    @ThreadSafe
    void transformPubSubToMqtt(
            @NotNull PubSubToMqttInput pubSubToMqttInput, @NotNull PubSubToMqttOutput pubSubToMqttOutput);
}
