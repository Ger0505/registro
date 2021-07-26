package registro

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StudentController {

    StudentService studentService
    def mailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond studentService.list(params), model:[studentCount: studentService.count()]
    }

    def show(Long id) {
        respond studentService.get(id)
    }

    def create() {
        respond new Student(params)
    }

    def save(Student student) {
        if (student == null) {
            notFound()
            return
        }

        if(studentService.findByMatricula(student.matricula) != null){
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'El alumno con la matricula {0}, ya está registrado', args: [message(code: 'student.label', default: student.matricula), student.matricula])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
            return
        }

        if(student.semestre != 1){
            request.withFormat {
                form multipartForm {
                    flash.message = 'Solo puede ser registrados alumnos de 1er. semestre'
                    flash.args = []
                    flash.default = 'Solo 1er. Semestre'
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
            return
        }

        try {
            studentService.save(student)
            // mailService.sendMail {
            //     to student.correo
            //     from "gerzonex360@gmail.com"
            //     subject "Prueba pruebil"
            //     html "El alumno con la matricula " + student.matricula + ", fue registrado exitosamente"
            // }
            // 1*80DqjBcf#z
        } catch (ValidationException e) {
            respond student.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentService.get(id)
    }

    def update(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*'{ respond student, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
