package desafio

import java.time.LocalDate

class DigitalHouseManager() {
    var listaAlunos = mutableListOf<Aluno>()
    var listaProfessores = mutableListOf<Professor>()
    var listaCursos = mutableListOf<Curso>()
    var listaMatrículas = mutableListOf<Matricula>()

    fun registrarCurso(nome: String,
                       codigoCurso: Int,
                       quantidadeMaximaDeAlunos: Int): Boolean {
        for (curso in listaCursos) {
            if (curso.codigo == codigoCurso) {
                println("Curso ${curso.nome} com codigo ${curso.codigo} já existe.")
                return false
            }
        }
        println("Curso $nome com codigo $codigoCurso registrado com sucesso.")
        listaCursos.add(Curso(nome, codigoCurso, null, null, quantidadeMaximaDeAlunos, null))
        return true
    }
    fun excluirCurso(codigoCurso: Int): Boolean {
        for (curso in listaCursos) {
            if (curso.codigo == codigoCurso) {
                println("Curso ${curso.nome} com codigo ${curso.codigo} excluido com sucesso.")
                listaCursos.remove(curso)
                return true
            }
        }
        println("Curso com codigo $codigoCurso não existe.")
        return false
    }
    fun registrarProfessorAdjunto(nome: String,
                                  sobrenome: String,
                                  codigoProfessor: Int,
                                  quantidadeDeHoras: Int): Boolean {
        for (professor in listaProfessores) {
            if (professor.codigo == codigoProfessor) {
                println("Professor adjunto ${professor.nome} com codigo ${professor.codigo} já existe.")
                return false
            }
        }
        println("Professor adjunto $nome com codigo $codigoProfessor registrado com sucesso.")
        listaProfessores.add(ProfessorAdjunto(nome, sobrenome, 0, codigoProfessor, quantidadeDeHoras))
        return true
    }
    fun registrarProfessorTitular(nome: String,
                                  sobrenome: String,
                                  codigoProfessor: Int,
                                  especialidade: String): Boolean {
        for (professor in listaProfessores) {
            if (professor.codigo == codigoProfessor) {
                println("Professor titular ${professor.nome} com codigo ${professor.codigo} já existe.")
                return false
            }
        }
        println("Professor titular $nome com codigo $codigoProfessor registrado com sucesso.")
        listaProfessores.add(ProfessorTitular(nome, sobrenome, 0, codigoProfessor, especialidade))
        return true
    }
    fun excluirProfessor(codigoProfessor: Int): Boolean {
        for (professor in listaProfessores) {
            if (professor.codigo == codigoProfessor) {
                println("Professor ${professor.nome} com codigo ${professor.codigo} excluido com sucesso.")
                listaCursos.remove(professor)
                return true
            }
        }
        println("Professor com codigo $codigoProfessor não existe.")
        return false
    }
    fun registrarAluno(nome: String,
                        sobrenome: String,
                        codigoAluno: Int): Boolean {
        for (aluno in listaAlunos) {
            if (aluno.codigo == codigoAluno) {
                println("Codigo ${aluno.codigo} de aluno ${aluno.nome} com já existe.")
                return false
            }
        }
        println("Aluno $nome com codigo $codigoAluno registrado com sucesso.")
        return true
    }
    fun matricularAluno(codigoAluno: Int,
                        codigoCurso: Int): Boolean {
        var existeCurso = false
        var existeAluno = false
        var cursoEncontrado = Curso("",
                                    0,
                                    null,
                                    null,
                                    0,
                                    null)
        var alunoEncontrado = Aluno("",
                                    "",
                                    0)
        for (curso in listaCursos) {
            if (curso.codigo == codigoCurso) {
                println("Curso encontrado: ${curso.nome}")
                cursoEncontrado = curso
                var quantAlunosMatricula = curso.alunosMatriculados?.size
                existeCurso = true
            }
        }
        for (aluno in listaAlunos) {
            if (aluno.codigo == codigoAluno) {
                println("Aluno encontrado: ${aluno.nome}")
                alunoEncontrado = aluno
                existeAluno = true
            }
        }
        if(existeCurso && existeAluno) {
            if(cursoEncontrado.alunosMatriculados?.size!! < cursoEncontrado.quantidadeMaxAlunos) {
                var alunoAdicionado = cursoEncontrado.adicionarUmAluno(alunoEncontrado)
                println("Aluno ${alunoEncontrado.nome} adicionado no curso ${cursoEncontrado.nome}.")
                listaMatrículas.add(Matricula(alunoEncontrado, cursoEncontrado, LocalDate.now()))
                println("Matricula realizada com sucesso.")
                return alunoAdicionado
            } else {
                println("Não existe vagas para o curso ${cursoEncontrado.nome}.")
                return false
            }
        } else {
            return false
        }
    }
}