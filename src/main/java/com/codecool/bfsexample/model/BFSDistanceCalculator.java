package com.codecool.bfsexample.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class BFSDistanceCalculator {
    private UserNode source;
    private UserNode target;

    public BFSDistanceCalculator(UserNode source, UserNode target) {
        this.source = source;
        this.target = target;
    }

    public Integer calculateDistance() {
        Map<UserNode, Integer> map = new HashMap<>();
        Queue<UserNode> friendsQueue = new LinkedBlockingQueue<>();

        setInitialNeighbours(friendsQueue);
        int distance = 1;

        while (!friendsQueue.isEmpty()) {

            int friendsAmountPerStep = friendsQueue.size();

            for (int i = 0; i < friendsAmountPerStep; i++) {
                UserNode friend = friendsQueue.poll();
                Set<UserNode> friendsOfFriend = friend.getFriends();

                if (!map.containsKey(friend) && friendsOfFriend != null) {
                    friendsQueue.addAll(friendsOfFriend);
                    map.put(friend, distance);
                }
            }

            distance++;
        }

        return map.get(this.target);
    }

    private void setInitialNeighbours(Queue<UserNode> friendsQueue) {
        friendsQueue.addAll(this.source.getFriends());
    }
}
