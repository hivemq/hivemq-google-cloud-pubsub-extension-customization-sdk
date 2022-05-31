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

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.pubsub.api.builders.PubSubMessageBuilder;
import com.hivemq.extensions.pubsub.api.model.PubSubMessage;

import java.util.List;

/**
 * The output parameter of the {@link MqttToPubSubTransformer}. It allows access to the {@link PubSubMessageBuilder}.
 * <p>
 * After the {@link MqttToPubSubTransformer#transformMqttToPubSub(MqttToPubSubInput, MqttToPubSubOutput)} method returns
 * the {@link PubSubMessage}s given to this output will be published to PubSub by the HiveMQ Enterprise Extension for
 * PubSub.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@DoNotImplement
public interface MqttToPubSubOutput {

    /**
     * Create a new {@link PubSubMessageBuilder}. One {@link PubSubMessageBuilder} can be used to build multiple PubSub
     * messages.
     *
     * @return an empty instance of the {@link PubSubMessageBuilder}.
     * @since 4.9.0
     */
    @NotNull PubSubMessageBuilder newPubSubMessageBuilder();

    /**
     * Sets the {@link PubSubMessage}s, that will be pushed to PubSub after the
     * {@link MqttToPubSubTransformer#transformMqttToPubSub(MqttToPubSubInput, MqttToPubSubOutput)} call returns.
     *  The HiveMQ Enterprise Extension for PubSub will publish the messages in the order provided by the
     *  {@code pubSubMessages} argument when PubSub ordering is enabled.
     * <p>
     * If desired, the same message can occupy multiple places in the {@code pubSubMessages} list. When no message shall
     * be pushed to PubSub for a {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}, call this method
     * with an empty list.
     * <p>
     * Use the {@link PubSubMessageBuilder} to create new messages as desired.
     * <p>
     * Each additional call of this method will overwrite the previous one.
     *
     * @param pubSubMessages a list of to be published {@link PubSubMessage}s.
     * @throws NullPointerException     if {@code pubSubMessages} or any element of it is null.
     * @throws IllegalArgumentException if any element in {@code pubSubMessages} was not created via a
     *                                  {@link PubSubMessageBuilder}.
     * @since 4.9.0
     */
    void setPubSubMessages(@NotNull List<@NotNull PubSubMessage> pubSubMessages);
}
