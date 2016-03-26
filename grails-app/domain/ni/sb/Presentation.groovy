package ni.sb

class Presentation implements Serializable {
    String name

    static constraints = {
        name blank: false, maxSize: 255
    }

    static belongsTo = Medicine
    static hasMany = [medicines: Medicine, measures: Measure]

    String toString() { name }
}
