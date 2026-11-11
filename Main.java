public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom("General");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        chatRoom.registerUser(budi);
        chatRoom.registerUser(andi);
        chatRoom.registerUser(cici);

        chatRoom.addBadWord("bodoh");
        chatRoom.addBadWord("kasar");

        budi.send("Halo semuanya!");

        System.out.println();

        budi.send("Kamu bodoh!");

        System.out.println();

        budi.sendPrivate("Cici", "Jangan berkata kasar!");
    }
}