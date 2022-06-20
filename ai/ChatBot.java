import java.util.Scanner;

public class ChatBot {
    String[][] chatBot = {
            {"hi", "hello", "hola", "ola", "howdy"},
            {"hi", "hello", "hey"},

            {"how are you", "how r you", "how r u", "how are u"},
            {"good", "doing well"},

            {"yes"},
            {"no", "NO", "NO!!!!!!!"},

            {"good", "bye", "I am not supposed to say"}
    };

    ChatBot() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String quote = in.nextLine();
            if (quote.equals("exit") || quote.isEmpty()) {
                System.out.println("Bye...");
                break;
            }
            System.out.print("Bot: ");
            while (
                    quote.charAt(quote.length() - 1) == '!' ||
                            quote.charAt(quote.length() - 1) == '.' ||
                            quote.charAt(quote.length() - 1) == '?'
            ) {
                quote = quote.substring(0, quote.length() - 1);
            }
            byte response = 0;

            int j = 0;
            while (response == 0) {
                if (inArray(quote.toLowerCase(), chatBot[j * 2])) {
                    response = 2;
                    int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
                    System.out.print(chatBot[(j * 2) + 1][r]);
                }
                j++;
                if (j * 2 == chatBot.length - 1 && response == 0) {
                    response = 1;
                }
            }
            if (response == 1) {
                int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
                System.out.print(chatBot[chatBot.length - 1][r]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new ChatBot();
    }

    public boolean inArray(String in, String[] str) {
        for (String s : str) if (s.equals(in)) return true;
        return false;
    }
}