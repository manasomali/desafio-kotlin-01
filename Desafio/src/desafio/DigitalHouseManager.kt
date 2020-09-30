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
        listaCursos.add(Curso(nome, codigoCurso, null, null, quantidadeMaximaDeAlunos, mutableListOf<Aluno>()))
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
        listaAlunos.add(Aluno(nome, sobrenome,codigoAluno))
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
                                    mutableListOf<Aluno>())
        var alunoEncontrado = Aluno("",
                                    "",
                                    0)
        var quantAlunosMatriculados = 0
        for (curso in listaCursos) {
            if (curso.codigo == codigoCurso) {
                cursoEncontrado = curso
                quantAlunosMatriculados = curso.alunosMatriculados.size
                if (quantAlunosMatriculados==1) {
                    println("Curso ${curso.nome} encontrado com $quantAlunosMatriculados aluno matriculado.")
                } else {
                    println("Curso ${curso.nome} encontrado com $quantAlunosMatriculados alunos matriculados.")
                }
                existeCurso = true
            }
        }
        for (aluno in listaAlunos) {
            if (aluno.codigo == codigoAluno) {
                println("Aluno ${aluno.nome} encontrado.")
                alunoEncontrado = aluno
                existeAluno = true
            }
        }
        if(existeCurso && existeAluno) {
            return if(quantAlunosMatriculados < cursoEncontrado.quantidadeMaxAlunos) {
                var alunoAdicionado = cursoEncontrado.adicionarUmAluno(alunoEncontrado)
                println("Aluno ${alunoEncontrado.nome} adicionado no curso ${cursoEncontrado.nome}.")
                listaMatrículas.add(Matricula(alunoEncontrado, cursoEncontrado, LocalDate.now()))
                println("Matricula realizada com sucesso.")
                alunoAdicionado
            } else {
                println("Não foi possivel adicionar o aluno ${alunoEncontrado.nome} no curso ${cursoEncontrado.nome}.")
                println("Quantidade máxima de alunos ${cursoEncontrado.quantidadeMaxAlunos}, curso lotado.")
                false
            }
        } else if (!existeCurso && !existeAluno){
            println("Não existe curso com codigo $codigoCurso e aluno com codigo $codigoAluno.")
            return false
        } else if (existeCurso && !existeAluno){
            println("Não existe aluno com codigo $codigoAluno.")
            return false
        } else if (!existeCurso && existeAluno){
            println("Não existe curso com codigo $codigoCurso.")
            return false
        }
        return false
    }
    fun alocarProfessores(codigoCurso: Int,
                          codigoTitular: Int,
                          codigoAdjunto: Int): Boolean {
        var profTitular = ProfessorTitular("",
                                           "",
                                           0,
                                            -1,
                                            "")
        var profAdjunto = ProfessorAdjunto("",
                                            "",
                                            9,
                                            -1,
                                            0)
        for (professor in listaProfessores) {
            if (professor.codigo == codigoTitular && professor is ProfessorTitular) {
                profTitular = professor
            }
            if (professor.codigo == codigoAdjunto && professor is ProfessorAdjunto) {
                profAdjunto = professor
            }
        }
        if (profAdjunto.codigo != -1 && profTitular.codigo != -1) {
            for (curso in listaCursos) {
                if (curso.codigo == codigoCurso) {
                    println("Professor Titular ${profTitular.nome} e Professor Adjunto ${profAdjunto.nome} foram alocados no curso ${curso.nome} com sucesso.")
                    curso.professorTitular = profTitular
                    curso.professorAdjunto = profAdjunto
                    return true
                }
            }
        } else if (profTitular.codigo==-1 && profAdjunto.codigo==-1) {
            println("Professor Titular e Adjunto com códigos $codigoTitular e $codigoAdjunto não existem.")
            return false
        } else if (profTitular.codigo==-1) {
            println("Professor Titular com código $codigoTitular não existe.")
            return false
        } else if (profAdjunto.codigo==-1) {
            println("Professor Adjunto com código $codigoAdjunto não existe.")
            return false
        } else {
            println("Curso com código $codigoCurso não existe.")
            return false
        }
        return false
    }
}