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

package com.hivemq.extensions.gcp.pubsub.api.model;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;

/**
 * Timestamp that represents a point in time from epoch (UTC 00:00:00 01.01.1970), encoded as a count of seconds and
 * fractions of seconds at nanosecond resolution.
 *
 * @author Florian Limpöck
 * @author Mario Schwede
 * @since 4.9.0
 */
@Immutable
@DoNotImplement
public interface Timestamp {

    /**
     * @return count of seconds from epoch.
     * @since 4.9.0
     */
    long getSeconds();

    /**
     * @return fractions of seconds at nanosecond resolution.
     * @since 4.9.0
     */
    int getNanos();

    /**
     * @return count of milliseconds from epoch. The applied conversion truncates, so lose precision.
     * @since 4.9.0
     */
    long toMillis();
}
