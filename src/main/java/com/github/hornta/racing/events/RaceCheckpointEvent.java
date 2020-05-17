package com.github.hornta.racing.events;

import com.github.hornta.racing.objects.Race;
import com.github.hornta.racing.objects.RaceCheckpoint;

abstract public class RaceCheckpointEvent extends RaceEvent {
  private final RaceCheckpoint checkpoint;

  RaceCheckpointEvent(Race race, RaceCheckpoint checkpoint) {
    super(race);

    this.checkpoint = checkpoint;
  }

  public RaceCheckpoint getCheckpoint() {
    return checkpoint;
  }
}
