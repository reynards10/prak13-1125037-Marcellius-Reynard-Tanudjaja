public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom("General");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        chatRoom.registerUser(budi);
        chatRoom.registerUser(andi);
        chatRoom.registerUser(cici);

        System.out.println("[Budi]: Memproses pengiriman pesan grup...");
        budi.send("Halo semuanya!");

        System.out.println();

        System.out.println("[Budi]: Mengirim pesan privat ke Cici...");
        budi.sendPrivate("Cici", "Rahasia ya ini");
    }
}