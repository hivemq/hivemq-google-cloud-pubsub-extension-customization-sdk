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

package com.hivemq.extensions.pubsub.api.builders;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.pubsub.api.model.OutboundPubSubMessage;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * The {@code PubSubMessageBuilder} enables the creation of {@link OutboundPubSubMessage}s via its fluent API.
 * <p>
 * Make sure that {@code topicName} together with at least {@code data} or one {@code attribute} is set before calling
 * {@link OutboundPubSubMessageBuilder#build()}.
 * <p>
 * The internal state of this interface can only be changed via its methods. All arguments, that have mutable data
 * types, are deep copied before the setting method returns.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@DoNotImplement
public interface OutboundPubSubMessageBuilder {

    /**
     * Set the topicName of the PubSub message.
     * <p>
     * This is required to successfully build a {@link OutboundPubSubMessage}.
     *
     * @param topicName the name of the topicName.
     * @return this builder
     * @throws IllegalArgumentException if topicName is not a valid name for a PubSub topicName
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder topicName(@NotNull String topicName);

    /**
     * Set the data of the PubSub message.
     *
     * @param data the value of the data.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(@NotNull ByteBuffer data);

    /**
     * Set the data of the PubSub message.
     *
     * @param data the value of the data.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(byte @NotNull [] data);

    /**
     * Set the data of the PubSub message.
     *
     * @param data the value of the data. {@link java.nio.charset.StandardCharsets#UTF_8} is used for encoding.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(@NotNull String data);

    /**
     * Set the data of the PubSub message.
     *
     * @param data    the value of the data.
     * @param charset the {@link Charset} used for encoding.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(@NotNull String data, @NotNull Charset charset);

    /**
     * Add an attribute to the PubSub message.
     *
     * @param key   the key of the attribute.
     * @param value the value of the attribute.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder attribute(@NotNull String key, @NotNull String value);

    /**
     * Add attributes to the PubSub message.
     *
     * @param attributes the attributes to add to the PubSub message.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder attributes(@NotNull Map<String, String> attributes);

    /**
     * Set the orderingKey of the PubSub message.
     *
     * @param orderingKey the orderingKey.
     * @return this builder
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder orderingKey(@NotNull String orderingKey);

    /**
     * Create a new {@link OutboundPubSubMessage} from the current state of this builder. The builder can be reused
     * afterwards.
     *
     * @return a new {@link OutboundPubSubMessage} containing a snapshot of the current state of this builder.
     * @throws IllegalStateException if the {@code topicName} was not set.
     * @throws IllegalStateException if data was not set and attributes is empty.
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessage build();
}
