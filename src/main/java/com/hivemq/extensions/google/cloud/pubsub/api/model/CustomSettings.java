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

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This interface provides information about {@code <custom-settings>} as it is configured in the
 * {@code pubsub-extension.xml}.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface CustomSettings {

    /**
     * @param name The name of the custom setting to get.
     * @return An {@link Optional} that contains the first custom setting with the specified name.
     * @since 4.9.0
     */
    @NotNull Optional<String> getFirst(@NotNull String name);

    /**
     * @param name The name of the custom settings to get.
     * @return The values custom setting with the specified name.
     * @since 4.9.0
     */
    @Immutable @NotNull List<@NotNull String> getAllForName(@NotNull String name);

    /**
     * @return A list of all {@link CustomSetting}s.
     * @since 4.9.0
     */
    @Immutable @NotNull List<@NotNull CustomSetting> asList();

    /**
     * @return A map with the first value for every custom setting name.
     * @since 4.9.0
     */
    @Immutable @NotNull Map<String, String> asSingleValueMap();

    /**
     * @return <code>true</code> if no custom settings are present, else <code>false</code>.
     * @since 4.9.0
     */
    boolean isEmpty();
}
