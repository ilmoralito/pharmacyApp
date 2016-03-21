package ni.sb

import org.grails.databinding.BindUsing
import grails.util.Holders

class Product implements Serializable {
    @BindUsing({ obj, source ->
        source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
    })
    String name
    String location
    Boolean status = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        location blank: false, inList: Holders.config.ni.sb.locations as List
    }

    static mapping = {
        version false
        sort dateCreated: "desc"
    }

    static belongsTo = [provider: Provider]

    String toString() { name }
}
