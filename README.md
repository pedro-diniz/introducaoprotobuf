Cenário:
Imagine que tenhamos que criar uma API gRPC para cadastrar um novo carro no nosso sistema. Para isso, nosso tech lead conversou com o cliente e levantou as informações na qual um carro precisa necessariamente ter:

modelo (ex: Gol, Palio, Celta etc);

placa (ex: HPX-1234, OIP-9876 etc);

ano (ex: 1984, 2001, 2019 etc);

nome e CPF do proprietário;

tipo de combustível, nesse caso deve suportar 3 tipos: GASOLINA, ALCOOL e FLEX;

Além disso, ele identificou com o time do cliente que o retorno da API pode ser algo simples nesse momento, portanto nossa API gRPC deve retornar somente 2 campos:
ID interno criado pelo sistema;
data e hora de criação do carro no sistema;
Para facilitar nossa vida, nosso tech lead começou a escrever o arquivo .proto com a configuração inicial utilizada pela Zup:

syntax = "proto3";

option java_multiple_files = true;
option java_outer_classname = "CarrosGrpc";

package br.com.zup.edu.carros;

/** o código da sua API gRPC vai aqui * /

  Com base nos requisitos e no código inicial do .proto acima, como você desenharia essa API gRPC com Protobuf?
