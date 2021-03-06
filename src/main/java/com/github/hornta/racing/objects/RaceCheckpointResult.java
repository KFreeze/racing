package com.github.hornta.racing.objects;

import org.bukkit.Location;

public class RaceCheckpointResult {
  private final RaceCheckpoint checkpoint;
  private final int ticks;
  private final Location location;

  public RaceCheckpointResult(RaceCheckpoint checkpoint, int ticks, Location location) {
    this.checkpoint = checkpoint;
    this.ticks = ticks;
    this.location = location;
  }

  public Location getLocation() {
    return location;
  }

  public RaceCheckpoint getCheckpoint() {
    return checkpoint;
  }

  public int getTicks() {
    return ticks;
  }
}
