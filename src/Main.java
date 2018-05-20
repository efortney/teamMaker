/**
 * This is a generator to create random teams.
 * It was originally created with foosball in mind on a family vacation.
 *
 * Elliot Fortney || Nathaniel Fortney
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        int totalPlayers;
        int totalTeams;
        try {
            totalPlayers = setPlayers_Total();
        } catch (Exception e) {
            System.out.println("Not a valid number.");
            totalPlayers = setPlayers_Total();
        }

        try {
            totalTeams = setTeam_Total();
        } catch (Exception e) {
            System.out.println("Not a valid number.");
            totalTeams = setTeam_Total();
        }

        // check to ensure that we have more players than teams, if not we will swap them
        if (totalPlayers < totalTeams) {
            int temp = 0;
            temp = totalTeams;
            totalTeams = totalPlayers;
            totalPlayers = temp;
        }


        ArrayList players = setPlayerNames(totalPlayers);
        ArrayList<Team> teams = assignPlayers(totalPlayers, totalTeams, players);

        menu(teams);

    }// end of main

    private static void menu(ArrayList<Team> team) {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print(" 1: exit.\n" + " 2: Update team records \n" + " 3: View Team Records \n");
            int choice = scan.nextInt();
            if (choice == 1) {
                System.exit(1);
            } else if (choice == 2) {
                System.out.print("Enter team name: ");
                String teamName = scan.next();
                for (Team i : team) {
                    if (i.name.equalsIgnoreCase(teamName)) {
                        updateRecord(i);
                        menu(team);
                    }
                }
            } else if (choice == 3) {
                for (Team i : team) {
                    System.out.println( i.name + " record: " + i.wins + ", " + i.losses);
                }
                menu(team);
            } else {
                System.out.println("Not a valid choice");
            }
        } while (scan.nextInt() != 1);
    }

    /**
     * This allows for the user to update a teams record.
     */
    private static void updateRecord(Team team) {
        String result = "L";
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter whether the team won or loss");
        System.out.println("W / L");
        result = scan.next();

        if (result.equalsIgnoreCase("W")) {
            team.wins++;
        } else {
            team.losses++;
        }

        System.out.println("Successful update");
    }


    /**
     * Sets the total number of players
     * @return total_players {int}
     */
    private static int setPlayers_Total() {
        int total_players = 0; // Create and initialize variable
        Scanner scan = new Scanner(System.in); // Create new scanner object, reads user input
        System.out.println("Please enter the total number of players and press enter"); // Giving command to user

        // sets the total number of players
        total_players = scan.nextInt();
        System.out.println("You entered " + total_players + " players");


        return total_players;
    }

    /**
     * Sets the total number of teams
     * @return total_teams {int}
     */
    private static int setTeam_Total() {
        int total_teams; // Create and initialize variable
        Scanner scan = new Scanner(System.in); // Create new scanner object, reads user input
        System.out.println("Please enter the total number of teams and press enter"); // Giving command to user

        // sets the total number of teams
        total_teams = scan.nextInt();
        System.out.println("You entered " + total_teams + " teams");

        return total_teams;
    }


    private static ArrayList setPlayerNames(int total_players) {
        ArrayList<String> players = new ArrayList<>(total_players);
        Scanner scan = new Scanner(System.in); // Create new scanner object, reads user input
        System.out.println("Please enter the names of all players.");

        // loops over array and creates names of players
        for (int i = 0; i < total_players; i++) {
            System.out.println("Enter player name: ");
            players.add(scan.next()); // adds player names until array size is reached
        }

        return players;
    }


    /**
     * AssignPlayers: randomly selects players from the playerNames arrayList, then creates random teams based on the
     * total number of teams requested. This method currently assumes that both the total number of players and the
     * total number of teams are both even numbers.
     * @param total_players: total number of players in the game
     * @param total_teams: total number of teams in the game
     * @param playerNames: the names of all players in game
     */
    private static ArrayList<Team> assignPlayers(int total_players, int total_teams, ArrayList<String> playerNames) {

        int playersPerTeam = total_players / total_teams;
        ArrayList<Team> teams = new ArrayList<>();

        for (int i = 0; i < total_teams; i++) {
            Random rand = new Random();
            Team newTeam = new Team();
            System.out.print("Add team name: ");
            Scanner scan = new Scanner(System.in);
            newTeam.setName(scan.next());

            teams.add(newTeam);
            System.out.println("Team created!");
            int j = 0;
            while (j < playersPerTeam) {
                String randomPlayer = playerNames.get(rand.nextInt(playerNames.size()));
                newTeam.addPlayer(randomPlayer);
                playerNames.remove(randomPlayer);
                j++;
            }

        }

        for (Team team : teams) {
            System.out.println(team.toString());
        }

        return teams;

    } // end of assignPlayers


} // end of main