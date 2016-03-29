package ni.sb

import org.grails.databinding.BindUsing

class Detail {
    @BindUsing({obj, source ->
        source["name"]?.capitalize()
    })
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
    }

    static belongsTo = Brand
    static hasMany = [brands: Brand]

    String toString() { name }
}
