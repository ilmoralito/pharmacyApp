package ni.sb

import org.grails.databinding.BindUsing

class Measure {
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
        version false
        sort "name"
    }

    static belongsTo = Presentation
    static hasMany = [presentations: Presentation]

    String toString() { name }
}
