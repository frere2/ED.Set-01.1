package br.edu.fateczl.thiago.set;

public class Set<T> {
    No<T> primeiro;

    public Set() {
        primeiro = null;
    }

    public boolean isEmpty() {
        return primeiro == null;
    }

    public int size() {
        int cont = 0;
        if (!isEmpty()) {
            No<T> auxiliar = primeiro;
            while (auxiliar != null) {
                cont++;
                auxiliar = auxiliar.proximo;
            }
        }
        return cont;
    }

    private No<T> getNo(int pos) throws Exception {
        if (isEmpty()) {
            throw new Exception("Set vazio");
        }
        int tamanho = size();
        if (pos < 0 || pos > tamanho - 1) {
            throw new Exception("PosiInvalida");
        }
        int cont = 0;
        No<T> auxiliar = primeiro;
        while (cont < pos) {
            cont++;
            auxiliar = auxiliar.proximo;
        }
        return auxiliar;
    }

    public void addFirst(T valor) {
        No<T> elemento = new No<>();
        elemento.dado = valor;
        elemento.proximo = primeiro;
        primeiro = elemento;
    }

    public void addLast(T valor) throws Exception {
        if (isEmpty()) {
            addFirst(valor);
        }
        if (contains(valor)) {
            throw new Exception("Valor já existe.");
        }
        int tamanho = size();
        No<T> elemento = new No<>();
        elemento.dado = valor;
        elemento.proximo = null;
        No<T> ultimo = getNo(tamanho - 1);
        ultimo.proximo = elemento;
    }

    public void add(T valor, int pos) throws Exception {
        int tamanho = size();
        if (pos < 0 || pos > tamanho) {
            throw new Exception("PosiInvalida");
        }
        if (contains(valor)) {
            throw new Exception("Valor já existe.");
        }
        if (pos == 0) {
            addFirst(valor);
        } else if (pos == tamanho) {
            addLast(valor);
        } else {
            No<T> anterior = getNo(pos - 1);
            No<T> elemento = new No<>();
            elemento.dado = valor;
            elemento.proximo = anterior.proximo;
            anterior.proximo = elemento;
        }
    }

    public boolean contains(T valor) throws Exception {
        int tamanho = size();
        No<T> aux = primeiro;
        for (int i = 0; i < tamanho; i++) {
            if (aux.dado == valor) {
                return true;
            }
            aux = aux.proximo;
        }
        return false;
    }

    public void removeFirst() throws Exception {
        if (isEmpty()) {
            throw new Exception("Set Vazio");
        }
        primeiro = primeiro.proximo;
    }

    public void removeLast() throws Exception {
        if (isEmpty()) {
            throw new Exception("Set Vazia");
        }
        int tamanho = size();
        if (tamanho == 1) {
            removeFirst();
        } else {
            No<T> penultimo = getNo(tamanho - 2);
            penultimo.proximo = null;
        }
    }

    public void remove(int pos) throws Exception {
        if (isEmpty()) {
            throw new Exception("Set Vazio");
        }
        int tamanho = size();
        if (pos < 0 || pos > tamanho - 1) {
            throw new Exception("PosiInvalida");
        }
        if (pos == 0) {
            removeFirst();
        } else if (pos == tamanho - 1) {
            removeLast();
        } else {
            No<T> anterior = getNo(pos - 1);
            No<T> atual = getNo(pos);
            anterior.proximo = atual.proximo;
        }
    }

    public T get(int pos) throws Exception {
        if (isEmpty()) {
            throw new Exception("Set vazio");
        }
        int tamanho = size();
        if (pos < 0 || pos > tamanho - 1) {
            throw new Exception("PosiInvalida");
        }
        No<T> auxiliar = getNo(pos);
        return auxiliar.dado;
    }

    public void clean() throws Exception {
        if (isEmpty()) {
            throw new Exception("O set já esta vazio!");
        }

        int tamanho = size();
        for (int i = 0; i < tamanho; i++) {
            removeLast();
        }
    }
}
