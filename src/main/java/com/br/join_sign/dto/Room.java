package com.br.join_sign.dto;

import java.util.HashSet;
import java.util.Set;

public class Room {
  private String roomId;
  private Set<String> participants;

  public Room(String roomId) {
    this.roomId = roomId;
    this.participants = new HashSet<>(); // Initialize as a HashSet to store unique participants
  }

  public String getRoomId() {
    return roomId;
  }

  public Set<String> getParticipants() {
    return participants;
  }

  public void add(String userId) {
    participants.add(userId);
  }


  public void remove(String userId) {
    participants.remove(userId);
  }
}
