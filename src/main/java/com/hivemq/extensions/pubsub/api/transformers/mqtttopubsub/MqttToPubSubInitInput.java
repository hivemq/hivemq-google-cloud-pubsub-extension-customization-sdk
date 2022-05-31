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
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.pubsub.api.model.PubSubConnection;
import com.hivemq.extensions.pubsub.api.transformers.TransformerInitInput;

/**
 * Provides context for the initialization of a {@link MqttToPubSubTransformer}.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface MqttToPubSubInitInput extends TransformerInitInput {

    /**
     * @return the {@link PubSubConnection} this transformer is associated with.
     * @since 4.9.0
     */
    @NotNull PubSubConnection getPubSubConnection();
}
