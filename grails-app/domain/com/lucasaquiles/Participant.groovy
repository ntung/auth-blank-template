package com.lucasaquiles
import com.lucasaquiles.auth.*

class Participant {
	
	User user
	static hasMany = [contribuitions:ParticipantItemParty]

    static constraints = {
    }
}
