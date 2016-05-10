package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Employee {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String telephoneNumber
    String inss
    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
        telephoneNumber blank: false, size: 8..8
        inss unique: true
    }

    static mapping = {
        sort "fullName"
    }

    static belongsTo = [company: Company]
}
