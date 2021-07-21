package registro

import grails.transaction.Transactional

@Transactional
class StudentService {

    def get(id){
        Student.get(id)
    }

    def list(Map args){
        return Student.list(args)
    }

    def count(){
        return Student.count()
    }

    def save(Student student){
        student.save()
    }

    def delete(Serializable id){
        Student.get(id).delete()
    }

    def findByMatricula (String matricula){
        return Student.findByMatricula(matricula)
    }
}
// package registro

// import grails.gorm.services.Service

// @Service(Student)
// class StudentService {

//     Student get(Serializable id)

//     List<Student> list(Map args)

//     Long count()

//     void delete(Serializable id)

//     Student save(Student student)

// }