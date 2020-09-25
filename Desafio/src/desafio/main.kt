package desafio

fun main() {
    var escola =  DigitalHouseManager()
    escola.registrarProfessorAdjunto("prof a1", "", 1,0)
    escola.registrarProfessorAdjunto("prof a2", "", 2,0)

    escola.registrarProfessorTitular("prof t1", "", 3,"esp1")
    escola.registrarProfessorTitular("prof t2", "", 4,"esp2")

    escola.registrarCurso("Full Stack",20001,3)
    escola.registrarCurso("Android",20002,2)

}