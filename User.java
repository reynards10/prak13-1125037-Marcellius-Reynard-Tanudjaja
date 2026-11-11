import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    List<ChatRoom> chatRooms;

    public User(String name) {
        this.name = name;
        this.chatRooms = new ArrayList<>();
    }

    public void addChatRoom(ChatRoom chatRoom) {
        if (!chatRooms.contains(chatRoom)) {
            chatRooms.add(chatRoom);
        }
    }

    public void send(ChatRoom chatRoom, String message) {
        if (chatRooms.contains(chatRoom)) {
            chatRoom.sendBroadcast(this, message);
        }
    }

    public void sendPrivate(
            ChatRoom chatRoom, String recipientName, String message) {

        if (chatRooms.contains(chatRoom)) {
            chatRoom.sendPrivateMessage(this, recipientName, message);
        }
    }

    public void receiveMessage(User sender, String message) {
        System.out.println("[" + name + "] Menerima pesan dari "
                + sender.name + ": " + message);
    }

    public void receivePrivateMessage(User sender, String message) {
        System.out.println("[" + name + "] Menerima pesan privat dari "
                + sender.name + ": " + message);
    }
}