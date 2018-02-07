/*
 *  Copyright 2018 Curity AB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.curity.identityserver.plugin.events.listeners.descriptor;

import io.curity.identityserver.plugin.events.listeners.AccessTokenIssuedListener;
import io.curity.identityserver.plugin.events.listeners.config.EventListenerConfiguration;
import se.curity.identityserver.sdk.event.EventListener;
import se.curity.identityserver.sdk.event.EventListenerCollection;
import se.curity.identityserver.sdk.plugin.descriptor.EventListenerPluginDescriptor;

import java.util.Collections;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class ApigeeEdgeTokenPublisherPluginDescriptor implements EventListenerPluginDescriptor<EventListenerConfiguration>
{
    @Override
    public Class<? extends EventListenerCollection> getEventListenerCollection()
    {
        return ApigeeEdgeTokenPublisherListenerCollection.class;
    }

    @Override
    public String getPluginImplementationType()
    {
        return "apigee-edge-token-publisher";
    }

    @Override
    public Class<? extends EventListenerConfiguration> getConfigurationType()
    {
        return EventListenerConfiguration.class;
    }

    public static class ApigeeEdgeTokenPublisherListenerCollection implements EventListenerCollection
    {
        private final Set<EventListener<?>> _listeners;

        public ApigeeEdgeTokenPublisherListenerCollection(EventListenerConfiguration configuration)
        {
            _listeners = Collections.singleton(new AccessTokenIssuedListener(configuration));
        }

        @Override
        public Set<? extends EventListener<?>> getListeners()
        {
            return unmodifiableSet(_listeners);
        }
    }

}
