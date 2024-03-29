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

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.google.cloud.pubsub.api.builders.OutboundPubSubMessageBuilder;
import com.hivemq.extensions.google.cloud.pubsub.api.model.OutboundPubSubMessage;

import java.util.List;

/**
 * The output parameter of the {@link MqttToPubSubTransformer}. It allows access to the
 * {@link OutboundPubSubMessageBuilder}.
 * <p>
 * After the {@link MqttToPubSubTransformer#transformMqttToPubSub(MqttToPubSubInput, MqttToPubSubOutput)} method returns
 * the {@link OutboundPubSubMessage}s given to this output will be published to Google Cloud Pub/Sub by the HiveMQ
 * Enterprise Extension for PubSub.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@DoNotImplement
public interface MqttToPubSubOutput {

    /**
     * Create a new {@link OutboundPubSubMessageBuilder}. One {@link OutboundPubSubMessageBuilder} can be used to build
     * multiple Google Cloud Pub/Sub messages.
     *
     * @return An empty instance of the {@link OutboundPubSubMessageBuilder}.
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder newOutboundPubSubMessageBuilder();

    /**
     * Sets the {@link OutboundPubSubMessage}s, that will be pushed to Google Cloud Pub/Sub after the
     * {@link MqttToPubSubTransformer#transformMqttToPubSub(MqttToPubSubInput, MqttToPubSubOutput)} call returns. The
     * "HiveMQ Enterprise Extension for Google Cloud Pub/Sub" will publish the messages in the order provided by the
     * {@code outboundPubSubMessages} argument when Google Cloud Pub/Sub ordering is enabled.
     * <p>
     * If desired, the same message can occupy multiple places in the {@code outboundPubSubMessages} list. When no
     * message shall be pushed to Google Cloud Pub/Sub for a given
     * {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}, provide an empty list or just don't call this
     * method.
     * <p>
     * Use the {@link OutboundPubSubMessageBuilder} to create new messages as desired.
     * <p>
     * Each additional call of this method will overwrite the previous one.
     *
     * @param outboundPubSubMessages A list of to be published {@link OutboundPubSubMessage}s.
     * @throws NullPointerException     If {@code outboundPubSubMessages} or any element of it is null.
     * @throws IllegalArgumentException If any element in {@code outboundPubSubMessages} was not created via a
     *                                  {@link OutboundPubSubMessageBuilder}.
     * @since 4.9.0
     */
    void setOutboundPubSubMessages(@NotNull List<@NotNull OutboundPubSubMessage> outboundPubSubMessages);
}
