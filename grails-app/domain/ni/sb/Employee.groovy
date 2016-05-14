package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Employee implements Serializable {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String telephoneNumber
    String identificationCard
    String inss
    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
        telephoneNumber blank: false
        identificationCard blank: false, unique: true, minSize: 16, maxSize: 16
        inss unique: true
    }

    static mapping = {
        sort "fullName"
    }

    static belongsTo = [company: Company]
}
