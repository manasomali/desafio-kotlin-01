package desafio

class ProfessorTitular(nome: String,
                       sobrenome: String,
                       tempoDeCasa: Int,
                       codigo: Int,
                       var especialidade:String) : Professor(nome, sobrenome, tempoDeCasa, codigo) {
}