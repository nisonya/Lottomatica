package lotto.matica.scommesse;

public class Exersise {
    int id;
    int id_musle;
    String name;
    String photo;
    String description;

    public Exersise(int id, int id_musle, String name, String description, String photo) {
        this.id = id;
        this.id_musle = id_musle;
        this.name = name;
        this.photo = photo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getId_musle() {
        return id_musle;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }
}
