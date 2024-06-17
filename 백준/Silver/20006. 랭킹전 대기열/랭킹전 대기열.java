import java.util.*;
import java.io.*;

/*
240617
BJ20006 랭킹전 대기열 - 실버 2
 */

public class Main {

    /*

    플레이어 입장 신청 시
    매칭 가능 방 X > 생성자의 레벨 +-10 입장 가능한 방 생성 후 입장
    입장 가능 방 여러 개 > 먼저 생성된 방에 입장
    정원 전부 차면 게임 시작
     */
    static class Player implements Comparable<Player> {
        int lv;
        String name;

        Player(int lv, String name) {
            this.lv = lv;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }

    static class Room {
        int numOfPlayer;
        int limitLv;
        List<Player> players;

        Room(Player player) {
            this.limitLv = player.lv;
            this.players = new ArrayList<>();
            this.players.add(player);
            this.numOfPlayer = 1;
        }
    }

    static int p, m;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 방 목록
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; ++i) {
            st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            Player p = new Player(l, n);

            boolean entered = false;

            for (int j = 0; j < rooms.size(); ++j) {
                Room room = rooms.get(j);
                if (Math.abs(room.limitLv - p.lv) <= 10 && room.numOfPlayer < m) { // 입장 가능
                    room.players.add(p);
                    room.numOfPlayer++;
                    entered = true;
                    break;
                }
            }

            if (!entered) { // 아무 곳에도 입장 못한 경우'
                // 방 생성해서 입장시킴
                Room room = new Room(p);
                rooms.add(room);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rooms.size(); ++i) {
            Room room = rooms.get(i);
            if (room.numOfPlayer == m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }
            Collections.sort(room.players);
            for (int j = 0; j < room.players.size(); ++j) {
                sb.append(room.players.get(j).lv).append(" ").append(room.players.get(j).name).append("\n");
            }
        }

        System.out.print(sb);
    }
}
