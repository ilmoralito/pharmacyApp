package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Client implements Serializable {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String email
    String address
    String telephoneNumber
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
        email nullable: true, email: true, unique: true
        address nullable: true, maxSize: 1000
        telephoneNumber nullable: true
    }

    static mapping = {
        sort dateCreated: "desc"
    }
}
