import java.util.Random;
public class App {
        public static void main(String[] args) {
            //inicio (informações)
            int dano = calcularDano(
                50,       //nivel
                false,    //critico
                100,      //poder
                120,      //ataque
                100,      //defesa
                false,    //refletir/tela de luz ativa
                false,    //autodestrutivo
                true,     //físico
                true,     //stab
                1.0,      //tipo1
                2.0       //tipo2
            );
    
            System.out.println("Dano causado: " + dano);
        }
    
        public static int calcularDano(
            int nivel,
            boolean critico,
            int poder,
            int ataque,
            int defesa,
            boolean refletir,
            boolean autodestrutivo,
            boolean fisico,
            boolean stab,
            double tipo1,
            double tipo2
        ) {
            int multiplicadorCritico = critico ? 2 : 1;
    
            //limitando ataque e defesa acima de 255
            if (ataque > 255) ataque /= 4;
            if (defesa > 255) defesa /= 4;
    
            //ignorado se for critico
            if (!critico) {
                if (refletir) {
                    defesa *= 2;
                }
                if (autodestrutivo) {
                    defesa = Math.max(1, defesa / 2);
                }
            }
    
            //danobase
            int base = ((2 * nivel * multiplicadorCritico) / 5) + 2;
            int danoBase = ((base * poder * ataque) / defesa) / 50 + 2;
    
            //stav
            double multiplicadorSTAB = stab ? 1.5 : 1.0;
    
            //random
            Random rand = new Random();
            int aleatorio = rand.nextInt(255 - 217 + 1) + 217;
            double fatorAleatorio = aleatorio / 255.0;
    
            //final
            double dano = danoBase * multiplicadorSTAB * tipo1 * tipo2 * fatorAleatorio;
    
            return (int) dano;
        }
    }
