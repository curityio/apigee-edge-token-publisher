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

package io.curity.identityserver.plugin.events.listeners;

import io.curity.identityserver.plugin.events.listeners.config.EventListenerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.curity.identityserver.sdk.data.events.IssuedAccessTokenOAuthEvent;
import se.curity.identityserver.sdk.event.EventListener;
import se.curity.identityserver.sdk.http.HttpRequest;
import se.curity.identityserver.sdk.http.HttpResponse;
import se.curity.identityserver.sdk.service.WebServiceClient;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenIssuedListener implements EventListener<IssuedAccessTokenOAuthEvent>
{
    private static final Logger _logger = LoggerFactory.getLogger(AccessTokenIssuedListener.class);

    private final WebServiceClient _httpClient;

    public AccessTokenIssuedListener(EventListenerConfiguration configuration)
    {
        _httpClient = configuration.getHttpClient();
    }

    @Override
    public Class<IssuedAccessTokenOAuthEvent> getEventType()
    {
        return IssuedAccessTokenOAuthEvent.class;
    }

    @Override
    public void handle(IssuedAccessTokenOAuthEvent event)
    {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("client_id", event.getClientId());
        parameters.put("token", event.getDelegation().getId());
        parameters.put("scope", event.getScope());
        parameters.put("grant_type", "client_credentials");

        HttpResponse response = _httpClient.request()
                .contentType("application/x-www-form-urlencoded")
                .body(HttpRequest.createFormUrlEncodedBodyProcessor(parameters))
                .post()
                .response();

        if (response.statusCode() != 200) {
            _logger.warn("Event posted to Apigee but response was not successful: {}",
                    response.body(HttpResponse.asString()));
        }
        else {
            _logger.debug("Successfully sent event to Apigee: {}", event);
        }
    }
}