package com.lucasaquiles

import com.lucasaquiles.auth.*

class Party {

	String title
	String description

	User author

	Date initAt

	static hasMany = [partyItems:PartyItem, invites:Participant, accepted:Participant]

    static constraints = {

    	description nullable:true, blank:true

    }
}
