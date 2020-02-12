package com.itersdesktop.javatechs.grails.ssrest

import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured

class UserController {
    GrailsApplication grailsApplication

    @Secured('ROLE_ADMIN')
    def index() {
        String hostname = grailsApplication.config.grails.serverURL
        String hostIPAddress = InetAddress.getByName(hostname).address.collect { it & 0xFF }.join('.')
        def localHost = java.net.InetAddress.getLocalHost()
        println "You are seeing index page rendered from the host: $hostIPAddress but $localHost"
        [hostIPAddress: hostIPAddress, localHost: localHost]
    }
}
