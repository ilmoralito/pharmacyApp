package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Contact implements Serializable {
    @BindUsing({
        obj, source -> source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
    })
    String fullName
    String email
    String telephoneNumber

    static constraints = {
        fullName blank: false
        email email: true, blank: false, unique: true // TODO: check if there exist distribuitor email
        telephoneNumber blank: false, unique: true
    }

    static belongsTo = [distribuitor: Distributor]
}
