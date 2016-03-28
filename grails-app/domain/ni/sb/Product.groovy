package ni.sb

import org.grails.databinding.BindUsing

class Product implements Serializable {
    @BindUsing({ obj, source ->
        source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
    })
    String name
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: "provider"
    }

    static mapping = {
        tablePerHierarchy false
        sort dateCreated: "desc"
    }

    static belongsTo = [provider: Provider]

    String toString() { name }
}
