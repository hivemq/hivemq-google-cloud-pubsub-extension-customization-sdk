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
import com.hivemq.extensions.google.cloud.pubsub.api.model.OutboundPubSubMessage;

import java.util.List;

/**
 * Implement this transformer for the programmatic creation of
 * {@link OutboundPubSubMessage}s from
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
     * This callback is executed for every MQTT PUBLISH that arrives at your HiveMQ cluster matching the
     * {@code <mqtt-to-pubsub-transformer>} tag configured in the {@code <mqtt-pubsub-filters>}. It allows the
     * publication of any number of {@link OutboundPubSubMessage}s via the
     * {@link MqttToPubSubOutput} object.
     *
     * @param mqttToPubSubInput  the {@link MqttToPubSubInput} contains the triggering
     *                           {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket} information.
     * @param mqttToPubSubOutput pass the list of new
     *                           {@link OutboundPubSubMessage}s to the
     *                           {@link MqttToPubSubOutput#setOutboundPubSubMessages(List)} method.
     * @since 4.9.0
     */
    void transformMqttToPubSub(
            @NotNull MqttToPubSubInput mqttToPubSubInput, @NotNull MqttToPubSubOutput mqttToPubSubOutput);
}
