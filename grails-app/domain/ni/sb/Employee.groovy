package ni.sb

import org.grails.databinding.BindUsing

class Employee {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String inss

    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
        inss blank: false, unique: true // TODO: implement inss number validation
    }

    static mapping = {
        sort "fullName"
    }

    static belongsTo = [company: Company]

    String toString() { fullName }
}