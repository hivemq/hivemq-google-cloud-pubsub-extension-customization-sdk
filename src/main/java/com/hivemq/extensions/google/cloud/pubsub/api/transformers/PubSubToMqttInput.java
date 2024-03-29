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
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.google.cloud.pubsub.api.model.InboundPubSubMessage;

/**
 * The input parameter of the {@link PubSubToMqttTransformer}. It contains the information of
 * {@link InboundPubSubMessage} the to be transformed.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface PubSubToMqttInput {

    /**
     * @return The {@link InboundPubSubMessage} that triggered this transformer call.
     * @since 4.9.0
     */
    @NotNull InboundPubSubMessage getInboundPubSubMessage();
}
