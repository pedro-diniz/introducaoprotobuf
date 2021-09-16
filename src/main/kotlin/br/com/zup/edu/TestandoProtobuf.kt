package br.com.zup.edu

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {

    // as classes geradas pelo protobuf não são geradas diretamente,
    // elas são baseadas pela design pattern builder.
    val request = FuncionarioRequest.newBuilder()
        .setNome("Yuri Matheus")
        .setCpf("000.000.000-00")
        .setSalario(2000.20)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua das Tabajaras")
            .setCep("00000-000")
            .setComplemento("Casa 20")
            .build())
        .build()

    println(request)

    // escrevemos o objeto em disco
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    // lemos o objeto em disco
    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.GERENTE)

    println(request2)

}