package ni.sb

import grails.util.Holders

class Presentation implements Serializable {
    String name
    List measures

    static constraints = {
        name blank: false, maxSize: 255, inList: Holders.config.ni.sb.presentationsAndMeasures.keySet() as List
    }

    static belongsTo = [medicine: Medicine]
    static hasMany = [measures: String]

    String toString() { name }
}
