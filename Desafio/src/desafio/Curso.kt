package desafio

data class Curso(var nome: String,
                var codigo: Int,
                var professorTitular: ProfessorTitular?,
                var professorAdjunto: ProfessorAdjunto?,
                var quantidadeMaxAlunos: Int,
                var alunosMatriculados: MutableList<Aluno>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Aluno) return false
        if (codigo != other.codigo) return false

        return true
    }
    fun adicionarUmAluno(umAluno: Aluno): Boolean {
        for (aluno in alunosMatriculados) {
            if (aluno.codigo == umAluno.codigo) {
                println("Aluno ${aluno.nome} com o código ${aluno.codigo} já está matriculado")
                return false
            }
        }
        alunosMatriculados.add(umAluno)
        println("Aluno ${umAluno.nome} matriculado com o código ${umAluno.codigo} com sucesso.")
        return true
    }
    fun excluirAluno(umAluno: Aluno): Boolean {
        for (aluno in alunosMatriculados!!) {
            if (aluno.codigo == umAluno.codigo) {
                println("Aluno ${umAluno.nome} de código ${umAluno.codigo} excluido com sucesso.")
                alunosMatriculados!!.remove(umAluno)
                return true
            }
        }
        println("Aluno ${umAluno.nome} de código ${umAluno.codigo} não existe.")
        return true
    }
}