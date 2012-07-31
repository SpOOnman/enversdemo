package pl.refaktor.enversdemo

import org.hibernate.envers.RevisionListener

class SpringSecurityRevisionListener implements RevisionListener {

    public void newRevision(Object entity) {
        UserRevisionEntity revisionEntity = (UserRevisionEntity) entity
        User user = SpringSecurityServiceHolder.springSecurityService?.currentUser
        revisionEntity.currentUser = user
    }
}