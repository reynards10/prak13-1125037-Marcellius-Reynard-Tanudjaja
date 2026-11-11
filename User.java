public class User {
    String name;
    ChatRoom chatRoom;

    public User(String name) {
        this.name = name;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void send(String message) {
        if (chatRoom != null) {
            chatRoom.sendBroadcast(this, message);
        }
    }

    public void receiveMessage(User sender, String message) {
        System.out.println("[" + name + "] Menerima pesan dari "
                + sender.name + ": " + message);
    }
}