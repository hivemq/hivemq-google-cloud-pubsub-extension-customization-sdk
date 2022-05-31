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

import java.util.Map;
import java.util.Optional;

/**
 * This interface contains all {@link PubSubAttributes}s belonging to a single {@link PubSubMessage}.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface PubSubAttributes {

    /**
     * @return all attributes as a {@link Map}.
     * @since 4.9.0
     */
    @Immutable @NotNull Map<String, String> asMap();

    /**
     * @param key the name of the attribute to get.
     * @return an {@link Optional} that contains the attribute value with the specified name.
     * @since 4.9.0
     */
    @NotNull Optional<String> get(@NotNull String key);
}
