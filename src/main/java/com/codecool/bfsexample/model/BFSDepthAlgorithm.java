package com.codecool.bfsexample.model;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

public class BFSDepthAlgorithm {
    private UserNode fromObject;

    public BFSDepthAlgorithm(UserNode from) {
        this.fromObject = from;
    }

    public Set<UserNode> findNearbyFriendsInRange(Integer rangeToInvestigate) throws ImproperArgumentException {
        validateInitialDepth(rangeToInvestigate);

        Set<UserNode> allNearbyFriends = new HashSet<>();
        Queue<UserNode> friendsQueue = new LinkedBlockingDeque<>();
        setInitialNeighbours(friendsQueue);

        for (int i = 0; i < rangeToInvestigate; i++) {
            int nearbyFriendsAmountForStep = friendsQueue.size();

            for (int j = 0; j < nearbyFriendsAmountForStep; j++) {
                UserNode friend = friendsQueue.poll();

                Set<UserNode> friendsOfFriend = friend.getFriends();
                if (isDepthReached(i, rangeToInvestigate)) {
                    allNearbyFriends.addAll(friendsOfFriend);

                } else if (!isFriendAlreadyCollected(friend, allNearbyFriends)) {
                    friendsQueue.addAll(friendsOfFriend);
                }

                allNearbyFriends.add(friend);
            }
        }

        return allNearbyFriends;
    }

    private boolean isFriendAlreadyCollected(UserNode friend, Set<UserNode> allNearbyFriends) {
        return allNearbyFriends.contains(friend);
    }

    private void validateInitialDepth(Integer depth) throws ImproperArgumentException {
        if (depth == null)
            throw new ImproperArgumentException("Depth should not be null");

        if (depth < 1)
            throw new ImproperArgumentException("Depth value should higher or equal 1.");
    }

    private void setInitialNeighbours(Queue<UserNode> queue) {
        queue.addAll(this.fromObject.getFriends());
    }

    private boolean isDepthReached(int level, int maxDepth) {
        return level >= maxDepth;
    }
}
