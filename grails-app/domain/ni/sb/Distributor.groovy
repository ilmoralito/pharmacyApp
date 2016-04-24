package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Distributor implements Serializable {
    @BindUsing({
        obj, source -> source["name"]?.capitalize()
    })
    String name
    String telephoneNumber
    Integer daysToPay
    Boolean enabled = true
    Contact contact

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        telephoneNumber blank: false, unique: true
        daysToPay min: 1
        contact nullable: false
        providers nullable: false, minSize: 1
    }

    List<Provider> providers
    static hasMany = [providers: Provider]

    static mapping = {
        employees cascade: "all-delete-orphan"
    }
}
