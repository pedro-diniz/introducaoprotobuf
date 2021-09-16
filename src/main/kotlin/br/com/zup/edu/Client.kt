package br.com.zup.edu

import io.grpc.ManagedChannelBuilder

fun main() {

    // cria o nosso canal de comunicação com o servidor
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext() // para uso local, com HTTP
        .build()

    // cria nossa request que será passada ao servidor
    val request = FuncionarioRequest.newBuilder()
        .setNome("Yuri Matheus")
        .setCpf("000.000.000-00")
        .setIdade(22)
        .setSalario(2000.20)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua das Tabajaras")
            .setCep("00000-000")
            .setComplemento("Casa 20")
            .build())
        .build()

    // cria o client a partir do channel
    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    // chama o método cadastrar a partir do client conectado ao servidor pelo channel
    val response = client.cadastrar(request)
    println(response)

}