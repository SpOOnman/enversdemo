import pl.refaktor.enversdemo.SpringSecurityServiceHolder

// Place your Spring DSL code here
beans = {
    springSecurityServiceHolder(SpringSecurityServiceHolder, ref('springSecurityService'))
}
