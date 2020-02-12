package spring

import com.itersdesktop.javatechs.grails.CorsFilter
import com.itersdesktop.javatechs.grails.auth.DefaultJsonPayloadCredentialsExtractor
import com.itersdesktop.javatechs.grails.auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    corsFilter(CorsFilter)
    credentialsExtractor(DefaultJsonPayloadCredentialsExtractor)
}
