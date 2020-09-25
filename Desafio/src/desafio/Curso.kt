package desafio

data class Curso(var nome: String,
                var codigo: Int,
                var professorTitular: ProfessorTitular?,
                var professorAdjunto: ProfessorAdjunto?,
                var quantidadeMaxAlunos: Int,
                var alunosMatriculados: MutableList<Aluno>?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Aluno) return false
        if (codigo != other.codigo) return false

        return true
    }
    fun adicionarUmAluno(umAluno: Aluno): Boolean {
        if(alunosMatriculados?.equals(umAluno)!!) {
            println("O código ${umAluno.codigo} já está sendo usado.")
            return false
        } else {
            alunosMatriculados?.add(umAluno)
            println("Aluno ${umAluno.nome} matriculado com o código ${umAluno.codigo}.")
            return true
        }
    }
    fun excluirAluno(umAluno: Aluno): Boolean {
        if(alunosMatriculados?.equals(umAluno)!!) {
            println("Aluno ${umAluno.nome} de código ${umAluno.codigo} excluido com sucesso.")
            alunosMatriculados!!.remove(umAluno)
            return true
        } else {
            println("Aluno ${umAluno.nome} de código ${umAluno.codigo} não existe.")
            return false
        }
    }
}