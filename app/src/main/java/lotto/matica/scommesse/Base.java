package lotto.matica.scommesse;

import java.util.Arrays;
import java.util.List;

public class Base {
    public static List<Integer> id_group= Arrays.asList(1,1,1,1,1,2,2,2,3,3,3,3);
    public static List<String> name_muscle= Arrays.asList("Shoulders","Traps", "Quads", "Hamstrings", "Calves", "Chest",
            "Triceps", "Obliques", "Biceps", "Traps (mid-back)", "Lower back","Lats");


    public static List<Integer> id_musle= Arrays.asList(1,1,1);
    public static List<Integer> getId_group() {
        return id_group;
    }

    public static List<Integer> getId_musle() {
        return id_musle;
    }

    public static List<String> getName_muscle() {
        return name_muscle;
    }
}
