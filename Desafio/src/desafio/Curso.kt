package desafio

class Curso(var nome:String, var codigo:Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Aluno) return false
        if (codigo != other.codigo) return false

        return true
    }
}