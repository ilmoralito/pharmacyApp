package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Measure implements Serializable {
    @BindUsing({ obj, source ->
        source["name"]?.capitalize()
    })
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        sort name: "asc"
    }

    static belongsTo = Presentation
    static hasMany = [presentations: Presentation]
}
