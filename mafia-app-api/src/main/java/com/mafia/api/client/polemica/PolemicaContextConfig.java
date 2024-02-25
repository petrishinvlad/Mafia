package com.mafia.api.client.polemica;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class PolemicaContextConfig {
    public static class PolemicaContextConfigInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
            template.header("Content-Type", "application/json");
        }
    }
}
