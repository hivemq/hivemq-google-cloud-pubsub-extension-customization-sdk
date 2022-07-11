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

package com.hivemq.extensions.gcp.pubsub.api.transformers.pubsub2mqtt;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.services.builder.PublishBuilder;
import com.hivemq.extension.sdk.api.services.publish.Publish;
import com.hivemq.extensions.gcp.pubsub.api.model.InboundPubSubMessage;

import java.util.List;

/**
 * The output parameter of the {@link PubSubToMqttTransformer}. It allows access to the {@link PublishBuilder}.
 * <p>
 * After the {@link PubSubToMqttTransformer#transformPubSubToMqtt(PubSubToMqttInput, PubSubToMqttOutput)} method returns
 * the {@link Publish}es given to this output will be published by HiveMQ.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@DoNotImplement
public interface PubSubToMqttOutput {

    /**
     * @return a new {@link PublishBuilder}.
     * @since 4.9.0
     */
    @NotNull PublishBuilder newPublishBuilder();

    /**
     * Sets the {@link Publish}es, that will be published by HiveMQ after the
     * {@link PubSubToMqttTransformer#transformPubSubToMqtt(PubSubToMqttInput, PubSubToMqttOutput)} call returns. The
     * HiveMQ Enterprise Extension for PubSub will publish the publishes in the order provided by the {@code publishes}
     * argument.
     * <p>
     * If desired, the same publish can occupy multiple places in the {@code publishes} list. When no publish shall be
     * published by HiveMQ for a {@link InboundPubSubMessage}, call this method
     * with an empty list.
     * <p>
     * Use the {@link #newPublishBuilder() PublishBuilder} to create new publishes as desired.
     * <p>
     * Each additional call of this method will overwrite the previous one.
     *
     * @param publishes a list of to be published {@link Publish}es.
     * @throws NullPointerException     if {@code publishes} or any element of it is null.
     * @throws IllegalArgumentException if any element in {@code publishes} was not created via a
     *                                  {@link PublishBuilder}.
     * @since 4.9.0
     */
    void setPublishes(@NotNull List<@NotNull Publish> publishes);
}
