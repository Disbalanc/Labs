import java.util.*;
import java.util.stream.Collectors;

//1166. Funny Card Game
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Of course all of you want to know how to become ACM world champions. There is no exact answer to this question but it is well known that the champions of the last two ACM World Finals enjoyed playing the following funny card game. Two or more players can play this game simultaneously. It is played using a standard 54-card deck. At the beginning the players sit forming a circle. One of the players shuffles the deck and then he deals the cards in clockwise order starting from the neighbour on his left. He deals the top card of the deck to the current player each time. He does it until each player gets five cards. Then he takes the top card of the deck and lays it onto the table face up and he also lays the remainder of the deck nearby (these cards are laid face down preserving their original order). The card laid by the shuffler is considered as the first turn of the game (as if it was made by the shuffler to the player on his left).
//The normal game flow as following: the player should cover the last laid card with the card of the same suit or value. If he has none, he takes one card from the top of the deck and again checks this condition. If still there are no matching cards, the move will go to the next player (his left neighbour). But for some cards special rules are applied:
//If the laid card is 6, the player takes one card from the top of the deck and skips his turn
//If the laid card is 7, the player takes two cards from the top of the deck (if there is only one card in the deck, he takes just it) and skips his turn
//If the laid card is Ace the player skips his turn without taking any cards
//If the player lays Queen, he himself announces the suit of the card it should be covered with
//Eight is one of the most powerful weapons in this game. When it is laid, the next player has to cover it in any case. If he cannot cover it with his cards he has to take cards from the deck, until he is able to cover it.
//And the most important card in the game is the King of Spades. If it's laid, the next player takes 4 cards from the top of the deck (if there is not enough cards in the deck, he takes all of them) and skips his turn.
//You may assume that the deck is looped and the laid cards are immediately moving to the bottom of the deck. So it can happen that the player has to cover the card by itself.
//We should say some words about Jokers. Jokers can have any card value by the wish of the player who has it. If the player lays the joker, he assigns a definite card value and suit for it, so this Joker has this assigned value until another player takes it from the deck (if it ever happens). The player is free to use or not to use the Joker whenever he wants (if it is his turn to play, of course).
//If the player is left without any cards in his hand, he is considered a winner and the game continues without him (his left neighbour becomes the left neighbour of his right neighbour).
//If there is only one player left, he is the looser, so he is called a Japanese Fool (it is a Russian name of this game).
//We are interested in the following situation. Consider the moment when only two players are left in the game. If one of them has a special combination of cards, it may happen that he can lay out all his cards in a some sequence so that the other player won't get a move (he'll just have to take cards from the deck and skip turns) and will be the loser — provided the first one finds the winning sequence.
//You will be given the position of the game in which only two players are left. Your task will be to determine whether such a winning sequence for the first player exists or not.
//We will consider that the first player have already taken all cards from the deck that he had to (if any), so he cannot take any cards from the deck. We will also consider that if the last laid card is a skip-turn card, it was the second player who skipped the turn.
//Исходные данные
//The first line contains cards of the first player separated by spaces. The second line contains the last laid face up card.
//The card description consists of two characters. The first of them corresponds to the card value (2-9 for digits, T for 10, J for Jack, Q for Queen, K for King and A for Ace). The next describes the suit of the card and may be one of the following: S for Spades, C for Clubs, D for Diamonds or H for Hearts. Joker is represented by a character '*'. If the last laid card is Queen, it is followed by a suit letter. If the last laid card is a joker, then the '*' is followed by an actual card description (the card specified by the player who laid the Joker).
//Результат
//The first line should contain a single word YES or NO signalling whether the winning sequence exists. If the answer is positive the second line must contain the winning sequence of cards separated by spaces. As in the input, the Joker is to be followed by a card specification and the Queen should follow a suit letter. If there is more than one solution you may output an arbitrary one.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] hand = sc.nextLine().split(" ");
        String lastCard = sc.nextLine().trim();
        if (lastCard.startsWith("*")) {
            lastCard = lastCard.substring(1);
        }

        Set<String> skipTurnSet = new HashSet<>();
        String[] suits = {"S", "C", "D", "H"};
        for (String suit : suits) {
            skipTurnSet.add("6" + suit);
            skipTurnSet.add("7" + suit);
            skipTurnSet.add("A" + suit);
        }
        skipTurnSet.add("KS");

        List<List<String>> permutations = generatePermutations(hand);
        for (List<String> perm : permutations) {
            if (!checkFirstFour(perm, skipTurnSet)) continue;
            if (!checkCoveringSimple(lastCard, perm)) continue;
            String[] assigned = assignJokers(lastCard, perm, skipTurnSet);
            if (assigned != null) {
                System.out.println("YES");
                System.out.println(formatOutput(perm, assigned));
                return;
            }
        }
        System.out.println("NO");
    }

    static List<List<String>> generatePermutations(String[] array) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), array, new boolean[array.length]);
        return result;
    }

    static void backtrack(List<List<String>> result, List<String> temp, String[] array, boolean[] used) {
        if (temp.size() == array.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (!used[i]) {
                used[i] = true;
                temp.add(array[i]);
                backtrack(result, temp, array, used);
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    static boolean checkFirstFour(List<String> perm, Set<String> skipTurnSet) {
        for (int i = 0; i < 4; i++) {
            String card = perm.get(i);
            if (card.equals("*")) continue;
            if (!skipTurnSet.contains(card)) return false;
        }
        return true;
    }

    static boolean checkCoveringSimple(String lastCard, List<String> perm) {
        if (!coversSimple(lastCard, perm.get(0))) return false;
        for (int i = 0; i < 4; i++) {
            if (!coversSimple(perm.get(i), perm.get(i + 1))) return false;
        }
        return true;
    }

    static boolean coversSimple(String A, String B) {
        if (B.equals("*")) return true;
        if (A.equals("*")) return true;
        if (A.charAt(0) == 'Q') return true;
        if (B.charAt(0) == 'Q') return A.charAt(1) == B.charAt(1);
        return A.charAt(1) == B.charAt(1) || A.charAt(0) == B.charAt(0);
    }

    static String[] assignJokers(String lastCard, List<String> perm, Set<String> skipTurnSet) {
        String[] assigned = new String[5];
        for (int i = 0; i < 5; i++) {
            String card = perm.get(i);
            if (card.equals("*")) {
                String left = i == 0 ? lastCard : assigned[i - 1];
                String right = i < 4 ? perm.get(i + 1) : null;
                String assignedCard = findAssignment(left, right, skipTurnSet);
                if (assignedCard == null) return null;
                assigned[i] = assignedCard;
            } else {
                assigned[i] = card;
            }
        }
        return assigned;
    }

    static String findAssignment(String left, String right, Set<String> skipTurnSet) {
        for (String candidate : skipTurnSet) {
            if (!coversActual(left, candidate)) continue;
            if (right != null && !right.equals("*") && !coversActual(candidate, right)) continue;
            return candidate;
        }
        return null;
    }

    static boolean coversActual(String A, String B) {
        if (A.charAt(0) == 'Q') return true;
        if (B.charAt(0) == 'Q') return A.charAt(1) == B.charAt(1);
        return A.charAt(1) == B.charAt(1) || A.charAt(0) == B.charAt(0);
    }

    static String formatOutput(List<String> perm, String[] assigned) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (perm.get(i).equals("*")) {
                sb.append("*").append(assigned[i]);
            } else {
                sb.append(assigned[i]);
            }
            if (i < 4) sb.append(" ");
        }
        return sb.toString();
    }
}