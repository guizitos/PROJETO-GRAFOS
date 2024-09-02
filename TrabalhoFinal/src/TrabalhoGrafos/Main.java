package TrabalhoGrafos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Inserir Vértice");
            System.out.println("2. Inserir Aresta");
            System.out.println("3. Remover Vértice");
            System.out.println("4. Remover Aresta");
            System.out.println("5. Visualizar Grafo");
            System.out.println("6. Informar Grau de um Vértice");
            System.out.println("7. Verificar se o Grafo é Conexo");
            System.out.println("8. Converter para Matriz de Adjacência");
            System.out.println("9. Caminhamento em Largura (BFS)");
            System.out.println("10. Caminhamento em Profundidade (DFS)");
            System.out.println("11. Caminho Mínimo (Dijkstra)");
            System.out.println("12. Árvore Geradora Mínima (Prim)");
            System.out.println("13. Representar Grafo em ASCII");
            System.out.println("14. Sair");
            System.out.print("Digite sua escolha: ");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o ID do vértice: ");
                    int vertice = scanner.nextInt();
                    grafo.inserirVertice(vertice);
                    break;
                case 2:
                    System.out.print("Digite o vértice de origem: ");
                    int origem = scanner.nextInt();
                    System.out.print("Digite o vértice de destino: ");
                    int destino = scanner.nextInt();
                    System.out.print("Digite o peso da aresta: ");
                    int peso = scanner.nextInt();
                    grafo.inserirAresta(origem, destino, peso);
                    break;
                case 3:
                    System.out.print("Digite o ID do vértice a ser removido: ");
                    int verticeRemover = scanner.nextInt();
                    grafo.removerVertice(verticeRemover);
                    break;
                case 4:
                    System.out.print("Digite o vértice de origem da aresta a ser removida: ");
                    int origemRemover = scanner.nextInt();
                    System.out.print("Digite o vértice de destino da aresta a ser removida: ");
                    int destinoRemover = scanner.nextInt();
                    grafo.removerAresta(origemRemover, destinoRemover);
                    break;
                case 5:
                    grafo.visualizarGrafo();
                    break;
                case 6:
                    System.out.print("Digite o ID do vértice para informar o grau: ");
                    int verticeGrau = scanner.nextInt();
                    grafo.informarGrauVertice(verticeGrau);
                    break;
                case 7:
                    grafo.verificarConexidade();
                    break;
                case 8:
                    grafo.converterParaMatrizAdjacencia();
                    break;
                case 9:
                    System.out.print("Digite o vértice inicial para BFS: ");
                    int inicioBFS = scanner.nextInt();
                    grafo.buscaEmLargura(inicioBFS);
                    break;
                case 10:
                    System.out.print("Digite o vértice inicial para DFS: ");
                    int inicioDFS = scanner.nextInt();
                    grafo.buscaEmProfundidade(inicioDFS);
                    break;
                case 11:
                    System.out.print("Digite o vértice de origem para Dijkstra: ");
                    int origemDijkstra = scanner.nextInt();
                    System.out.print("Digite o vértice de destino para Dijkstra: ");
                    int destinoDijkstra = scanner.nextInt();
                    grafo.caminhoMinimoDijkstra(origemDijkstra, destinoDijkstra);
                    break;
                case 12:
                    grafo.arvoreGeradoraMinimaPrim();
                    break;
                case 13:
                    grafo.representarGrafoASCII();
                    break;
                case 14:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        }
    }
}

