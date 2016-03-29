package ni.sb

import org.grails.databinding.BindUsing

class Brand implements Serializable {
    @BindUsing({obj, source ->
        source["name"]?.capitalize()
    })
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        details nullable: false, minSize: 1
    }

    static mapping = {
        sort "name"
    }

    List<Detail> details

    static belongsTo = BrandProduct
    static hasMany = [brandProducts: BrandProduct, details: Detail]

    String toString() { name }
}
