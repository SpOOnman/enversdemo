package pl.refaktor.enversdemo

import grails.plugins.springsecurity.SpringSecurityService

class SpringSecurityServiceHolder {

    static SpringSecurityService springSecurityService

    SpringSecurityServiceHolder(SpringSecurityService springSecurityService) {
        this.springSecurityService = springSecurityService
    }
}
