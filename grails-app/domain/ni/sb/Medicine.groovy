package ni.sb

import org.grails.databinding.BindUsing

class Medicine extends Product {
    @BindUsing({obj, source ->
        source["code"]?.toUpperCase() 
    })
    String code

    @BindUsing({obj, source ->
        source["genericName"]?.capitalize()
    })
    String genericName

    static constraints = {
        code blank: false
        genericName nullable: true
        presentations minSize: 1
    }

    static mapping = {
        version false
    }

    static hasMany = [presentations: Presentation]

    String toString() { name }
}
