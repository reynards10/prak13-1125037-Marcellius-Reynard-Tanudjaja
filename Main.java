public class Main {
    public static void main(String[] args) {
        ChatRoom general = new ChatRoom("General");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        general.registerUser(budi);
        general.registerUser(andi);
        general.registerUser(cici);

        general.addBadWord("bodoh");

        System.out.println("=== BROADCAST ===");
        budi.send(general, "Halo semuanya!");

        System.out.println();

        System.out.println("=== PRIVATE MESSAGE ===");
        budi.sendPrivate(general, "Cici", "Halo Cici!");

        System.out.println();

        System.out.println("=== MUTE USER ===");
        general.muteUser(budi, andi);

        andi.send(general, "Halo semuanya!");

        System.out.println();

        System.out.println("=== BUDI MENGIRIM PESAN ===");
        budi.send(general, "Jangan berkata bodoh!");

        System.out.println();

        System.out.println("=== UNMUTE USER ===");
        general.unmuteUser(budi, andi);

        andi.send(general, "Saya sudah bisa mengirim pesan!");

        System.out.println();

        general.showHistory();
    }
}