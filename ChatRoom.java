import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    String roomName;
    List<User> members;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.members = new ArrayList<>();
    }

    public void registerUser(User user) {
        if (!members.contains(user)) {
            members.add(user);
            user.setChatRoom(this);
        }
    }

    public void sendBroadcast(User sender, String message) {
        System.out.println("[ChatRoom - " + roomName
                + "] (Broadcast dari " + sender.name + "): " + message);

        for (User member : members) {
            if (member != sender) {
                member.receiveMessage(sender, message);
            }
        }
    }

    public void sendPrivateMessage(
            User sender, String recipientName, String message) {

        for (User member : members) {
            if (member.name.equalsIgnoreCase(recipientName)) {
                System.out.println("[ChatRoom - " + roomName
                        + "] (Private " + sender.name + " -> "
                        + member.name + "): " + message);

                member.receivePrivateMessage(sender, message);
                return;
            }
        }

        System.out.println("Pengguna tidak ditemukan.");
    }
}