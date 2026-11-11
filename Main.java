public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom("General");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        chatRoom.registerUser(budi);
        chatRoom.registerUser(andi);
        chatRoom.registerUser(cici);

        budi.send("Halo semuanya!");
    }
}