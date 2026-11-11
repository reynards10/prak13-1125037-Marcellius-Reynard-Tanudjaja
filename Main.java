public class Main {
    public static void main(String[] args) {
        ChatRoom general = new ChatRoom("General");
        ChatRoom sports = new ChatRoom("Sports");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        general.registerUser(budi);
        general.registerUser(andi);

        sports.registerUser(budi);
        sports.registerUser(cici);

        general.addBadWord("bodoh");
        sports.addBadWord("kasar");

        System.out.println("=== GENERAL ===");
        budi.send(general, "Halo anggota General!");

        System.out.println();

        System.out.println("=== SPORTS ===");
        budi.send(sports, "Halo anggota Sports!");

        System.out.println();

        System.out.println("=== ADMIN KICK ===");
        general.removeUser(budi, andi);

        System.out.println();

        System.out.println("=== GENERAL SETELAH KICK ===");
        budi.send(general, "Andi sudah tidak ada di sini.");

        System.out.println();

        System.out.println("=== SPORTS SETELAH KICK ===");
        budi.send(sports, "Cici masih ada di Sports.");
    }
}