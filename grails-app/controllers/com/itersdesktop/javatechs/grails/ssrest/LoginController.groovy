package com.itersdesktop.javatechs.grails.ssrest

import com.itersdesktop.javatechs.grails.auth.AuthenticationToken
import grails.config.Config
import grails.plugin.springsecurity.rest.token.storage.*

class LoginController
        extends grails.plugin.springsecurity.LoginController
        implements grails.core.support.GrailsConfigurationAware {

    Map currentUser

    def tokenStorageService
    def jwtService

    def auth() {
        def conf = getConf()

        if (springSecurityService.isLoggedIn()) {
            println "export user details"

            def user = springSecurityService.authentication.principal as org.springframework.security.core.userdetails.UserDetails
            def generator = new grails.plugin.springsecurity.rest.token.generation.SecureRandomTokenGenerator()
            grails.plugin.springsecurity.rest.token.AccessToken accessToken = generator.generateAccessToken(user)
            println generator
            println user
            println "access Token: ${accessToken.accessToken} -- ${accessToken.refreshToken} -- ${accessToken.authenticated}"
            GormTokenStorageService gormTokenStorageService = new GormTokenStorageService()
            tokenStorageService.storeToken(accessToken.accessToken, user)
            gormTokenStorageService.storeToken(accessToken.accessToken, user)
            response.setHeader('X-Auth-Token', accessToken.accessToken)
            response.setHeader("Author", "Tung Nguyen")
            assert AuthenticationToken.count() > 0
            render "success"//redirect uri: conf.successHandler.defaultTargetUrl
            return false
        }

        String postUrl = request.contextPath + conf.apf.filterProcessesUrl
        render view: 'auth', model: [postUrl: postUrl,
                                     rememberMeParameter: conf.rememberMe.parameter,
                                     usernameParameter: conf.apf.usernameParameter,
                                     passwordParameter: conf.apf.passwordParameter,
                                     gspLayout: conf.gsp.layoutAuth]
    }
    @Override
    void setConfiguration(Config co) {

    }
}
