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

package com.hivemq.extensions.pubsub.api.transformers.mqtttopubsub;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.pubsub.api.model.PubSubConnection;
import com.hivemq.extensions.pubsub.api.model.PubSubMessage;
import com.hivemq.extensions.pubsub.api.transformers.Transformer;

import java.util.List;

/**
 * Implement this transformer for the programmatic creation of {@link PubSubMessage}s from
 * {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}s.
 * <p>
 * Your implementation of the MqttToPubSubTransformer must be placed in a java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the HiveMQ Enterprise Extension for PubSub. In addition, a
 * {@code <mqtt-to-pubsub-transformer>} referencing the implementing class via its canonical name must be configured in
 * the {@code pubsub-extension.xml} file.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@FunctionalInterface
public interface MqttToPubSubTransformer extends Transformer<MqttToPubSubInitInput> {

    /**
     * This callback is executed for every MQTT PUBLISH that arrives at your HiveMQ cluster matching the in the
     * {@code <mqtt-to-pubsub-transformer>} tag configured {@code <mqtt-pubsub-filters>}. It allows the publication of
     * any number of {@link PubSubMessage}s via the {@link MqttToPubSubOutput} object.
     *
     * @param input  the {@link MqttToPubSubInput} contains the triggering
     *               {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket} and the {@link PubSubConnection}
     *               information.
     * @param output pass the list of new {@link PubSubMessage}s to the
     *               {@link MqttToPubSubOutput#setPubSubMessages(List)} method.
     * @since 4.9.0
     */
    void transformMqttToPubSub(@NotNull MqttToPubSubInput input, @NotNull MqttToPubSubOutput output);
}
