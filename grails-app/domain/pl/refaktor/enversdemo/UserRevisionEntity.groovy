package pl.refaktor.enversdemo

import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp

@RevisionEntity(SpringSecurityRevisionListener.class)
class UserRevisionEntity {

    @RevisionNumber
    Long id

    @RevisionTimestamp
    Long timestamp

    User currentUser

    static constraints = {
        currentUser(nullable: true)
    }

    static transients = ['revisionDate']

    public Date getRevisionDate() {
        return new Date(timestamp);
    }
}
