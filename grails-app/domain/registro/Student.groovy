package registro

class Student {
    String nombre
    String correo
    String matricula
    Integer semestre
    String nivel
    String curso

    static constraints = {
        nombre blank: false
        correo email: true, blank: false, matches: "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.([a-zA-Z]{2,4})+"
        matricula blank: false, unique: true, matches: "[0-9]{9}"
        semestre range: 1..10, blank: false
        nivel blank: false
        curso blank: false
        // a1RPE0$jUEKW
    }
}
