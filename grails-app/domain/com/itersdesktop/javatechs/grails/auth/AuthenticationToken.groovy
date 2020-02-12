package com.itersdesktop.javatechs.grails.auth

import grails.persistence.Entity

@Entity
class AuthenticationToken {
    String tokenValue
    String username

    static mapping = {
        version false
    }
}
