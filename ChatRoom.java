import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    String roomName;
    List<User> members;
    List<String> badWords;
    User admin;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.members = new ArrayList<>();
        this.badWords = new ArrayList<>();
    }

    public void registerUser(User user) {
        if (!members.contains(user)) {
            members.add(user);
            user.addChatRoom(this);

            if (admin == null) {
                admin = user;
            }
        }
    }

    public void removeUser(User admin, User user) {
        if (admin != this.admin) {
            System.out.println("Hanya admin yang dapat mengeluarkan pengguna.");
            return;
        }

        if (members.contains(user)) {
            if (user == this.admin) {
                System.out.println("Admin tidak dapat dikeluarkan.");
                return;
            }

            members.remove(user);
            user.chatRooms.remove(this);

            System.out.println(user.name + " telah dikeluarkan dari "
                    + roomName + ".");
        }
    }

    public void addBadWord(String word) {
        badWords.add(word.toLowerCase());
    }

    public String filterMessage(String message) {
        String filteredMessage = message;

        for (String word : badWords) {
            filteredMessage = filteredMessage.replaceAll(
                    "(?i)\\b" + java.util.regex.Pattern.quote(word) + "\\b",
                    "***"
            );
        }

        return filteredMessage;
    }

    public void sendBroadcast(User sender, String message) {
        String filteredMessage = filterMessage(message);

        System.out.println("[ChatRoom - " + roomName
                + "] (Broadcast dari " + sender.name + "): "
                + filteredMessage);

        for (User member : members) {
            if (member != sender) {
                member.receiveMessage(sender, filteredMessage);
            }
        }
    }

    public void sendPrivateMessage(
            User sender, String recipientName, String message) {

        String filteredMessage = filterMessage(message);

        for (User member : members) {
            if (member.name.equalsIgnoreCase(recipientName)) {
                System.out.println("[ChatRoom - " + roomName
                        + "] (Private " + sender.name + " -> "
                        + member.name + "): " + filteredMessage);

                member.receivePrivateMessage(sender, filteredMessage);
                return;
            }
        }

        System.out.println("Pengguna tidak ditemukan.");
    }
}