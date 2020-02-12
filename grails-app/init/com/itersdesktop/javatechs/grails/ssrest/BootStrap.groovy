package com.itersdesktop.javatechs.grails.ssrest


import com.itersdesktop.javatechs.grails.auth.Role
import com.itersdesktop.javatechs.grails.auth.User
import com.itersdesktop.javatechs.grails.auth.UserRole

class BootStrap {

    def init = { servletContext ->

        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        def adminUser = new User(username: 'user', password: 'user').save()

        UserRole.create adminUser, roleAdmin

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        def count = User.count()
		println "Number of users: ${count}"

    }
    def destroy = {
    }
}
