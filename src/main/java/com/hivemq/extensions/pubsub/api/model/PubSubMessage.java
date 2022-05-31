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

package com.hivemq.extensions.pubsub.api.model;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * Represents a PubSub message, that was either read from or should be written to PubSub.
 * <p>
 * The internal state of this interface is completely immutable. All returned {@link ByteBuffer}s are read only and a
 * deep copy of any {@code byte[]} is made for every method call returning one.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface PubSubMessage {

    /**
     * @return the topic name of this message.
     * @since 4.9.0
     */
    @NotNull String getTopicName();

    /**
     * @return the {@link PubSubAttributes} of this message. These might be empty.
     * @since 4.9.0
     */
    @NotNull PubSubAttributes getAttributes();

    /**
     * @return an {@link Optional} of the data of this message.
     * @since 4.9.0
     */
    @NotNull Optional<@Immutable ByteBuffer> getData();

    /**
     * @return an {@link Optional} of the data of this message.
     * @since 4.9.0
     */
    @NotNull Optional<byte[]> getDataAsByteArray();

    /**
     * @return an {@link Optional} of the messageId of this message.
     * @since 4.9.0
     */
    @NotNull Optional<String> getMessageId();

    /**
     * @return an {@link Optional} of the publishTime of this message.
     * @since 4.9.0
     */
    @NotNull Optional<Timestamp> getPublishTime();

    /**
     * @return an {@link Optional} of the orderingKey of this message.
     * @since 4.9.0
     */
    @NotNull Optional<String> getOrderingKey();
}
