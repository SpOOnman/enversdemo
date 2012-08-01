import pl.refaktor.enversdemo.SpringSecurityServiceHolder

// Place your Spring DSL code here
beans = {
    springSecurityServiceHolder(SpringSecurityServiceHolder, ref('springSecurityService'))

    System.setProperty('org.hibernate.envers.audit_table_prefix', 'AUD_')
    System.setProperty('org.hibernate.envers.audit_table_suffix', '')
}
