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

package com.hivemq.extensions.google.cloud.pubsub.api.builders;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.google.cloud.pubsub.api.model.OutboundPubSubMessage;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * The {@code OutboundPubSubMessageBuilder} enables the creation of {@link OutboundPubSubMessage}s via its fluent API.
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
     * Set the {@code topicName} of the Google Cloud Pub/Sub message. This is required to successfully build a
     * {@link OutboundPubSubMessage}.
     * <p>
     * Must conform to the following guidelines:
     * <ul>
     *     <li>Not begin with the string {@code goog}</li>
     *     <li>Start with a letter</li>
     *     <li>Contain between 3 and 255 characters</li>
     *     <li>Contain only the following characters<ul>
     *          <li>Letters [A-Za-z]</li>
     *          <li>numbers [0-9]</li>
     *          <li>dashes -</li>
     *          <li>underscores _</li>
     *          <li>periods .</li>
     *          <li>tildes ~</li>
     *          <li>plus signs +</li>
     *          <li>percent signs %</li>
     *      </ul></li>
     * </ul>
     * <p>
     *
     * @param topicName The name of the topic.
     * @return This builder.
     * @throws NullPointerException     If {@code topicName} is null.
     * @throws IllegalArgumentException If {@code topicName} is not conform with the guidelines listed above.
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder topicName(@NotNull String topicName);

    /**
     * Set the data of the Google Cloud Pub/Sub message.
     *
     * @param data The value of the data.
     * @return This builder.
     * @throws NullPointerException     If {@code data} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 10,000,000 bytes (10MB).
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(@NotNull ByteBuffer data);

    /**
     * Set the data of the Google Cloud Pub/Sub message.
     *
     * @param data The value of the data.
     * @return This builder.
     * @throws NullPointerException     If {@code data} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 10,000,000 bytes (10MB).
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(byte @NotNull [] data);

    /**
     * Set the data of the Google Cloud Pub/Sub message.
     *
     * @param data The value of the data {@link java.nio.charset.StandardCharsets#UTF_8} is used for encoding.
     * @return This builder.
     * @throws NullPointerException     If {@code data} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 10,000,000 bytes (10MB).
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(@NotNull String data);

    /**
     * Set the data of the Google Cloud Pub/Sub message.
     *
     * @param data    The value of the data.
     * @param charset The {@link Charset} used for encoding.
     * @return This builder.
     * @throws NullPointerException     If {@code data} or {@code charset} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 10,000,000 bytes (10MB).
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder data(@NotNull String data, @NotNull Charset charset);

    /**
     * Add an attribute to the Google Cloud Pub/Sub message. If the builder previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   The key of the attribute.
     * @param value The value of the attribute.
     * @return This builder.
     * @throws NullPointerException     If {@code key} or {@code value} is null.
     * @throws IllegalArgumentException If {@code key} exceeds the max size of 256 bytes (UTF-8).
     * @throws IllegalArgumentException If {@code value} exceeds the max size of 1,024 bytes (UTF-8).
     * @throws IllegalArgumentException If the total number of attributes exceeds the max size of 100.
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder attribute(@NotNull String key, @NotNull String value);

    /**
     * Add all {@code attributes} to the Google Cloud Pub/Sub message. If the builder previously contained a mapping for
     * a key, the old value is replaced by the specified value.
     *
     * @param attributes The attributes to add to the Google Cloud Pub/Sub message.
     * @return This builder.
     * @throws NullPointerException     If {@code attributes} is null.
     * @throws NullPointerException     If any {@code key} or {@code value} of {@code attributes} is null.
     * @throws IllegalArgumentException If any {@code key} of {@code attributes} exceeds the max size of 256 bytes
     *                                  (UTF-8).
     * @throws IllegalArgumentException If any {@code value} of {@code attributes} exceeds the max size of 1,024 bytes
     *                                  (UTF-8).
     * @throws IllegalArgumentException If the total number of attributes exceeds the max size of 100.
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder attributes(@NotNull Map<String, String> attributes);

    /**
     * Set the orderingKey of the Google Cloud Pub/Sub message.
     *
     * @param orderingKey The orderingKey.
     * @return This builder.
     * @throws NullPointerException     If {@code orderingKey} is null.
     * @throws IllegalArgumentException If {@code orderingKey} exceeds the max size of 1,024 bytes (UTF-8).
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessageBuilder orderingKey(@NotNull String orderingKey);

    /**
     * Create a new {@link OutboundPubSubMessage} from the current state of this builder. The builder can be reused
     * afterwards.
     *
     * @return A new {@link OutboundPubSubMessage} containing a snapshot of the current state of this builder.
     * @throws IllegalStateException If {@code topicName} was not set.
     * @throws IllegalStateException If {@code data} was not set and {@code attributes} is empty.
     * @throws IllegalStateException If the consequential request size exceeds the max size of 10,000,000 bytes (10MB).
     * @since 4.9.0
     */
    @NotNull OutboundPubSubMessage build();
}
