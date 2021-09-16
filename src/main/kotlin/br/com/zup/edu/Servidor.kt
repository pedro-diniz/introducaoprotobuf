package br.com.zup.edu

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {

    val server = ServerBuilder // cria o servidor
        .forPort(50051)
        .addService(FuncionarioEndpoint())
        .build()

    server.start() // inicializa o servidor
    server.awaitTermination() // faz o servidor ficar no ar, algo que um main normalmente não faria

}

// Não podemos chamar a classe de serviço diretamente, pois ela não pode ser instanciada.
// Para isso, criamos uma classe que a implemente
class FuncionarioEndpoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase() {

    // responseObserver é a forma que o gRPC usa pra trabalhar tanto com request/response como com streams
    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        println(request!!)

        var nome : String? = request.nome
        // como o default de uma string é vazio, validamos com hasField
        if (!request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))) {
            nome = "[???]"
        }

        // código bem feio aqui. Não tem muito o que explicar, só copiar, entender e sentir
        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val criadoEm = Timestamp.newBuilder() // importar timestamp do com.google.protobuf.Timestamp
            .setSeconds(instant.epochSecond)
            .setNanos(instant.nano)
            .build()

        // cria o retorno a partir, também, de um builder
        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        responseObserver?.onNext(response) // retorna a response
        responseObserver?.onCompleted() // encerra a chamada

    }

}