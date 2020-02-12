package com.itersdesktop.javatechs.grails.auth

import grails.plugin.springsecurity.rest.credentials.AbstractJsonPayloadCredentialsExtractor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import javax.servlet.http.HttpServletRequest

class DefaultJsonPayloadCredentialsExtractor extends AbstractJsonPayloadCredentialsExtractor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    String usernamePropertyName
    String passwordPropertyName

    UsernamePasswordAuthenticationToken extractCredentials(HttpServletRequest httpServletRequest) {
        def jsonBody = getJsonBody(httpServletRequest)

        if (jsonBody) {
            String username = jsonBody."${usernamePropertyName}"
            String password = jsonBody."${passwordPropertyName}"

            logger.debug "Extracted credentials from JSON payload. Username: ${username}, password: ${password?.size() ?'[PROTECTED]':'[MISSING]'}"

            new UsernamePasswordAuthenticationToken(username, password)
        } else {
            logger.debug "No JSON body sent in the request"
            return null
        }
    }

}
