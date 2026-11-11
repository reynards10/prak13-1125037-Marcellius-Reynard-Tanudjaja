import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    String roomName;
    List<User> members;
    List<String> badWords;
    List<User> mutedUsers;
    List<String> chatHistory;
    User admin;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.members = new ArrayList<>();
        this.badWords = new ArrayList<>();
        this.mutedUsers = new ArrayList<>();
        this.chatHistory = new ArrayList<>();
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
            mutedUsers.remove(user);

            System.out.println(user.name + " telah dikeluarkan dari "
                    + roomName + ".");
        }
    }

    public void muteUser(User admin, User user) {
        if (admin != this.admin) {
            System.out.println("Hanya admin yang dapat melakukan mute.");
            return;
        }

        if (members.contains(user) && !mutedUsers.contains(user)) {
            mutedUsers.add(user);
            System.out.println(user.name + " berhasil di-mute.");
        }
    }

    public void unmuteUser(User admin, User user) {
        if (admin != this.admin) {
            System.out.println("Hanya admin yang dapat melakukan unmute.");
            return;
        }

        if (mutedUsers.remove(user)) {
            System.out.println(user.name + " berhasil di-unmute.");
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
        if (mutedUsers.contains(sender)) {
            System.out.println(sender.name + " sedang di-mute.");
            return;
        }

        String filteredMessage = filterMessage(message);

        String log = "[Broadcast] " + sender.name + ": " + filteredMessage;
        chatHistory.add(log);

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

        if (mutedUsers.contains(sender)) {
            System.out.println(sender.name + " sedang di-mute.");
            return;
        }

        String filteredMessage = filterMessage(message);

        for (User member : members) {
            if (member.name.equalsIgnoreCase(recipientName)) {
                String log = "[Private] " + sender.name
                        + " -> " + member.name + ": " + filteredMessage;

                chatHistory.add(log);

                System.out.println("[ChatRoom - " + roomName
                        + "] (Private " + sender.name + " -> "
                        + member.name + "): " + filteredMessage);

                member.receivePrivateMessage(sender, filteredMessage);
                return;
            }
        }

        System.out.println("Pengguna tidak ditemukan.");
    }

    public void showHistory() {
        System.out.println("\n=== CHAT HISTORY: " + roomName + " ===");

        if (chatHistory.isEmpty()) {
            System.out.println("Belum ada riwayat pesan.");
            return;
        }

        for (String message : chatHistory) {
            System.out.println(message);
        }
    }
}