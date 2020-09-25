package desafio

class Curso(var nome:String,
            var codigo:Int,
            var professorTitular: ProfessorTitular,
            var professorAdjunto: ProfessorAdjunto,
            var quantidadeMaxAlunos:Int,
            var alunosMatriculados:MutableList<Aluno>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Aluno) return false
        if (codigo != other.codigo) return false

        return true
    }
}