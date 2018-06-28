package com.lucasaquiles

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import com.lucasaquiles.auth.*

class PartySpec extends Specification implements DomainUnitTest<Party> {

    def setup() {
   		def author = new User(username:"username", passoword:"senha")
   		author.save flush:true


   		new User(username:"u1", passoword:"senha").save flush:true
   		new User(username:"u2", passoword:"senha").save flush:true
   		new User(username:"u3", passoword:"senha").save flush:true
   		new User(username:"u4", passoword:"senha").save flush:true

    	new Item(description:"beer").save flush:true
    	new Item(description:"coffee").save flush:true
    	new Item(description:"juice").save flush:true
    }


    def cleanup() {
    }

    void "create party without author"(){

    	given:
    		def party = new Party()
    	expect:
    		party.validate() == false
    }

    void "party creation"() {

        given:
        	setup()

        	def user = User.findByUsername("username")
        	def party = new Party(author:user)

        	party.addToPartyItems(new PartyItem(item:Item.findByDescription("beer"), active:true))
        	party.addToPartyItems(new PartyItem(item:Item.findByDescription("coffee"), active:true))
        	party.addToPartyItems(new PartyItem(item:Item.findByDescription("juice"), active:true))

        expect:
    		party.partyItems.size() == 3
    }
}
