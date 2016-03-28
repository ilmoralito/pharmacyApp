package ni.sb

import org.grails.databinding.BindUsing

class Presentation implements Serializable {
    @BindUsing({ obj, source ->
        source["name"]?.capitalize()
    })
    String name

    static constraints = {
        name blank: false, unique: true, maxSize: 255
        measures nullable: false, minSize: 1
    }

    static mapping = {
        sort "name"
    }

    static belongsTo = Medicine

    List<Measure> measures
    static hasMany = [medicines: Medicine, measures: Measure]

    String toString() { name }
}
