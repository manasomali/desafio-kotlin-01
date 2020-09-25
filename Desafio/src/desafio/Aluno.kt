package desafio

data class Aluno(var nome: String, var sobrenome: String, var codigo: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Aluno) return false
        if (codigo != other.codigo) return false

        return true
    }
}