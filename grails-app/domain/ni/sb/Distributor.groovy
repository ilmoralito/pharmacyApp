package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Distributor implements Serializable {
    @BindUsing({
        obj, source -> source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
    })
    String name
    String telephoneNumber

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank:false, unique:true
        telephoneNumber blank:false, unique:true
    }

    static mapping = {
        version false
    }
}
