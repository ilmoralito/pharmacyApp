package ni.sb

import org.grails.databinding.BindUsing

class CompanyContact {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String telephoneNumber
    String personalTelephoneNumber
    String email

    static constraints = {
        fullName blank: false
        telephoneNumber blank: false, unique: true
        personalTelephoneNumber blank: false, unique: true
        email blank: false, email: true, unique: true
    }

    static belongsTo = [company: Company]
}
