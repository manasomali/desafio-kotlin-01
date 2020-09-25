package desafio

class ProfessorAdjunto(nome: String,
                       sobrenome: String,
                       tempoDeCasa: Int,
                       codigo: Int,
                       var horasMonitoria:Int) : Professor(nome, sobrenome, tempoDeCasa, codigo) {
}