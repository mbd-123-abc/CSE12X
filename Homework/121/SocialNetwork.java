//Mahika Bagri 
//CSE 121 
//February 12 2025

import java.util.*;
//Creates a network of people (like eg. SSN)
public class SocialNetwork {
    public static void main(String[] args) {
        String initialNetwork = "";
        System.out.println("Initial Network:" + initialNetwork);
        System.out.println();
        System.out.println();

        //Adding swofferh, achen47, and hibbahk
        System.out.println("Network after adding swofferh, achen47, and hibbahk:");
        String swofferhFriends = "gbuck21-haleyc22-macy16-mnats-mxw-nwang913-gumball";
        String achen47Friends = "ali1726-cartierc-tan7271-yethanli-hzpan8";
        String hibbahkFriends = "janvi26-jvdyfu";
        String swofferhNetwork = addUser(initialNetwork,"swofferh",swofferhFriends);
        String achen47Network = addUser(swofferhNetwork,"achen47",achen47Friends);
        String hibbahkNetwork = addUser(achen47Network,"hibbahk",hibbahkFriends);
        String network1 = hibbahkNetwork;
        System.out.println(network1);
        int network1Size = networkSize(network1);
        System.out.println("Network size: " + network1Size);
        System.out.println();

        //Removing achen47
        System.out.println("Network after removing achen47:");
        String network2 = removeUser(network1, "achen47");
        System.out.println(network2);
        int network2Size = networkSize(network2);
        System.out.println("Network size: " + network2Size);
        System.out.println();

        //Adding juliak24
        System.out.println("Network after adding juliak24:");
        String juliak24Friends = "qsun05-ldhar-lukel7-parakhm";
        String network3 = addUser(network2,"juliak24",juliak24Friends);
        System.out.println(network3);
        int network3Size = networkSize(network3);
        System.out.println("Network size: " + network3Size);
        System.out.println();
        
        //Adding meravf
        System.out.println("Network after adding meravf:");
        String meravfFriends = "rkorol-samkoro-sbabu23-shay2022-sshan854-vwang2";
        String network4 = addUser(network3,"meravf",meravfFriends);
        System.out.println(network4);
        int network4Size = networkSize(network4);
        System.out.println("Network size: " + network4Size);
        System.out.println();

        //Adding mxw
        System.out.println("Network after adding mxw:");
        String mxwFriends = "";
        String network5 = addUser(network4,"mxw",mxwFriends);
        System.out.println(network5);
        int network5Size = networkSize(network5);
        System.out.println("Network size: " + network5Size);
        System.out.println();

        //visualizes network5
        System.out.println ("Network visualization:");
        visualizeNetwork(network5);
    }

    //Method adds a user to network 
    public static String addUser(String network, String add, String friends){
        String newNetwork = network + add + ":" + friends + ","; 
        return newNetwork;
    }

    //Method calculates size of network 
    public static int networkSize(String network){
        int numberUsers = 0;
        int networkLength = network.length();
        //checks every character in network
        for(int i = 0; i < networkLength;i++){
            //adds 1 everytime there is a : signifying user
            if (network.charAt(i) == ':'){
                numberUsers++;
            }
        }
        return numberUsers;
    }

    //Removes a user
    public static String removeUser(String network, String delete){
        //Initializing variables 
        int networkLength = network.length();
        int deleteLength = delete.length();
        int indexStartName = 0;
        int indexComma = 0;
        String newNetwork = "";
        //Checks each charater in network
        for(int i = 0; i < networkLength;i++){
            //Checks before colon for name 
            if (network.charAt(i) == ':'){
                indexStartName = i-deleteLength;
                if (indexStartName>=0){
                    String name = network.substring(indexStartName, i);
                    if (name.equals(delete)){
                        //If correct name, removes user and creates new rectified network 
                        String afterName = network.substring(indexStartName, networkLength);
                        indexComma = afterName.indexOf(',');
                        newNetwork = network.substring(0,indexStartName) + network.substring(indexStartName + indexComma + 1);

                    }
                }
            }
        }
        return newNetwork;
    } 

    //visualizes each network
    public static void visualizeNetwork(String network){
        //calculates how many users are in the network
        int networkSize = networkSize(network);
        //prints out the header for each user 
        for(int j=0;j<networkSize;j++){
            //finds the users name 
            int indexCollan = network.indexOf(':');
            int indexComma = network.indexOf(',');
            String name = network.substring(0, indexCollan);
            System.out.println (name + "'s friends:");
            //finds the users friends name(s) 
            String friends = network.substring(indexCollan+1, indexComma);
            network = network.substring(indexComma+1);
            int numberFriends = 0;
            int lengthFriends = friends.length();
            for(int i = 0; i < lengthFriends;i++){
            //adds 1 everytime there is a - signifying friend
            if (friends.charAt(i) == '-'){
                numberFriends++;
            }
            }
                for(int k=0;k<numberFriends;k++){
                int indexHyphen = friends.indexOf('-');
                String nameFriend = friends.substring(0, indexHyphen);
                System.out.println(nameFriend + " [follow]");
                friends = friends.substring(indexHyphen+1);
            }
            //prints out last friends name 
            if (friends == ""){

            } else {
            System.out.println(friends  + " [follow]");
            System.out.println();
            }
        }
    }
}
