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

package com.hivemq.extensions.google.cloud.pubsub.api.model;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

/**
 * Represents an inbound Google Cloud Pub/Sub message that was read from PubSub.
 * <p>
 * The internal state of this interface is immutable.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface InboundPubSubMessage extends PubSubMessage {

    /**
     * @return The subscription name where this message came from.
     * @since 4.9.0
     */
    @NotNull String getSubscriptionName();

    /**
     * @return The message id of this message.
     * @since 4.9.0
     */
    @NotNull String getMessageId();

    /**
     * @return The publishing time of this message. This timestamp references to the time the PubSub server originally
     *         received the message.
     * @since 4.9.0
     */
    @NotNull Timestamp getPublishTime();
}
