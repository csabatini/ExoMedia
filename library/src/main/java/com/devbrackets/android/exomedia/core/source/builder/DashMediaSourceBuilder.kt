/*
 * Copyright (C) 2017 - 2018 ExoMedia Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devbrackets.android.exomedia.core.source.builder

import android.content.Context
import android.net.Uri
import android.os.Handler
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.upstream.TransferListener

class DashMediaSourceBuilder : MediaSourceBuilder() {
    override fun build(context: Context, uri: Uri, userAgent: String, handler: Handler, transferListener: TransferListener?): MediaSource {
        val dataSourceFactory = buildDataSourceFactory(context, userAgent, null)
        val meteredDataSourceFactory = buildDataSourceFactory(context, userAgent, transferListener)

        return DashMediaSource.Factory(DefaultDashChunkSource.Factory(meteredDataSourceFactory), dataSourceFactory)
                .createMediaSource(uri)
    }
}
