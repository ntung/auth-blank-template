package tutorial

import com.lucasaquiles.auth.*

class BootStrap {

    def init = { servletContext ->
			  
			  def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
			  def adminUser = new User(username: 'user', password: 'user').save()    	
    
			  UserRole.create adminUser, roleAdmin
    
			  UserRole.withSession {
				    it.flush()
				    it.clear()
			  }


			  println "criando usuários"
			  def count = User.count()

			  println "usuario ${count}"

    }
    def destroy = {
    }
}
