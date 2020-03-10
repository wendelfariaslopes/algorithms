package algorithms.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

/**
 * @author Victor
 */
public class SeparadorDeImage {

    // Usado para diferenciar o fundo das outras cores.
    private static final int LIMIAR = 232;

    // Cores conhecidas.
    public static final int VERMELHO = 0xFF_FF_00_00;
    public static final int VERDE = 0xFF_00_FF_00;
    public static final int AZUL = 0xFF_00_00_FF;
    public static final int BRANCO = 0xFF_FF_FF_FF;
    public static final int PRETO = 0xFF_00_00_00;
    public static final int AMARELO = 0xFF_FF_FF_00;
    public static final int CIANO = 0xFF_00_FF_FF;
    public static final int LIMA = 0xFF_80_FF_00;
    public static final int MAGENTA = 0xFF_FF_00_FF;
    public static final int ROSA = 0xFF_FF_00_80;
    public static final int VIOLETA = 0xFF_C0_80_FF;
    public static final int MARROM = 0xFF_80_40_00;
    public static final int LARANJA = 0xFF_80_40_00;
    public static final int CINZA = 0xFF_80_80_80;

    // Dá nome as cores.
    public static final Map<Integer, String> CORES = new HashMap<>(20);
    static {
        CORES.put(VERMELHO, "vermelho");
        CORES.put(VERDE, "verde");
        CORES.put(AZUL, "azul");
        CORES.put(BRANCO, "branco");
        CORES.put(PRETO, "preto");
        CORES.put(AMARELO, "amarelo");
        CORES.put(CIANO, "ciano");
        CORES.put(LIMA, "verde-lima");
        CORES.put(MAGENTA, "magenta");
        CORES.put(ROSA, "rosa");
        CORES.put(VIOLETA, "violeta");
        CORES.put(MARROM, "marrom");
        CORES.put(LARANJA, "laranja");
        CORES.put(CINZA, "cinza");
    }

    // Isola a quantidade de vermelho existente em uma cor.
    private static int vermelho(int cor) {
        return (cor & 0xFF0000) >>> 16;
    }

    // Isola a quantidade de verde existente em uma cor.
    private static int verde(int cor) {
        return (cor & 0xFF00) >>> 8;
    }

    // Isola a quantidade de azul existente em uma cor.
    private static int azul(int cor) {
        return cor & 0xFF;
    }

    // Reduz a cor dada para uma da lista de cores conhecidas.
    private static int reduzirCor(int cor) {
        int r1 = vermelho(cor);
        int g1 = verde(cor);
        int b1 = azul(cor);
        int melhorCor = 0;
        int melhorDistancia = Integer.MAX_VALUE;
        for (int talvez : CORES.keySet()) {
            int r2 = vermelho(talvez);
            int g2 = verde(talvez);
            int b2 = azul(talvez);
            int r3 = Math.abs(r1 - r2);
            int g3 = Math.abs(g1 - g2);
            int b3 = Math.abs(b1 - b2);

            // Calcula o quadrado da distância pitagórica ponderada entre a cor do pixel e a cor conhecida.
            // Usa como ponderação 3 para o vermelho, 6 para o verde e 1 para o azul.
            int distancia = r3 * r3 * 3 + g3 * g3 * 6 + b3 * b3;
            if (distancia < melhorDistancia) {
                melhorDistancia = distancia;
                melhorCor = talvez;
            }
        }
        return melhorCor;
    }

    // Reduz as cores da imagem para as que estão na lista de cores conhecidas.
    private static BufferedImage reduzirCores(BufferedImage input) {
        BufferedImage bi = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int cor = reduzirCor(input.getRGB(x, y));
                bi.setRGB(x, y, cor);
            }
        }
        return bi;
    }

    // Monta um histograma das cores presentes em uma imagem.
    private static Map<Integer, Integer> histogramaDeCores(BufferedImage imagem) {
        Map<Integer, Integer> histograma = new HashMap<>(50);
        for (int y = 0; y < imagem.getHeight(); y++) {
            for (int x = 0; x < imagem.getWidth(); x++) {
                int cor = imagem.getRGB(x, y);
                histograma.merge(cor, 1, (a, b) -> a + b);
            }
        }
        return histograma;
    }

    // Obtém a cor predominante de um histograma de cores.
    private static int corMaisFrequente(Map<Integer, Integer> histograma) {
        return histograma.entrySet()
                .stream()
                .reduce((a, b) -> a.getValue() > b.getValue() ? a : b)
                .get().getKey();
    }

    // Reduz a imagem para apenas duas cores: preto e branco.
    // Considera branco tudo que tiver vermelho, verde e azul maior ou igual a LIMIAR e preto todo o resto.
    private static BufferedImage pretoEBranco(BufferedImage input) {
        BufferedImage bi = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int cor = input.getRGB(x, y);
                int substituta = vermelho(cor) > LIMIAR && verde(cor) > LIMIAR && azul(cor) > LIMIAR ? BRANCO : PRETO;
                bi.setRGB(x, y, substituta);
            }
        }
        return bi;
    }

    // Preenche toda a área contígua que tenha uma mesma cor na figura dada com a nova cor informada.
    // O preenchimento é feito a partir das coordenadas informadas.
    private static void floodFill(int x, int y, BufferedImage input, int novaCor) {
        int corAntiga = input.getRGB(x, y);
        if (novaCor == corAntiga) return;
        // Cria uma fila de pixels ininicialmente com o pixel da coordenada dada.
        Queue<Coordinate2D> c = new ArrayDeque<>(200);
        c.add(new Coordinate2D(input, x, y));

        // Percorre a fila até acabar, colocando nela os pixels vizinhos do pixel retirado, mas apenas se este
        // puder ser recolorido.
        while (!c.isEmpty()) {
            Coordinate2D p = c.poll();
            if (input.getRGB(p.x, p.y) != corAntiga) continue;
            input.setRGB(p.x, p.y, novaCor);
            c.addAll(Arrays.asList(p.plusX(), p.plusY(), p.minusX(), p.minusY()).stream().filter(e -> e != null).collect(Collectors.toList()));
        }
    }

    // Retorna uma lista descrevendo as figuras que estão em uma imagem.
    // Cada item da lista é a descrição de uma figura.
    public static List<String> descrever(BufferedImage input) {
        // Cria uma cópia da imagem em preto em branco, para separar o fundo (branco) dos objetos (preto).
        BufferedImage pb = pretoEBranco(input);

        // Procura os pixels pretos, e sempre que achar um, pinta a área toda com uma cor diferente.
        // Isso faz com que cada objeto seja recolorido com uma cor única - ou seja, a segmentação é realizada aqui.
        int cores = 0;
        for (int y = 0; y < pb.getHeight(); y++) {
            for (int x = 0; x < pb.getWidth(); x++) {
                int cor = pb.getRGB(x, y);
                if (cor == PRETO) {
                    cores++;
                    floodFill(x, y, pb, cores);
                }
            }
        }

        // Cria uma descrição de imagens, uma para cada segmento.
        List<String> descs = new ArrayList<>(cores);
        for (int i = 0; i < cores; i++) {

            // Calcula o retângulo mínimo para enquadrar o objeto.
            // Como o objeto foi recolorido com uma cor única, basta procurar por todos os pixels com esta cor, ignorando os demais.
            // Esta parte do algoritmo é bem ineficiente e deve existir coisa muito melhor.
            // Mas o importante é que funciona. Se quiser otimizar isso depois, fique a vontade.
            int maxX = 0, minX = pb.getWidth() - 1;
            int maxY = 0, minY = pb.getHeight() - 1;
            for (int y = 0; y < pb.getHeight(); y++) {
                for (int x = 0; x < pb.getWidth(); x++) {
                    int cor = pb.getRGB(x, y);
                    if (cor == i + 1) {
                        if (y > maxY) maxY = y;
                        if (y < minY) minY = y;
                        if (x > maxX) maxX = x;
                        if (x < minX) minX = x;
                    }
                }
            }

            // Copia os pixels da imagem, criando duas imagens.
            // Uma imagem tem a figura preta a ser identificada.
            // A outra contém os pixels da figura original para identificar a cor.
            // Em ambos os casos, usa a cor única da figura recolorizada para saber quais são os pixels que interessam. 
            int w = maxX - minX + 1;
            int h = maxY - minY + 1;
            BufferedImage segmentoPb = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            BufferedImage segmentoCor = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int cor = pb.getRGB(minX + x, minY + y);
                    segmentoPb.setRGB(x, y, cor == i + 1 ? PRETO : BRANCO);
                    segmentoCor.setRGB(x, y, cor == i + 1 ? input.getRGB(minX + x, minY + y) : BRANCO);
                }
            }

            // Descobre a cor. Para isso, pega a imagem colorida, aproxima as cores para cores conhecidas,
            // e então descobre qual é a cor predominante, excluindo o branco do fundo.
            Map<Integer, Integer> hist = histogramaDeCores(reduzirCores(segmentoCor));
            hist.remove(BRANCO);
            int corDaImagem = corMaisFrequente(hist);
            String nomeCor = CORES.get(corDaImagem);

            // Descobre a figura. Para cada tipo de figura há uma função para determinar se a figura é o não do determinado tipo.
            String figura = retangulo(segmentoPb) ? "Retângulo"
                    : elipse(segmentoPb) ? "Elipse"
                    : "Figura irregular";
            descs.add(figura + " " + nomeCor);
        }
        return descs;
    }

    // Decide se a figura dada parece corresponder a um retângulo preto.
    private static boolean retangulo(BufferedImage imagem) {
        floodFill(imagem.getWidth() / 2, imagem.getHeight() / 2, imagem, PRETO);
        Map<Integer, Integer> histograma = histogramaDeCores(imagem);
        int preto = histograma.get(PRETO);
        return preto > imagem.getWidth() * imagem.getHeight() * 0.99;
    }

    // Decide se a figura dada parece corresponder a uma elipse preta.
    private static boolean elipse(BufferedImage imagem) {
        int cx = imagem.getWidth() / 2;
        int cy = imagem.getHeight() / 2;
        floodFill(cx, cy, imagem, PRETO);
        int certoDentro = 0, certoFora = 0, erradoDentro = 0, erradoFora = 0;
        for (int y = 0; y < imagem.getHeight(); y++) {
            for (int x = 0; x < imagem.getWidth(); x++) {
                double a = (x - cx) / (double) cx;
                double b = (y - cy) / (double) cy;
                boolean dentro = a * a + b * b <= 1;
                boolean preto = imagem.getRGB(x, y) == PRETO;
                if (preto && dentro) {
                    certoDentro++;
                } else if (preto && !dentro) {
                    erradoFora++;
                } else if (!preto && dentro) {
                    erradoDentro++;
                } else {
                    certoFora++;
                }
            }
        }
        return certoDentro + certoFora > 50 * (erradoDentro + erradoFora);
    }

    private static BufferedImage carregarImagem() throws IOException {
    	
    	File file = new File("/Users/wendellopes/git/algorithms/src/image/image2.jpg");
    	
        try {
//        	return ImageIO.read(new URL("/Users/wendellopes/Documents/workspaceLunaJava/PureJava/69Wj9.jpg"));
            return ImageIO.read(file);
        } catch (MalformedURLException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(descrever(carregarImagem()));
    }

    // Classe que representa um par de coordenadas x,y. A ser utilizado no algoritmo de floodfill.
    private static final class Coordinate2D {
        private final BufferedImage imagem;
        private final int x, y;

        public Coordinate2D(BufferedImage imagem, int x, int y) {
            Objects.requireNonNull(imagem);
            if (x < 0 || x >= imagem.getWidth() || y < 0 || y >= imagem.getHeight()) throw new IndexOutOfBoundsException();
            this.imagem = imagem;
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(imagem, x, y);
        }

        @Override
        public boolean equals(Object another) {
            if (!(another instanceof Coordinate2D)) return false;
            Coordinate2D c4d = (Coordinate2D) another;
            return imagem == c4d.imagem && x == c4d.x && y == c4d.y;
        }

        public int squareDistance(Coordinate2D another) {
            Objects.requireNonNull(another);
            if (imagem != another.imagem) throw new IllegalArgumentException();
            int dx = Math.abs(x - another.x);
            int dy = Math.abs(y - another.y);
            return dx + dy;
        }

        public Coordinate2D minusX() { return x == 0                      ? null : new Coordinate2D(imagem, x - 1, y); };
        public Coordinate2D plusX()  { return x == imagem.getWidth()  - 1 ? null : new Coordinate2D(imagem, x + 1, y); };
        public Coordinate2D minusY() { return y == 0                      ? null : new Coordinate2D(imagem, x, y - 1); };
        public Coordinate2D plusY()  { return y == imagem.getHeight() - 1 ? null : new Coordinate2D(imagem, x, y + 1); };
        public BufferedImage getImage() { return imagem; }
    }
}
