# Projeto de Grafos em Java

Este projeto implementa um sistema de gerenciamento de grafos utilizando a linguagem Java. Ele permite criar, manipular e visualizar grafos, além de realizar diversas operações como buscas, encontrar o caminho mínimo e calcular a árvore geradora mínima.

## Arquivos

### 1. Aresta.java
Este arquivo define a classe `Aresta`, que representa uma aresta em um grafo com dois atributos principais:
- `destino`: o vértice de destino da aresta.
- `peso`: o peso da aresta.

### 2. Grafo.java
Este arquivo contém a classe `Grafo`, que implementa um grafo não-direcionado e ponderado. As funcionalidades implementadas nesta classe incluem:
- **Inserir Vértice**: Adiciona um novo vértice ao grafo.
- **Inserir Aresta**: Adiciona uma nova aresta entre dois vértices, com um peso específico.
- **Remover Vértice**: Remove um vértice e todas as arestas associadas a ele.
- **Remover Aresta**: Remove uma aresta específica entre dois vértices.
- **Visualizar Grafo**: Imprime uma representação do grafo.
- **Informar Grau de um Vértice**: Exibe o grau (número de arestas conectadas) de um vértice.
- **Verificar Conexidade**: Determina se o grafo é conexo.
- **Converter para Matriz de Adjacência**: Converte a representação do grafo para uma matriz de adjacência.
- **Busca em Largura (BFS)**: Realiza uma busca em largura a partir de um vértice inicial.
- **Busca em Profundidade (DFS)**: Realiza uma busca em profundidade a partir de um vértice inicial.
- **Caminho Mínimo (Dijkstra)**: Calcula o caminho mínimo entre dois vértices utilizando o algoritmo de Dijkstra.
- **Árvore Geradora Mínima (Prim)**: Calcula a árvore geradora mínima utilizando o algoritmo de Prim.
- **Representar Grafo em ASCII**: Gera uma representação visual do grafo em ASCII.

### 3. Main.java
O arquivo `Main.java` contém o ponto de entrada para o programa. Ele fornece um menu interativo para o usuário executar as várias operações disponíveis na classe `Grafo`.

### Dependências
Este projeto não possui dependências externas além do JDK para compilar e executar os arquivos Java.

Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request ou relatar problemas no repositório.

### Licença


Este README fornece uma visão geral do projeto e como utilizá-lo. Você pode ajustá-lo conforme necessário antes de adicioná-lo ao seu repositório Git. &#8203;:contentReference[oaicite:0]{index=0}&#8203;


