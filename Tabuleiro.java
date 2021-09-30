package Entities;

public class Tabuleiro {
    final int size = 8;
    int maxValue, plays;
    int[][] posicoesTabuleiro, savePositions;
    int[] caminhos;
    boolean returnCase;
    String resultado;

    public Tabuleiro(Cavalo cavalo) {
        caminhos = new int[size * size];
        posicoesTabuleiro = new int[size][size];
        savePositions = new int[size][size];

        resolvendoCaminho(cavalo);
    }

    @Override
    public String toString() {
        String print = resultado + "\n\s";
        for (int firstLine = 0; firstLine < size; ++firstLine)
            print += String.format(firstLine == size - 1 ? "\s\s\s\s%d\n" : "\s\s\s\s%d", firstLine);
        for (int collumn = 0; collumn < size; ++collumn) {
            print += collumn;
            for (int line = 0; line < size; ++line) {
                String valorRepresent = posicoesTabuleiro[collumn][line] > 9 ? "\s\s\s" + posicoesTabuleiro[collumn][line]
                        : "\s\s\s\s" + posicoesTabuleiro[collumn][line];
                print += valorRepresent;
            }
            print += "\n";
        }
        if(maxValue == 64) {
            print += "\nN. tentativas N. passos\tN. tentativas N. passos\n";
            for(int pos = 0; pos < 32; pos++) {
                print += String.format("%d\t\t%d\t%d\t\t%d\n", caminhos[pos], pos + 1, caminhos[pos + 32], pos + 33);
            }
        }
        return print;
    }

    // OKAY!!!
    private void resolvendoCaminho(Cavalo cavalo) {
        int valor = 1;

        int[] matrizX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] matrizY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        posicoesTabuleiro[cavalo.posY][cavalo.posX] = valor;

        if (checandoPosibilidades(cavalo.posX, cavalo.posY, ++valor, matrizX, matrizY)) {
            resultado = String.format("O passeio finalizou com %d movimentos\nHouve um passeio completo! Parabéns!", maxValue);
            return;
        } else {
            resultado = String.format("O passeio finalizou com %d movimentos\nNão houve um passeio completo! Sem solução!", maxValue);
            posicoesTabuleiro = savePositions.clone();
        }
    }

    private boolean checandoPosibilidades(int valorX, int valorY, int valor, int[] posX, int[] posY) {
        int prox_X, prox_Y;
        if (valor > size * size)
            return true;
        else if(plays > 1e7)
            return false;

        for (int pos = 0; pos < posX.length; ++pos) {
            prox_X = valorX + posX[pos];
            prox_Y = valorY + posY[pos];

            if (isSafe(prox_X, prox_Y)) {
                ++plays;
                posicoesTabuleiro[prox_Y][prox_X] = valor;

                if (valor > maxValue) {
                    maxValue = valor;
                    for(int yValue = 0; yValue < size; ++yValue) 
                        for(int xValue = 0; xValue < size; ++xValue) {
                            savePositions[yValue][xValue] = posicoesTabuleiro[yValue][xValue];
                        }
                }

                if (checandoPosibilidades(prox_X, prox_Y, ++valor, posX, posY))
                    return true;
                else {
                    --valor;
                    if(plays < 1000)
                        caminhos[valor]++;
                    posicoesTabuleiro[prox_Y][prox_X] = 0;
                }
            }
        }
        return false;
    }

    // OK!
    private boolean isSafe(int xValue, int yValue) {
        if (xValue >= 0 && xValue < size && yValue >= 0 && yValue < size && posicoesTabuleiro[yValue][xValue] == 0)
            return true;
        return false;
    }
}
