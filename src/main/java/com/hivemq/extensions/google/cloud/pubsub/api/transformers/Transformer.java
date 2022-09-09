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

/**
 * This is the base interface for all HiveMQ Enterprise Extension for Google Cloud Pub/Sub transformer.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@DoNotImplement
public interface Transformer<I extends TransformerInitInput> {

    /**
     * Use the init method to initialize the transformer.
     *
     * @param transformerInitInput See the specific transformerInitInput e.g. {@link MqttToPubSubInitInput}.
     * @since 4.9.0
     */
    default void init(final @NotNull I transformerInitInput) {
    }
}
