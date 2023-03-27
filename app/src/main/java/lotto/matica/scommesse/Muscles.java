package lotto.matica.scommesse;

public class Muscles {
    private int id;
    private int group_id;
    private String name;

    public Muscles(int id, int group_id, String name) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public  String getName() {
        return name;
    }

}
