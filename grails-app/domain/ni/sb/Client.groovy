package ni.sb

import org.grails.databinding.BindUsing

class Client implements Serializable {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String address
    @BindUsing({ obj, source ->
        source["identificationCard"]?.toUpperCase()
    })
    String identificationCard
    Boolean status = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
        address blank: false
        identificationCard blank: false, unique: true, maxSize: 14, minSize: 14
        telephones nullable: true
    }

    static mapping = {
        version false
        sort dateCreated: "desc"
        telephones cascade: "all-delete-orphan"
    }

    List<Telephone> telephones
    static hasMany = [telephones: Telephone]

    String toString() { fullName }
}
