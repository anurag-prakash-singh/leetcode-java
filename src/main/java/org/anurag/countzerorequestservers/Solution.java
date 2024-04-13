package org.anurag.countzerorequestservers;

import java.util.*;

public class Solution {

    static class ServerRequestLog {
        int serverId;
        int time;

        ServerRequestLog(int serverId, int time) {
            this.serverId = serverId;
            this.time = time;
        }
    }

    static class QueryAndIndex {
        int query;
        int index;

        QueryAndIndex(int query, int index) {
            this.query = query;
            this.index = index;
        }
    }

    // Binary search for the index corresponding to the start of logs with timestamp.
    static int startingIndexOfLogTimestamp(ServerRequestLog[] serverRequestLogs, int timestamp) {
        int start = 0;
        int end = serverRequestLogs.length - 1;
        int mid = (start + end) / 2;

        if (timestamp > serverRequestLogs[end].time) {
            // timestamp is too far ahead in to the future. There are no logs starting at this time.
            return -1;
        }

        while (start <= end) {
            mid = (start + end) / 2;
            ServerRequestLog logAtMid = serverRequestLogs[mid];

            if (logAtMid.time >= timestamp) {
                if (mid - 1 < start || serverRequestLogs[mid -1].time < timestamp) {
                    return mid;
                }

                end = mid - 1;
                continue;
            }

            // mid ts < timestamp; search right
            start = mid + 1;
        }

        return mid;
    }

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[] result = new int[queries.length];
        ServerRequestLog[] serverRequestLogs = new ServerRequestLog[logs.length];

        // Copy logs into serverRequestLogs and sort serverRequestLogs
        for (int i = 0; i < logs.length; i++) {
            serverRequestLogs[i] = new ServerRequestLog(logs[i][0], logs[i][1]);
        }

        QueryAndIndex[] queryAndIndices = new QueryAndIndex[queries.length];
        for (int i = 0; i < queries.length; i++) {
            queryAndIndices[i] = new QueryAndIndex(queries[i], i);
        }

        Arrays.sort(serverRequestLogs, Comparator.comparing(s -> s.time));
        Arrays.sort(queryAndIndices, Comparator.comparing(q -> q.query));
        Arrays.sort(queries);

        int wStart = -1, wEnd = -1;
        Set<Integer> zeroRequestCountServers = new HashSet<>();
        Map<Integer, Integer> serverRequestCounts = new Hashtable<>();

        for (int qIndex = 0; qIndex < queryAndIndices.length; qIndex++) {
            int qEnd = queryAndIndices[qIndex].query;
            int qStart = qEnd - x;

            while (wEnd < serverRequestLogs.length && (wEnd == -1 || serverRequestLogs[wEnd].time <= qEnd)) {
                if (wEnd > -1) {
                    int serverId = serverRequestLogs[wEnd].serverId;

                    if (!serverRequestCounts.containsKey(serverId)) {
                        serverRequestCounts.put(serverId, 1);
                    } else {
                        int requestCountForServer = serverRequestCounts.get(serverId);
                        serverRequestCounts.put(serverId, requestCountForServer + 1);
                    }

                    zeroRequestCountServers.remove(serverId);
                }

                wEnd++;
            }

            while (wStart < wEnd && (wStart == -1 || serverRequestLogs[wStart].time < qStart)) {
                if (wStart > -1) {
                    int serverId = serverRequestLogs[wStart].serverId;
                    int requestCountForServer = serverRequestCounts.get(serverId);
                    requestCountForServer--;
                    serverRequestCounts.put(serverId, requestCountForServer);

                    if (requestCountForServer == 0) {
                        zeroRequestCountServers.add(serverId);
                        serverRequestCounts.remove(serverId);
                    }
                }

                wStart++;
            }

            result[queryAndIndices[qIndex].index] = n - serverRequestCounts.size();
        }

        return result;
    }

    public int[] countServersBinarySearch(int n, int[][] logs, int x, int[] queries) {
        int[] result = new int[queries.length];
        ServerRequestLog[] serverRequestLogs = new ServerRequestLog[logs.length];

        // Copy logs into serverRequestLogs and sort serverRequestLogs
        for (int i = 0; i < logs.length; i++) {
            serverRequestLogs[i] = new ServerRequestLog(logs[i][0], logs[i][1]);
        }

        Arrays.sort(serverRequestLogs, Comparator.comparing(s -> s.time));

        int resultIndex = 0;
        for (int q : queries) {
            int startingTs = q - x;
            int endingTs = q + 1;

            int startingTsIndex = startingIndexOfLogTimestamp(serverRequestLogs, startingTs);
            int endingTsIndex = startingIndexOfLogTimestamp(serverRequestLogs, endingTs);

            if (startingTsIndex == -1) {
                // No server served requests starting at this query TS
                result[resultIndex++] = n;
                continue;
            }

            if (endingTsIndex == -1) {
                endingTsIndex = serverRequestLogs.length - 1;
            } else {
                endingTsIndex = Math.max(startingTsIndex, endingTsIndex - 1);
            }

            Set<Integer> serversWithRequests = new HashSet<>();

            for (int l = startingTsIndex; l <= endingTsIndex; l++) {
                if (serverRequestLogs[l].time >= startingTs && serverRequestLogs[l].time < endingTs) {
                    serversWithRequests.add(serverRequestLogs[l].serverId);
                }
            }

            result[resultIndex++] = n - serversWithRequests.size();
        }

        return result;
    }

}
