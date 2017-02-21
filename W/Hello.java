import static java.lang.System.out;

public class LetterMap {
    public HashMap<Char, Integer> buildLetterMap(Char[] s){
        HashMap<Char, Integer> map = new HashMap<Char, Integer>();
        for (Char letter : s){
            map.put(letter, 1);
        }
        return map;
    }

    public static void main(String[] args) {
        HashMap<Char, Integer> map = buildLetterMap("aba");
    }
}

