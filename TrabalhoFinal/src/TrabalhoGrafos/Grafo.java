package TrabalhoGrafos;

import java.util.*;

public class Grafo {
    private final Map<Integer, List<Aresta>> adjacencias;
    private int numVertices;

    public Grafo() {
        adjacencias = new HashMap<>();
        numVertices = 0;
    }

    public void inserirVertice(int id) {
        if (!adjacencias.containsKey(id)) {
            adjacencias.put(id, new ArrayList<>());
            numVertices++;
            System.out.println("Vértice " + id + " inserido.");
        } else {
            System.out.println("Vértice " + id + " já existe.");
        }
    }

    public void inserirAresta(int origem, int destino, int peso) {
        if (adjacencias.containsKey(origem) && adjacencias.containsKey(destino)) {
            adjacencias.get(origem).add(new Aresta(destino, peso));
            adjacencias.get(destino).add(new Aresta(origem, peso)); // Grafo não direcionado
            System.out.println("Aresta entre " + origem + " e " + destino + " com peso " + peso + " inserida.");
        } else {
            System.out.println("Um ou ambos os vértices não existem.");
        }
    }

    public void removerVertice(int id) {
        if (adjacencias.containsKey(id)) {
            adjacencias.values().forEach(list -> list.removeIf(aresta -> aresta.destino == id));
            adjacencias.remove(id);
            numVertices--;
            System.out.println("Vértice " + id + " removido.");
        } else {
            System.out.println("Vértice " + id + " não existe.");
        }
    }

    public void removerAresta(int origem, int destino) {
        if (adjacencias.containsKey(origem) && adjacencias.containsKey(destino)) {
            adjacencias.get(origem).removeIf(aresta -> aresta.destino == destino);
            adjacencias.get(destino).removeIf(aresta -> aresta.destino == origem);
            System.out.println("Aresta entre " + origem + " e " + destino + " removida.");
        } else {
            System.out.println("Um ou ambos os vértices não existem.");
        }
    }

    public void visualizarGrafo() {
        System.out.println("Representação do Grafo:");
        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            System.out.print("Vértice " + entry.getKey() + ": ");
            for (Aresta aresta : entry.getValue()) {
                System.out.print(" -> " + aresta.destino + "(peso=" + aresta.peso + ")");
            }
            System.out.println();
        }
    }

    public void informarGrauVertice(int id) {
        if (adjacencias.containsKey(id)) {
            int grau = adjacencias.get(id).size();
            System.out.println("Grau do vértice " + id + ": " + grau);
        } else {
            System.out.println("Vértice " + id + " não existe.");
        }
    }

    public void verificarConexidade() {
        if (numVertices == 0) {
            System.out.println("O grafo está vazio.");
            return;
        }
        
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        
        Integer primeiroVertice = adjacencias.keySet().iterator().next();
        fila.add(primeiroVertice);
        visitados.add(primeiroVertice);
        
        while (!fila.isEmpty()) {
            Integer atual = fila.poll();
            for (Aresta aresta : adjacencias.get(atual)) {
                if (!visitados.contains(aresta.destino)) {
                    visitados.add(aresta.destino);
                    fila.add(aresta.destino);
                }
            }
        }
        
        if (visitados.size() == numVertices) {
            System.out.println("O grafo é conexo.");
        } else {
            System.out.println("O grafo não é conexo.");
        }
    }

    public void converterParaMatrizAdjacencia() {
        int[][] matriz = new int[numVertices][numVertices];
        for (int[] linha : matriz) {
            Arrays.fill(linha, 0);
        }

        // Mapeamento de ID de vértice para índice de matriz
        Map<Integer, Integer> idParaIndice = new HashMap<>();
        int index = 0;
        for (Integer id : adjacencias.keySet()) {
            idParaIndice.put(id, index++);
        }

        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            int origemIndice = idParaIndice.get(entry.getKey());
            for (Aresta aresta : entry.getValue()) {
                int destinoIndice = idParaIndice.get(aresta.destino);
                matriz[origemIndice][destinoIndice] = aresta.peso;
            }
        }

        System.out.println("Matriz de Adjacência:");
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
    
    public void buscaEmLargura(int inicio) {
        if (!adjacencias.containsKey(inicio)) {
            System.out.println("Vértice " + inicio + " não existe.");
            return;
        }

        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        
        fila.add(inicio);
        visitados.add(inicio);
        
        System.out.println("BFS a partir do vértice " + inicio + ":");

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            System.out.print(atual + " ");
            
            for (Aresta aresta : adjacencias.get(atual)) {
                if (!visitados.contains(aresta.destino)) {
                    fila.add(aresta.destino);
                    visitados.add(aresta.destino);
                }
            }
        }

        System.out.println();
    }
    
    public void buscaEmProfundidade(int inicio) {
        if (!adjacencias.containsKey(inicio)) {
            System.out.println("Vértice " + inicio + " não existe.");
            return;
        }

        Set<Integer> visitados = new HashSet<>();
        System.out.println("DFS a partir do vértice " + inicio + ":");
        dfsUtil(inicio, visitados);
        System.out.println();
    }

    private void dfsUtil(int vertice, Set<Integer> visitados) {
        visitados.add(vertice);
        System.out.print(vertice + " ");

        for (Aresta aresta : adjacencias.get(vertice)) {
            if (!visitados.contains(aresta.destino)) {
                dfsUtil(aresta.destino, visitados);
            }
        }
    }
    
    public void caminhoMinimoDijkstra(int origem, int destino) {
        if (!adjacencias.containsKey(origem) || !adjacencias.containsKey(destino)) {
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }

        Map<Integer, Integer> distancias = new HashMap<>();
        Map<Integer, Integer> anteriores = new HashMap<>();
        PriorityQueue<Integer> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        for (int vertice : adjacencias.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
            anteriores.put(vertice, null);
        }
        
        distancias.put(origem, 0);
        filaPrioridade.add(origem);

        while (!filaPrioridade.isEmpty()) {
            int atual = filaPrioridade.poll();

            if (atual == destino) break;

            for (Aresta aresta : adjacencias.get(atual)) {
                int vizinho = aresta.destino;
                int novaDistancia = distancias.get(atual) + aresta.peso;

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    anteriores.put(vizinho, atual);
                    filaPrioridade.add(vizinho);
                }
            }
        }

        if (distancias.get(destino) == Integer.MAX_VALUE) {
            System.out.println("Não há caminho entre " + origem + " e " + destino);
            return;
        }

        System.out.println("Caminho mais curto de " + origem + " para " + destino + " com peso " + distancias.get(destino));
        LinkedList<Integer> caminho = new LinkedList<>();
        for (Integer vertice = destino; vertice != null; vertice = anteriores.get(vertice)) {
            caminho.addFirst(vertice);
        }

        System.out.println("Caminho: " + caminho);
    }
    
    public void arvoreGeradoraMinimaPrim() {
        if (numVertices == 0) {
            System.out.println("O grafo está vazio.");
            return;
        }

        PriorityQueue<Aresta> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));
        Set<Integer> verticesIncluidos = new HashSet<>();
        List<Aresta> arestasAGM = new ArrayList<>();
        int pesoTotal = 0;

        int inicio = adjacencias.keySet().iterator().next();
        verticesIncluidos.add(inicio);
        filaPrioridade.addAll(adjacencias.get(inicio));

        while (verticesIncluidos.size() < numVertices) {
            Aresta aresta = filaPrioridade.poll();
            if (aresta == null) break;

            if (!verticesIncluidos.contains(aresta.destino)) {
                verticesIncluidos.add(aresta.destino);
                pesoTotal += aresta.peso;
                arestasAGM.add(aresta);

                for (Aresta adjacente : adjacencias.get(aresta.destino)) {
                    if (!verticesIncluidos.contains(adjacente.destino)) {
                        filaPrioridade.add(adjacente);
                    }
                }
            }
        }

        System.out.println("Árvore Geradora Mínima (Prim):");
        for (Aresta aresta : arestasAGM) {
            System.out.println("Aresta de " + inicio + " para " + aresta.destino + " com peso " + aresta.peso);
            inicio = aresta.destino;  // Atualizando o início para o próximo vértice
        }
        System.out.println("Peso total da AGM: " + pesoTotal);
    }
    
    public void representarGrafoASCII() {
        System.out.println("Representação do Grafo em ASCII:");

        // Mapeamento de linhas por vértice para facilitar o layout
        Map<Integer, String> linhasPorVertice = new HashMap<>();
        
        // Armazena as arestas já representadas
        Set<String> arestasRepresentadas = new HashSet<>();
        
        // Determinando o número de linhas para imprimir
        int maxVertices = adjacencias.size();
        int linhas = maxVertices * 2; // Cada vértice pode ter uma linha superior e uma linha de conexão
        
        // Inicializando o mapeamento de linhas para os vértices
        for (int i = 0; i < linhas; i++) {
            linhasPorVertice.put(i, ""); // Inicialmente, linhas vazias
        }
        
        // Iterando sobre cada vértice e suas arestas
        int linhaAtual = 0;
        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            int origem = entry.getKey();
            List<Aresta> arestas = entry.getValue();
            
            // Adicionando o vértice à linha
            String linhaVertice = "V" + origem;
            linhasPorVertice.put(linhaAtual, linhasPorVertice.get(linhaAtual) + linhaVertice);
            
            // Adicionando as arestas deste vértice
            for (Aresta aresta : arestas) {
                int destino = aresta.destino;
                int peso = aresta.peso;
                
                // Evitando duplicação de arestas
                String representacaoAresta = origem < destino ? origem + "-" + destino : destino + "-" + origem;
                if (!arestasRepresentadas.contains(representacaoAresta)) {
                    arestasRepresentadas.add(representacaoAresta);
                    linhaAtual++;
                    String linhaAresta = "---(" + peso + ")--";
                    linhasPorVertice.put(linhaAtual, linhasPorVertice.get(linhaAtual) + linhaAresta);
                    linhaAtual++;
                }
            }
        }

        // Imprimindo a representação do grafo
        for (int i = 0; i < linhas; i++) {
            if (!linhasPorVertice.get(i).isEmpty()) {
                System.out.println(linhasPorVertice.get(i));
            }
        }
        
        System.out.println("\nLegenda:");
        System.out.println("Vx: Representa o vértice x.");
        System.out.println("Número entre parênteses representa o peso da aresta.");
        System.out.println("Cada linha representa a conexão entre os vértices.");
    }
    
    public String getTextoParaMostrar() {
        // Crie uma representação em texto do grafo e das operações
        // Exemplo:
        StringBuilder sb = new StringBuilder();
        // Adicione aqui o texto das operações
        return sb.toString();
    }



    public int getNumVertices() {
        return numVertices;
    }
}
